package com.nucleusteq.asessmentPlatform.controllers;

import com.nucleusteq.asessmentPlatform.dto.CategoryDto;
import com.nucleusteq.asessmentPlatform.exception.CategoryNotFoundException;
import com.nucleusteq.asessmentPlatform.exception.DuplicateResourceException;
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
import java.util.Arrays;

class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveCategory_SuccessfulSave_ReturnsCreatedResponse() {
    	CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setTitle("Java");
        categoryDto.setDescription("Java mcq");
        when(categoryService.addCategory(any(CategoryDto.class))).thenReturn(categoryDto);
        ResponseEntity<String> response = categoryController.saveCategory(categoryDto);
        verify(categoryService, times(1)).addCategory(categoryDto);
        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody().equals("category added successfully.");
    }

 

	@Test
    void saveCategory_DuplicateCategory_ReturnsConflictResponse() throws DuplicateResourceException {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setTitle("Java");
        categoryDto.setDescription("Java mcq");
        doThrow(new DuplicateResourceException("Category already exists")).when(categoryService).addCategory(categoryDto);
        ResponseEntity<String> response = categoryController.saveCategory(categoryDto);

        verify(categoryService, times(1)).addCategory(categoryDto);
        assert response.getStatusCode() == HttpStatus.CONFLICT;
        assert response.getBody().equals("category already exist");
    }
	
	@Test
	public void testGetAllCategories() {
		CategoryDto categoryDto1 = new CategoryDto();
		categoryDto1.setCategoryId(1);
		categoryDto1.setTitle("Java");
		categoryDto1.setDescription("Java mcq");
		
		CategoryDto categoryDto2 = new CategoryDto();
		categoryDto2.setCategoryId(2);
		categoryDto2.setTitle("React");
		categoryDto2.setDescription("React mcq");
		
		List<CategoryDto> categoryList = Arrays.asList(categoryDto1, categoryDto2);
        when(categoryService.getAllCategories()).thenReturn(categoryList);
        List<CategoryDto> response = categoryController.getCategories();
        assertEquals(categoryList, response);
        verify(categoryService, times(1)).getAllCategories();      
}
	
        @Test
        public void testGetCategoryById_returnsCategoryExist_returnsCategoryDto() throws CategoryNotFoundException {
        	int categoryId = 1;
        	CategoryDto categoryDto = new CategoryDto(1,"Java","Java mcq");
        	when(categoryService.getCategoryById(categoryId)).thenReturn(categoryDto);
        	
        	ResponseEntity<?> response = categoryController.getCategoryById(categoryId);
        	verify(categoryService, times(1)).getCategoryById(categoryId);
        	assertEquals(HttpStatus.OK, response.getStatusCode());
        	assertEquals(categoryDto, response.getBody());
	    
        }
        
        @Test
        void getCategoryById_CategoryNotFound_ReturnsNotFoundResponse() throws CategoryNotFoundException {
            int categoryId = 1;
            when(categoryService.getCategoryById(categoryId)).thenThrow(new CategoryNotFoundException("Category not found"));

            ResponseEntity<?> response = categoryController.getCategoryById(categoryId);

            verify(categoryService, times(1)).getCategoryById(categoryId);
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertEquals("Category not found", response.getBody());
        }
        
        @Test
        void getCategoryById_ExceptionThrown_ReturnsInternalServerErrorResponse() throws CategoryNotFoundException {
            int categoryId = 1;
            when(categoryService.getCategoryById(categoryId)).thenThrow(new RuntimeException("Runtime exception"));

            ResponseEntity<?> response = categoryController.getCategoryById(categoryId);

            verify(categoryService, times(1)).getCategoryById(categoryId);
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            assertEquals("An error occurred while processing your request.", response.getBody());
        }               
	
	    @Test
	    public void testUpdateCategory() {
		 int categoryId = 1;
	        CategoryDto updatedCategoryDto = new CategoryDto(categoryId, "UpdatedCategory", "UpdateDescription");
	        when(categoryService.updateCategory(updatedCategoryDto, categoryId)).thenReturn(updatedCategoryDto);
	        CategoryDto result = categoryController.updateCategory(updatedCategoryDto, categoryId);
	        verify(categoryService, times(1)).updateCategory(updatedCategoryDto, categoryId);
	        assertEquals(updatedCategoryDto, result);
	}
	    @Test
	    public void testDeleteCategory() {
	        int categoryId = 1;
	        when(categoryService.deleteCategory(categoryId)).thenReturn("1 deleted successfully");
	        String response = categoryController.deleteCategory(categoryId);        
	        assertEquals("1 deleted successfully", response);
	        verify(categoryService, times(1)).deleteCategory(categoryId);
	    }

}
