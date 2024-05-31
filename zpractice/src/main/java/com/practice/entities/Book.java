package com.practice.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "book_id")
	private int bid;
	
	private String name;
	
	private double price;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private Author author;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int bid, String name, double price, Author author) {
		super();
		this.bid = bid;
		this.name = name;
		this.price = price;
		this.author = author;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [bid=" + bid + ", name=" + name + ", price=" + price + ", author=" + author + "]";
	}

	
	
	
	
}
