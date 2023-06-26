package com.guiLabs.MyBooksApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guiLabs.MyBooksApi.Entities.Author;



public interface AuthorRepository extends JpaRepository<Author, Integer> {
	Author findByNameEquals(String name);
}
