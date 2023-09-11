package com.nucleusteq.asessmentPlatform.controllers;

import com.nucleusteq.asessmentPlatform.dto.CategoryDto;
import com.nucleusteq.asessmentPlatform.exception.DuplicateResourceException;
import com.nucleusteq.asessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.asessmentPlatform.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.List;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

class CategoryControllerTest {
    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setTitle("Java");
        categoryDto.setDescription("Java mcq");
        when(categoryService.addCategory(any(CategoryDto.class)))
                .thenReturn(categoryDto);
        ResponseEntity<String> response = categoryController
                .saveCategory(categoryDto);
        verify(categoryService, times(1)).addCategory(categoryDto);
        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody().equals("category added successfully.");

    }

    @Test
    public void testSaveCategoryDuplicateException() {
        CategoryDto categoryDto = new CategoryDto();
        doThrow(new DuplicateResourceException("Category already exists"))
                .when(categoryService).addCategory(categoryDto);

        ResponseEntity<String> response = categoryController
                .saveCategory(categoryDto);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("category already exist", response.getBody());
    }

    @Test
    public void testGetCategories() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        when(categoryService.getAllCategories()).thenReturn(categoryDtoList);

        List<CategoryDto> result = categoryController.getCategories();

        assertEquals(categoryDtoList, result);
    }

    @Test
    public void testGetCategoryById() {
        int categoryId = 1;
        CategoryDto categoryDto = new CategoryDto();
        when(categoryService.getCategoryById(categoryId))
                .thenReturn(categoryDto);

        ResponseEntity<?> response = categoryController
                .getCategoryById(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
    }

    @Test
    void getCategoryById_ExceptionThrown_ReturnsInternalServerErrorResponse()
            throws ResourceNotFoundException {
        int categoryId = 1;
        when(categoryService.getCategoryById(categoryId))
                .thenThrow(new RuntimeException("Runtime exception"));

        ResponseEntity<?> response = categoryController
                .getCategoryById(categoryId);

        verify(categoryService, times(1)).getCategoryById(categoryId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                response.getStatusCode());
        assertEquals("An error occurred while processing your request.",
                response.getBody());
    }

    @Test
    public void testGetCategoryByIdNotFound() {
        int categoryId = 1;
        when(categoryService.getCategoryById(categoryId))
                .thenThrow(new ResourceNotFoundException("Category not found"));

        ResponseEntity<?> response = categoryController
                .getCategoryById(categoryId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Category not found", response.getBody());
    }

    @Test
    public void testUpdateCategory() {
        int categoryId = 1;
        CategoryDto categoryDto = new CategoryDto();
        when(categoryService.updateCategory(categoryDto, categoryId))
                .thenReturn(categoryDto);

        CategoryDto result = categoryController.updateCategory(categoryDto,
                categoryId);

        assertEquals(categoryDto, result);
    }

    @Test
    public void testDeleteCategory() {
        int categoryId = 1;
        when(categoryService.deleteCategory(categoryId))
                .thenReturn("Category deleted successfully");

        String result = categoryController.deleteCategory(categoryId);

        assertEquals("Category deleted successfully", result);
    }
}