package com.team.surveymanager.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int statusCode;
    private long timestamp;
    private String errorCode;
}

