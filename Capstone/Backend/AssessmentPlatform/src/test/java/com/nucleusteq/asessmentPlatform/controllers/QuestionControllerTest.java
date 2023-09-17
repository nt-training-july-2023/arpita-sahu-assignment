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

import com.nucleusteq.asessmentPlatform.dto.QuestionDto;
import com.nucleusteq.asessmentPlatform.exception.BadCredentialsException;
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
        ResponseEntity<String> response = questionController
                .addQuestion(questionDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Question Added Successfully.", response.getBody());
    }

    @Test
    public void testAddQuestionBadRequest() {
        QuestionDto questionDto = new QuestionDto();
        when(questionService.addQuestion(questionDto)).thenThrow(
                new BadCredentialsException("Question must not be empty"));
        ResponseEntity<String> response = questionController
                .addQuestion(questionDto);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Question must not be empty", response.getBody());
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
    public void testUpdateQuestion() {
        int quesId = 1;
        QuestionDto existingQuestion = new QuestionDto();
        when(questionService.updateQuestion(existingQuestion, quesId))
                .thenReturn(existingQuestion);
        ResponseEntity<String> response = questionController
                .updateQuestion(existingQuestion, quesId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Question Updated Successfully.", response.getBody());

    }

    @Test
    public void testUpdatedBadRequest() {
        int quesId = 1;
        QuestionDto questionDto = new QuestionDto();
        when(questionService.updateQuestion(questionDto, quesId))
                .thenThrow(new BadCredentialsException("An error occurred."));
        ResponseEntity<String> response = questionController
                .updateQuestion(questionDto, quesId);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("An error occurred.", response.getBody());
    }
    
    @Test
    public void testDeleteQuestion() {
        int quesid = 1;
        when(questionService.deleteQuestion(quesid))
                .thenReturn("Question deleted Successfully");
        String result = questionController.deleteQuestion(quesid);
        assertEquals("Question deleted Successfully", result);
    }

}
