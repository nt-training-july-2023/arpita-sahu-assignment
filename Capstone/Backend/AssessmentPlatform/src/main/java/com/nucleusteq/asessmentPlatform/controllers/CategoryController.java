package com.nucleusteq.asessmentPlatform.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nucleusteq.asessmentPlatform.entities.Category;
import com.nucleusteq.asessmentPlatform.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
		return new ResponseEntity<Category>(categoryService.addCategory(category), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public List<Category> getCategories() {
		return categoryService.getAllCategories();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Category getCategoryById(@PathVariable int id) {
		return categoryService.getCategoryById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Category updateCategory(@RequestBody Category category, @PathVariable int id) {
		return categoryService.updateCategory(category,id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteCategory(@PathVariable int id) {
		 categoryService.deleteCategory(id);
	}

}
