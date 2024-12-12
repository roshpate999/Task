package com.team.surveymanager.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.team.surveymanager.dto.SurveyResponseDTO;
import com.team.surveymanager.dto.SurveyResponseRequest;
import com.team.surveymanager.dto.SurveyResultDTO;
import com.team.surveymanager.dto.SurveyWithQuestionsDTO;
import com.team.surveymanager.service.SurveyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping("/createSurvey")
    public ResponseEntity<Long> createSurvey(@RequestBody SurveyWithQuestionsDTO request) {
        Long surveyId = surveyService.createSurvey(request);
        return ResponseEntity.ok(surveyId);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SurveyWithQuestionsDTO> getSurveyWithQuestions(@PathVariable Long id) {
        SurveyWithQuestionsDTO survey = surveyService.getSurveyWithQuestions(id);
        return ResponseEntity.ok(survey);
    }
    
    @PostMapping("/{id}/responses")
    public ResponseEntity<Void> submitSurveyResponse(
            @PathVariable Long id,
            @Valid @RequestBody SurveyResponseRequest request) {
        surveyService.submitSurveyResponse(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/results")
    public ResponseEntity<SurveyResultDTO> getSurveyResults(@PathVariable Long id) {
        SurveyResultDTO response = surveyService.getSurveyResults(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/allresponses")
    public ResponseEntity<List<SurveyResponseDTO>> getAllSurveyResponses(@PathVariable Long id) {
    List<SurveyResponseDTO> responses = surveyService.getSurveyResponses(id);
    return ResponseEntity.ok(responses);
}
}
