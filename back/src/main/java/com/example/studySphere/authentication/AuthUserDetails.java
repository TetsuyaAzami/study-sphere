package com.example.studySphere.authentication;

import java.util.Arrays;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import lombok.Getter;

@Getter
public class AuthUserDetails extends User {

	private AuthUser authUser;

	public AuthUserDetails(AuthUser authUser) {
		//
		super(authUser.getUsername(), authUser.getPassword(),
				Arrays.asList(authUser.getRoles().split(",")).stream()
						.map(role -> new SimpleGrantedAuthority(role)).toList());
		this.authUser = authUser;
	}
}
