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
import com.nucleusteq.asessmentPlatform.exception.CategoryNotFoundException;
import com.nucleusteq.asessmentPlatform.service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<CategoryDto> saveCategory(
            @RequestBody CategoryDto category) {
        return new ResponseEntity<CategoryDto>(
                categoryService.addCategory(category), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<CategoryDto> getCategories() {
        return categoryService.getAllCategories();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCategoryById(@PathVariable int id) {
        try {
            CategoryDto categoryDto = categoryService.getCategoryById(id);
            if (categoryDto == null) {
                String errorMessage = "Category with ID " + id + " not found.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }
            return ResponseEntity.ok(categoryDto);
        } catch (CategoryNotFoundException e) {
            String error = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            String errorMessage = "An error occurred while processing your request.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public CategoryDto updateCategory(@RequestBody CategoryDto category,
            @PathVariable int id) {
        return categoryService.updateCategory(category, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }

}
