package com.nucleusteq.asessmentPlatform.controllers;

import java.util.List;

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
import com.nucleusteq.asessmentPlatform.exception.DuplicateResourceException;
import com.nucleusteq.asessmentPlatform.service.QuestionService;

@CrossOrigin
@RestController
@RequestMapping("/ques")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public final ResponseEntity<String> addQuestion(
            @RequestBody QuestionDto questionDto) {
        try {
            questionService.addQuestion(questionDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Question Added Successfully.");
        } catch (DuplicateResourceException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Question already exist");
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public final List<QuestionDto> getQuestions() {
        return questionService.getAllQuestions();
    }

    @RequestMapping(value = "/{quesId}", method = RequestMethod.GET)
    public final ResponseEntity<QuestionDto> getQuestionById(
            @PathVariable int quesId) {
        return new ResponseEntity<QuestionDto>(
                questionService.getQuestionById(quesId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{quesId}", method = RequestMethod.PUT)
    public final ResponseEntity<String> updateQuestion(
            @RequestBody QuestionDto questionDto, @PathVariable int quesId) {
        try {
            questionService.updateQuestion(questionDto, quesId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Question Updated Successfully.");
        } catch (DuplicateResourceException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Question already exist");
        }

    }

    @RequestMapping(value = "/{quesId}", method = RequestMethod.DELETE)
    public final String deleteQuestion(@PathVariable int quesId) {
        questionService.deleteQuestion(quesId);
        return " deleted Successfully";
    }

}
