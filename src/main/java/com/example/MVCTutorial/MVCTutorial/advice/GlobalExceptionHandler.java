package com.example.MVCTutorial.MVCTutorial.advice;

import com.example.MVCTutorial.MVCTutorial.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleEmployeeNotFound(ResourceNotFoundException exception){
        ApiError error =ApiError.
                builder().
                status(HttpStatus.NOT_FOUND).
                errorMessage(exception.getMessage()).build();
        return buildErrorResponseEntity(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(Exception exception){
        ApiError error =ApiError.
                builder().
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                errorMessage(exception.getMessage()).build();
        return buildErrorResponseEntity(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException exception){
        List<String> errors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ApiError error =ApiError.
                builder().
                status(HttpStatus.BAD_REQUEST).
                errorMessage("Input Validation Failed")
                .subErrors(errors)
                .build();
        return buildErrorResponseEntity(error);

    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError error){
        return new ResponseEntity<>(new ApiResponse(error),error.getStatus());
    }
}
