package com.example.vehiclerental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex) {

        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now().toString(),
                ex.getMessage(),
                "Internal Server Error"
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidRentalPlanException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(InvalidRentalPlanException ex) {

        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now().toString(),
                ex.getMessage(),
                "Rental plan not found"
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidation(MethodArgumentNotValidException ex) {

        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now().toString(),
                ex.getBindingResult().getFieldError().getDefaultMessage(),
                "Validation Error"
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}