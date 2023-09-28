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

import com.nucleusteq.asessmentPlatform.dto.QuizDto;
import com.nucleusteq.asessmentPlatform.service.QuizService;

import jakarta.validation.Valid;

/**
 * Controller class for managing quizzes.
 */
@CrossOrigin
@RestController
@RequestMapping("/quiz")
public class QuizController {

    /**
     * The service responsible for managing quiz-related operations.
     */
    @Autowired
    private QuizService quizService;
    /**
     * The logger instance for logging messages related to QuizController.
     */
    private Logger logger = LoggerFactory.getLogger(QuizController.class);

    /**
     * Create a new quiz.
     *
     * @param quizDto The DTO representing the quiz to be created.
     * @return A ResponseEntity containing the created QuizDto and HTTP status
     *         201 (Created).
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public final ResponseEntity<String> addQuiz(
            @RequestBody @Valid final QuizDto quizDto) {
        quizService.addQuiz(quizDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Quiz Added Successfully.");
    }

    /**
     * Get a list of all quizzes.
     *
     * @return A list of QuizDto objects representing all available quizzes.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public final List<QuizDto> getQuizzes() {
        logger.info("Get All Quizzes");
        return quizService.getAllQuiz();
    }

    /**
     * Get a quiz by its ID.
     *
     * @param quizId The ID of the quiz to retrieve.
     * @return A ResponseEntity containing the QuizDto of the retrieved quiz and
     *         HTTP status 200 (OK).
     */
    @RequestMapping(value = "/{quizId}", method = RequestMethod.GET)
    public final ResponseEntity<QuizDto> getQuizById(
            @PathVariable final int quizId) {
        logger.info("Get Quiz By Quiz Id");
        return new ResponseEntity<QuizDto>(quizService.getQuizById(quizId),
                HttpStatus.OK);
    }

    /**
     * Retrieves a list of quizzes by their category ID.
     *
     * @param categoryId The ID of the category for which quizzes should be
     *                   retrieved.
     * @return A list of QuizDto objects representing quizzes that belong to the
     *         specified category.
     */
    @RequestMapping(value = "category/{categoryId}", method = RequestMethod.GET)
    public final List<QuizDto> getQuizByCategoryId(
            @PathVariable final int categoryId) {
        logger.info("Get Quiz By Category Id");
        return quizService.getQuizByCategoryId(categoryId);
    }

    /**
     * Update an existing quiz by its ID.
     *
     * @param quizId  The ID of the quiz to update.
     * @param quizDto The DTO representing the updated quiz.
     * @return A ResponseEntity containing the successfully updating the quiz.
     */
    @RequestMapping(value = "/{quizId}", method = RequestMethod.PUT)
    public final ResponseEntity<String> updateQuiz(
            @PathVariable @Valid final int quizId,
            @RequestBody final QuizDto quizDto) {
        quizService.updateQuiz(quizDto, quizId);
        logger.info("Quiz Updated Successfully.");
        return ResponseEntity.status(HttpStatus.OK)
                .body("Quiz Updated Successfully.");
    }

    /**
     * Delete a quiz by its ID.
     *
     * @param quizId The ID of the quiz to delete.
     * @return A message indicating the successful deletion of the quiz.
     */
    @RequestMapping(value = "/{quizId}", method = RequestMethod.DELETE)
    public final String deleteCategory(@PathVariable final int quizId) {
        logger.info("Quiz deleted Sucessfully");
        return quizService.deleteQuiz(quizId);
    }

}
