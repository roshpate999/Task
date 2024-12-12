package com.team.surveymanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.team.surveymanager.dto.ErrorResponse;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(SurveyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSurveyNotFoundException(SurveyNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), "SURVEY_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildErrorResponse(ex.getMessage(), "INVALID_INPUT", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return buildErrorResponse(
                "An unexpected error occurred. Please try again later.",
                "INTERNAL_SERVER_ERROR",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String message, String errorCode, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatusCode(status.value());
        errorResponse.setTimestamp(System.currentTimeMillis());
        errorResponse.setErrorCode(errorCode);
        return ResponseEntity.status(status).body(errorResponse);
    }
}

