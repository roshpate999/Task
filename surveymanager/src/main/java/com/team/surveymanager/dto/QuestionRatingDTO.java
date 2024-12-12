package com.team.surveymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionRatingDTO {
    private String questionText;
    private int rating;
}
