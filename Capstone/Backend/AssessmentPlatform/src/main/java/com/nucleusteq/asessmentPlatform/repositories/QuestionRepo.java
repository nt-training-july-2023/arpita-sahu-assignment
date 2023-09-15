package com.nucleusteq.asessmentPlatform.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nucleusteq.asessmentPlatform.entities.Question;


public interface QuestionRepo extends JpaRepository<Question, Integer> {
    Optional<Question> findByQuestion(String question);

}
