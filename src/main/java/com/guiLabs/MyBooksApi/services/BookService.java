package com.guiLabs.MyBooksApi.services;

import java.util.List;

import com.guiLabs.MyBooksApi.Entities.Book;

public interface BookService{
	List<Book> getAllBooks();
	Book getBookById(Integer Id);
	Book saveNewBook(Book book);
	Book updateByBookId(Integer bookId, Book book);
	Boolean deleteByBookId(Integer bookId);
	Book patchBookById(Integer bookId, Book book);
}
