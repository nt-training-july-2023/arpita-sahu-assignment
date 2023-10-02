package com.nucleusteq.asessmentPlatform.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nucleusteq.asessmentPlatform.dto.CategoryDto;
import com.nucleusteq.asessmentPlatform.dto.QuizDto;
import com.nucleusteq.asessmentPlatform.entities.Category;
import com.nucleusteq.asessmentPlatform.entities.Quiz;
import com.nucleusteq.asessmentPlatform.exception.BadCredentialsException;
import com.nucleusteq.asessmentPlatform.exception.DuplicateResourceException;
import com.nucleusteq.asessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.asessmentPlatform.repositories.CategoryRepo;
import com.nucleusteq.asessmentPlatform.repositories.QuizRepo;
import com.nucleusteq.asessmentPlatform.service.QuizService;

import ValidationMessage.ErrorMessage;
import ValidationMessage.LoggerMessage;
import ValidationMessage.Message;

/**
 * Service implementation for managing quizzes.
 * It uses a logger to log messages related to its functionality.
 */
@Service
public class QuizServiceImpl implements QuizService {

    /**
     * Autowired field for the ModelMapper instance.
     */
    @Autowired
    private ModelMapper modelMapper;
    /**
     * This is category Repository object that is for calling the repository
     * methods.
     */
    @Autowired
    private CategoryRepo categoryRepo;

    /**
     * This is Quiz Repository object that is for calling the repository
     * methods.
     */
    @Autowired
    private QuizRepo quizRepo;

    /**
     * The logger instance for logging messages related to QuizServiceImpl.
     */
    private Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);

    /**
     * Create a new quiz.
     *
     * @param quizDto The DTO representing the quiz to be created.
     * @return The DTO of the created quiz.
     * @throws DuplicateResourceException if a quiz with the same title already
     *                                    exists.
     */
    @Override
    public final QuizDto addQuiz(final QuizDto quizDto) {
        Quiz quiz = this.dtoToQuiz(quizDto);
        Optional<Quiz> existingQuiz = quizRepo.findByTitle(quiz.getTitle());
        if (existingQuiz.isPresent()) {
            logger.error(LoggerMessage.QUIZ_TITLE_EXIST);
            throw new DuplicateResourceException(ErrorMessage.QUIZ_ALREADY_EXISTS_PREFIX
                    + quiz.getTitle() + ErrorMessage.QUIZ_ALREADY_EXISTS_SUFFIX );
        }
        if (categoryRepo.findById(quiz.getCategory().getCategoryId())
                .isPresent()) {
            logger.info(LoggerMessage.SAVE_QUIZ);
            quizRepo.save(quiz);
        } else {
            logger.error(LoggerMessage.CATEGORY_NOT_FOUND);
            throw new BadCredentialsException(ErrorMessage.CATEGORY_NOT_FOUND);
        }
        return this.quizToDto(quiz);
    }

    /**
     * Get a list of all quizzes.
     *
     * @return A list of DTOs representing all available quizzes.
     */
    @Override
    public final List<QuizDto> getAllQuiz() {
        List<Quiz> quizzes = quizRepo.findAll();
        List<QuizDto> quizDtos = quizzes.stream()
                .map(quiz -> this.quizToDto(quiz)).collect(Collectors.toList());
        logger.info(LoggerMessage.GET_QUIZ);
        return quizDtos;
    }

    @Override
    public final List<QuizDto> getQuizByCategoryId(final int categoryId) {
        categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Catgeory not found with id " + categoryId));
        List<Quiz> quizzes = quizRepo.findQuizByCategoryId(categoryId);
        List<QuizDto> quizDtos = quizzes.stream()
                .map(quiz -> this.quizToDto(quiz)).collect(Collectors.toList());
        logger.info(LoggerMessage.GET_QUIZ_BY_CATEGORYID);
        return quizDtos;
    }

    /**
     * Get a quiz by its ID.
     *
     * @param quizId The ID of the quiz to retrieve.
     * @return The DTO of the retrieved quiz.
     * @throws ResourceNotFoundException if the quiz with the specified ID is
     *                                   not found.
     */
    @Override
    public final QuizDto getQuizById(final int quizId) {
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Quiz not found with id " + quizId));
        logger.info(LoggerMessage.GET_QUIZ_BY_ID);
        return this.quizToDto(quiz);
    }

    /**
     * Update an existing quiz by its ID.
     *
     * @param quizDto The DTO representing the updated quiz.
     * @param quizId  The ID of the quiz to update.
     * @return The message after updating of the quiz.
     * @throws ResourceNotFoundException if the quiz with the specified ID is
     *                                   not found.
     */
    @Override
    public final String updateQuiz(final QuizDto quizDto, final int quizId) {
        Quiz updatedQuiz = this.dtoToQuiz(quizDto);
        String newTitle = updatedQuiz.getTitle();
        Optional<Quiz> existingQuizById = quizRepo.findById(quizId);

        if (existingQuizById.isPresent()) {
            Quiz existingQuiz = existingQuizById.get();
            if (!existingQuiz.getTitle().equals(newTitle)) {
                Optional<Quiz> existingQuizByTitle = quizRepo
                        .findByTitle(newTitle);
                if (existingQuizByTitle.isPresent()) {
                    logger.error(LoggerMessage.QUIZ_TITLE_EXIST);
                    throw new DuplicateResourceException(ErrorMessage.QUIZ_ALREADY_EXISTS_PREFIX
                            + newTitle + ErrorMessage.QUIZ_ALREADY_EXISTS_SUFFIX);
                }
            }
            existingQuiz.setTitle(newTitle);
            existingQuiz.setDescription(updatedQuiz.getDescription());
            existingQuiz.setQuizTimer(updatedQuiz.getQuizTimer());
            // existingQuiz.setCategory(updatedQuiz.getCategory());
            quizRepo.save(existingQuiz);
            logger.info(LoggerMessage.UPDATE_QUIZ);
            return Message.UPDATE_QUIZ;
        } else {
            logger.error(LoggerMessage.QUIZ_NOT_FOUND);
            throw new ResourceNotFoundException(
                    ErrorMessage.QUIZID_NOT_FOUND + quizId);
        }
    }

    /**
     * Delete a quiz by its ID.
     *
     * @param quizId The ID of the quiz to delete.
     * @return A message indicating the successful deletion of the quiz.
     * @throws ResourceNotFoundException if the quiz with the specified ID is
     *                                   not found.
     */
    @Override
    public final String deleteQuiz(final int quizId) {
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.QUIZID_NOT_FOUND + quizId));
        quizRepo.delete(quiz);
        logger.info(LoggerMessage.DELETE_QUIZ);
        return quizId + Message.DELETE_QUIZ;
    }

    /**
     * Map a Quiz entity to a QuizDto.
     *
     * @param quiz The Quiz entity to map.
     * @return The QuizDto.
     */
    public final QuizDto quizToDto(final Quiz quiz) {
        QuizDto quizDTO = modelMapper.map(quiz, QuizDto.class);
        if (quiz.getCategory() != null) {
            CategoryDto categoryDto = modelMapper.map(quiz.getCategory(),
                    CategoryDto.class);
            quizDTO.setCategory(categoryDto);
        }
        return quizDTO;
    }

    /**
     * Map a QuizDto to a Quiz entity.
     *
     * @param quizDto The QuizDto to map.
     * @return The Quiz entity.
     */
    public final Quiz dtoToQuiz(final QuizDto quizDto) {
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        if (quizDto.getCategory() != null) {
            Category category = modelMapper.map(quizDto.getCategory(),
                    Category.class);
            quiz.setCategory(category);
        }
        return quiz;
    }

}
