package com.nucleusteq.asessmentPlatform.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

/**
 * Service implementation for managing quizzes.
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
     * Create a new quiz.
     *
     * @param quizDto The DTO representing the quiz to be created.
     * @return The DTO of the created quiz.
     * @throws DuplicateResourceException if a quiz with the same title already
     *                                    exists.
     */
    @Override
    public final String addQuiz(final QuizDto quizDto) {
        Quiz quiz = this.dtoToQuiz(quizDto);
        Optional<Quiz> existingQuiz = quizRepo.findByTitle(quiz.getTitle());
        if (existingQuiz.isPresent()) {
            throw new DuplicateResourceException("Quiz with title '"
                    + quiz.getTitle() + "' already exists.");
        }
        if (categoryRepo.findById(quiz.getCategory().getCategoryId())
                .isPresent()) {
            quizRepo.save(quiz);
        } else {
            throw new BadCredentialsException("Category not exist");
        }
        return " Quiz Added Successfully";
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
        return quizDtos;
    }

    @Override
    public final List<QuizDto> getQuizByCategoryId(int categoryId) {
        List<Quiz> quizzes = quizRepo.findQuizByCategoryId(categoryId);
        List<QuizDto> quizDtos = quizzes.stream()
                .map(quiz -> this.quizToDto(quiz)).collect(Collectors.toList());
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
        return this.quizToDto(quiz);
    }

    /**
     * Update an existing quiz by its ID.
     *
     * @param quizDto The DTO representing the updated quiz.
     * @param quizId  The ID of the quiz to update.
     * @return The updated DTO of the quiz.
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
                    throw new DuplicateResourceException("Quiz with title '"
                            + newTitle + "' already exists.");
                }
            }
            existingQuiz.setTitle(newTitle);
            existingQuiz.setDescription(updatedQuiz.getDescription());
            quizRepo.save(existingQuiz);
            return "Quiz Updated Successfully";
        } else {
            throw new ResourceNotFoundException(
                    "Quiz not found with ID " + quizId);
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
                        "Quiz not found with id " + quizId));
        quizRepo.delete(quiz);
        return quizId + " deleted successfully";
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