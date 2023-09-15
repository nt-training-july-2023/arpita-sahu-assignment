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

import com.nucleusteq.asessmentPlatform.dto.QuizDto;
import com.nucleusteq.asessmentPlatform.service.QuizService;

class QuizControllerTest {

    @Mock
    private QuizService quizService;

    @InjectMocks
    private QuizController quizController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddQuiz() {
        QuizDto quizDto = new QuizDto();
        when(quizService.addQuiz(quizDto))
                .thenReturn(quizDto);
        ResponseEntity<String> response = quizController.addQuiz(quizDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Quiz Added Successfully.", response.getBody());

    }

    @Test
    public void testGetQuizzes() {
        List<QuizDto> quizDtoList = new ArrayList<>();
        when(quizService.getAllQuiz()).thenReturn(quizDtoList);
        List<QuizDto> result = quizController.getQuizzes();
        assertEquals(quizDtoList, result);
    }

    @Test
    public void testGetQuizById() {
        int quizId = 1;
        QuizDto quizDto = new QuizDto();
        when(quizService.getQuizById(quizId)).thenReturn(quizDto);
        ResponseEntity<QuizDto> response = quizController.getQuizById(quizId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(quizDto, response.getBody());
    }

    @Test
    public void testUpdateQuiz() {
        int quizId = 1;
        QuizDto quizDto = new QuizDto();
        when(quizService.updateQuiz(quizDto, quizId))
                .thenReturn("Updated Successfully");
        ResponseEntity<String> result = quizController.updateQuiz(quizId,
                quizDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Quiz Updated Successfully.", result.getBody());
    }

    @Test
    public void testDeleteQuiz() {
        int quizId = 1;
        when(quizService.deleteQuiz(quizId))
                .thenReturn("Quiz deleted successfully");
        String result = quizController.deleteCategory(quizId);
        assertEquals("Quiz deleted successfully", result);
    }

}
