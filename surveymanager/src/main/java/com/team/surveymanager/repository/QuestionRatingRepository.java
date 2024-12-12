package com.team.surveymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team.surveymanager.entity.QuestionRating;

public interface QuestionRatingRepository extends JpaRepository<QuestionRating, Long> {

}
