package at.decisionexpert.controller.user;

import at.decisionexpert.neo4jentity.dto.user.AdminUserCreationDto;
import at.decisionexpert.neo4jentity.dto.user.UserActivationDto;
import at.decisionexpert.neo4jentity.node.User;
import at.decisionexpert.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/user")
@ResponseBody
public class AdminUserControllerImpl implements AdminUserController {
	
	@Autowired
    UserService userService;

	@Override
	@RequestMapping(value = "{idUser}", method = RequestMethod.GET)
	public User getUserById(@PathVariable Long idUser){
		return userService.getUserById(idUser);
	}

	@Override
	@RequestMapping(value = "activate/{userId}", method = RequestMethod.POST)
	public UserActivationDto activateUser(@PathVariable Long userId){
		return userService.activateUser(userId);
	}
	
	@RequestMapping(value = "deactivate/{userId}", method = RequestMethod.POST)
	public UserActivationDto deactivateUser (@PathVariable Long userId){
		return userService.deactivateUser(userId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public User updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}
	
	@RequestMapping(value = "create/", method = RequestMethod.POST)
	public AdminUserCreationDto createUser(@RequestBody AdminUserCreationDto user){
		return userService.createUserByAdmin(user);
	}
	
	@RequestMapping(value = "resetpw/{userId}", method = RequestMethod.POST)
	public AdminUserCreationDto resetPassword(@PathVariable Long userId){
		return userService.resetPassword(userId);
	}

}
