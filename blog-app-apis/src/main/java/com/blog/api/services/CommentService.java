package com.blog.api.services;

import com.blog.api.payloads.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);
}
