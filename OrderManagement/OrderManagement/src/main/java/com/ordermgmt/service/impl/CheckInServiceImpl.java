package com.ordermgmt.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ordermgmt.lm.repository.BookCopyDao;
import com.ordermgmt.lm.repository.BookLoanDao;
import com.ordermgmt.model.BookCopy;
import com.ordermgmt.model.BookLoan;
import com.ordermgmt.model.bean.CheckInSearchBean;
import com.ordermgmt.service.CheckInService;

@Service
@Transactional
public class CheckInServiceImpl implements CheckInService {

	@Autowired
	BookLoanDao bookLoanDao;

	@Autowired
	BookCopyDao bookCopyDao;

	@Override
	public List<BookLoan> search(int pageNo, int maxResults, CheckInSearchBean checkInSearchBean) {
		return bookLoanDao.search(pageNo, maxResults, checkInSearchBean);
	}

	@Override
	public List<BookLoan> search(CheckInSearchBean checkInSearchBean) {
		return bookLoanDao.search(checkInSearchBean);
	}

	@Override
	public BookLoan checkin(BookLoan bookLoan) {
		bookLoan = bookLoanDao.getByKey(bookLoan.getLoanId());
		bookLoan.setDateIn(new Date());

		BookCopy bookCopy = bookLoan.getBookCopy();
		bookCopy.setAvailCopies(bookCopy.getAvailCopies() + 1);
		bookCopyDao.save(bookCopy);

		return bookLoanDao.save(bookLoan);
	}
}
