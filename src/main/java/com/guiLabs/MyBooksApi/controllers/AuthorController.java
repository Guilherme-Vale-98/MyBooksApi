package com.guiLabs.MyBooksApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.guiLabs.MyBooksApi.Entities.Author;
import com.guiLabs.MyBooksApi.services.AuthorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthorController {
	public static final String AUTHOR_PATH = "/api/v1/authors";
    public static final String AUTHOR_PATH_ID = AUTHOR_PATH + "/{authorsId}";
    
    private final AuthorService authorService;
    
    @GetMapping(value = AUTHOR_PATH)
    ResponseEntity<List<Author>> listAuthors() {
    	List<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
    
    @GetMapping(value = AUTHOR_PATH_ID)
    ResponseEntity<Author> getAuthorById(@PathVariable("authorsId") Integer id) {
    	Author author = authorService.getAuthorById(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
}
