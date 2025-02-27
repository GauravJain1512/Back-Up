package com.blog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.api.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
