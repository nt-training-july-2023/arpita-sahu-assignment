package com.nucleusteq.asessmentPlatform.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nucleusteq.asessmentPlatform.dto.ResultDto;
import com.nucleusteq.asessmentPlatform.service.ResultService;

/**
 * Controller class for managing results.
 */
@RestController
@CrossOrigin
@RequestMapping("/result")
public class ResultController {
    /**
     * The service responsible for managing result-related operations.
     */
    @Autowired
    private ResultService resultService;
    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    /**
     * Adds a new result.
     *
     * @param resultDto The ResultDto object containing the result data to be
     *                  added.
     * @return A ResponseEntity with a success message and HTTP status code 201
     *         (Created) on successful addition.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public final ResponseEntity<String> addResult(
            @RequestBody final ResultDto resultDto) {
        resultService.addResult(resultDto);
        logger.info("Result Added Successfully.");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Result Added Successfully.");
    }

    /**
     * Retrieves a list of all results.
     *
     * @return A list of ResultDto objects representing all results.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public final List<ResultDto> getAllResults() {
        logger.info("Get All Results");
        return resultService.getAllResults();
    }

    /**
     * Retrieves a list of results for a given email.
     *
     * @param email The email address for which results are to be retrieved.
     * @return A list of ResultDto objects representing results for the given
     *         email.
     */
    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public final List<ResultDto> getResultByEmail(
            @PathVariable final String email) {
        logger.info("Get Results by User email");
        return resultService.getResultByEmail(email);
    }
}
