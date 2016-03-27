package com.ordermgmt.lm.repository;

import java.util.List;

import com.ordermgmt.model.BookLoan;
import com.ordermgmt.model.Borrower;
import com.ordermgmt.model.bean.CheckInSearchBean;

public interface BookLoanDao {

	public List<BookLoan> search(int pageNo, int maxResults, CheckInSearchBean checkInSearchBean);

	public List<BookLoan> search(CheckInSearchBean checkInSearchBean);

	public int noOfCheckOuts(Borrower borrower);

	public BookLoan save(BookLoan bookLoan);

	public BookLoan getByKey(Integer key);

}
