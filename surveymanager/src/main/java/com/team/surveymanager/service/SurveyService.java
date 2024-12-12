package com.team.surveymanager.service;

import java.util.List;

import com.team.surveymanager.dto.SurveyResponseDTO;
import com.team.surveymanager.dto.SurveyResponseRequest;
import com.team.surveymanager.dto.SurveyResultDTO;
import com.team.surveymanager.dto.SurveyWithQuestionsDTO;

public interface SurveyService {
    Long createSurvey(SurveyWithQuestionsDTO request);
    SurveyWithQuestionsDTO getSurveyWithQuestions(Long surveyId);
    void submitSurveyResponse(Long surveyId, SurveyResponseRequest request);
    SurveyResultDTO getSurveyResults(Long surveyId);
    List<SurveyResponseDTO> getSurveyResponses(Long surveyId);
}
