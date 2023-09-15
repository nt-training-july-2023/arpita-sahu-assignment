package com.nucleusteq.asessmentPlatform.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nucleusteq.asessmentPlatform.dto.QuestionDto;
import com.nucleusteq.asessmentPlatform.entities.Question;
import com.nucleusteq.asessmentPlatform.entities.Quiz;
import com.nucleusteq.asessmentPlatform.exception.BadCredentialsException;
import com.nucleusteq.asessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.asessmentPlatform.repositories.QuestionRepo;
import com.nucleusteq.asessmentPlatform.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public QuestionDto addQuestion(QuestionDto questionDto) {
        Question question = this.dtoToQues(questionDto);
        Optional<Question> existingQuestion = questionRepo.findByQuestion(question.getQuestion());
        if(existingQuestion.isEmpty())
            throw new BadCredentialsException("Question must not be empty");
        Quiz quiz = new Quiz();
        quiz.setQuizId(questionDto.getQuizId());
        question.setQuiz(quiz);
        questionRepo.save(question);
        return this.quesToDto(question);
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionRepo.findAll();
        List<QuestionDto> questionDtos = questions.stream()
                .map(question -> this.quesToDto(question))
                .collect(Collectors.toList());
        return questionDtos;
    }

    @Override
    public QuestionDto getQuestionById(int quesId) {
        Question question = questionRepo.findById(quesId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Question not found with id " + quesId));
        return this.quesToDto(question);
    }

    @Override
    public QuestionDto updateQuestion(QuestionDto question, int quesId) {
        Question updatedQuestion = questionRepo.findById(quesId).orElseThrow(
                () -> new ResourceNotFoundException("Question Not Found."));
        updatedQuestion.setQuestion(question.getQuestion());
        updatedQuestion.setOption1(question.getOption1());
        updatedQuestion.setOption2(question.getOption2());
        updatedQuestion.setOption3(question.getOption3());
        updatedQuestion.setOption4(question.getOption4());
        updatedQuestion.setAnswer(question.getAnswer());
        questionRepo.save(updatedQuestion);
        return this.quesToDto(updatedQuestion);
    }

    @Override
    public String deleteQuestion(int quesId) {
        Question question = questionRepo.findById(quesId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Question Not found with id " + quesId));
        questionRepo.delete(question);
        return quesId + " deleted Successfully";
    }

    public final QuestionDto quesToDto(final Question question) {
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
//        if(question.getQuiz() != null) {
//            QuizDto quizDto = modelMapper.map(question.getQuiz(), QuizDto.class);
//            questionDto.setQuiz(quizDto);
//        }
        return questionDto;
    }

    public final Question dtoToQues(final QuestionDto questionDto) {
        Question question = modelMapper.map(questionDto, Question.class);
//        if(questionDto.getQuiz() != null) {
//            Quiz quiz = modelMapper.map(questionDto.getQuiz(), Quiz.class);
//            question.setQuiz(quiz);
//        }
        return question;
    }

}
