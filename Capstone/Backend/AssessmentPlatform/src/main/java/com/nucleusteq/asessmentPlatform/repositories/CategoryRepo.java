package com.nucleusteq.asessmentPlatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nucleusteq.asessmentPlatform.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
