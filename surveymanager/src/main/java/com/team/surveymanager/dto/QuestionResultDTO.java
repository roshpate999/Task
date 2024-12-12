package com.team.surveymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionResultDTO {
    private String question;
    private Double averageRating;
}

