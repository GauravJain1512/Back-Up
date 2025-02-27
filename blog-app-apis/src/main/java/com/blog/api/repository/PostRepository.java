package com.blog.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.api.entities.Category;
import com.blog.api.entities.Post;
import com.blog.api.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	//List<Post> findByTitleContaining(String title);
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title);
}
