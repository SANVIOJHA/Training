package com.library.libmanagement.exception;


import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleException(ResourceNotFoundException ex) {
        return ex.getMessage();
    }
}
