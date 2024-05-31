package com.blog.api.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.api.entities.Category;
import com.blog.api.entities.Post;
import com.blog.api.entities.User;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.PostDto;
import com.blog.api.payloads.PostResponse;
import com.blog.api.repository.CategoryRepository;
import com.blog.api.repository.PostRepository;
import com.blog.api.repository.UserRepository;
import com.blog.api.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user =	this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User_id", userId));
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category_id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savePost = this.postRepository.save(post);
		return this.modelMapper.map(savePost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post_id", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post upadtedPost = this.postRepository.save(post);
		
		return this.modelMapper.map(upadtedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post_id", postId));
		
		this.postRepository.delete(post);

	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> pagePost = this.postRepository.findAll(p);
		
		List<Post> posts = pagePost.getContent();
		
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post_id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category_id", categoryId));
		List<Post> posts = this.postRepository.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user =	this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User_id", userId));
		List<Post> posts = this.postRepository.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = this.postRepository.searchByTitle("%"+keyword+"%");
		
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos ;
	}

}
