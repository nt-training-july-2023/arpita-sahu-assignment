package com.nucleusteq.asessmentPlatform.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nucleusteq.asessmentPlatform.dto.ResultDto;
import com.nucleusteq.asessmentPlatform.entities.Result;
import com.nucleusteq.asessmentPlatform.repositories.ResultRepo;
import com.nucleusteq.asessmentPlatform.service.ResultService;
import com.nucleusteq.assessmentPlatform.validationmessage.LoggerMessage;

/**
 * Service class for managing results.
 * It uses a logger to log messages related to its functionality.
 */
@Service
public class ResultServiceImpl implements ResultService {

    /**
     * Autowired instance of ModelMapper for mapping between entities and DTOs.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Autowired instance of ResultRepo for database operations.
     */
    @Autowired
    private ResultRepo resultRepo;
    /**
     * The logger instance for logging messages related to ResultServiceImpl.
     */
    private Logger logger = LoggerFactory.getLogger(ResultServiceImpl.class);

    /**
     * Adds a new result.
     * @param resultDto The ResultDto object containing the result data to be
     *                  added.
     * @return The ResultDto object representing the added result.
     */
    @Override
    public final ResultDto addResult(final ResultDto resultDto) {
        Result result = dtoToResult(resultDto);
        resultRepo.save(result);
        logger.info(LoggerMessage.SAVE_RESULT);
        return resultToDto(result);
    }

    /**
     * Retrieves a list of all results.
     *
     * @return A list of ResultDto objects representing all results.
     */
    @Override
    public final List<ResultDto> getAllResults() {
        List<Result> results = resultRepo.findAll();
        List<ResultDto> resultDtos = results.stream()
                .map(result -> this.resultToDto(result))
                .collect(Collectors.toList());
        logger.info(LoggerMessage.GET_RESULT);
        return resultDtos;
    }

    /**
     * Retrieves a list of results for a given email.
     * @param email The email address for which results are to be retrieved.
     * @return A list of ResultDto objects representing results for the given
     *         email.
     */
    @Override
    public final List<ResultDto> getResultByEmail(final String email) {
        List<Result> results = resultRepo.findResultByEmail(email);
        List<ResultDto> resultDtos = results.stream()
                .map(result -> this.resultToDto(result))
                .collect(Collectors.toList());
        logger.info(LoggerMessage.GET_RESULT_BY_EMAIL);
        return resultDtos;
    }

    /**
     * Converts a Result object to a ResultDto object.
     *
     * @param result The Result object to be converted.
     * @return The corresponding ResultDto object.
     */
    public final ResultDto resultToDto(final Result result) {
        return modelMapper.map(result, ResultDto.class);
    }

    /**
     * Converts a ResultDto object to a Result object.
     *
     * @param resultDto The ResultDto object to be converted.
     * @return The corresponding Result object.
     */
    public final Result dtoToResult(final ResultDto resultDto) {
        return modelMapper.map(resultDto, Result.class);
    }
}
