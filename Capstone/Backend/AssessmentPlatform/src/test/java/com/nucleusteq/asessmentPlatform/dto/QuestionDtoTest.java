package com.nucleusteq.asessmentPlatform.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class QuestionDtoTest {

    QuestionDto questionDto;

    @BeforeEach
    void init() {
        questionDto = new QuestionDto();
    }
    
    @Test
    void testGettersAndSetters() {
        
        assertEquals(0, questionDto.getQuesId());
        assertEquals(null, questionDto.getQuestion());
        assertEquals(null, questionDto.getOption1());
        assertEquals(null, questionDto.getOption2());
        assertEquals(null, questionDto.getOption3());
        assertEquals(null, questionDto.getOption4());
        assertEquals(null, questionDto.getAnswer());
        assertEquals(0, questionDto.getQuizId());
        
        questionDto.setQuesId(1);
        questionDto.setQuestion("what is java");
        questionDto.setOption1("a");
        questionDto.setOption2("b");
        questionDto.setOption3("c");
        questionDto.setOption4("d");
        questionDto.setAnswer("a");
        questionDto.setQuizId(1);

        assertEquals(1, questionDto.getQuesId());
        assertEquals("what is java", questionDto.getQuestion());
        assertEquals("a", questionDto.getOption1());
        assertEquals("b", questionDto.getOption2());
        assertEquals("c", questionDto.getOption3());
        assertEquals("d", questionDto.getOption4());
        assertEquals("a", questionDto.getAnswer());
        assertEquals(1, questionDto.getQuizId());
        
    }
    
    @Test
    void testDefaultConstructor() {
        assertEquals(0, questionDto.getQuesId());
        assertEquals(null, questionDto.getQuestion());
        assertEquals(null, questionDto.getOption1());
        assertEquals(null, questionDto.getOption2());
        assertEquals(null, questionDto.getOption3());
        assertEquals(null, questionDto.getOption4());
        assertEquals(null, questionDto.getAnswer());
        assertEquals(0, questionDto.getQuizId());
    }
    
    @Test
    void testParameterisedConstructor() {
        QuestionDto questionDto = new QuestionDto(1,"what is java","a","b","c","d","a",1);
        assertEquals(1, questionDto.getQuesId());
        assertEquals("what is java", questionDto.getQuestion());
        assertEquals("a", questionDto.getOption1());
        assertEquals("b", questionDto.getOption2());
        assertEquals("c", questionDto.getOption3());
        assertEquals("d", questionDto.getOption4());
        assertEquals("a", questionDto.getAnswer());
        assertEquals(1, questionDto.getQuizId());
    }


}
