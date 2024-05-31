package com.api.book.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int author_id;
	
	@Column(name="first_name")
	private String firstName;
	private String lastName;
	private String langauge;
	
	@OneToOne(mappedBy = "author")
	@JsonBackReference
	private Book book;

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Author(int author_id, String firstName, String lastName, String langauge, Book book) {
		super();
		this.author_id = author_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.langauge = langauge;
		this.book = book;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLangauge() {
		return langauge;
	}

	public void setLangauge(String langauge) {
		this.langauge = langauge;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
}
