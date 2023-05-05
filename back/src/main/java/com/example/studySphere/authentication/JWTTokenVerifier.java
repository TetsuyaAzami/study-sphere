package com.example.studySphere.authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JWTTokenVerifier {

	public UserDetails verify(JWTAuthenticationToken authentication) {
		//
		String jwtToken = authentication.getToken();

		if (!StringUtils.hasText(jwtToken))
			throw new BadCredentialsException("トークンが不正です。ログインし直してください");

		DecodedJWT decodedToken;
		try {
			decodedToken = JWT.require(Algorithm.HMAC256(System.getenv("SECRET_KEY"))).build()
					.verify(jwtToken);
		} catch (JWTDecodeException e) {
			throw new BadCredentialsException("トークンが不正です。ログインし直してください");
		}

		AuthUser authUser = AuthUser.fromJwt(decodedToken);

		return new AuthUserDetails(authUser);
	}
}
