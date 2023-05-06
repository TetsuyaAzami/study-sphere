package com.example.studySphere.authentication.jwtValidation;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.studySphere.authentication.jwt.JWTTokenVerifier;

public class JWTTokenAuthenticationProvider implements AuthenticationProvider {

	private JWTTokenVerifier jwtTokenVerifier;

	public JWTTokenAuthenticationProvider(JWTTokenVerifier jwtTokenVerifier) {
		//
		this.jwtTokenVerifier = jwtTokenVerifier;
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		//
		if (!supports(authentication.getClass())) {
			return null;
		}

		UserDetails authUser = this.jwtTokenVerifier.verify((JWTAuthenticationToken)authentication);

		return JWTAuthenticationToken.authenticated(authUser, ((JWTAuthenticationToken)authentication).getToken(), authUser.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		//
		return JWTAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
