package com.nucleusteq.asessmentPlatform.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.nucleusteq.asessmentPlatform.dto.CategoryDto;

@Service
public interface CategoryService {

    CategoryDto addCategory(CategoryDto category);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(int id);

    CategoryDto updateCategory(CategoryDto category, int id);

    String deleteCategory(int id);

}
