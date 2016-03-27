package com.ordermgmt.model;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

/**
 * The persistent class for the library_branch database table.
 * 
 */
@Entity
@Table(name = "library_branch")
@NamedQuery(name = "LibraryBranch.findAll", query = "SELECT l FROM LibraryBranch l")
public class LibraryBranch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "IdOrGenerated", strategy = "edu.utdallas.dbdesign.lm.model.UseExistingOrGenerateIdGenerator")
	@Column(name = "branch_id")
	private int branchId;

	private String address;

	@Column(name = "branch_name")
	private String branchName;

	// bi-directional many-to-one association to BookCopy
	@JsonIgnore
	@OneToMany(mappedBy = "libraryBranch")
	private List<BookCopy> bookCopies;

	public LibraryBranch() {
	}

	public int getBranchId() {
		return this.branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public List<BookCopy> getBookCopies() {
		return this.bookCopies;
	}

	public void setBookCopies(List<BookCopy> bookCopies) {
		this.bookCopies = bookCopies;
	}

	public BookCopy addBookCopy(BookCopy bookCopy) {
		getBookCopies().add(bookCopy);
		bookCopy.setLibraryBranch(this);

		return bookCopy;
	}

	public BookCopy removeBookCopy(BookCopy bookCopy) {
		getBookCopies().remove(bookCopy);
		bookCopy.setLibraryBranch(null);

		return bookCopy;
	}

}