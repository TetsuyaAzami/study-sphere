package com.example.studySphere.authentication;

import java.io.IOException;
import java.util.Arrays;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import com.example.studySphere.web.MyCookies;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTTokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public JWTTokenAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher,
			AuthenticationManager authenticationManager) {
		//
		super(requiresAuthenticationRequestMatcher, authenticationManager);

		setAuthenticationSuccessHandler((request, response, authentication) -> {
			//
			log.info("ログイン成功");
		});
		setAuthenticationFailureHandler((request, response, exception) -> {
			//
			log.error("ログイン失敗", exception);
			throw new BadCredentialsException(exception.getMessage());
		});
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		//
		Cookie[] requestCookies = request.getCookies();

		if (request.getCookies() == null)
			throw new BadCredentialsException("認証情報がありません。ログインしてください");

		Cookie authCookie = Arrays.stream(requestCookies)
				.filter(cookie -> MyCookies.AUTH.name().equals(cookie.getName())).findFirst()
				.orElseThrow(() -> new BadCredentialsException("認証情報がありません。ログインしてください"));

		String jwtToken = authCookie.getValue();

		JWTAuthenticationToken token = JWTAuthenticationToken.unauthenticated(null, jwtToken);

		return getAuthenticationManager().authenticate(token);
	}
}
