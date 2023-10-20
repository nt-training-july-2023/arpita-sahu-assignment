package com.nucleusteq.asessmentPlatform.services.impl;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import static org.mockito.ArgumentMatchers.any;
import com.nucleusteq.asessmentPlatform.dto.CategoryDto;
import com.nucleusteq.asessmentPlatform.dto.QuizDto;
import com.nucleusteq.asessmentPlatform.entities.Category;
import com.nucleusteq.asessmentPlatform.entities.Quiz;
import com.nucleusteq.asessmentPlatform.exception.BadCredentialsException;
import com.nucleusteq.asessmentPlatform.exception.DuplicateResourceException;
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
        quizDto.setTitle("Test Quiz");
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        Category category = new Category();
        category.setCategoryId(1);
        quizDto.setCategory(categoryDto);
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setCategory(category);
        when(modelMapper.map(quizDto, Quiz.class)).thenReturn(quiz);
        when(modelMapper.map(quiz, QuizDto.class)).thenReturn(quizDto);
        when(quizRepo.findByTitle(quizDto.getTitle())).thenReturn(Optional.empty());
        when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
    }

    @Test
    public void testAddQuiz_DuplicateTitle() {
        QuizDto quizDto = new QuizDto();
        quizDto.setTitle("Test Title");
        
        Quiz quiz = new Quiz();
        quiz.setTitle("Test Title");
        when(modelMapper.map(quizDto, Quiz.class)).thenReturn(quiz);
        when(quizRepo.findByTitle(quiz.getTitle())).thenReturn(Optional.of(quiz));
        DuplicateResourceException exception = assertThrows(DuplicateResourceException.class, ()->{quizService.addQuiz(quizDto);});
        assertEquals("Quiz with title 'Test Title' already exists.", exception.getMessage());
    }

    @Test
    public void testAddQuiz_CategoryNotFound() {
        QuizDto quizDto = new QuizDto();
        quizDto.setTitle("Test Title");
        Category category = new Category();
        category.setCategoryId(1);
        Quiz quiz = new Quiz();
        quiz.setCategory(category);
        when(quizRepo.findByTitle(quizDto.getTitle())).thenReturn(Optional.empty());
        when(modelMapper.map(quizDto, Quiz.class)).thenReturn(quiz);
        when(categoryRepo.findById(quiz.getCategory().getCategoryId())).thenReturn(Optional.empty());
        BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> {
            quizService.addQuiz(quizDto);
        });
        assertEquals("Category not found", exception.getMessage());
    }
    
    @Test
    public void testGetQuizByCategoryId() {
        int categoryId = 1;
        Category category = new Category();
        category.setCategoryId(categoryId);
        List<Quiz> ListQuizzes = new ArrayList<>();
        Quiz quiz1 = new Quiz();
        quiz1.setQuizId(1);
        quiz1.setTitle("Quiz 1");
        quiz1.setCategory(new Category());
        quiz1.getCategory().setCategoryId(categoryId);
        QuizDto quizDto=new QuizDto();
        quizDto.setTitle(quiz1.getTitle());
        quizDto.setCategory(new CategoryDto());
        ListQuizzes.add(quiz1);
        when(modelMapper.map(quiz1, QuizDto.class)).thenReturn(quizDto);
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
        when(quizRepo.findQuizByCategoryId(categoryId)).thenReturn(ListQuizzes);
        List<QuizDto> quizDtos = quizService.getQuizByCategoryId(categoryId);
        assertNotNull(quizDtos);
        assertEquals(1, quizDtos.size());
        assertEquals("Quiz 1", quizDtos.get(0).getTitle());
    }
    @Test
    public void testGetQuizByCategoryIdCategoryNotFound() {
        int categoryId = 1;
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            quizService.getQuizByCategoryId(categoryId);
        });
    }

    @Test
    public void testGetAllQuiz_Success() {
        List<Quiz> quizList = new ArrayList<>();

        quizList.add(new Quiz(1,"Quiz Title","Quiz Description",45));
        quizList.add(new Quiz(2,"Quiz Title2","Quiz Description2",50));
        List<QuizDto> expectedQuizDtos = quizList.stream()
                .map(quizz -> quizService.quizToDto(quizz))
                .collect(Collectors.toList());
        when(quizRepo.findAll()).thenReturn(quizList);
        List<QuizDto> actualQuizDtos = quizService.getAllQuiz();
        assertNotNull(actualQuizDtos);
        assertEquals(expectedQuizDtos, actualQuizDtos);
    }

    @Test
    public void testGetQuizById_Success() {
        int quizId = 1;
        QuizDto quizDto = new QuizDto();
        quizDto.setQuizId(quizId);
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
        int quizId=1;
        QuizDto updatedQuizDto = new QuizDto();
        updatedQuizDto.setTitle("Updated Quiz");
        updatedQuizDto.setDescription("Updated Description");

        Quiz existingQuiz = new Quiz();
        existingQuiz.setQuizId(quizId);
        existingQuiz.setTitle("Existing Quiz Title");
        existingQuiz.setDescription("Existing Quiz Description");
        when(quizRepo.findById(quizId)).thenReturn(Optional.of(existingQuiz));
        when(quizRepo.findByTitle("Updated Quiz")).thenReturn(Optional.empty());
        when(modelMapper.map(updatedQuizDto, Quiz.class))
                .thenReturn(existingQuiz);
        when(quizRepo.save(any(Quiz.class))).thenReturn(existingQuiz);
        String result = quizService.updateQuiz(updatedQuizDto, quizId);

        assertEquals("Quiz Updated Successfully.", result);
        assertEquals("Existing Quiz Title", existingQuiz.getTitle());
        assertEquals("Existing Quiz Description", existingQuiz.getDescription());
    }

    @Test
    public void testDeleteQuiz_Success() {
        int quizIdToDelete = 1;
        Quiz quizToDelete = new Quiz();
        quizToDelete.setQuizId(quizIdToDelete);
        when(quizRepo.findById(quizIdToDelete))
                .thenReturn(Optional.of(quizToDelete));
        String result = quizService.deleteQuiz(quizIdToDelete);
        assertEquals("Quiz deleted successfully", result);
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
    public void testUpdateQuiz_DuplicateTitle() {
        int quizId=1;
        Quiz quiz = new Quiz();
        quiz.setTitle("Updated Title");

        Quiz existingQuiz = new Quiz();;
        existingQuiz.setTitle("Existing Quiz");
        
        QuizDto quizDto = new QuizDto();
        quizDto.setTitle("Existing Quiz");
        when(modelMapper.map(quizDto, Quiz.class)).thenReturn(existingQuiz);
        when(quizRepo.findById(quizId)).thenReturn(Optional.of(quiz));
        when(quizRepo.findByTitle(quizDto.getTitle())).thenReturn(Optional.of(existingQuiz));
        DuplicateResourceException exception = assertThrows(DuplicateResourceException.class, () -> {
            quizService.updateQuiz(quizDto, quizId);
        });
        assertEquals("Quiz with title 'Existing Quiz' already exists.", exception.getMessage());
    }
    
    @Test
    public void testUpdateQuiz_ResourceNotFound() {
        int quizId=1;
        QuizDto quizDto = new QuizDto();
        quizDto.setTitle("Test Title");
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        when(modelMapper.map(quizDto, Quiz.class)).thenReturn(quiz);
        when(quizRepo.findById(quizId)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> quizService.updateQuiz(quizDto, quizId));
        assertEquals("Quiz not found with ID " + quizId,
                exception.getMessage());
    }

    @Test
    public void testDeleteQuiz_QuizNotFound() {
        int quizIdToDelete = 1;
        when(quizRepo.findById(quizIdToDelete)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> quizService.deleteQuiz(quizIdToDelete));

        assertEquals("Quiz not found with ID " + quizIdToDelete,
                exception.getMessage());
    }

}
