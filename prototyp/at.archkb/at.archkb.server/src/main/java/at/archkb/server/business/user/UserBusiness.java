package at.archkb.server.business.user;

import org.springframework.security.provisioning.UserDetailsManager;

import at.archkb.server.neo4jentity.dto.user.AdminUserCreationDto;
import at.archkb.server.neo4jentity.dto.user.UserActivationDto;
import at.archkb.server.neo4jentity.dto.user.UserDto;
import at.archkb.server.neo4jentity.node.User;

public interface UserBusiness extends UserDetailsManager {
	User getUserByOriginalUsername(String username);

	User getUserByEmail(String email);
	
	Iterable<UserDto> getAllUsers();
	
	User getUserById(Long userId);
	
	UserActivationDto activateUser(Long userId);
	
	UserActivationDto deactivateUser (Long userId);

	User updateUser(User user);

	AdminUserCreationDto createUserByAdmin(AdminUserCreationDto user);

	AdminUserCreationDto resetPassword(Long userId);
}
