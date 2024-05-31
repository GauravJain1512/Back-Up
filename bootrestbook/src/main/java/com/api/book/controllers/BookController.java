package com.api.book.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entities.Book;
import com.api.book.services.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
		
		List<Book> list = this.bookService.getAllBooks();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		return ResponseEntity.of(Optional.of(list));
	}
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		
		Book book = this.bookService.getBook(id);
		if(book==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book b = null;
		try {
		b= this.bookService.addBook(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(book);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	
	}
	
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
		try {
		this.bookService.deleteBook(bookId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//Update book handler
	@PutMapping("/books/{bid}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("bid") int bid) {
		try {
		this.bookService.updateBook(book,bid);
		return ResponseEntity.ok().body(book);
		}catch (Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	
}
