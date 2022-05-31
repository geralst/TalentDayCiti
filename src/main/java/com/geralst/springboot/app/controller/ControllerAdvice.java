package com.geralst.springboot.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.geralst.springboot.app.dto.ErrorDTO;
import com.geralst.springboot.app.exception.RequestException;

@RestControllerAdvice
public class ControllerAdvice {

	 @ExceptionHandler(value = RequestException.class)
	 public ResponseEntity<ErrorDTO> dataAccessHandler(RequestException e) {
		 ErrorDTO error = ErrorDTO.builder().code(e.getCode()).message(e.getMessage()).build();
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	 }
}
