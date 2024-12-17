package com.example.MVCTutorial.MVCTutorial.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Data
public class ApiError {

    private HttpStatus status;

    private String errorMessage;
    private List<String> subErrors;
}
