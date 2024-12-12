package com.team.surveymanager.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.team.surveymanager.entity.Survey;

import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @EntityGraph(attributePaths = {"questions"})
    Optional<Survey> findWithQuestionsById(Long surveyId);
}

