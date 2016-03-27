package com.ordermgmt.lm.repository;

import java.util.List;

import com.ordermgmt.model.BookCopy;
import com.ordermgmt.model.BookCopyPK;
import com.ordermgmt.model.bean.CheckOutSearchBean;

public interface BookCopyDao {

	public List<BookCopy> search(int pageNo, int maxResults, CheckOutSearchBean bean);

	public List<BookCopy> search(CheckOutSearchBean checkOutSearchBean);

	public BookCopy save(BookCopy bookCopy);

	public BookCopy getByKey(BookCopyPK key);

}
