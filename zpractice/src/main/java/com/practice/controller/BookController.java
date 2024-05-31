package com.practice.controller;

import java.security.PublicKey;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.entities.Book;
import com.practice.service.impl.BookServiceImplementation;

@RestController
public class BookController {
	
	@Autowired
	private BookServiceImplementation bookServiceImplementation;
	
	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public ResponseEntity<Book> getBook(@PathVariable("id") int id){
		Book b = null;
		try {
			Book book = this.bookServiceImplementation.getBook(id);
			return ResponseEntity.status(HttpStatus.OK).body(book);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks(){
		try {
			List<Book> books = this.bookServiceImplementation.getAllBooks();
			return ResponseEntity.status(HttpStatus.OK).body(books);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/books")
	public ResponseEntity<Void> createBook(@RequestBody Book book){
		try {
			if(book != null) {
				this.bookServiceImplementation.createBook(book);
				return ResponseEntity.status(HttpStatus.CREATED).build();
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int id){
		try {
			Book book2 = this.bookServiceImplementation.updateBook(book, id);
			return ResponseEntity.status(HttpStatus.OK).body(book);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable("id") int id){
		try {
			Book book = this.bookServiceImplementation.deleteBook(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(book);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{s}")
	public ResponseEntity<List<Book>> findBookByName(@PathVariable("s") String s){
		try {
			List<Book> books = this.bookServiceImplementation.findBooksByStartingLetters(s);
			return ResponseEntity.status(HttpStatus.OK).body(books);
		}catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/price/{p}")
	public ResponseEntity<List<Book>> getBooksByPrice(@PathVariable("p") double price){
		try {
			List<Book> books = this.bookServiceImplementation.getBookByPrice(price);
			return ResponseEntity.status(HttpStatus.OK).body(books);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/native")
	public ResponseEntity<List<Book>> allBook(){
		try {
			List<Book> allBook = this.bookServiceImplementation.allBook();
			return ResponseEntity.status(HttpStatus.OK).body(allBook);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}



