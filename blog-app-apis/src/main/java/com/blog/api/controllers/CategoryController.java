package com.blog.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.CategoryDto;
import com.blog.api.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	// create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	//update  
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer categoryId){
		CategoryDto upadteCategory = this.categoryService.upadteCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(upadteCategory,HttpStatus.OK);
	}
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Integer id){
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfull",true),HttpStatus.OK);
	}
	//get
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer categoryId){
		CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		List<CategoryDto> categories = this.categoryService.getCategories();
		return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
	}
}







