package com.example.jwt.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RolesController {
	
	@PostMapping({"admin"})
	public String admin() {
		return "Welcome admin meet";
	}
	@PostMapping({"user"})
	public String user() {
		return "Welcome user aarti";
	}

}
