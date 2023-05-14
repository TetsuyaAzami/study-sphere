package com.example.studySphere.authentication.login;

import java.io.IOException;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.server.ResponseStatusException;
import com.example.studySphere.authentication.AuthUserDetails;
import com.example.studySphere.authentication.ResponseService;
import com.example.studySphere.authentication.jwt.JWTTokenProvider;
import com.example.studySphere.web.MyCookies;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public MyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager,
			JWTTokenProvider jwtTokenProvider, ResponseService responseService) {
		//
		setAuthenticationManager(authenticationManager);
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));

		this.setAuthenticationSuccessHandler((request, response, authentication) -> {
			//
			AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();

			String jwtToken = jwtTokenProvider.getJWTToken(userDetails, authentication);

			MyCookies.AUTH.setCookie(response, jwtToken, Duration.ofHours(1));
			responseService.sendJsonErrorResponse(response, HttpStatus.OK, "ログインに成功しました");
		});

		this.setAuthenticationFailureHandler((request, response, exception) -> {
			//
			responseService.sendJsonErrorResponse(response, HttpStatus.UNAUTHORIZED, "ユーザ名もしくはパスワードが一致しません");
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
