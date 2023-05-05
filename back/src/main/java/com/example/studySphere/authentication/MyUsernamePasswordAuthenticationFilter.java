package com.example.studySphere.authentication;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.server.ResponseStatusException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public MyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		//
		setAuthenticationManager(authenticationManager);
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));

		this.setAuthenticationSuccessHandler((request, response, authentication) -> {
			//
			AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();

			String jwtToken = JWT.create().withIssuer("studySphere.com").withIssuedAt(Instant.now())
					.withExpiresAt(Instant.now().plus(Duration.ofHours(1)))
					.withSubject(String.valueOf(userDetails.getAuthUser().getId()))
					.withClaim("username", userDetails.getAuthUser().getUsername())
					.withClaim("email", userDetails.getAuthUser().getEmail())
					.withClaim("age", userDetails.getAuthUser().getAge())
					.withClaim("role", authentication.getAuthorities().iterator().next().toString())
					.sign(Algorithm.HMAC256(System.getenv("SECRET_KEY")));

			response.addHeader("X-AUTH-TOKEN", "Bearer " + jwtToken);
			response.setStatus(200);
			response.getWriter()
					.write(new ObjectMapper().writeValueAsString(authentication.getName()));
		});
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		//
		try {
			AuthenticationRequest authRequest = new ObjectMapper()
					.readValue(request.getInputStream(), AuthenticationRequest.class);

			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					authRequest.getUsername(), authRequest.getPassword()));
		} catch (IOException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "ユーザ名もしくはパスワードが一致しません");
		}
	}
}
