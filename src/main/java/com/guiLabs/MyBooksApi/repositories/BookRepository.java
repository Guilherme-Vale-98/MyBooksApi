package com.guiLabs.MyBooksApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guiLabs.MyBooksApi.Entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
