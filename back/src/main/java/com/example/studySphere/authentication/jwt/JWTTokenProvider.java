package com.example.studySphere.authentication.jwt;

import java.time.Duration;
import java.time.Instant;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.studySphere.authentication.AuthUserDetails;

@Component
public class JWTTokenProvider {

	public String getJWTToken(AuthUserDetails userDetails, Authentication authentication) {
		//
		String jwtToken = JWT.create().withIssuer("studySphere.com").withIssuedAt(Instant.now())
				.withExpiresAt(Instant.now().plus(Duration.ofHours(1)))
				.withSubject(String.valueOf(userDetails.getAuthUser().getId()))
				.withClaim("username", userDetails.getAuthUser().getUsername())
				.withClaim("email", userDetails.getAuthUser().getEmail())
				.withClaim("age", userDetails.getAuthUser().getAge())
				.withClaim("role", authentication.getAuthorities().iterator().next().toString())
				.sign(Algorithm.HMAC256(System.getenv("SECRET_KEY")));

		return jwtToken;
	}
}
