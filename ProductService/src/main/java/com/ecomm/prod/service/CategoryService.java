package com.ecomm.prod.service;

import java.util.List;

import com.ecomm.prod.dto.CategoryDto;
import com.ecomm.prod.request.addCategoryRequest;
import com.ecomm.prod.request.updateCategoryRequest;



public interface CategoryService {

	CategoryDto addCategory(addCategoryRequest request);
	
	CategoryDto updateCategory(Integer categoryId,updateCategoryRequest request);
	
	CategoryDto getCategoryById(Integer categoryId);
	
	void deleteCategoryById(Integer categoryId);
	
	List<CategoryDto> getAllCategory();
}
