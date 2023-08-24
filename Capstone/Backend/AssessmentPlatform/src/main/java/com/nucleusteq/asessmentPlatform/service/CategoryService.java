package com.nucleusteq.asessmentPlatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nucleusteq.asessmentPlatform.entities.Category;

@Service
public interface CategoryService {
	
	Category addCategory(Category category);
	List<Category> getAllCategories();
	Category getCategoryById(int id);
	Category updateCategory(Category category, int id);
	void deleteCategory(int id);
	
}
