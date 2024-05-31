package com.practice.service;

import java.util.List;

import com.practice.entities.Book;

public interface BookService {
	
	public Book getBook(int id);
	
	public List<Book> getAllBooks();
	
	public void createBook(Book book);
	
	public Book updateBook(Book book, int id);
	
	public Book deleteBook(int id);
	
	public List<Book> findBooksByStartingLetters(String s);

	public List<Book> getBookByPrice(double d);
	
	public List<Book> allBook();
	
	

}
