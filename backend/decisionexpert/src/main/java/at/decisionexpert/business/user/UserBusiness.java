package at.decisionexpert.business.user;

import at.decisionexpert.neo4jentity.dto.user.AdminUserCreationDto;
import at.decisionexpert.neo4jentity.dto.user.UserActivationDto;
import at.decisionexpert.neo4jentity.dto.user.UserCreationDto;
import at.decisionexpert.neo4jentity.dto.user.UserDto;
import at.decisionexpert.neo4jentity.node.User;
import org.springframework.security.provisioning.UserDetailsManager;

public interface UserBusiness extends UserDetailsManager {
	User getUserByOriginalUsername(String username);

	User getUserByEmail(String email);

	UserDto createUser(UserCreationDto user);
	
	Iterable<UserDto> getAllUsers();
	
	User getUserById(Long userId);
	
	UserActivationDto activateUser(Long userId);
	
	UserActivationDto deactivateUser(Long userId);

	User updateUser(User user);

	AdminUserCreationDto createUserByAdmin(AdminUserCreationDto user);

	AdminUserCreationDto resetPassword(Long userId);
}
