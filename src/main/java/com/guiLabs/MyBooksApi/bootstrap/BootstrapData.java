package com.guiLabs.MyBooksApi.bootstrap;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.guiLabs.MyBooksApi.Entities.Author;
import com.guiLabs.MyBooksApi.Entities.Book;
import com.guiLabs.MyBooksApi.model.BookGenre;
import com.guiLabs.MyBooksApi.repositories.AuthorRepository;
import com.guiLabs.MyBooksApi.repositories.BookRepository;

import lombok.RequiredArgsConstructor;

/**
 * Created by jt, Spring Framework Guru.
 */
@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {
        loadInitData();
    }

    private void loadInitData() {
        if( bookRepository.count() == 0) {
        	Author author1 = Author.builder().name("Carl Sagan").build();
        	Author author2 = Author.builder().name("J. R. R. Tolkien").build();       
            authorRepository.saveAll(Arrays.asList(author1,author2));
            
//            for(Long i = 0L; i<10000000000000L; i +=1) {
//            	Author tempauthor = authorRepository.save(author1.builder().name("Carl" + i).build());
//            	Book temp = Book.builder().title("book"+ i).genre(BookGenre.SCIENCE).build();
//            	temp.addAuthor(tempauthor);
//            	bookRepository.save(temp);
//            }
            
            Book book2 = Book.builder().title("Cosmos").genre(BookGenre.SCIENCE).build();
            Book book1 = Book.builder().title("The hobbit").genre(BookGenre.FANTASY).build();        
        	book1.addAuthor(author2);
        	book2.addAuthor(author1);
        	bookRepository.saveAll(Arrays.asList(book1,book2));
        }
    }
}
