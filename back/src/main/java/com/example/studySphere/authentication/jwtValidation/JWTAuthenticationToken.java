package com.example.studySphere.authentication.jwtValidation;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {

	private final Object principal;

	private String token;

	public JWTAuthenticationToken(Object principal, String token) {
		//
		super(null);
		this.principal = principal;
		this.token = token;
	}

	public JWTAuthenticationToken(Object principal, String token, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.token = token;
		super.setAuthenticated(true);
	}

	public static JWTAuthenticationToken unauthenticated(Object principal, String token) {
		//
		return new JWTAuthenticationToken(principal, token);
	}

	public static JWTAuthenticationToken authenticated(Object principal, String token,
			Collection<? extends GrantedAuthority> authorities) {
		//
		return new JWTAuthenticationToken(principal, token, authorities);
	}

	@Override
	public Object getCredentials() {
		//
		return null;
	}

	@Override
	public Object getPrincipal() {
		//
		return this.principal;
	}

	public String getToken() {
		//
		return this.token;
	}

	public void setToken(String token) {
		//
		this.token = token;
	}
}
