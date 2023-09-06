package com.nucleusteq.asessmentPlatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nucleusteq.asessmentPlatform.entities.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {

}
