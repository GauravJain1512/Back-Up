package com.blog.api.services;

import java.util.List;

import com.blog.api.entities.Post;
import com.blog.api.payloads.PostDto;
import com.blog.api.payloads.PostResponse;

public interface PostService {

	//create
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update
	public PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	public void deletePost(Integer postId);
	
	//get all post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	//get single post
	PostDto getPostById(Integer postId);
	
	// get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	// get all post by user
	List<PostDto> getPostsByUser(Integer userId);
	
	//search
	List<PostDto> searchPost(String keyword);
	
}
