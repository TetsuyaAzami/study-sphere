package com.example.studySphere.authentication;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
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
		sendJsonErrorResponseBase(response, httpStatus, ()-> new ErrorResponse(httpStatus.value(), message));
	}

	public void sendJsonErrorResponse(HttpServletResponse response, HttpStatus httpStatus,
			List<String> messages) throws JsonProcessingException, IOException {
		//
		sendJsonErrorResponseBase(response, httpStatus, () -> new ErrorResponse(httpStatus.value(), messages));
	}

	private void sendJsonErrorResponseBase(HttpServletResponse response, HttpStatus httpStatus,
			Supplier<ErrorResponse> errorResponseSupplier)
			throws JsonProcessingException, IOException {
		//
		response.setStatus(httpStatus.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
		ErrorResponse errorResponse = errorResponseSupplier.get();
		response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
	}
}
