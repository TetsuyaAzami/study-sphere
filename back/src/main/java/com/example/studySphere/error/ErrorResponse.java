package com.example.studySphere.error;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ErrorResponse {

	private int status;
	private String message;
	private List<String> messages;

	public ErrorResponse(int status, String message) {
		//
		this.status = status;
		this.message = message;
		this.messages = new ArrayList<String>();
	}

	public ErrorResponse(int status, List<String> messages) {
		//
		this.status = status;
		this.message = "";
		this.messages = messages;
	}
}
