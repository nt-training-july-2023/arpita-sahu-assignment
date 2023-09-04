package com.nucleusteq.asessmentPlatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nucleusteq.asessmentPlatform.entities.Category;

/**
 * Repository interface for accessing and managing category data in the
 * database.
 */
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Category findByTitle(String title);
}
