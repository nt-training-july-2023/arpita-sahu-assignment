package com.nucleusteq.asessmentPlatform.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import com.nucleusteq.asessmentPlatform.dto.ResultDto;
import com.nucleusteq.asessmentPlatform.entities.Result;
import com.nucleusteq.asessmentPlatform.repositories.ResultRepo;

class ResultServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ResultRepo resultRepo;

    @InjectMocks
    private ResultServiceImpl resultService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddResult() {
        ResultDto resultDto = new ResultDto();
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

        Result result = new Result();
        result.setResultId(resultDto.getResultId());
        result.setObtainedMarks(result.getObtainedMarks());
        result.setTotalMarks(resultDto.getTotalMarks());
        result.setUserName(resultDto.getUserName());
        result.setEmail(resultDto.getEmail());
        result.setQuizTitle(resultDto.getQuizTitle());
        result.setCategoryTitle(resultDto.getCategoryTitle());
        result.setNumofAttemptedQues(resultDto.getNumofAttemptedQues());
        result.setTotalNumOfQues(resultDto.getTotalNumOfQues());
        result.setDateAndTime(resultDto.getDateAndTime());

        when(modelMapper.map(result, ResultDto.class)).thenReturn(resultDto);
        when(modelMapper.map(resultDto, Result.class)).thenReturn(result);
        when(resultRepo.save(result)).thenReturn(result);

        ResultDto finalResultDto = resultService.addResult(resultDto);
        assertEquals(finalResultDto, resultDto);
    }

    @Test
    public void testGetAllResults() {
        List<Result> results = new ArrayList<>();
        results.add(new Result(1, 12, 20, "Arpita", "arpita@nucleusteq.com",
                "React Quiz", "React Category", 12, 20, "2023-09-22 15:30:00"));
        List<ResultDto> expectedResultDtos = results.stream()
                .map(result -> resultService.resultToDto(result))
                .collect(Collectors.toList());
        when(resultRepo.findAll()).thenReturn(results);
        List<ResultDto> actualResultDtos = resultService.getAllResults();
        assertEquals(expectedResultDtos, actualResultDtos);
    }

    @Test
    public void testGetResultByEmail() {
        String email = "arpita@nucleusteq.com";
        List<Result> resultList = new ArrayList<>();
        ResultDto resultDto = new ResultDto();
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

        Result result = new Result();
        result.setResultId(resultDto.getResultId());
        result.setObtainedMarks(result.getObtainedMarks());
        result.setTotalMarks(resultDto.getTotalMarks());
        result.setUserName(resultDto.getUserName());
        result.setEmail(resultDto.getEmail());
        result.setQuizTitle(resultDto.getQuizTitle());
        result.setCategoryTitle(resultDto.getCategoryTitle());
        result.setNumofAttemptedQues(resultDto.getNumofAttemptedQues());
        result.setTotalNumOfQues(resultDto.getTotalNumOfQues());
        result.setDateAndTime(resultDto.getDateAndTime());

        resultList.add(result);

        when(modelMapper.map(result, ResultDto.class)).thenReturn(resultDto);
        when(resultRepo.findResultByEmail(email)).thenReturn(resultList);
        List<ResultDto> resultDtos = resultService.getResultByEmail(email);
        assertNotNull(resultDtos);
        assertEquals(1, resultDtos.size());
    }

}
