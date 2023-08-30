package com.nucleusteq.asessmentPlatform.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nucleusteq.asessmentPlatform.dto.CategoryDto;
import com.nucleusteq.asessmentPlatform.entities.Category;
import com.nucleusteq.asessmentPlatform.exception.CategoryNotFoundException;
import com.nucleusteq.asessmentPlatform.repositories.CategoryRepo;
import com.nucleusteq.asessmentPlatform.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category newCategory = new Category();
        newCategory.setTitle(categoryDto.getTitle());
        newCategory.setDescription(categoryDto.getDescription());
        newCategory = categoryRepo.save(newCategory);
        return this.categoryToDto(newCategory);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> this.categoryToDto(category))
                .collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public CategoryDto getCategoryById(int id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id " + id));
        return this.categoryToDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto category, int id) {
        Category updatedCategory = categoryRepo.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("category not found"));
        updatedCategory.setTitle(category.getTitle());
        updatedCategory.setDescription(category.getDescription());
        categoryRepo.save(updatedCategory);
        return this.categoryToDto(updatedCategory);
    }

    @Override
    public String deleteCategory(int id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "category not found with id " + id));
        categoryRepo.delete(category);
        return id + " deleted sucessfully";
    }

    public CategoryDto categoryToDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    public Category dtoToCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        return category;
    }
}
