package at.decisionexpert.controller.admin;

import at.decisionexpert.neo4jentity.dto.user.AdminUserCreationDto;
import at.decisionexpert.neo4jentity.dto.user.UserActivationDto;
import at.decisionexpert.neo4jentity.dto.user.UserDto;
import at.decisionexpert.neo4jentity.node.User;
import at.decisionexpert.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/user")
@ResponseBody
public class AdminUserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	Iterable<UserDto>getAllUser(){
		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "{idUser}", method = RequestMethod.GET)
	User getUserById(@PathVariable Long idUser){
		return userService.getUserById(idUser);
	}
	
	@RequestMapping(value = "activate/{userId}", method = RequestMethod.POST)
	UserActivationDto activateUser(@PathVariable Long userId){
		return userService.activateUser(userId);
	}
	
	@RequestMapping(value = "deactivate/{userId}", method = RequestMethod.POST)
	UserActivationDto deactivateUser (@PathVariable Long userId){
		return userService.deactivateUser(userId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	User updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}
	
	@RequestMapping(value = "create/", method = RequestMethod.POST)
	AdminUserCreationDto createUser(@RequestBody AdminUserCreationDto user){
		return userService.createUserByAdmin(user);
	}
	
	@RequestMapping(value = "resetpw/{userId}", method = RequestMethod.POST)
	AdminUserCreationDto resetPassword(@PathVariable Long userId){
		return userService.resetPasword(userId);
	}

}
