package com.example.jwt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.Model.UserModel;
import com.example.jwt.Repository.UserRepository;
import com.example.jwt.Service.JwtService;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AuthenticationManager manager;
	@Autowired 
	private JwtService jwtService;

	@PostMapping({"registerIdAndPassword"})
	public UserModel register(@RequestBody UserModel userModel) {
		return userRepo.save(userModel);
	}
	
	@PostMapping({"login"})
	public String login(@RequestBody UserModel user) {
		Authentication auth = manager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		
		if(auth.isAuthenticated())
			return jwtService.generateToken(user.getUsername());
		else
			return "false";
		
	}
}
