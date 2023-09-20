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

    /**
     * Adds a new category.
     *
     * @param categoryDto The CategoryDto containing category details to be
     *                    added.
     * @return The added CategoryDto.
     * @throws DuplicateResourceException If a category with the same title
     *                                    already exists.
     */
    @Override
    public final CategoryDto addCategory(final CategoryDto categoryDto) {
        Category category = this.dtoToCategory(categoryDto);
        Optional<Category> existingCategory = categoryRepo
                .findByTitle(category.getTitle());
        if (existingCategory.isPresent()) {
            throw new DuplicateResourceException("Category with title '"
                    + category.getTitle() + "' already exists.");
        }
        categoryRepo.save(category);
        return this.categoryToDto(category);
    }

    /**
     * Retrieves all categories.
     *
     * @return A list of CategoryDtos containing all categories.
     */
    @Override
    public final List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> this.categoryToDto(category))
                .collect(Collectors.toList());
        return categoryDtos;
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The CategoryDto representing the retrieved category.
     * @throws ResourceNotFoundException If the category with the specified ID
     *                                   is not found.
     */
    @Override
    public final CategoryDto getCategoryById(final int id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id " + id));
        return this.categoryToDto(category);
    }

    /**
     * Updates a category with the given ID.
     *
     * @param category The updated CategoryDto.
     * @param id       The ID of the category to update.
     * @return The updated CategoryDto.
     * @throws ResourceNotFoundException If the category with the specified ID
     *                                   is not found.
     */

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

    /**
     * Deletes a category by its ID.
     *
     * @param id The ID of the category to delete.
     * @return A success message indicating the deletion.
     * @throws ResourceNotFoundException If the category with the specified ID
     *                                   is not found.
     */
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
