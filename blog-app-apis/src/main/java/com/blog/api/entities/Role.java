package com.blog.api.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role {

	@Id
	private Integer roleId;
	
	private String name;
	
//	@ManyToMany
//	private Set<User> users;
}
