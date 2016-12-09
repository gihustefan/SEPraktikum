package at.decisionexpert.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/authentications")
public class AuthenticationController {
	
	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
}
