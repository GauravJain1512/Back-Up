package com.h2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.h2.model.Alien;

public interface AlientRepo extends JpaRepository<Alien, Integer> {

}
