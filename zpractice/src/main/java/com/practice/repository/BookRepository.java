package com.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practice.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByNameContaining(String s);
	
	@Query("select b from Book b where b.price=:p")
	List<Book> getBookByPrice(@Param("p") double price);
	
	@Query(value = "select * from book", nativeQuery = true)
	List<Book> getAllBooksByNativeQuery();
}
