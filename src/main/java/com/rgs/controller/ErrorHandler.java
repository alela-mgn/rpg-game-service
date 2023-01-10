package com.rgs.controller;

import com.rgs.exception.PlayerNotFoundException;
import com.rgs.exception.PlayerValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = PlayerNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(PlayerNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PlayerValidationException.class)
    protected ResponseEntity<Object> handleValidationException(PlayerValidationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
