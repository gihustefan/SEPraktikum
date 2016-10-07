package at.archkb.server.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.archkb.server.business.user.UserBusiness;
import at.archkb.server.neo4jentity.dto.user.AdminUserCreationDto;
import at.archkb.server.neo4jentity.dto.user.UserActivationDto;
import at.archkb.server.neo4jentity.dto.user.UserDto;
import at.archkb.server.neo4jentity.node.User;
import at.archkb.server.neo4jentity.node.UserAuthority;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserBusiness userBusiness;
	
	
	@Override
	public void createUser(String username, String password, String... authorities) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		for(String authority : authorities) {
			user.addAuthority(new UserAuthority(authority));
		}
		
		userBusiness.createUser(user);
	}


	@Override
	public User getUser(String username) {
		return userBusiness.getUserByOriginalUsername(username);
	}


	@Override
	public Iterable<UserDto> getAllUsers() {
		return userBusiness.getAllUsers();
	}


	@Override
	public User getUserById(Long userId) {
		return userBusiness.getUserById(userId);
	}


	@Override
	public UserActivationDto activateUser(Long userId) {
		return userBusiness.activateUser(userId);
	}


	@Override
	public UserActivationDto deactivateUser(Long userId) {
		return userBusiness.deactivateUser(userId);
	}


	@Override
	public User updateUser(User user) {
		return userBusiness.updateUser(user);
	}


	@Override
	public AdminUserCreationDto createUserByAdmin(AdminUserCreationDto user) {
		return userBusiness.createUserByAdmin(user);
	}


	@Override
	public AdminUserCreationDto resetPasword(Long userId) {
		return userBusiness.resetPassword(userId);
	}

}
