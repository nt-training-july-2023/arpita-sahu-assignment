package com.nucleusteq.asessmentPlatform.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nucleusteq.asessmentPlatform.entities.Category;
import com.nucleusteq.asessmentPlatform.exception.CategoryNotFoundException;
import com.nucleusteq.asessmentPlatform.repositories.CategoryRepo;
import com.nucleusteq.asessmentPlatform.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category addCategory(Category category) {
		Category newCategory= new Category();
		newCategory.setTitle(category.getTitle());
		newCategory.setDescription(category.getDescription());
		return categoryRepo.save(newCategory);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public Category getCategoryById(int id) {
		return categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));	}

	@Override
	public Category updateCategory(Category category, int id) {
		Category updatedCategory = categoryRepo.findById(id).orElseThrow(()-> new CategoryNotFoundException("category not found"));
		updatedCategory.setTitle(category.getTitle());
		updatedCategory.setDescription(category.getDescription());
		return categoryRepo.save(updatedCategory);
	}

	@Override
	public void deleteCategory(int id) {
		Category category = categoryRepo.findById(id).orElseThrow(()-> new CategoryNotFoundException("category not found with id " + id));
		categoryRepo.delete(category);
	}
}
