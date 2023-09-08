package com.nucleusteq.asessmentPlatform.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nucleusteq.asessmentPlatform.dto.QuizDto;
import com.nucleusteq.asessmentPlatform.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public final ResponseEntity<QuizDto> addQuiz(
            @RequestBody final QuizDto quizDto) {
        QuizDto quizDto2 = quizService.addQuiz(quizDto);
        return new ResponseEntity<QuizDto>(quizDto2, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public final List<QuizDto> getQuizzes() {
        return quizService.getAllQuiz();
    }

    @RequestMapping(value = "/{quizId}", method = RequestMethod.GET)
    public final ResponseEntity<QuizDto> getQuizById(
            @PathVariable final int quizId) {
        return new ResponseEntity<QuizDto>(quizService.getQuizById(quizId),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{quizId}", method = RequestMethod.PUT)
    public final QuizDto updateQuiz(@PathVariable final int quizId,
            @RequestBody final QuizDto quizDto) {
        return quizService.updateQuiz(quizDto, quizId);
    }

    @RequestMapping(value = "/{quizId}", method = RequestMethod.DELETE)
    public final String deleteCategory(@PathVariable final int quizId) {
        return quizService.deleteQuiz(quizId);
    }

}
