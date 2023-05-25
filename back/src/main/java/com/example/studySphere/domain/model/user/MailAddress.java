package com.example.studySphere.domain.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MailAddress {

	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "メールアドレスの形式で入力してください")
	String value = "";

	public MailAddress(String value) {
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
