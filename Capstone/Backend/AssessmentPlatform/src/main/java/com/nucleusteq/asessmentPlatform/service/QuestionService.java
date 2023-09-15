package com.nucleusteq.asessmentPlatform.service;

import java.util.List;

import com.nucleusteq.asessmentPlatform.dto.QuestionDto;

public interface QuestionService {

    QuestionDto addQuestion(QuestionDto question);

    List<QuestionDto> getAllQuestions();

    QuestionDto getQuestionById(int quesId);

    QuestionDto updateQuestion(QuestionDto question, int quesId);

    String deleteQuestion(int quesId);

}
