package com.ordermgmt.model;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

/**
 * The persistent class for the book_copies database table.
 * 
 */
@Entity
@Table(name = "book_copies")
@NamedQuery(name = "BookCopy.findAll", query = "SELECT b FROM BookCopy b")
public class BookCopy implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BookCopyPK id;

	@Column(name = "avail_copies")
	private int availCopies;

	@Column(name = "no_of_copies")
	private int noOfCopies;

	// bi-directional many-to-one association to Book
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id", insertable = false, updatable = false)
	private Book book;

	// bi-directional many-to-one association to LibraryBranch
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "branch_id", insertable = false, updatable = false)
	private LibraryBranch libraryBranch;

	// bi-directional many-to-one association to BookLoan
	@JsonIgnore
	@OneToMany(mappedBy = "bookCopy")
	private List<BookLoan> bookLoans;

	public BookCopy() {
	}

	public BookCopyPK getId() {
		return this.id;
	}

	public void setId(BookCopyPK id) {
		this.id = id;
	}

	public int getAvailCopies() {
		return this.availCopies;
	}

	public void setAvailCopies(int availCopies) {
		this.availCopies = availCopies;
	}

	public int getNoOfCopies() {
		return this.noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LibraryBranch getLibraryBranch() {
		return this.libraryBranch;
	}

	public void setLibraryBranch(LibraryBranch libraryBranch) {
		this.libraryBranch = libraryBranch;
	}

	public List<BookLoan> getBookLoans() {
		return this.bookLoans;
	}

	public void setBookLoans(List<BookLoan> bookLoans) {
		this.bookLoans = bookLoans;
	}

	public BookLoan addBookLoan(BookLoan bookLoan) {
		getBookLoans().add(bookLoan);
		bookLoan.setBookCopy(this);

		return bookLoan;
	}

	public BookLoan removeBookLoan(BookLoan bookLoan) {
		getBookLoans().remove(bookLoan);
		bookLoan.setBookCopy(null);

		return bookLoan;
	}

}