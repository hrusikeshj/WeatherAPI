package com.demo.weatherapp.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.weatherapp.exception.model.ErrorMessage;

@ControllerAdvice
public class ServiceException extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger(ServiceException.class);

	@ExceptionHandler(value = { HttpClientErrorException.class })
	public ResponseEntity<Object> dataValidationException(
			HttpClientErrorException exception, WebRequest req) {
		logger.error("HttpClientErrorException while processing request");
		ErrorMessage errorMessage = new ErrorMessage("Invalid Input",
				LocalDateTime.now());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(),
				HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAllException(Exception exception,
			WebRequest req) {
		logger.error("Exception while processing request");

		String errorMsg = exception.getLocalizedMessage();
		if (errorMsg == null)
			errorMsg = exception.toString();
		ErrorMessage errorMessage = new ErrorMessage(errorMsg,
				LocalDateTime.now());

		return new ResponseEntity<>(errorMessage, new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
