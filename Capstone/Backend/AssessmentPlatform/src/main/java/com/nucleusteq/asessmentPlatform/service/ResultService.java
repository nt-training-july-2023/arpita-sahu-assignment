package com.nucleusteq.asessmentPlatform.service;

import java.util.List;

import com.nucleusteq.asessmentPlatform.dto.ResultDto;

/**
 * This interface defines the contract for managing results.
 */
public interface ResultService {

    /**
     * Adds a new result.
     *
     * @param resultDto The ResultDto object containing the result data to be
     *                  added.
     * @return The ResultDto object representing the added result.
     */
    ResultDto addResult(ResultDto resultDto);

    /**
     * Retrieves a list of all results.
     *
     * @return A list of ResultDto objects representing all results.
     */
    List<ResultDto> getAllResults();

    /**
     * Retrieves a list of results for a given email.
     *
     * @param email The email address for which results are to be retrieved.
     * @return A list of ResultDto objects representing results for the given
     *         email.
     */
    List<ResultDto> getResultByEmail(String email);
}
