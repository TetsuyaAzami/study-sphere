package com.example.studySphere.authentication;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import com.example.studySphere.error.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ResponseService {

	public void sendJsonErrorResponse(HttpServletResponse response, HttpStatus httpStatus,
			String message) throws JsonProcessingException, IOException {
		//
		response.setStatus(httpStatus.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
		ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
		response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
	}
}
