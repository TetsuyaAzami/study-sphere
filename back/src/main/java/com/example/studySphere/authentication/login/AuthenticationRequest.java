package com.example.studySphere.authentication.login;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import lombok.Data;

@Data
public class AuthenticationRequest {

	private String username;

	private static final Integer USERNAME_MIN_LENGTH = 5;

	private static final Integer USERNAME_MAX_LENGTH = 30;

	private String password;

	private static final Integer PASSWORD_MIN_LENGTH = 4;

	private static final Integer PASSWORD_MAX_LENGTH = 128;

	public List<String> generateValidationErrorMessages() {
		//
		ArrayList<String> errorKeyList = new ArrayList<>();

		ArrayList<String> usernameValidationErrorMessages = generateUsernameValidationErrorMessages();
		ArrayList<String> passwordValidationErrorMessages = generatePasswordValidationErrorMessages();

		errorKeyList.addAll(usernameValidationErrorMessages);
		errorKeyList.addAll(passwordValidationErrorMessages);

		return errorKeyList;
	}

	private ArrayList<String> generateUsernameValidationErrorMessages() {
		//
		ArrayList<String> usernameErrorKeyList = new ArrayList<>();

		if (!StringUtils.hasText(this.username) || this.username.length() < USERNAME_MIN_LENGTH || USERNAME_MAX_LENGTH < this.username.length())
			usernameErrorKeyList.add("ユーザ名は" + USERNAME_MIN_LENGTH + "文字以上" + USERNAME_MAX_LENGTH + "文字以下で入力してください");

		return usernameErrorKeyList;
	}

	private ArrayList<String> generatePasswordValidationErrorMessages(){
		//
		ArrayList<String> passwordErrorKeyList = new ArrayList<>();

		if(!StringUtils.hasText(this.password) || this.password.length() < PASSWORD_MIN_LENGTH || PASSWORD_MAX_LENGTH < this.password.length())
			passwordErrorKeyList.add("パスワードは" + PASSWORD_MIN_LENGTH + "文字以上" + PASSWORD_MAX_LENGTH + "文字以下で入力してください");

			return passwordErrorKeyList;
	}
}
