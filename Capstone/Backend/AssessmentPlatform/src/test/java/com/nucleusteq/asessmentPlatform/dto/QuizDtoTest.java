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
          CategoryDto categoryDto = new CategoryDto(); 
          
          assertEquals(0, quizDto.getQuizId());
          assertEquals(null, quizDto.getDescription());
          assertEquals(null, quizDto.getDescription());
          assertEquals(0, quizDto.getQuizTimer());
          assertEquals(null, quizDto.getCategory());

          quizDto.setQuizId(1);
          quizDto.setTitle("Quiz1");
          quizDto.setDescription("Quiz1 based on category Java");
          quizDto.setQuizTimer(45);
          quizDto.setCategory(categoryDto);

          assertEquals(1, quizDto.getQuizId());
          assertEquals("Quiz1", quizDto.getTitle());
          assertEquals("Quiz1 based on category Java", quizDto.getDescription());
          assertEquals(45, quizDto.getQuizTimer());
          assertEquals(categoryDto, quizDto.getCategory()); 
      }
      
      @Test
      void testDefaultConstructor() {  
          assertEquals(0, quizDto.getQuizId());
          assertEquals(null, quizDto.getDescription());
          assertEquals(null, quizDto.getDescription());
          assertEquals(0, quizDto.getQuizTimer());
          assertEquals(null, quizDto.getCategory());
          
      }
      
      @Test
      void testParameterisedConstructor() {
          CategoryDto categoryDto = new CategoryDto(1, "java","Java based mcq");
          QuizDto quizDto = new QuizDto(1, "Quiz1", "Quiz1 based on category Java", 45, categoryDto);
          assertEquals(1, quizDto.getQuizId());
          assertEquals("Quiz1", quizDto.getTitle());
          assertEquals("Quiz1 based on category Java", quizDto.getDescription());
          assertEquals(45, quizDto.getQuizTimer());
          assertEquals(categoryDto, quizDto.getCategory()); 
          
      }
}
