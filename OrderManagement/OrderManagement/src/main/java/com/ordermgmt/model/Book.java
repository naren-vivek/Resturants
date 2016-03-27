package com.ordermgmt.model;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

/**
 * The persistent class for the book database table.
 * 
 */
@Entity
@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String isbn;

	private String cover;

	private String isbn13;

	private int pages;

	private String publisher;

	private String title;

	// bi-directional many-to-many association to Author
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "book_authors", joinColumns = { @JoinColumn(name = "isbn") }, inverseJoinColumns = {
			@JoinColumn(name = "author_id") })
	private List<Author> authors;

	// bi-directional many-to-one association to BookCopy
	@JsonIgnore
	@OneToMany(mappedBy = "book")
	private List<BookCopy> bookCopies;

	public Book() {
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCover() {
		return this.cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getIsbn13() {
		return this.isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public int getPages() {
		return this.pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return this.authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<BookCopy> getBookCopies() {
		return this.bookCopies;
	}

	public void setBookCopies(List<BookCopy> bookCopies) {
		this.bookCopies = bookCopies;
	}

	public BookCopy addBookCopy(BookCopy bookCopy) {
		getBookCopies().add(bookCopy);
		bookCopy.setBook(this);

		return bookCopy;
	}

	public BookCopy removeBookCopy(BookCopy bookCopy) {
		getBookCopies().remove(bookCopy);
		bookCopy.setBook(null);

		return bookCopy;
	}

}