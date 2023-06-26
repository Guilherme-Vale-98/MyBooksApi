package com.guiLabs.MyBooksApi.Entities;


import java.util.ArrayList;
import java.util.List;

import com.guiLabs.MyBooksApi.model.BookGenre;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = "authors")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @NotBlank
    @NotNull
	private String title;
	
    @Enumerated(EnumType.STRING)
    @NotNull
	private BookGenre genre;
	
	@ManyToMany
	@Builder.Default
	@JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> authors = new ArrayList<>();
	
	public void addAuthor(Author author) {
		this.getAuthors().add(author);
		author.getBooks().add(this);
	}
	
	public void removeAuthor(Author author) {
		this.getAuthors().remove(author);
		author.getBooks().remove(this);
	}
	
}
