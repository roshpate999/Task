package com.team.surveymanager.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SurveyWithQuestionsDTO {
    private Long id;
    private String title;
    private List<QuestionDTO> questions;
}
