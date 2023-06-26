package com.guiLabs.MyBooksApi.services;

import java.util.List;

import com.guiLabs.MyBooksApi.Entities.Book;

public interface BookService{
	List<Book> getAllBooks();
	Book getBookById(Integer Id);
	Book saveNewBook(Book book);
}
