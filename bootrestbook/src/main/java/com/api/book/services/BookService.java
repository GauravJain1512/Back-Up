package com.api.book.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.dao.BookRepository;
import com.api.book.entities.Book;

@Service
public class BookService {

//	private static List<Book> list = new ArrayList<>();
	
//	static {
//		list.add(new Book(12,"Java Oops","Ram"));
//		list.add(new Book(13,"Python Oops","Prasad"));
//		list.add(new Book(14,"C++ Oops","Mayur"));
//	}
	@Autowired
	private BookRepository bookRepository;
	
	// get all books
	public List<Book> getAllBooks(){
		List<Book> list = (List<Book>)this.bookRepository.findAll();
		return list ;
	}
	
	// get single book
	public Book getBook(int id) {
		Book book = null;
		try {
//		book =list.stream().filter(e->e.getId()==id).findFirst().get();
		 book = this.bookRepository.findById(id);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
	//create book
	public Book addBook(Book book) {
		Book result = this.bookRepository.save(book);
		return result;
	}
	// delete book
	public void deleteBook(int bookId) {
	
	//	list = list.stream().filter(book->book.getId()!=bookId).collect(Collectors.toList());
	this.bookRepository.deleteById(bookId);
	}

	// update the book
	public void updateBook(Book book, int bid) {
		
//		list = list.stream().map(b->{
//			if(b.getId()==bid) {
//				b.setTitle(book.getTitle());
//				b.setAuthor(book.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());
		book.setId(bid);
		this.bookRepository.save(book);
		
	}

	
}
