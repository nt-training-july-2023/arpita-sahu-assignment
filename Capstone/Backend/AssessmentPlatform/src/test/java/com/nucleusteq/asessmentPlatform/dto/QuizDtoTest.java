package com.nucleusteq.asessmentPlatform.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuizDtoTest {

    QuizDto quizDto;

    @BeforeEach
    void init() {
        quizDto = new QuizDto();
    }

    @Test
    void testGettersAndSetters() {

        assertEquals(0, quizDto.getQuizId());
        assertEquals(null,quizDto.getTitle());
        assertEquals(null, quizDto.getDescription());
        assertEquals(0, quizDto.getQuizTimer());

        quizDto.setQuizId(1);
        quizDto.setTitle("Quiz1");
        quizDto.setDescription("Quiz1 based on category Java");
        quizDto.setQuizTimer(45);

        assertEquals(1, quizDto.getQuizId());
        assertEquals("Quiz1", quizDto.getTitle());
        assertEquals("Quiz1 based on category Java", quizDto.getDescription());
        assertEquals(45, quizDto.getQuizTimer());
       
    }
    
    @Test
    void testCategoryGettersAndSetters() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setTitle("Java");
        categoryDto.setDescription("Java Category");

        QuizDto quizDto = new QuizDto();
        quizDto.setCategory(categoryDto);

        CategoryDto newCategoryDto = quizDto.getCategory();
        assertEquals(1, newCategoryDto.getCategoryId());
        assertEquals("Java", newCategoryDto.getTitle());
        assertEquals("Java Category", newCategoryDto.getDescription());
    }

    @Test
    void testDefaultConstructor() {
        assertEquals(0, quizDto.getQuizId());
        assertEquals(null, quizDto.getDescription());
        assertEquals(null, quizDto.getDescription());
        assertEquals(0, quizDto.getQuizTimer());
       

    }

    @Test
    void testParameterisedConstructor() {
        CategoryDto categoryDto = new CategoryDto(1, "java", "Java based mcq");
        QuizDto quizDto = new QuizDto(1, "Quiz1",
                "Quiz1 based on category Java", 45, categoryDto);
        assertEquals(1, quizDto.getQuizId());
        assertEquals("Quiz1", quizDto.getTitle());
        assertEquals("Quiz1 based on category Java", quizDto.getDescription());
        assertEquals(45, quizDto.getQuizTimer());

    }
}
