package com.team.surveymanager.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SurveyResultDTO {
    private String surveyTitle;
    private long totalResponses;
    private List<QuestionResultDTO> results;
}