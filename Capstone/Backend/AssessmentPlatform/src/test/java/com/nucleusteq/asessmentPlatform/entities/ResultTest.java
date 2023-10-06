package com.nucleusteq.asessmentPlatform.entities;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResultTest {

    Result result;

    @BeforeEach
    void init() {
        result = new Result();
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, result.getResultId());
        assertEquals(0, result.getObtainedMarks());
        assertEquals(0, result.getTotalMarks());
        assertEquals(null, result.getUserName());
        assertEquals(null, result.getEmail());
        assertEquals(null, result.getQuizTitle());
        assertEquals(null, result.getCategoryTitle());
        assertEquals(0, result.getNumofAttemptedQues());
        assertEquals(0, result.getTotalNumOfQues());
        assertEquals(null, result.getDateAndTime());

        result.setResultId(1);
        result.setObtainedMarks(12);
        result.setTotalMarks(20);
        result.setUserName("Arpita");
        result.setEmail("arpita@nucleusteq.com");
        result.setQuizTitle("React Quiz");
        result.setCategoryTitle("React Category");
        result.setNumofAttemptedQues(12);
        result.setTotalNumOfQues(20);
        result.setDateAndTime("2023-09-22 15:30:00");

        assertEquals(1, result.getResultId());
        assertEquals(12, result.getObtainedMarks());
        assertEquals(20, result.getTotalMarks());
        assertEquals("Arpita", result.getUserName());
        assertEquals("arpita@nucleusteq.com", result.getEmail());
        assertEquals("React Quiz", result.getQuizTitle());
        assertEquals("React Category", result.getCategoryTitle());
        assertEquals(12, result.getNumofAttemptedQues());
        assertEquals(20, result.getTotalNumOfQues());
        assertEquals("2023-09-22 15:30:00", result.getDateAndTime());

    }

    @Test
    void testDefaultConstructor() {
        Result result = new Result();
        assertEquals(0, result.getResultId());
        assertEquals(0, result.getObtainedMarks());
        assertEquals(0, result.getTotalMarks());
        assertEquals(null, result.getUserName());
        assertEquals(null, result.getEmail());
        assertEquals(null, result.getQuizTitle());
        assertEquals(null, result.getCategoryTitle());
        assertEquals(0, result.getNumofAttemptedQues());
        assertEquals(0, result.getTotalNumOfQues());
        assertEquals(null, result.getDateAndTime());
    }

    @Test
    void testParameterisedConstructor() {
        Result result = new Result(1, 12, 20, "Arpita","arpita@nucleusteq.com","React Quiz","React Category", 12, 20,"2023-09-22 15:30:00");
        assertEquals(1, result.getResultId());
        assertEquals(12, result.getObtainedMarks());
        assertEquals(20, result.getTotalMarks());
        assertEquals("Arpita", result.getUserName());
        assertEquals("arpita@nucleusteq.com", result.getEmail());
        assertEquals("React Quiz", result.getQuizTitle());
        assertEquals("React Category", result.getCategoryTitle());
        assertEquals(12, result.getNumofAttemptedQues());
        assertEquals(20, result.getTotalNumOfQues());
        assertEquals("2023-09-22 15:30:00", result.getDateAndTime());

    }

}
