package com.nucleusteq.asessmentPlatform.service;

import java.util.List;

import com.nucleusteq.asessmentPlatform.dto.QuizDto;

public interface QuizService {

    QuizDto addQuiz(QuizDto quizDto);

    List<QuizDto> getAllQuiz();

    QuizDto getQuizById(int quizId);

    QuizDto updateQuiz(QuizDto quizDto, int quizId);

    String deleteQuiz(int quizId);

}
