package com.blog.api.services;

import java.util.List;

import com.blog.api.entities.Category;
import com.blog.api.payloads.CategoryDto;

public interface CategoryService {

	//create
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	public CategoryDto upadteCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	public void deleteCategory(Integer categoryId);
	
	//get
	public CategoryDto getCategory(Integer categoryId);
	
	//get All
	public List<CategoryDto> getCategories();
}
