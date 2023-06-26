package com.guiLabs.MyBooksApi.Entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    @NotBlank
    @NotNull
	private String name;
	
	@Builder.Default
	@JsonIgnore
	@ManyToMany(mappedBy = "authors")
	private Set<Book> books = new HashSet<>();

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(name, other.name);
	}



	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
