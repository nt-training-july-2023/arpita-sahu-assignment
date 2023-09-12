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
import com.nucleusteq.asessmentPlatform.dto.CategoryDto;
import com.nucleusteq.asessmentPlatform.exception.DuplicateResourceException;
import com.nucleusteq.asessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.asessmentPlatform.service.CategoryService;

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
     * Saves a new category using the provided CategoryDto object.
     *
     * @param categoryDto The CategoryDto object containing category information
     *                    to be added.
     * @return A ResponseEntity with a success status (201 Created) and a
     *         success message if the category is added successfully. If a
     *         DuplicateResourceException is thrown, it returns a ResponseEntity
     *         with a conflict status (409 Conflict) and an error message.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public final ResponseEntity<String> saveCategory(
            @RequestBody final CategoryDto categoryDto) {
        try {
            categoryService.addCategory(categoryDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("category added successfully.");
        } catch (DuplicateResourceException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("category already exist");
        }
    }

    /**
     * Endpoint for retrieving a list of all categories.
     * @return A list of CategoryDto objects representing all categories.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public final List<CategoryDto> getCategories() {
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
        try {
            CategoryDto categoryDto = categoryService.getCategoryById(id);
            return ResponseEntity.ok(categoryDto);
        } catch (ResourceNotFoundException e) {
            String error = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            String errorMessage =
                    "An error occurred while processing your request.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorMessage);
        }
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
    public final CategoryDto updateCategory(
            @RequestBody final CategoryDto category,
            @PathVariable final int id) {
        return categoryService.updateCategory(category, id);
    }

    /**
     * Endpoint for deleting a category by its ID.
     *
     * @param id The ID of the category to delete.
     * @return A message indicating the result of the deletion operation.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final String deleteCategory(@PathVariable final int id) {
        return categoryService.deleteCategory(id);
    }
    
}
