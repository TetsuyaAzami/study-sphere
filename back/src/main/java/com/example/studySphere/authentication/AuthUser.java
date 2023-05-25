package com.example.studySphere.authentication;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AuthUser {

	private long id;
	private String username;
	@JsonIgnore
	private String password;
	private String email;
	private Integer age;
	private Boolean enabled;
	private String roles;

	public static AuthUser fromJwt(DecodedJWT jwtToken) {
		//
		AuthUser authUser = new AuthUser();

		authUser.setId(Long.valueOf(jwtToken.getSubject()));
		authUser.setUsername(jwtToken.getClaim("username").asString());
		authUser.setPassword("");
		authUser.setEmail(jwtToken.getClaim("email").asString());
		authUser.setAge(jwtToken.getClaim("age").asInt());
		authUser.setRoles(jwtToken.getClaim("role").asString());

		return authUser;
	}
}
