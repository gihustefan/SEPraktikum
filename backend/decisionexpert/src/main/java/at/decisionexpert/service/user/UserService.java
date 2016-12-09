package at.decisionexpert.service.user;

import at.decisionexpert.neo4jentity.dto.user.AdminUserCreationDto;
import at.decisionexpert.neo4jentity.dto.user.UserActivationDto;
import at.decisionexpert.neo4jentity.dto.user.UserCreationDto;
import at.decisionexpert.neo4jentity.dto.user.UserDto;
import at.decisionexpert.neo4jentity.node.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public interface UserService {
	
	void createUser(@NotNull String username, @Size(min = 8) String password, String... authorities);
	
	User getUser(@NotNull String username);

	UserDto createUser(@NotNull UserCreationDto user);
	
	// TODO: Evaluate if this is necessary
	////    @PreAuthorize("hasRole('ROLE_ADMIN')")
	Iterable<UserDto> getAllUsers();

	// TODO: Evaluate if this is necessary
//	//    @PreAuthorize("hasRole('ROLE_ADMIN')")
	User getUserById(Long userId);
	
	//    @PreAuthorize("hasRole('ROLE_ADMIN')")
	UserActivationDto activateUser(Long userId);
	
	//    @PreAuthorize("hasRole('ROLE_ADMIN')")
	UserActivationDto deactivateUser(Long userId);
	
	// dateLocked, dateActivated, mailActivationToken, password, email, authorities should not be changed here
	//    @PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#user, 'NOSENSIBLEDATA')")// TODO allow user to change himself
	User updateUser(User user);
	
	//    @PreAuthorize("hasRole('ROLE_ADMIN')")
	AdminUserCreationDto createUserByAdmin(AdminUserCreationDto user);
	
	//    @PreAuthorize("hasRole('ROLE_ADMIN')")
	AdminUserCreationDto resetPassword(Long userId);
}
