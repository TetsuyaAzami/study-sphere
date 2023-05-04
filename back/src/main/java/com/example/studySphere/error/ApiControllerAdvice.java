package com.example.studySphere.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body,
			HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
		//
		log.error("サーバ内で何らかのエラーが発生しました", ex);
		ErrorResponse errorResponse = new ErrorResponse(statusCode.value(), "サーバ内で何らかのエラーが発生しました");

		return super.handleExceptionInternal(ex, errorResponse, headers, statusCode, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		//
		ErrorResponse errorResponse =
				new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "サーバ内で何らかのエラーが発生しました");
		log.error("サーバ内で何らかのエラーが発生しました");

		return ResponseEntity.internalServerError().body(errorResponse);
	}
}
