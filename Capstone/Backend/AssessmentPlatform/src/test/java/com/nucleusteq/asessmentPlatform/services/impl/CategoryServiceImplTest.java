package com.nucleusteq.asessmentPlatform.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.nucleusteq.asessmentPlatform.dto.CategoryDto;
import com.nucleusteq.asessmentPlatform.entities.Category;
import com.nucleusteq.asessmentPlatform.exception.DuplicateResourceException;
import com.nucleusteq.asessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.asessmentPlatform.repositories.CategoryRepo;

class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testAddCategory_Success() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setTitle("Test Title");
        categoryDto.setDescription("Test Description");
        
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);
        when(categoryRepo.findByTitle(categoryDto.getTitle()))
        .thenReturn(Optional.empty());
        CategoryDto result = categoryService.addCategory(categoryDto);
        assertNotNull(result);
        assertEquals(categoryDto.getTitle(), result.getTitle());
    }

    @Test
    public void testAddCategoryDuplicate() {
        CategoryDto categoryDTO = new CategoryDto();
        categoryDTO.setTitle("Test Category");
        categoryDTO.setDescription("Test Description");
        Category category = new Category();
        category.setTitle("Test Category");
        category.setDescription("Test Description");
        when(modelMapper.map(categoryDTO, Category.class)).thenReturn(category);
        when(categoryRepo.findByTitle(categoryDTO.getTitle()))
                .thenReturn(Optional.of(category));
        assertThrows(DuplicateResourceException.class, () -> {
            categoryService.addCategory(categoryDTO);
        });
    }

    @Test
    public void testGetAllCategoriess() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Java", "Java Category"));
        categories.add(new Category(2, "React", "React Category"));

        when(categoryRepo.findAll()).thenReturn(categories);

        List<CategoryDto> categoryDtos = categoryService.getAllCategories();
        assertNotNull(categoryDtos);
        assertEquals(categories.size(), categoryDtos.size());

    }

    @Test
    void testGetCategoryById() {
        int categoryId = 1;
        Category category = new Category(categoryId, "Test Category",
                "Description");
        CategoryDto categoryDto = new CategoryDto(1, "Test Category",
                "Description");
        when(categoryRepo.findById(categoryId))
                .thenReturn(Optional.of(category));
        when(modelMapper.map(category, CategoryDto.class))
                .thenReturn(categoryDto);
        CategoryDto resultDto = categoryService.getCategoryById(categoryId);
        assertEquals(categoryDto, resultDto);
    }

    @Test
    void testGetCategoryByIdNotFound() {
        int categoryId = 1;
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> categoryService.getCategoryById(categoryId));
        assertEquals("category not found with id " + categoryId,
                exception.getMessage());
    }

    @Test
    void testUpdateCategory() {
        CategoryDto updatedCategoryDto = new CategoryDto();
        updatedCategoryDto.setTitle("Updated Title");
        updatedCategoryDto.setDescription("Updated Description");

        int categoryId = 1;
        Category existingCategory = new Category();
        existingCategory.setCategoryId(updatedCategoryDto.getCategoryId());
        existingCategory.setTitle(updatedCategoryDto.getTitle());
        existingCategory.setDescription(updatedCategoryDto.getDescription());
        when(categoryRepo.findById(categoryId))
                .thenReturn(Optional.of(existingCategory));
        when(modelMapper.map(existingCategory, CategoryDto.class)).thenReturn(updatedCategoryDto);
        CategoryDto resultDto = categoryService
                .updateCategory(updatedCategoryDto, categoryId);
        assertNotNull(resultDto);
        assertEquals(updatedCategoryDto.getTitle(), resultDto.getTitle());
        assertEquals(updatedCategoryDto.getDescription(),
                resultDto.getDescription());

    }

    @Test
    void testUpdateCategory_CategoryNotFound() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setTitle("Updated Title");
        categoryDto.setDescription("Updated Description");
        when(categoryRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {categoryService.updateCategory(categoryDto, 1);
        });
    }

    @Test
    void testDeleteCategory() {
        int categoryIdToDelete = 1;
        Category categoryToDelete = new Category(categoryIdToDelete, "Java",
                "Java Category");
        when(categoryRepo.findById(categoryIdToDelete))
                .thenReturn(Optional.of(categoryToDelete));
        String result = categoryService.deleteCategory(categoryIdToDelete);
        assertEquals(categoryIdToDelete + " deleted successfully", result);
    }

    @Test
    void testDeleteCategoryNotFound() {
        int categoryIdToDelete = 1;
        when(categoryRepo.findById(categoryIdToDelete))
                .thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> categoryService.deleteCategory(categoryIdToDelete));

        assertEquals("category not found with id " + categoryIdToDelete,
                exception.getMessage());
    }

}
