package com.nucleusteq.asessmentPlatform.service;

import java.util.List;

import com.nucleusteq.asessmentPlatform.dto.QuestionDto;

    /**
     * An interface for managing questions.
     */
    public interface QuestionService {

        /**
         * Adds a new question.
         *
         * @param question The QuestionDto containing
         * question details to be added.
         * @return The added QuestionDto.
         */
        QuestionDto addQuestion(QuestionDto question);

        /**
         * Retrieves all questions.
         *
         * @return A list of QuestionDtos containing all questions.
         */
        List<QuestionDto> getAllQuestions();

        /**
         * Retrieves a question by its ID.
         *
         * @param quesId The ID of the question to retrieve.
         * @return The QuestionDto representing the retrieved question.
         */
        QuestionDto getQuestionById(int quesId);

        /**
         * Updates a question with the given ID.
         *
         * @param question The updated QuestionDto.
         * @param quesId   The ID of the question to update.
         * @return The updated QuestionDto.
         */
        QuestionDto updateQuestion(QuestionDto question, int quesId);

        /**
         * Deletes a question by its ID.
         *
         * @param quesId The ID of the question to delete.
         * @return A success message indicating the deletion.
         */
        String deleteQuestion(int quesId);

        List<QuestionDto> getQuestionsByQuizId(int quizId);
    }

