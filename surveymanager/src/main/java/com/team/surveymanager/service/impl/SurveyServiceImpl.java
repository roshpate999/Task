package com.team.surveymanager.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.team.surveymanager.dto.QuestionDTO;
import com.team.surveymanager.dto.QuestionRatingDTO;
import com.team.surveymanager.dto.QuestionResultDTO;
import com.team.surveymanager.dto.SurveyResponseDTO;
import com.team.surveymanager.dto.SurveyResponseRequest;
import com.team.surveymanager.dto.SurveyResultDTO;
import com.team.surveymanager.dto.SurveyWithQuestionsDTO;
import com.team.surveymanager.entity.Question;
import com.team.surveymanager.entity.QuestionRating;
import com.team.surveymanager.entity.Survey;
import com.team.surveymanager.entity.SurveyResponse;
import com.team.surveymanager.exception.SurveyNotFoundException;
import com.team.surveymanager.repository.SurveyRepository;
import com.team.surveymanager.repository.SurveyResponseRepository;
import com.team.surveymanager.service.SurveyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

        private final SurveyRepository surveyRepository;
        private final SurveyResponseRepository surveyResponseRepository;


        @Override
        public Long createSurvey(SurveyWithQuestionsDTO request) {
            if (request.getTitle() == null || request.getTitle().isEmpty()) {
                throw new IllegalArgumentException("Survey title cannot be null or empty");
            }
            if (request.getQuestions() == null || request.getQuestions().isEmpty()) {
                throw new IllegalArgumentException("Survey must have at least one question");
            }
    
            Survey survey = new Survey();
            survey.setTitle(request.getTitle());
            survey.setCreatedAt(new Date());
    
            List<Question> questions = request.getQuestions().stream()
                    .map(questionDTO -> {
                        Question question = new Question();
                        question.setSurvey(survey);
                        question.setQuestionText(questionDTO.getQuestionText());
                        return question;
                    })
                    .collect(Collectors.toList());
    
            survey.setQuestions(questions);
    
            surveyRepository.save(survey);
    
            return survey.getId();
        }

        @Override
        public SurveyWithQuestionsDTO getSurveyWithQuestions(Long surveyId) {
                Survey survey = surveyRepository.findById(surveyId)
                                .orElseThrow(() -> new SurveyNotFoundException(surveyId));

                List<QuestionDTO> questions = survey.getQuestions().stream()
                                .map(question -> new QuestionDTO(question.getId(), question.getQuestionText()))
                                .collect(Collectors.toList());

                return new SurveyWithQuestionsDTO(survey.getId(), survey.getTitle(), questions);
        }

        @Override
        public void submitSurveyResponse(Long surveyId, SurveyResponseRequest request) {
                Survey survey = surveyRepository.findById(surveyId)
                                .orElseThrow(() -> new SurveyNotFoundException(surveyId));

                Map<Long, Question> questionMap = survey.getQuestions().stream()
                                .collect(Collectors.toMap(Question::getId, question -> question));

                if (!request.getResponses().stream().allMatch(r -> questionMap.containsKey(r.getQuestionId()))) {
                        throw new IllegalArgumentException("Invalid question IDs in the responses");
                }

                SurveyResponse response = new SurveyResponse();
                response.setSurvey(survey);
                response.setCompletedAt(new Date());

                Set<QuestionRating> questionRatings = request.getResponses().stream()
                                .map(questionResponse -> {
                                        QuestionRating questionRating = new QuestionRating();
                                        questionRating.setSurveyResponse(response);
                                        questionRating.setQuestion(questionMap.get(questionResponse.getQuestionId()));
                                        questionRating.setRating(questionResponse.getRating());
                                        return questionRating;
                                })
                                .collect(Collectors.toSet());

                response.setQuestionRatings(questionRatings);

                surveyResponseRepository.save(response);
        }

        @Override
        public SurveyResultDTO getSurveyResults(Long surveyId) {
                Survey survey = surveyRepository.findById(surveyId)
                                .orElseThrow(() -> new SurveyNotFoundException(surveyId));

                List<Question> questions = survey.getQuestions();

                List<QuestionResultDTO> questionResults = questions.stream()
                                .map(question -> {
                                        double averageRating = surveyResponseRepository.findBySurveyId(surveyId)
                                                        .stream()
                                                        .flatMap(response -> response.getQuestionRatings().stream())
                                                        .filter(qr -> qr.getQuestion().getId().equals(question.getId()))
                                                        .mapToInt(QuestionRating::getRating)
                                                        .average()
                                                        .orElse(0.0);

                                        return new QuestionResultDTO(
                                                        question.getQuestionText(),
                                                        Math.round(averageRating * 100.0) / 100.0);
                                })
                                .collect(Collectors.toList());

                long responseCount = surveyResponseRepository.countBySurveyId(surveyId);

                return new SurveyResultDTO(
                                survey.getTitle(),
                                responseCount,
                                questionResults);
        }


@Override
public List<SurveyResponseDTO> getSurveyResponses(Long surveyId) {
    surveyRepository.findById(surveyId)
            .orElseThrow(() -> new SurveyNotFoundException(surveyId));

    return surveyResponseRepository.findBySurveyId(surveyId).stream()
            .map(response -> new SurveyResponseDTO(
                    response.getId(),
                    response.getCompletedAt(),
                    response.getQuestionRatings().stream()
                            .map(qr -> new QuestionRatingDTO(
                                    qr.getQuestion().getQuestionText(),
                                    qr.getRating()
                            ))
                            .collect(Collectors.toList())
            ))
            .collect(Collectors.toList());
}


}
