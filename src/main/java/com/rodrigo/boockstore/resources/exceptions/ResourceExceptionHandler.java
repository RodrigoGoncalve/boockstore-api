package com.rodrigo.boockstore.resources.exceptions;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rodrigo.boockstore.service.exceptions.DataIntegrityViolationException;
import com.rodrigo.boockstore.service.exceptions.ObjectNoutFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNoutFoundException.class)
	public ResponseEntity<StanderError> objectNoutFoundException(ObjectNoutFoundException e, ServletRequest request){
		
		StanderError error = new StanderError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), 
				e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StanderError> dataIntegrityViolationException(DataIntegrityViolationException e, ServletRequest request){
		
		StanderError error = new StanderError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StanderError> validationError(MethodArgumentNotValidException e, ServletRequest request){
		
		ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), 
				"Erro de validação dos campos");
		
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			error.addErros(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
