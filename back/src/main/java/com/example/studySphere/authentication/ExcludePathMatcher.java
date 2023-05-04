package com.example.studySphere.authentication;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.web.util.matcher.RequestMatcher;
import jakarta.servlet.http.HttpServletRequest;

public class ExcludePathMatcher implements RequestMatcher {

	private Set<String> excludePaths;

	public ExcludePathMatcher(String[] excludePaths) {
		//
		this.excludePaths = new HashSet<>(Arrays.asList(excludePaths));
	}

	@Override
	public boolean matches(HttpServletRequest request) {
		//
		return !this.excludePaths.contains(request.getServletPath());
	}
}
