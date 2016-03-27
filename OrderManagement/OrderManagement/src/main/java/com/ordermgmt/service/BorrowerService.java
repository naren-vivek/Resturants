package com.ordermgmt.service;

import java.util.List;

import com.ordermgmt.model.Borrower;

public interface BorrowerService {

	public List<Borrower> search(int pageNo, int maxResults, Borrower borrower);

	public Borrower save(Borrower borrower);

}
