package com.nucleusteq.asessmentPlatform.services.impl;

import java.util.List;

import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nucleusteq.asessmentPlatform.dto.QuestionDto;
import com.nucleusteq.asessmentPlatform.entities.Question;
import com.nucleusteq.asessmentPlatform.entities.Quiz;
import com.nucleusteq.asessmentPlatform.exception.BadCredentialsException;
import com.nucleusteq.asessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.asessmentPlatform.repositories.QuestionRepo;
import com.nucleusteq.asessmentPlatform.repositories.QuizRepo;
import com.nucleusteq.asessmentPlatform.service.QuestionService;

/**
 * Service implementation for managing questions.
 * It uses a logger to log messages related to its functionality.
 */

@Service
public class QuestionServiceImpl implements QuestionService {
    /**
     * Autowired field for the ModelMapper instance.
     */
    @Autowired
    private ModelMapper modelMapper;
    /**
     * This is question Repository object that is for calling the repository
     * methods.
     */
    @Autowired
    private QuestionRepo questionRepo;
    /**
     * This is quiz Repository object that is for calling the repository
     * methods.
     */
    @Autowired
    private QuizRepo quizRepo;

    /**
     * The logger instance for logging messages related to QuestionServiceImpl.
     */
    private Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    /**
     * Adds a new question.
     *
     * @param questionDto The QuestionDto containing question details to be
     *                    added.
     * @return The added QuestionDto.
     * @throws BadCredentialsException If the question is empty.
     */
    @Override
    public final QuestionDto addQuestion(final QuestionDto questionDto) {
        quizRepo.findById(questionDto.getQuizId()).orElseThrow(
                () -> new ResourceNotFoundException("Quiz Id not found"));
        Question question = this.dtoToQues(questionDto);
        if (question == null || question.getQuestion() == null
                || question.getQuestion().isEmpty()) {
            logger.error("Question must not be empty");
            throw new BadCredentialsException("Question must not be empty");
        }
        Quiz quiz = new Quiz();
        quiz.setQuizId(questionDto.getQuizId());
        question.setQuiz(quiz);
        questionRepo.save(question);
        logger.info("Question saved in Question Repository");
        return this.quesToDto(question);
    }

    /**
     * Retrieves all questions.
     *
     * @return A list of QuestionDtos containing all questions.
     */

    @Override
    public final List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionRepo.findAll();
        List<QuestionDto> questionDtos = questions.stream()
                .map(question -> this.quesToDto(question))
                .collect(Collectors.toList());
        logger.info("Get All Questions");
        return questionDtos;
    }

    /**
     * Retrieves a question by its ID.
     *
     * @param quesId The ID of the question to retrieve.
     * @return The QuestionDto representing the retrieved question.
     * @throws ResourceNotFoundException If the question with the specified ID
     *                                   is not found.
     */
    @Override
    public final QuestionDto getQuestionById(final int quesId) {
        Question question = questionRepo.findById(quesId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Question not found with id " + quesId));
        logger.info("Get Question by Question Id");
        return this.quesToDto(question);
    }

    @Override
    public final List<QuestionDto> getQuestionsByQuizId(final int quizId) {
        List<Question> questions = questionRepo.findQuestionByQuizId(quizId);
        List<QuestionDto> quesDtos = questions.stream()
                .map(ques -> this.quesToDto(ques)).collect(Collectors.toList());
        logger.info("Get Question by Quiz Id");
        return quesDtos;
    }

    /**
     * Updates a question with the given ID.
     *
     * @param question The updated QuestionDto.
     * @param quesId   The ID of the question to update.
     * @return The updated QuestionDto.
     * @throws ResourceNotFoundException If the question with the specified ID
     *                                   is not found.
     */
    @Override
    public final QuestionDto updateQuestion(final QuestionDto question,
            final int quesId) {
        Question updatedQuestion = questionRepo.findById(quesId).orElseThrow(
                () -> new ResourceNotFoundException("Question Not Found."));
        updatedQuestion.setQuestion(question.getQuestion());
        updatedQuestion.setOption1(question.getOption1());
        updatedQuestion.setOption2(question.getOption2());
        updatedQuestion.setOption3(question.getOption3());
        updatedQuestion.setOption4(question.getOption4());
        updatedQuestion.setAnswer(question.getAnswer());
        questionRepo.save(updatedQuestion);
        logger.info("Question Updated");
        return this.quesToDto(updatedQuestion);
    }

    /**
     * Deletes a question by its ID.
     *
     * @param quesId The ID of the question to delete.
     * @return A success message indicating the deletion.
     * @throws ResourceNotFoundException If the question with the specified ID
     *                                   is not found.
     */
    @Override
    public final String deleteQuestion(final int quesId) {
        Question question = questionRepo.findById(quesId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Question Not found with id " + quesId));
        questionRepo.delete(question);
        logger.info("Question deleted Successfully");
        return quesId + " deleted Successfully";
    }

    /**
     * Maps a Question entity to a QuestionDto.
     *
     * @param question The Question entity to map.
     * @return The mapped QuestionDto.
     */
    public final QuestionDto quesToDto(final Question question) {
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
        return questionDto;
    }

    /**
     * Maps a QuestionDto to a Question entity.
     *
     * @param questionDto The QuestionDto to map.
     * @return The mapped Question entity.
     */
    public final Question dtoToQues(final QuestionDto questionDto) {
        Question question = modelMapper.map(questionDto, Question.class);
        return question;
    }

}
