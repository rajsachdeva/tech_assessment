package com.tech.example.tech_assessment.exception.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tech.example.tech_assessment.exception.ErrorResponse;
import com.tech.example.tech_assessment.exception.ServiceException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/** LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * @param ServiceException
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	protected ResponseEntity<Object> serviceException(ServiceException serviceException, WebRequest request) {
		LOGGER.debug("GlobalExceptionHandler#serviceException()...");
		ErrorResponse response = new ErrorResponse(serviceException.getHttpStatusCode(), serviceException.getMessage(),
				serviceException.getTranslationKey());
		LOGGER.debug("GlobalExceptionHandler#serviceException().");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
