package com.nucleusteq.asessmentPlatform.dto;

import static org.junit.jupiter.api.Assertions.*;
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
    
    @Test
    public void testIsAnswerValid() {
        questionDto.setOption1("Option1");
        questionDto.setOption2("Option2");
        questionDto.setOption3("Option3");
        questionDto.setOption4("Option4");
        questionDto.setAnswer("Option2");
        boolean isValid =  questionDto.isAnswerValid();
        assertTrue(isValid);
    }
    @Test
    public void testIsAnswerNotValid() {
        questionDto.setOption1("Option1");
        questionDto.setOption2("Option2");
        questionDto.setOption3("Option3");
        questionDto.setOption4("Option4");
        questionDto.setAnswer("Option5");
        boolean isValid =  questionDto.isAnswerValid();
        assertFalse(isValid);
    }


}
