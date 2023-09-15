package com.nucleusteq.asessmentPlatform.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionTest {

    Question question;

    @BeforeEach
    void init() {
        question = new Question();
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, question.getQuesId());
        assertEquals(null, question.getQuestion());
        assertEquals(null, question.getOption1());
        assertEquals(null, question.getOption2());
        assertEquals(null, question.getOption3());
        assertEquals(null, question.getOption4());
        assertEquals(null, question.getAnswer());

        question.setQuesId(1);
        question.setQuestion("what is java");
        question.setOption1("a");
        question.setOption2("b");
        question.setOption3("c");
        question.setOption4("d");
        question.setAnswer("a");

        assertEquals(1, question.getQuesId());
        assertEquals("what is java", question.getQuestion());
        assertEquals("a", question.getOption1());
        assertEquals("b", question.getOption2());
        assertEquals("c", question.getOption3());
        assertEquals("d", question.getOption4());
        assertEquals("a", question.getAnswer());

    }

    @Test
    void testDefaultConstructor() {
        Question question = new Question();
        assertEquals(0, question.getQuesId());
        assertEquals(null, question.getQuestion());
        assertEquals(null, question.getOption1());
        assertEquals(null, question.getOption2());
        assertEquals(null, question.getOption3());
        assertEquals(null, question.getOption4());
        assertEquals(null, question.getAnswer());
    }

//    @Test
//    void testParameterisedConstructor() {
//        Question question = new Question(1, "what is java", "a", "b", "c", "d",
//                "a");
//        assertEquals(1, question.getQuesId());
//        assertEquals("what is java", question.getQuestion());
//        assertEquals("a", question.getOption1());
//        assertEquals("b", question.getOption2());
//        assertEquals("c", question.getOption3());
//        assertEquals("d", question.getOption4());
//        assertEquals("a", question.getAnswer());
//    }

}
