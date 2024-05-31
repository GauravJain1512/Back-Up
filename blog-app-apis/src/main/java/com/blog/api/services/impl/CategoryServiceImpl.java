package com.blog.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.entities.Category;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.CategoryDto;
import com.blog.api.repository.CategoryRepository;
import com.blog.api.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category saveCategory = this.categoryRepository.save(category);
		
		return this.modelMapper.map(saveCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto upadteCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
	
		Category updateCategory = this.categoryRepository.save(category);
		
		return this.modelMapper.map(updateCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category category = this.categoryRepository.findById(categoryId)
		.orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
		
		this.categoryRepository.delete(category);

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
				
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> category = this.categoryRepository.findAll();
		
		List<CategoryDto> categoryDtos = category.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return categoryDtos;
		
	}

}
