package com.ordermgmt.lm.repository;

import java.util.List;

import com.ordermgmt.model.Borrower;

public interface BorrowerDao {

	public List<Borrower> search(int pageNo, int maxResults, Borrower borrower);

	public Borrower save(Borrower borrower);

	public boolean hasUniqueSsn(Borrower borrower);

	public Borrower getByKey(String key);
}
