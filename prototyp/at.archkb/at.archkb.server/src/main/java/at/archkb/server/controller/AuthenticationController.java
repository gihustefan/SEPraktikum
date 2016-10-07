package at.archkb.server.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentications")
public class AuthenticationController {
	
	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
}
