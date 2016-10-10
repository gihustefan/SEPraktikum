package at.decisionexpert.controller.user;

import at.decisionexpert.neo4jentity.dto.user.AdminUserCreationDto;
import at.decisionexpert.neo4jentity.dto.user.UserActivationDto;
import at.decisionexpert.neo4jentity.dto.user.UserDto;
import at.decisionexpert.neo4jentity.node.User;

/**
 * Created by stefanhaselboeck on 10.10.16.
 */
public interface AdminUserController {


    User getUserById(Long userId);

    UserActivationDto activateUser(Long userId);

    UserActivationDto deactivateUser (Long userId);

    User updateUser(User user);

    AdminUserCreationDto createUser(AdminUserCreationDto user);

    AdminUserCreationDto resetPassword(Long userId);
}
