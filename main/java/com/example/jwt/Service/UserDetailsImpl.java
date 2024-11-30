package com.example.jwt.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.jwt.Model.UserModel;

public class UserDetailsImpl implements UserDetails{

	private UserModel user;
	
	public UserDetailsImpl(UserModel user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//return user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		return List.of(new SimpleGrantedAuthority(user.getRoles()));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}
	@Override
	public String getUsername() {
		return user.getUsername();
	}

}
