package com.example.studySphere.domain.model.user;

import java.time.LocalDate;
import java.util.UUID;

public class EmailVerification {

	String uuid;

	MailAddress mailAddress;

	LocalDate expiredAt;

	public EmailVerification(MailAddress mailAddress) {
		this.uuid = UUID.randomUUID().toString();
		this.mailAddress = mailAddress;
		this.expiredAt = LocalDate.now().plusDays(1);
	}

	public String uuid() {
		return uuid;
	}

	public MailAddress mailAddress() {
		return mailAddress;
	}

	public LocalDate expiredAt() {
		return expiredAt;
	}
}
