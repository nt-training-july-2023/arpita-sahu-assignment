package com.nucleusteq.asessmentPlatform.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nucleusteq.asessmentPlatform.dto.CategoryDto;
import com.nucleusteq.asessmentPlatform.entities.Category;
import com.nucleusteq.asessmentPlatform.exception.DuplicateResourceException;
import com.nucleusteq.asessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.asessmentPlatform.repositories.CategoryRepo;
import com.nucleusteq.asessmentPlatform.service.CategoryService;
import com.nucleusteq.asessmentPlatform.validationmessage.LoggerMessage;
import com.nucleusteq.asessmentPlatform.validationmessage.Message;
import com.nucleusteq.asessmentPlatform.validationmessage.ErrorMessage;

/**
 * Implementation of the {@link CategoryService} interface for managing
 * category-related operations.
 * It uses a logger to log messages related to its functionality.
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
     * The logger instance for logging messages related to CategoryServiceImpl.
     */
    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

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
            logger.error(LoggerMessage.CATEGORY_TITLE_EXIST);
            throw new DuplicateResourceException(
                    ErrorMessage.CATEGORY_ALREADY_EXISTS_PREFIX
                            + category.getTitle()
                            + ErrorMessage.CATEGORY_ALREADY_EXISTS_SUFFIX);
        }
        categoryRepo.save(category);
        logger.info(LoggerMessage.CATEGORY_SAVED_IN_REPOSITORY);
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
        logger.info(LoggerMessage.GET_CATEGORY);
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
                        ErrorMessage.CATEGORY_ID_NOT_FOUND + id));
        logger.info(LoggerMessage.GET_CATEGORY_BY_ID);
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
    public final String updateCategory(final CategoryDto category,
            final int id) {
        Optional<Category> existingCategoryById = categoryRepo.findById(id);
        if (existingCategoryById.isPresent()) {
            Category existingCategory = existingCategoryById.get();
            String newTitle = category.getTitle();
            if (!existingCategory.getTitle().equals(newTitle)) {
                Optional<Category> existingCategoryByTitle = categoryRepo
                        .findByTitle(newTitle);
                if (existingCategoryByTitle.isPresent()) {
                    throw new DuplicateResourceException(
                            ErrorMessage.CATEGORY_TITLE_ALREADY_EXISTS);
                }
            }
            existingCategory.setTitle(newTitle);
            existingCategory.setDescription(category.getDescription());
            categoryRepo.save(existingCategory);
            logger.info(LoggerMessage.UPDATE_CATEGORY);
            return Message.UPDATE_CATEGORY;
        } else {
            logger.info(LoggerMessage.CATEGORY_NOT_FOUND);
            throw new ResourceNotFoundException(
                    ErrorMessage.CATEGORY_ID_NOT_FOUND);
        }
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id The ID of the category to delete.
     * @return A success message indicating the deletion.
     * @throws ResourceNotFoundException If the category with the specified ID
     * is not found.
     */
    @Override
    public final String deleteCategory(final int id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessage.CATEGORY_ID_NOT_FOUND + id));
        categoryRepo.delete(category);
        logger.info(LoggerMessage.DELETE_CATEGORY);
        return Message.DELETE_CATEGORY;
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
