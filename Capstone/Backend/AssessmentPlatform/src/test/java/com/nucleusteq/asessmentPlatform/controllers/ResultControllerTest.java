package com.nucleusteq.asessmentPlatform.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import com.nucleusteq.asessmentPlatform.dto.ResultDto;
import com.nucleusteq.asessmentPlatform.exception.ApiErrorResponse;
import com.nucleusteq.asessmentPlatform.service.ResultService;

class ResultControllerTest {

    @Mock
    private ResultService resultService;

    @InjectMocks
    private ResultController resultController;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddResult() {
        ResultDto resultDto = new ResultDto();
        when(resultService.addResult(resultDto)).thenReturn(resultDto);
        ApiErrorResponse response = resultController.addResult(resultDto);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Result Added Successfully.", response.getMessage());
    }

    @Test
    public void testGetAllResults() {
        List<ResultDto> expectedResults = new ArrayList<>();
        when(resultService.getAllResults()).thenReturn(expectedResults);
        List<ResultDto> actualResults = resultController.getAllResults();
        assertEquals(expectedResults, actualResults);
    }

    @Test
    public void testGetResultByEmail() {
        String email = "arpita@nucleusteq.com";
        List<ResultDto> resultDtos = new ArrayList<>();
        when(resultService.getResultByEmail(email)).thenReturn(resultDtos);
        List<ResultDto> result = resultController.getResultByEmail(email);
        assertEquals(resultDtos, result);
    }

}
