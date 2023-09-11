package com.nucleusteq.asessmentPlatform.services.impl;

import static org.mockito.ArgumentMatchers.any;
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

import com.nucleusteq.asessmentPlatform.dto.CategoryDto;
import com.nucleusteq.asessmentPlatform.dto.QuizDto;
import com.nucleusteq.asessmentPlatform.entities.Category;
import com.nucleusteq.asessmentPlatform.entities.Quiz;
import com.nucleusteq.asessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.asessmentPlatform.repositories.CategoryRepo;
import com.nucleusteq.asessmentPlatform.repositories.QuizRepo;

class QuizServiceImplTest {

    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private CategoryRepo categoryRepo;

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
        CategoryDto category = new CategoryDto();
        category.setCategoryId(1);
        category.setTitle("Category");
        category.setDescription("Category Description");
        
        Category category2 = new Category();
        category2.setCategoryId(category.getCategoryId());
        category2.setTitle(category.getTitle());
        quizDto.setTitle("Sample Quiz Title");
        
        Quiz quiz = new Quiz();
        quiz.setTitle("Sample Quiz Title");
        quiz.setCategory(category2);
        
        when(quizRepo.findByTitle(quiz.getTitle())).thenReturn(Optional.empty());
        when(categoryRepo.findById(category2.getCategoryId())).thenReturn(Optional.of(category2));
        quizDto.setCategory(category);
        when(modelMapper.map(quizDto, Quiz.class)).thenReturn(quiz);
        String result = quizService.addQuiz(quizDto);
        assertEquals(" Quiz Added Successfully", result); 

    }

    @Test
    public void testAddQuiz_DuplicateTitle() {
        QuizDto quizDto = new QuizDto();
        Quiz quiz = new Quiz();
        when(quizRepo.findByTitle(quiz.getTitle())).thenReturn(Optional.of(quiz));
        assertThrows(NullPointerException.class, () -> {quizService.addQuiz(quizDto);});
    }

    @Test
    public void testAddQuiz_CategoryNotFound() {
        QuizDto quizDto = new QuizDto();
        when(quizRepo.findByTitle(quizDto.getTitle())).thenReturn(Optional.empty());
        when(categoryRepo.findById(any())).thenReturn(Optional.empty()); 
        assertThrows(NullPointerException.class, () -> {quizService.addQuiz(quizDto);});
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

        Quiz existingQuiz = new Quiz();
        when(quizRepo.findById(1)).thenReturn(Optional.of(existingQuiz));
        when(modelMapper.map(updatedQuizDto, Quiz.class))
                .thenReturn(existingQuiz);
        when(quizRepo.save(existingQuiz)).thenReturn(existingQuiz);
        String result = quizService.updateQuiz(updatedQuizDto, 1);

        assertEquals(" Quiz Updated Successfully", result);
        assertEquals("Updated Quiz", existingQuiz.getTitle());
        assertEquals("Updated Description", existingQuiz.getDescription());
    }

    @Test
    public void testDeleteQuiz_Success() {
        int quizIdToDelete = 1;
        Quiz quizToDelete = new Quiz();
        quizToDelete.getQuizId();
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
        assertThrows(ResourceNotFoundException.class, () -> { quizService.updateQuiz(new QuizDto(), 1);});
    }

    @Test
    public void testDeleteQuiz_QuizNotFound() {
        int quizIdToDelete = 1;
        when(quizRepo.findById(quizIdToDelete)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> quizService.deleteQuiz(quizIdToDelete));

        assertEquals("Quiz not found with id " + quizIdToDelete,
                exception.getMessage());
    }

}
