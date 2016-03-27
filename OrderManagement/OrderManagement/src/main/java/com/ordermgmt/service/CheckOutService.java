package com.ordermgmt.service;

import java.util.List;

import com.ordermgmt.model.BookCopy;
import com.ordermgmt.model.BookLoan;
import com.ordermgmt.model.bean.CheckOutSearchBean;

public interface CheckOutService {

	public List<BookCopy> search(int pageNo, int maxResults, CheckOutSearchBean checkOutSearchBean);

	public List<BookCopy> search(CheckOutSearchBean checkOutSearchBean);

	public boolean isValidBorrower(String cardNo);

	public int noOfCheckOuts(String cardNo);

	public BookLoan checkOut(BookCopy bookCopy, String cardNo);

}
