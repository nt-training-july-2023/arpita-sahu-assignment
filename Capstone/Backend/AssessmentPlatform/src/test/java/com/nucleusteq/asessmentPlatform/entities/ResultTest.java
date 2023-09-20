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
        assertEquals(0, result.getTotalObtainedMarks());

        result.setResultId(1);
        result.setTotalObtainedMarks(380);

        assertEquals(1, result.getResultId());
        assertEquals(380, result.getTotalObtainedMarks());

    }

    @Test
    void testDefaultConstructor() {
        Result result = new Result();
        assertEquals(0, result.getResultId());
        assertEquals(0, result.getTotalObtainedMarks());
    }

    @Test
    void testParameterisedConstructor() {
        Result result = new Result(1, 380);
        assertEquals(1, result.getResultId());
        assertEquals(380, result.getTotalObtainedMarks());

    }

}
