package com.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
