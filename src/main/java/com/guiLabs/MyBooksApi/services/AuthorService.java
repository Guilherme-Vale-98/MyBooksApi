package com.guiLabs.MyBooksApi.services;

import java.util.List;

import com.guiLabs.MyBooksApi.Entities.Author;

public interface AuthorService{
	List<Author> getAllAuthors();
	Author getAuthorById(Integer Id);
	
}
