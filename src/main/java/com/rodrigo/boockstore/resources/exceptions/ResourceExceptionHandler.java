package com.rodrigo.boockstore.resources.exceptions;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rodrigo.boockstore.service.exceptions.ObjectNoutFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNoutFoundException.class)
	public ResponseEntity<StanderError> objectNoutFoundException(ObjectNoutFoundException e, ServletRequest request){
		StanderError error = new StanderError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), 
				e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
