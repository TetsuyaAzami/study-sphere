package com.example.studySphere.authentication.login;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.studySphere.authentication.AuthUser;
import com.example.studySphere.authentication.AuthUserDetails;

@Service
public class AuthUserDetailsService implements UserDetailsService {

	private final AuthUserRepository authUserRepository;

	public AuthUserDetailsService(AuthUserRepository authUserRepository) {
		//
		this.authUserRepository = authUserRepository;
	}

	@Override
	public AuthUserDetails loadUserByUsername(String username) {
		//
		AuthUser searchCondition = new AuthUser();
		searchCondition.setUsername(username);

		AuthUser authUser = this.authUserRepository.findByCondition(searchCondition);
		if (authUser == null)
			throw new UsernameNotFoundException("ユーザ名またはパスワードが間違っています");

		return new AuthUserDetails(authUser);
	}
}
