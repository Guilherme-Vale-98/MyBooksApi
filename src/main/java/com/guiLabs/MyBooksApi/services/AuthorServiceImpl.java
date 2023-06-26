package com.guiLabs.MyBooksApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.guiLabs.MyBooksApi.Entities.Author;
import com.guiLabs.MyBooksApi.repositories.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;
	
	@Override
	public List<Author> getAllAuthors() {
		List<Author> authors = authorRepository.findAll();
		return authors;
	}
	@Override
	public Author getAuthorById(Integer Id) {
		Optional<Author> author = authorRepository.findById(Id);
		return author.orElse(null);
	}


}
