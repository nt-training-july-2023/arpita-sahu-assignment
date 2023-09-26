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
import com.nucleusteq.asessmentPlatform.dto.QuestionDto;
import com.nucleusteq.asessmentPlatform.service.QuestionService;

import jakarta.validation.Valid;

/**
 * Controller for managing questions.
 */
@CrossOrigin
@RestController
@RequestMapping("/ques")
public class QuestionController {

    /**
     * The service responsible for managing question-related operations.
     */
    @Autowired
    private QuestionService questionService;
    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    /**
     * Adds a new question.
     *
     * @param questionDto The QuestionDto containing question details to be
     *                    added.
     * @return A ResponseEntity with a success message if the question is added
     *         successfully.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public final ResponseEntity<String> addQuestion(
            @RequestBody @Valid final QuestionDto questionDto) {
//        try {
//            questionService.addQuestion(questionDto);
//            logger.info("Question Added Successfully");
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body("Question Added Successfully.");
//        } catch (BadCredentialsException ex) {
//            logger.error("Question already exist");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Question must not be empty");
//        }
        questionService.addQuestion(questionDto);
        logger.info("Question Added Successfully");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Question Added Successfully.");
    }

    /**
     * Retrieves all questions.
     *
     * @return A list of QuestionDtos containing all questions.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public final List<QuestionDto> getQuestions() {
        logger.info("Get All Questions");
        return questionService.getAllQuestions();
    }

    /**
     * Retrieves a question by its ID.
     *
     * @param quesId The ID of the question to retrieve.
     * @return A ResponseEntity with the QuestionDto representing the retrieved
     *         question.
     */
    @RequestMapping(value = "/{quesId}", method = RequestMethod.GET)
    public final ResponseEntity<QuestionDto> getQuestionById(
            @PathVariable final int quesId) {
        logger.info("Get Question by Question Id");
        return new ResponseEntity<QuestionDto>(
                questionService.getQuestionById(quesId), HttpStatus.OK);
    }

    /**
     * Retrieves a list of questions by quiz ID.
     * @param quizId The unique identifier of the quiz for which questions are
     *               to be retrieved.
     * @return A list of QuestionDto objects representing questions associated
     *         with the specified quiz ID.
     */
    @RequestMapping(value = "quiz/{quizId}", method = RequestMethod.GET)
    public final List<QuestionDto> getQuestionByQuiId(
            @PathVariable final int quizId) {
        logger.info("Get Question by Quiz Id");
        return questionService.getQuestionsByQuizId(quizId);
    }

    /**
     * Updates a question with the given ID.
     *
     * @param questionDto The updated QuestionDto.
     * @param quesId      The ID of the question to update.
     * @return A ResponseEntity with a success message if the question is
     *         updated successfully.
     */
    @RequestMapping(value = "/{quesId}", method = RequestMethod.PUT)
    public final ResponseEntity<String> updateQuestion(
            @RequestBody @Valid final QuestionDto questionDto,
            @PathVariable final int quesId) {
//        try {
//            questionService.updateQuestion(questionDto, quesId);
//            logger.info("Question Updated Successfully.");
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body("Question Updated Successfully.");
//        } catch (BadCredentialsException ex) {
//            logger.error("An error occurred");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("An error occurred.");
//        }
        questionService.updateQuestion(questionDto, quesId);
        logger.info("Question Updated Successfully.");
        return ResponseEntity.status(HttpStatus.OK)
                .body("Question Updated Successfully.");
    }

    /**
     * Deletes a question by its ID.
     *
     * @param quesId The ID of the question to delete.
     * @return A success message indicating the deletion.
     */
    @RequestMapping(value = "/{quesId}", method = RequestMethod.DELETE)
    public final String deleteQuestion(@PathVariable final int quesId) {
        questionService.deleteQuestion(quesId);
        logger.info("Question deleted");
        return "Question deleted Successfully";
    }
}
