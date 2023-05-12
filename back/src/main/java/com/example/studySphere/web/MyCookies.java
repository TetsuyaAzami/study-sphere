package com.example.studySphere.web;

import java.time.Duration;
import org.springframework.boot.web.server.Cookie.SameSite;
import org.springframework.http.ResponseCookie;
import jakarta.servlet.http.HttpServletResponse;

public enum MyCookies {
	//
	AUTH("auth") {
		@Override
		public void setCookie(HttpServletResponse response, String value, Duration maxAge) {
			//
			ResponseCookie responseCookie = ResponseCookie.from(this.name(), value).maxAge(maxAge).httpOnly(true).secure(false)
					.sameSite(SameSite.LAX.attributeValue()).build();

			response.addHeader(SET_COOKIE, responseCookie.toString());
		}
	};

	private static final String SET_COOKIE = "Set-Cookie";
	private String name;

	private MyCookies(String name) {
		this.name = name;
	}

	public abstract void setCookie(HttpServletResponse response, String value, Duration maxAge);
}
