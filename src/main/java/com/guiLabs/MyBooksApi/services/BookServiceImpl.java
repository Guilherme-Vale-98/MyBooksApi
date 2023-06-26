package com.guiLabs.MyBooksApi.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.guiLabs.MyBooksApi.Entities.Author;
import com.guiLabs.MyBooksApi.Entities.Book;
import com.guiLabs.MyBooksApi.bootstrap.NotFoundException;
import com.guiLabs.MyBooksApi.repositories.AuthorRepository;
import com.guiLabs.MyBooksApi.repositories.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Override
	public List<Book> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		return books;
	}
	
	@Override
	public Book getBookById(Integer bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(NotFoundException::new);
		return book;
	}

	@Override
	public Book saveNewBook(Book book) {
		saveAuthors(book);		
		
		Book savedBook = bookRepository.save(book);
		return savedBook;
	
    }

	private void saveAuthors(Book book) {
		for(int i = 0 ; i < book.getAuthors().size(); i +=1) {
			if (authorRepository.findAll().contains(book.getAuthors().get(i))) {
				Author temp = authorRepository.findByNameEquals(book.getAuthors().get(i).getName());
				book.getAuthors().get(i).setId(temp.getId());
				authorRepository.save(book.getAuthors().get(i));
			} else {
				authorRepository.save(book.getAuthors().get(i));
			}
		}
	}

	@Override
	public Book updateByBookId(Integer bookId, Book book) {
		AtomicReference<Book> atomicReference = new AtomicReference<>();
		
		bookRepository.findById(bookId).ifPresentOrElse(foundBook -> {
			foundBook.setTitle(book.getTitle());
			foundBook.setGenre(book.getGenre());
			saveAuthors(book);
			foundBook.setAuthors(book.getAuthors());
			atomicReference.set(bookRepository.save(foundBook));
			
			
		}, () ->{
			atomicReference.set(null);
		});
		
		return atomicReference.get();
	}

	@Override
	public Boolean deleteByBookId(Integer bookId) {
		if (bookRepository.existsById(bookId)) {
			bookRepository.deleteById(bookId);
			return true;
		}
		return false;
		
	}
}