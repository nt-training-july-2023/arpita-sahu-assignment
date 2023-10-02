package com.nucleusteq.asessmentPlatform.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nucleusteq.asessmentPlatform.dto.ApiResponse;
import com.nucleusteq.asessmentPlatform.dto.CategoryDto;
import com.nucleusteq.asessmentPlatform.service.CategoryService;

import ValidationMessage.LoggerMessage;
import ValidationMessage.Message;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller class that handles CRUD operations for managing categories.
 */
@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    /**
     * The service responsible for managing category-related operations.
     */
    @Autowired
    private CategoryService categoryService;
    /**
     * The logger instance for logging messages related to CategoryController.
     */
    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    /**
     * Saves a new category using the provided CategoryDto object.
     *
     * @param categoryDto The CategoryDto object containing category information
     *                    to be added.
     * @return A ResponseEntity with a success status (201 Created) and a
     *         success message if the category is added successfully.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public final ApiResponse saveCategory(
            @RequestBody @Valid final CategoryDto categoryDto) {
        categoryService.addCategory(categoryDto);
        logger.info(LoggerMessage.SAVE_CATEGORY);
        return new ApiResponse(Message.SAVE_CATEGORY, 
                HttpStatus.OK.value());
    }

    /**
     * Endpoint for retrieving a list of all categories.
     * @return A list of CategoryDto objects representing all categories.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public final List<CategoryDto> getCategories() {
        logger.info(LoggerMessage.GET_CATEGORY);
        return categoryService.getAllCategories();
    }

    /**
     * Endpoint for retrieving a category by its ID.
     * @param id The ID of the category to retrieve.
     * @return A ResponseEntity with the retrieved CategoryDto or an error
     *         message if not found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final ResponseEntity<?> getCategoryById(@PathVariable final int id) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        logger.info(LoggerMessage.GET_CATEGORY_BY_ID);
        return ResponseEntity.ok(categoryDto);
    }

    /**
     * Endpoint for updating a category by its ID.
     *
     * @param category The CategoryDto object containing updated category
     *                 details.
     * @param id       The ID of the category to update.
     * @return The updated CategoryDto.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public final ApiResponse updateCategory(
            @RequestBody @Valid final CategoryDto category,
            @PathVariable final int id) {
        categoryService.updateCategory(category, id);
        logger.info(LoggerMessage.UPDATE_CATEGORY);
        return new ApiResponse(Message.UPDATE_CATEGORY, 
                HttpStatus.OK.value());

    }

    /**
     * Endpoint for deleting a category by its ID.
     *
     * @param id The ID of the category to delete.
     * @return A message indicating the result of the deletion operation.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final ApiResponse deleteCategory(@PathVariable final int id) {
        logger.info(LoggerMessage.DELETE_CATEGORY);
         categoryService.deleteCategory(id);
         return new ApiResponse(Message.DELETE_CATEGORY, HttpStatus.OK.value());
    }

}
