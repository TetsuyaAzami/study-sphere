package com.example.studySphere.authentication.login;

import java.io.IOException;
import java.time.Duration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.server.ResponseStatusException;
import com.example.studySphere.authentication.AuthUserDetails;
import com.example.studySphere.authentication.jwt.JWTTokenProvider;
import com.example.studySphere.error.ErrorResponse;
import com.example.studySphere.web.MyCookies;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public MyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager,
			JWTTokenProvider jwtTokenProvider) {
		//
		setAuthenticationManager(authenticationManager);
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));

		this.setAuthenticationSuccessHandler((request, response, authentication) -> {
			//
			AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();

			String jwtToken = jwtTokenProvider.getJWTToken(userDetails, authentication);

			MyCookies.AUTH.setCookie(response, jwtToken, Duration.ofHours(1));
			response.setStatus(200);
			response.getWriter()
					.write(new ObjectMapper().writeValueAsString(authentication.getName()));
		});

		this.setAuthenticationFailureHandler((request, response, exception) -> {
			//
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
			ErrorResponse errorResponse =
					new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "ユーザ名もしくはパスワードが一致しません");
			response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
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
