package com.ordermgmt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the book_copies database table.
 * 
 */
@Embeddable
public class BookCopyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="book_id", insertable=false, updatable=false)
	private String bookId;

	@Column(name="branch_id", insertable=false, updatable=false)
	private int branchId;

	public BookCopyPK() {
	}
	public String getBookId() {
		return this.bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public int getBranchId() {
		return this.branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BookCopyPK)) {
			return false;
		}
		BookCopyPK castOther = (BookCopyPK)other;
		return 
			this.bookId.equals(castOther.bookId)
			&& (this.branchId == castOther.branchId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.bookId.hashCode();
		hash = hash * prime + this.branchId;
		
		return hash;
	}
}