package com.blog.api.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.api.config.AppConstants;
import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.PostDto;
import com.blog.api.payloads.PostResponse;
import com.blog.api.services.FileService;
import com.blog.api.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId){
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	// get by user -- apply pegination
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId){
		List<PostDto> postsByUser = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postsByUser,HttpStatus.OK);
	}

	// get by category -- apply pegination
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") Integer categoryId){
		List<PostDto> postsByCategory = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postsByCategory,HttpStatus.OK);
	}
	
	// get All posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
			
			){
		PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	//get post by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId){
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	// delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable("postId") Integer postId){
		
		this.postService.deletePost(postId);
		
		return new ApiResponse("Post is successfully deleted", true);
	}
	
	// update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Integer postId){
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	// search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
		
		List<PostDto> resultPosts = this.postService.searchPost(keywords);
		return new ResponseEntity<List<PostDto>>(resultPosts,HttpStatus.OK);
	}
	
	// post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile file,
			@PathVariable("postId") Integer postId
			) throws IOException{
		
		PostDto postDto = this.postService.getPostById(postId);
		
		String fileName = this.fileService.uploadImage(path, file);
		
		postDto.setImageName(fileName);
		
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	
	//method to serve files
	@GetMapping(value = "/post/image/{imageName}", produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);
		org.springframework.util.StreamUtils.copy(resource, response.getOutputStream());
	}
}






