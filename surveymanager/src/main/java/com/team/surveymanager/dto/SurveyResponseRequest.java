package com.team.surveymanager.dto;

import lombok.Data;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class SurveyResponseRequest {
    @NotNull(message = "Responses cannot be null")
    @Size(min = 1, message = "At least one response is required")
    private List<QuestionResponseDTO> responses;
}

