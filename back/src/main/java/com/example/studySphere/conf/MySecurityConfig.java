package com.example.studySphere.conf;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.example.studySphere.authentication.MyAuthenticationEntryPoint;
import com.example.studySphere.authentication.ResponseService;
import com.example.studySphere.authentication.jwt.JWTTokenProvider;
import com.example.studySphere.authentication.jwt.JWTTokenVerifier;
import com.example.studySphere.authentication.jwtValidation.JWTTokenAuthenticationFilter;
import com.example.studySphere.authentication.jwtValidation.JWTTokenAuthenticationProvider;
import com.example.studySphere.authentication.login.AuthUserDetailsService;
import com.example.studySphere.authentication.login.MyUsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class MySecurityConfig {

	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	@Autowired
	private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

	@Autowired
	private ResponseService responseService;

	@Autowired
	public void configureProvider(AuthenticationManagerBuilder auth,
			AuthUserDetailsService authUserDetailsService, JWTTokenVerifier jwtTokenVerifier)
			throws Exception {
		//
		JWTTokenAuthenticationProvider jwtTokenAuthenticationProvider =
				new JWTTokenAuthenticationProvider(jwtTokenVerifier);
		auth.authenticationProvider(jwtTokenAuthenticationProvider);

		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(authUserDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(8));
		auth.authenticationProvider(daoAuthenticationProvider);
	}

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		//
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/api/login").permitAll()
				.anyRequest().authenticated());

		http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint);

		AuthenticationManager authenticationManager =
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));

		http.addFilter(new MyUsernamePasswordAuthenticationFilter(authenticationManager,
				jwtTokenProvider, responseService));
		http.addFilterBefore(new JWTTokenAuthenticationFilter(authenticationManager),
				MyUsernamePasswordAuthenticationFilter.class);

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.csrf().disable();
		http.cors();

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		//
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:3000"));
		configuration.setAllowedMethods(List.of("*"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowCredentials(true);

		var source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}
