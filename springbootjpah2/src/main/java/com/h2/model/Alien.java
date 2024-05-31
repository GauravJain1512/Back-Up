package com.h2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Alien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aid;
	
	private String name;

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

	@Override
	public String toString() {
		return "Alien [aid=" + aid + ", name=" + name + "]";
	}
	
	
}
