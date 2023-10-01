package com.nucleusteq.asessmentPlatform.controllers;

import com.nucleusteq.asessmentPlatform.dto.CategoryDto;
import com.nucleusteq.asessmentPlatform.exception.ApiErrorResponse;
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
        ApiErrorResponse response = categoryController
                .saveCategory(categoryDto);
        
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("category added successfully.", response.getMessage());
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
    public void testUpdateCategory() {
        int categoryId = 1;
        CategoryDto categoryDto = new CategoryDto();
        when(categoryService.updateCategory(categoryDto, categoryId))
                .thenReturn(categoryDto);
        ApiErrorResponse response = categoryController.updateCategory(categoryDto,
                categoryId);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Category Updated Successfully.", response.getMessage());
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