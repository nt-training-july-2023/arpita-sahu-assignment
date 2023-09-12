package com.nucleusteq.asessmentPlatform.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.nucleusteq.asessmentPlatform.entities.Quiz;

/**
 * Repository interface for accessing and managing quiz data in the database.
 */

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {

    /**
     * Retrieves an optional Quiz object based on the provided title.
     *
     * @param title The title used to search for a quiz.
     * @return An Optional containing the Quiz object if found, or an empty
     *         Optional if not found.
     */

    Optional<Quiz> findByTitle(String title);
    
    @Query("select q from Quiz q where q.category.categoryId=:categoryId")
    List<Quiz> findQuizByCategoryId(int categoryId);


}
