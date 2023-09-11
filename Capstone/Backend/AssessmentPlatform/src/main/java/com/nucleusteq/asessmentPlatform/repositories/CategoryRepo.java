package com.nucleusteq.asessmentPlatform.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nucleusteq.asessmentPlatform.entities.Category;

/**
 * Repository interface for accessing and managing category data in the
 * database.
 */
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    /**
     * Finds and retrieves a category by its title.
     *
     * @param title The title of the category to search for.
     * @return An Optional containing the Category if found, or an empty
     *         Optional if not found.
     * @throws NullPointerException if the provided title is null.
     */
    Optional<Category> findByTitle(String title);
}
