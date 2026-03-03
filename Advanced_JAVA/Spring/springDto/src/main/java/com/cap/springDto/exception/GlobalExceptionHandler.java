package com.cap.springDto.exception;

import com.cap.springDto.StudentNotFoundException;
import com.cap.springDto.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(StudentNotFoundException.class)
//    public ResponseEntity<ApiResponse<Object>> handleStudentNotFound(StudentNotFoundException ex) {
//
//        ApiResponse<Object> response =
//                new ApiResponse<>(404, ex.getMessage(), null);
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//


        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
                MethodArgumentNotValidException ex) {

            Map<String, String> errors = new HashMap<>();

            ex.getBindingResult().getFieldErrors()
                    .forEach(error ->
                            errors.put(error.getField(), error.getDefaultMessage())
                    );

            ApiResponse<Map<String, String>> response =
                    new ApiResponse<>(400, "Validation failed", errors);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }



    }





