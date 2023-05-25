package com.example.studySphere.domain.model.user;

import java.util.ArrayList;
import java.util.List;

public class Roles {

	List<Role> roles;

	public Roles(List<Role> roles) {
		//
		this.roles = roles;
	}

	public List<Role> list() {
		//
		return new ArrayList<>(roles);
	}
}
