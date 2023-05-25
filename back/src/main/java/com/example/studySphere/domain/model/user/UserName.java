package com.example.studySphere.domain.model.user;

import jakarta.validation.constraints.Size;

public class UserName {

	final int MIN = 5;
	final int MAX = 30;

	@Size(min = MIN, max = MAX, message = MIN + "文字以上" + MAX + "文字以下で入力してください")
	String value = "";

	public UserName(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
