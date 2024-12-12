package com.team.surveymanager.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SurveyResponseDTO {
    private Long responseId;
    private Date submittedAt;
    private List<QuestionRatingDTO> ratings;
}
