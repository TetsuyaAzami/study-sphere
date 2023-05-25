package com.example.studySphere.domain.model.user;

public enum Role {

	GENERAL("general"), ADMIN("admin");

	private String roleName;

	Role(String roleName) {
		//
		this.roleName = roleName;
	}

	public String roleName() {
		return roleName;
	}
}
