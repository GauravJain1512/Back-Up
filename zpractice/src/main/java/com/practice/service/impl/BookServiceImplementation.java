package com.practice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.entities.Book;
import com.practice.repository.BookRepository;
import com.practice.service.BookService;

@Service
public class BookServiceImplementation implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book getBook(int id) {
		Book b = null;
		
		Optional<Book> book = this.bookRepository.findById(id);
		b = book.get();
	
		return b;
	}

	@Override
	public List<Book> getAllBooks() {
		
		List<Book> books = this.bookRepository.findAll();
		return books;
	}

	@Override
	public void createBook(Book book) {
		
		Book b = this.bookRepository.save(book);
		
	}

	@Override
	public Book updateBook(Book book, int id) {
	
		Optional<Book> b = this.bookRepository.findById(id);
		
		if(b.get() != null) {
			book.setBid(id);
			Book book1 = this.bookRepository.save(book);
			return book1;
		}else {
			return null;
		}
		
		
	}

	@Override
	public Book deleteBook(int id) {
		
		Optional<Book> book = this.bookRepository.findById(id);
		Book b = book.get();
		if(book.get() != null) {
			this.bookRepository.deleteById(id);
			return b;
		}else {
			return null;
		}
		
	}

	@Override
	public List<Book> findBooksByStartingLetters(String s) {
		List<Book> books = this.bookRepository.findByNameContaining(s);
		
		return books;
	}

	@Override
	public List<Book> getBookByPrice(double d) {
		
		List<Book> books = this.bookRepository.getBookByPrice(d);
		return books;
	}

	@Override
	public List<Book> allBook() {
		
		List<Book> books = this.bookRepository.getAllBooksByNativeQuery();
		
		return books;
	}
}
