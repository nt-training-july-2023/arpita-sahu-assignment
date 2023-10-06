package com.nucleusteq.asessmentPlatform.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nucleusteq.asessmentPlatform.entities.Question;

/**
 * JPA repository interface for managing Question entities.
 */
public interface QuestionRepo extends JpaRepository<Question, Integer> {
    /**
     * Finds questions by quiz ID.
     *
     * @param quizId The unique identifier of the quiz for which questions are
     *               to be retrieved.
     * @return A list of Question objects representing questions associated with
     *         the specified quiz ID.
     */
    @Query("select q from Question q where q.quiz.quizId=:quizId")
    List<Question> findQuestionByQuizId(int quizId);

}

