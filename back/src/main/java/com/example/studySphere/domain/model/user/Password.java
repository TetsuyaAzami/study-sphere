package com.example.studySphere.domain.model.user;

import jakarta.validation.constraints.Size;

public class Password {

	final int MIN = 4;
	final int MAX = 128;

	@Size(min = MIN, max = MAX, message = MIN + "文字以上" + MAX + "文字以下で入力してください")
	String value = "";

	public Password(String value) {
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
