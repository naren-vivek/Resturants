package com.ordermgmt.service;

import java.util.List;

import com.ordermgmt.model.BookLoan;
import com.ordermgmt.model.bean.CheckInSearchBean;

public interface CheckInService {

	public List<BookLoan> search(int pageNo, int maxResults, CheckInSearchBean checkInSearchBean);
	
	public List<BookLoan> search(CheckInSearchBean checkInSearchBean);

	public BookLoan checkin(BookLoan bookLoan);
}
