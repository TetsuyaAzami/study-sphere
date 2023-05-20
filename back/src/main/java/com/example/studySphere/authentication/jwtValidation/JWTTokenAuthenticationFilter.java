package com.example.studySphere.authentication.jwtValidation;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.studySphere.web.MyCookies;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTTokenAuthenticationFilter extends OncePerRequestFilter {

	private AuthenticationManager authenticationManager;

	public JWTTokenAuthenticationFilter(AuthenticationManager authenticationManager) {
		//
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		//
		Cookie[] requestCookies = request.getCookies();

		if (requestCookies != null) {
			//
			Optional<Cookie> authCookie = Arrays.stream(requestCookies)
					.filter(cookie -> MyCookies.AUTH.name().equals(cookie.getName())).findFirst();

			authCookie.ifPresent(cookie -> {
				authenticate(cookie);
			});
		}

		filterChain.doFilter(request, response);
	}

	private void authenticate(Cookie cookie) {
		//
		String jwtToken = cookie.getValue();

		JWTAuthenticationToken token = JWTAuthenticationToken.unauthenticated(null, jwtToken);

		Authentication authenticate = this.authenticationManager.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authenticate);
	}
}
