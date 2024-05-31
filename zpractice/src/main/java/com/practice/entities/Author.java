package com.practice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "author_id")
	private int aid;
	
	private String name;
	
	private int age;
	
	@OneToOne(mappedBy="author")
	@JsonBackReference
	private Book book;

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Author(int aid, String name, int age, Book book) {
		super();
		this.aid = aid;
		this.name = name;
		this.age = age;
		this.book = book;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Author [aid=" + aid + ", name=" + name + ", age=" + age + ", book=" + book + "]";
	}


	
	
	
}









