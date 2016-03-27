package com.ordermgmt.model;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

/**
 * The persistent class for the book_loans database table.
 * 
 */
@Entity
@Table(name = "book_loans")
@NamedQuery(name = "BookLoan.findAll", query = "SELECT b FROM BookLoan b")
public class BookLoan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "loan_id")
	private int loanId;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_in")
	private Date dateIn;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_out")
	private Date dateOut;

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Date dueDate;

	// bi-directional many-to-one association to BookCopy
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "branch_id", referencedColumnName = "branch_id"),
			@JoinColumn(name = "isbn", referencedColumnName = "book_id") })
	private BookCopy bookCopy;

	// bi-directional many-to-one association to Borrower
	@ManyToOne
	@JoinColumn(name = "card_no")
	private Borrower borrower;

	// bi-directional one-to-one association to Fine
	@JsonIgnore
	@OneToOne(mappedBy = "bookLoan")
	private Fine fine;

	public BookLoan() {
	}

	public int getLoanId() {
		return this.loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public Date getDateIn() {
		return this.dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Date getDateOut() {
		return this.dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public BookCopy getBookCopy() {
		return this.bookCopy;
	}

	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

	public Borrower getBorrower() {
		return this.borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public Fine getFine() {
		return this.fine;
	}

	public void setFine(Fine fine) {
		this.fine = fine;
	}

}