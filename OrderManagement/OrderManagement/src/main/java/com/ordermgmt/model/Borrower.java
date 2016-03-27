package com.ordermgmt.model;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

/**
 * The persistent class for the borrower database table.
 * 
 */
/**
 * @author Sandeep
 *
 */
@Entity
@NamedQuery(name = "Borrower.findAll", query = "SELECT b FROM Borrower b")
public class Borrower implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "sequence_card_no", strategy = "edu.utdallas.dbdesign.lm.model.annotation.BorrowerGenerator")
	@GeneratedValue(generator = "sequence_card_no")
	@Column(name = "card_no")
	private String cardNo;

	private String address;

	private String city;

	private String email;

	private String fname;

	private String lname;

	private String phone;

	private String ssn;

	private String state;

	// bi-directional many-to-one association to BookLoan
	@JsonIgnore
	@OneToMany(mappedBy = "borrower")
	private List<BookLoan> bookLoans;

	@Transient
	private float fine;

	@Transient
	private float payable;

	public Borrower() {
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<BookLoan> getBookLoans() {
		return this.bookLoans;
	}

	public void setBookLoans(List<BookLoan> bookLoans) {
		this.bookLoans = bookLoans;
	}

	public BookLoan addBookLoan(BookLoan bookLoan) {
		getBookLoans().add(bookLoan);
		bookLoan.setBorrower(this);

		return bookLoan;
	}

	public BookLoan removeBookLoan(BookLoan bookLoan) {
		getBookLoans().remove(bookLoan);
		bookLoan.setBorrower(null);

		return bookLoan;
	}

	public float getFine() {
		return fine;
	}

	public void setFine(float fine) {
		this.fine = fine;
	}

	public float getPayable() {
		return payable;
	}

	public void setPayable(float payable) {
		this.payable = payable;
	}

	@Override
	public String toString() {
		return "Borrower [cardNo=" + cardNo + ", fname=" + fname + ", lname=" + lname + ", ssn=" + ssn + "]";
	}

}