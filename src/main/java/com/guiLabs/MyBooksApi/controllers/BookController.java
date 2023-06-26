package com.guiLabs.MyBooksApi.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.guiLabs.MyBooksApi.Entities.Book;
import com.guiLabs.MyBooksApi.bootstrap.NotFoundException;
import com.guiLabs.MyBooksApi.services.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookController {
	public static final String BOOK_PATH = "/api/v1/books";
    public static final String BOOK_PATH_ID = BOOK_PATH + "/{bookId}";
    
    private final BookService bookService;
    
    @GetMapping(value = BOOK_PATH)
    ResponseEntity<List<Book>> listBooks() {
    	List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    @GetMapping(value = BOOK_PATH_ID)
    ResponseEntity<Book> getBookById(@PathVariable("bookId") Integer id) {
    	Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    
    @PostMapping(BOOK_PATH)
    public ResponseEntity postBook(@Validated @RequestBody Book book){
        Book savedBook = bookService.saveNewBook(book);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", BOOK_PATH + "/" + savedBook.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
    
    @DeleteMapping(BOOK_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("bookId") Integer bookId){

        if(!bookService.deleteByBookId(bookId)){
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping(BOOK_PATH_ID)
    public ResponseEntity updateById(@PathVariable("bookId")Integer bookId, @Validated @RequestBody Book book){

        if( bookService.updateByBookId(bookId, book) == null){
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
