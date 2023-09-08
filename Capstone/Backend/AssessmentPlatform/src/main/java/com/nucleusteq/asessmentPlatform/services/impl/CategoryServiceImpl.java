package com.nucleusteq.asessmentPlatform.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nucleusteq.asessmentPlatform.dto.CategoryDto;
import com.nucleusteq.asessmentPlatform.entities.Category;
import com.nucleusteq.asessmentPlatform.exception.DuplicateResourceException;
import com.nucleusteq.asessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.asessmentPlatform.repositories.CategoryRepo;
import com.nucleusteq.asessmentPlatform.service.CategoryService;

/**
 * Implementation of the {@link CategoryService} interface for managing
 * category-related operations.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    /**
     * This is Category Repository object that is for calling. the repository
     * methods.
     */
    @Autowired
    private CategoryRepo categoryRepo;

    /**
     * Autowired field for the ModelMapper instance.
     */
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public final CategoryDto addCategory(final CategoryDto categoryDto) {
        Category category = new Category();
        Optional<Category> existingCategory = categoryRepo
                .findByTitle(categoryDto.getTitle());
        if (existingCategory != null) {
            throw new DuplicateResourceException("Category with title '"
                    + categoryDto.getTitle() + "' already exists.");
        }
        Category newCategory = new Category();
        newCategory.setTitle(category.getTitle());
        newCategory.setDescription(category.getDescription());
        newCategory = categoryRepo.save(newCategory);
        return this.categoryToDto(newCategory);
    }

    @Override
    public final List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> this.categoryToDto(category))
                .collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public final CategoryDto getCategoryById(final int id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id " + id));
        return this.categoryToDto(category);
    }

    @Override
    public final CategoryDto updateCategory(final CategoryDto category,
            final int id) {
        Category updatedCategory = categoryRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("category not found"));
        updatedCategory.setTitle(category.getTitle());
        updatedCategory.setDescription(category.getDescription());
        categoryRepo.save(updatedCategory);
        return this.categoryToDto(updatedCategory);
    }

    @Override
    public final String deleteCategory(final int id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "category not found with id " + id));
        categoryRepo.delete(category);
        return id + " deleted successfully";
    }

    /**
     * Converts a {@link Category} entity to a {@link CategoryDto} object.
     *
     * @param category The Category entity to be converted.
     * @return The converted CategoryDto object.
     */

    public final CategoryDto categoryToDto(final Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    /**
     * Converts a {@link CategoryDto} object to a {@link Category} entity.
     *
     * @param categoryDto The CategoryDto object to be converted.
     * @return The converted Category entity.
     */

    public final Category dtoToCategory(final CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        return category;
    }
}
