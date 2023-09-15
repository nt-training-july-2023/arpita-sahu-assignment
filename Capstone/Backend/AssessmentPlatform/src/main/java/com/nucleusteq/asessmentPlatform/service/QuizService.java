package com.nucleusteq.asessmentPlatform.service;

import java.util.List;

import com.nucleusteq.asessmentPlatform.dto.QuizDto;

/**
 * Service interface for managing quizzes.
 */
public interface QuizService {

    /**
     * Create a new quiz.
     *
     * @param quizDto The DTO representing the quiz to be created.
     * @return The DTO of the created quiz.
     */
    QuizDto addQuiz(QuizDto quizDto);

    /**
     * Get a list of all quizzes.
     *
     * @return A list of DTOs representing all available quizzes.
     */
    List<QuizDto> getAllQuiz();

    /**
     * Get a quiz by its ID.
     *
     * @param quizId The ID of the quiz to retrieve.
     * @return The DTO of the retrieved quiz.
     */
    QuizDto getQuizById(int quizId);

    /**
     * Update an existing quiz by its ID.
     *
     * @param quizDto The DTO representing the updated quiz.
     * @param quizId  The ID of the quiz to update.
     * @return The updated DTO of the quiz.
     */
     String updateQuiz(QuizDto quizDto, int quizId);

    /**
     * Delete a quiz by its ID.
     *
     * @param quizId The ID of the quiz to delete.
     * @return A message indicating the successful deletion of the quiz.
     */
    String deleteQuiz(int quizId);

    List<QuizDto> getQuizByCategoryId(int categoryId);

}
