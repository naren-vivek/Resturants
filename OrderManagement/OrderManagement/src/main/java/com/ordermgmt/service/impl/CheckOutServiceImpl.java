package com.ordermgmt.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ordermgmt.lm.repository.BookCopyDao;
import com.ordermgmt.lm.repository.BookLoanDao;
import com.ordermgmt.lm.repository.BorrowerDao;
import com.ordermgmt.model.BookCopy;
import com.ordermgmt.model.BookLoan;
import com.ordermgmt.model.Borrower;
import com.ordermgmt.model.bean.CheckOutSearchBean;
import com.ordermgmt.service.CheckOutService;

@Service
@Transactional
public class CheckOutServiceImpl implements CheckOutService {

	@Autowired
	BookCopyDao bookCopyDao;

	@Autowired
	BookLoanDao bookLoanDao;

	@Autowired
	BorrowerDao borrowerDao;

	@Override
	public List<BookCopy> search(int pageNo, int maxResults, CheckOutSearchBean checkOutSearchBean) {
		return bookCopyDao.search(pageNo, maxResults, checkOutSearchBean);
	}

	@Override
	public List<BookCopy> search(CheckOutSearchBean checkOutSearchBean) {
		return bookCopyDao.search(checkOutSearchBean);
	}

	@Override
	public BookLoan checkOut(BookCopy bookCopy, String cardNo) {
		Borrower borrower = new Borrower();
		borrower.setCardNo(cardNo);

		return checkOut(bookCopy, borrower);
	}

	public BookLoan checkOut(BookCopy bookCopy, Borrower borrower) {
		bookCopy = bookCopyDao.getByKey(bookCopy.getId());

		Date dateOut = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateOut);
		cal.add(Calendar.DATE, 14);
		Date dueDate = cal.getTime();

		BookLoan bookLoan = new BookLoan();
		bookLoan.setBookCopy(bookCopy);
		bookLoan.setBorrower(borrower);
		bookLoan.setDateOut(dateOut);
		bookLoan.setDueDate(dueDate);

		bookCopy.setAvailCopies(bookCopy.getAvailCopies() - 1);
		bookCopyDao.save(bookCopy);
		return bookLoanDao.save(bookLoan);
	}

	@Override
	public boolean isValidBorrower(String cardNo) {
		Borrower borrower = borrowerDao.getByKey(cardNo);
		return borrower != null;
	}

	@Override
	public int noOfCheckOuts(String cardNo) {
		Borrower borrower = borrowerDao.getByKey(cardNo);
		return bookLoanDao.noOfCheckOuts(borrower);
	}

}
