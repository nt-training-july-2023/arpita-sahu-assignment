package com.nucleusteq.asessmentPlatform.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nucleusteq.asessmentPlatform.entities.Result;

/**
 * Repository interface for managing Result entities.
 */
public interface ResultRepo extends JpaRepository<Result, Integer> {

    /**
     * Finds results by user email.
     *
     * @param userEmail The email address of the user for which results are to
     *                  be found.
     * @return A list of Result objects associated with the specified user
     *         email.
     */
    List<Result> findResultByEmail(String userEmail);
}
