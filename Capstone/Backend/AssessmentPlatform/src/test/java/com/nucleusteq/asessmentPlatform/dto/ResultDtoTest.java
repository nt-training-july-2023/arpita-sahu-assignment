package com.nucleusteq.asessmentPlatform.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ResultDtoTest {
    
    ResultDto resultDto;

    @BeforeEach
    void init() {
       resultDto = new ResultDto(); 
    }
    
    @Test
    void testGettersAndSetters() {
        assertEquals(0, resultDto.getResultId());
        assertEquals(0, resultDto.getObtainedMarks());
        assertEquals(0, resultDto.getTotalMarks());
        assertEquals(null, resultDto.getUserName());
        assertEquals(null, resultDto.getEmail());
        assertEquals(null, resultDto.getQuizTitle());
        assertEquals(null, resultDto.getCategoryTitle());
        assertEquals(0, resultDto.getNumofAttemptedQues());
        assertEquals(0, resultDto.getTotalNumOfQues());
        assertEquals(null, resultDto.getDateAndTime());

        resultDto.setResultId(1);
        resultDto.setObtainedMarks(12);
        resultDto.setTotalMarks(20);
        resultDto.setUserName("Arpita");
        resultDto.setEmail("arpita@nucleusteq.com");
        resultDto.setQuizTitle("React Quiz");
        resultDto.setCategoryTitle("React Category");
        resultDto.setNumofAttemptedQues(12);
        resultDto.setTotalNumOfQues(20);
        resultDto.setDateAndTime("2023-09-22 15:30:00");

        assertEquals(1, resultDto.getResultId());
        assertEquals(12, resultDto.getObtainedMarks());
        assertEquals(20, resultDto.getTotalMarks());
        assertEquals("Arpita", resultDto.getUserName());
        assertEquals("arpita@nucleusteq.com", resultDto.getEmail());
        assertEquals("React Quiz", resultDto.getQuizTitle());
        assertEquals("React Category", resultDto.getCategoryTitle());
        assertEquals(12, resultDto.getNumofAttemptedQues());
        assertEquals(20, resultDto.getTotalNumOfQues());
        assertEquals("2023-09-22 15:30:00", resultDto.getDateAndTime());
    }
    
    @Test
    void testDefaultConstructor() {
        ResultDto resultDto = new ResultDto();
        assertEquals(0, resultDto.getResultId());
        assertEquals(0, resultDto.getObtainedMarks());
        assertEquals(0, resultDto.getTotalMarks());
        assertEquals(null, resultDto.getUserName());
        assertEquals(null, resultDto.getEmail());
        assertEquals(null, resultDto.getQuizTitle());
        assertEquals(null, resultDto.getCategoryTitle());
        assertEquals(0, resultDto.getNumofAttemptedQues());
        assertEquals(0, resultDto.getTotalNumOfQues());
        assertEquals(null, resultDto.getDateAndTime());
    }

    @Test
    void testParameterisedConstructor() {
        ResultDto resultDto = new ResultDto(1, 12, 20, "Arpita","arpita@nucleusteq.com","React Quiz","React Category", 12, 20,"2023-09-22 15:30:00");
        assertEquals(1, resultDto.getResultId());
        assertEquals(12, resultDto.getObtainedMarks());
        assertEquals(20, resultDto.getTotalMarks());
        assertEquals("Arpita", resultDto.getUserName());
        assertEquals("arpita@nucleusteq.com", resultDto.getEmail());
        assertEquals("React Quiz", resultDto.getQuizTitle());
        assertEquals("React Category", resultDto.getCategoryTitle());
        assertEquals(12, resultDto.getNumofAttemptedQues());
        assertEquals(20, resultDto.getTotalNumOfQues());
        assertEquals("2023-09-22 15:30:00", resultDto.getDateAndTime());

    }


}
