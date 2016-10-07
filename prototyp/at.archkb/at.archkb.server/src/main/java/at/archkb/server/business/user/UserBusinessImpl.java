package at.archkb.server.business.user;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import at.archkb.server.neo4jentity.dto.user.AdminUserCreationDto;
import at.archkb.server.neo4jentity.dto.user.UserActivationDto;
import at.archkb.server.neo4jentity.dto.user.UserDto;
import at.archkb.server.neo4jentity.node.User;
import at.archkb.server.neo4jentity.node.UserAuthority;
import at.archkb.server.repository.node.CityRepository;
import at.archkb.server.repository.node.CompanyRepository;
import at.archkb.server.repository.node.user.UserRepository;
import at.archkb.server.repository.node.user.UserAuthorityRepository;
import at.archkb.server.util.PasswordUtils;

@Component
public class UserBusinessImpl implements UserBusiness {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	UserAuthorityRepository userAuthorityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	
	@Override
	public void changePassword(String oldPassword, String newPassword) {
	}

	@Override
	@Transactional(readOnly = false)
	public void createUser(UserDetails user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		
		newUser.setMailActivationToken(UUID.randomUUID().toString().replaceAll("-", ""));
		
		// hash password
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.getAuthorities().forEach(authority -> {
			newUser.addAuthority(new UserAuthority(authority.getAuthority()));
		});
		
		userRepository.save(newUser);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteUser(String username) {
		User user = getUserByEmail(username);
		Assert.notNull(user);

		userRepository.delete(user.getId());
	}

	@Override
	@Transactional(readOnly = false)
	public void updateUser(UserDetails user) {
		User loadedUser = getUserByEmail(user.getUsername());
		Assert.notNull(loadedUser);
		
		// Clear Authorities
		loadedUser.getAuthorities().clear();
		final User updated = userRepository.save(loadedUser);
		
		// Add authorities
		user.getAuthorities().forEach(ga -> {
			updated.addAuthority(new UserAuthority(ga.getAuthority()));
		});
		
		// Only set new password if not empty and not already hashed!
		if(StringUtils.hasText(user.getPassword()) && !PasswordUtils.isBcryptPassword(user.getPassword())) {
			updated.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
		userRepository.save(updated);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean userExists(String username) {
		return getUserByEmail(username) != null;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getUserByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("No user with username '" + username + "' exists.");
		}
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserByOriginalUsername(String username) {
		return userRepository.findByOriginalUsername(username);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Iterable<UserDto> getAllUsers() {
		return userRepository.findAllUserMin();
	}

	@Override
	public User getUserById(Long userId) {
		User user = userRepository.findOne(userId, 1);
		if(user.getCity()!=null){
			user.setCity(cityRepository.findOne(user.getCity().getId()));
		}
		if(user.getCompany()!=null){
			user.setCompany(companyRepository.findOne(user.getCompany().getId()));
			if(user.getCompany().getCity()!=null){
				user.getCompany().setCity(cityRepository.findOne(user.getCompany().getCity().getId()));
			}
		}
		return user;
	}

	@Override
	public UserActivationDto activateUser(Long userId) {
		User user = userRepository.findOne(userId,0);
		Assert.notNull(user);
		UserActivationDto dto;
		if(user.isAccountNonLocked()){
			dto = new UserActivationDto();
			dto.setEffectivChange(false);
			dto.setDateLocked(user.getDateLocked());
			dto.setDateActivated(user.getDateActivated());
			return dto;
		}
		dto = userRepository.activateUser(userId);
		Assert.notNull(dto);
		return dto;
	}

	@Override
	public UserActivationDto deactivateUser(Long userId) {
		User user = userRepository.findOne(userId,0);
		Assert.notNull(user);
		UserActivationDto dto;
		//Admins cannot be deactivated
		for(GrantedAuthority auth :user.getAuthorities()){
			if(auth.getAuthority().equals("ROLE_ADMIN")){
				dto = new UserActivationDto();
				dto.setEffectivChange(false);
				dto.setDateLocked(user.getDateLocked());
				dto.setDateActivated(user.getDateActivated());
				return dto;
			}
		}
		if(!user.isAccountNonLocked()){
			dto = new UserActivationDto();
			dto.setEffectivChange(false);
			dto.setDateLocked(user.getDateLocked());
			dto.setDateActivated(user.getDateActivated());
			return dto;
		}
		dto = userRepository.deactivateUser(userId);
		Assert.notNull(dto);
		return dto;
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public AdminUserCreationDto createUserByAdmin(AdminUserCreationDto user) {
		//check if email and username do not exist
		Iterable<Long> existingUsers = userRepository.checkForExistingUser(user.getEmail(), user.getUsername());
		Assert.isTrue(!existingUsers.iterator().hasNext());
		//create User
		User creationUser = new User();
		creationUser.setEmail(user.getEmail());
		creationUser.setOriginalUsername(user.getUsername());
		String password = RandomStringUtils.random(15, true, true);
		creationUser.setPassword(passwordEncoder.encode(password));
		creationUser.setMailActivationToken(UUID.randomUUID().toString().replaceAll("-", ""));
		//Set User Authority
		UserAuthority auth = userAuthorityRepository.findByAuthority("ROLE_USER");
		Assert.notNull(auth);
		creationUser.addAuthority(auth);
		creationUser = userRepository.save(creationUser);
		//creat Dto
		AdminUserCreationDto createdUser = new AdminUserCreationDto();
		createdUser.setId(creationUser.getId());
		createdUser.setEmail(creationUser.getEmail());
		createdUser.setUsername(creationUser.getOriginalUsername());
		createdUser.setPassword(password);
		return createdUser;
	}

	@Override
	public AdminUserCreationDto resetPassword(Long userId) {
		String password = RandomStringUtils.random(15, true, true);
		AdminUserCreationDto user = userRepository.resetPassword(userId, passwordEncoder.encode(password));
		Assert.notNull(user);
		user.setPassword(password);
		return user;
	}
}
