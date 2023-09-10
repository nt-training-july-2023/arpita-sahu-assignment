package com.nucleusteq.asessmentPlatform.services.impl;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import com.nucleusteq.asessmentPlatform.dto.QuizDto;
import com.nucleusteq.asessmentPlatform.entities.Quiz;
import com.nucleusteq.asessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.asessmentPlatform.repositories.QuizRepo;

class QuizServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private QuizRepo quizRepo;

    @InjectMocks
    private QuizServiceImpl quizService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddQuiz_Success() {
        QuizDto quizDto = new QuizDto();
        quizDto.setTitle("Sample Quiz");
        quizDto.setDescription("Sample Description");

        Quiz newQuiz = new Quiz();
        newQuiz.setTitle(quizDto.getTitle());
        newQuiz.setDescription(quizDto.getDescription());

        when(quizRepo.findByTitle(newQuiz.getTitle()))
                .thenReturn(Optional.empty());
        when(modelMapper.map(quizDto, Quiz.class)).thenReturn(newQuiz);
//        when(quizRepo.save(any(Quiz.class)).thenReturn(newQuiz); 
        QuizDto resultDto = quizService.addQuiz(quizDto);
        assertNotNull(resultDto);
        assertEquals(resultDto.getTitle(), quizDto.getTitle());
    }

    @Test
    public void testGetAllQuiz_Success() {

        List<Quiz> quizList = new ArrayList<>();
        quizList.add(new Quiz());
        quizList.add(new Quiz());
        when(quizRepo.findAll()).thenReturn(quizList);
        List<QuizDto> quizDtos = quizService.getAllQuiz();
        assertNotNull(quizDtos);
        assertEquals(2, quizDtos.size());
    }

    @Test
    public void testGetQuizById_Success() {

        int quizId = 1;
        QuizDto quizDto = new QuizDto();
        quizDto.setQuizId(1);
        quizDto.setTitle("Quiz1");
        quizDto.setDescription("Quiz1 based on Java");

        Quiz quiz = new Quiz();
        quiz.setQuizId(quizDto.getQuizId());
        quiz.setTitle(quiz.getTitle());
        quiz.setDescription(quiz.getDescription());
        when(quizRepo.findById(quizId)).thenReturn(Optional.of(quiz));
        when(modelMapper.map(quiz, QuizDto.class)).thenReturn(quizDto);
        QuizDto resultDto = quizService.getQuizById(quizId);
        assertEquals(quizDto, resultDto);
    }

    @Test
    public void testUpdateQuiz_Success() {

        QuizDto updatedQuizDto = new QuizDto();
        updatedQuizDto.setTitle("Updated Quiz");
        updatedQuizDto.setDescription("Updated Description");

        Quiz updatedQuiz = new Quiz();
        when(quizRepo.findById(1)).thenReturn(Optional.of(updatedQuiz));
        when(modelMapper.map(updatedQuizDto, Quiz.class))
                .thenReturn(updatedQuiz);
        when(quizRepo.save(updatedQuiz)).thenReturn(updatedQuiz);
        QuizDto resultDto = quizService.updateQuiz(updatedQuizDto, 1);
        assertNotNull(resultDto);
        assertEquals(updatedQuizDto, resultDto);
    }

    @Test
    public void testDeleteQuiz_Success() {
        int quizIdToDelete = 1;
        Quiz quizToDelete = new Quiz(quizIdToDelete, "Quiz1",
                "Quiz1 based on Java Category", 45);
        when(quizRepo.findById(quizIdToDelete))
                .thenReturn(Optional.of(quizToDelete));
        String result = quizService.deleteQuiz(quizIdToDelete);
        assertEquals(quizIdToDelete + " deleted successfully", result);
    }

    @Test
    public void testGetQuizById_QuizNotFound() {

        int quizId = 1;
        when(quizRepo.findById(quizId)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> quizService.getQuizById(quizId));
        assertEquals("Quiz not found with id " + quizId,
                exception.getMessage());
    }

    @Test
    public void testUpdateQuiz_QuizNotFound() {
        when(quizRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            quizService.updateQuiz(new QuizDto(), 1);
        });
    }

    @Test
    public void testDeleteQuiz_QuizNotFound() {
        int quizIdToDelete = 1;
        when(quizRepo.findById(quizIdToDelete)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> quizService.deleteQuiz(quizIdToDelete));

        assertEquals("quiz not found with id " + quizIdToDelete,
                exception.getMessage());
    }

}
