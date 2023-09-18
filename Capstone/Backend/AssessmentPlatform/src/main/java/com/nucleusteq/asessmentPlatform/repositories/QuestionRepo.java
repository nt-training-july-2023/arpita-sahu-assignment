package com.nucleusteq.asessmentPlatform.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nucleusteq.asessmentPlatform.entities.Question;

/**
 * JPA repository interface for managing Question entities.
 */
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    /**
     * Find a question by its content.
     *
     * @param question The content of the question to search for.
     * @return An Optional containing the Question entity if found, or empty if
     *         not found.
     */
//    Optional<Question> findByQuestion(String question);
}
