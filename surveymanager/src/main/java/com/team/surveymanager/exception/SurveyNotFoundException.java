package com.team.surveymanager.exception;

public class SurveyNotFoundException extends ApplicationException {

    private static final String ERROR_CODE = "SURVEY_NOT_FOUND";

    public SurveyNotFoundException(Long surveyId) {
        super(String.format("Survey with ID %d not found", surveyId), ERROR_CODE);
    }
}
