package com.nucleusteq.asessmentPlatform.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nucleusteq.asessmentPlatform.entities.Question;

/**
 * JPA repository interface for managing Question entities.
 */
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    @Query("select q from Question q where q.quiz.quizId=:quizId")
    List<Question> findQuestionByQuizId(int quizId);
    
}
