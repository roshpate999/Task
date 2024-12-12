package com.team.surveymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team.surveymanager.entity.SurveyResponse;

import java.util.List;

public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long> {
    List<SurveyResponse> findBySurveyId(Long surveyId);

    long countBySurveyId(Long surveyId);
}
