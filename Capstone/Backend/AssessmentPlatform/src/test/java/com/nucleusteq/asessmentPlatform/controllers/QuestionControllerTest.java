package com.nucleusteq.asessmentPlatform.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nucleusteq.asessmentPlatform.dto.ApiResponse;
import com.nucleusteq.asessmentPlatform.dto.QuestionDto;
import com.nucleusteq.asessmentPlatform.service.QuestionService;
class QuestionControllerTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionController questionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testAddQuestion() {
        QuestionDto questionDto = new QuestionDto();
        when(questionService.addQuestion(questionDto)).thenReturn(questionDto);
        ApiResponse response = questionController
                .addQuestion(questionDto);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Question Added Successfully.", response.getMessage());
    }

    @Test
    public void testGetAllQuestion() {
        List<QuestionDto> expectedQuestions = new ArrayList<>();
        when(questionService.getAllQuestions()).thenReturn(expectedQuestions);
        List<QuestionDto> actualQuestions = questionController.getQuestions();
        assertEquals(expectedQuestions, actualQuestions);
    }

    @Test
    public void testGetQuestionById() {
        int quesId = 1;
        QuestionDto expectedQuestion = new QuestionDto();
        when(questionService.getQuestionById(quesId))
                .thenReturn(expectedQuestion);
        ResponseEntity<QuestionDto> response = questionController
                .getQuestionById(quesId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedQuestion, response.getBody());
    }
    
    @Test
    public void testGetQuestionByQuizId() {
        int quizId=1;
        List<QuestionDto> questionDtos = new ArrayList<>();
        when(questionService.getQuestionsByQuizId(quizId)).thenReturn(questionDtos);
        List<QuestionDto> result = questionController.getQuestionByQuiId(quizId);
        assertEquals(questionDtos, result);
    }

    @Test
    public void testUpdateQuestion() {
        int quesId = 1;
        QuestionDto existingQuestion = new QuestionDto();
        when(questionService.updateQuestion(existingQuestion, quesId))
                .thenReturn(existingQuestion);
        ApiResponse response = questionController
                .updateQuestion(existingQuestion, quesId);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Question Updated Successfully.", response.getMessage());

    }

    
    @Test
    public void testDeleteQuestion() {
        int quesid = 1;
        when(questionService.deleteQuestion(quesid))
                .thenReturn("Question deleted Successfully");
        ApiResponse result = questionController.deleteQuestion(quesid);
        assertEquals("Question deleted Successfully", result.getMessage());
        assertEquals(HttpStatus.OK.value(), result.getStatus());
    }

}
