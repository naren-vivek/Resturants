package com.ordermgmt.lm.repository;

import java.util.List;

import com.ordermgmt.model.Borrower;

public interface FinesDao {

	public void updateFines();

	public List<Borrower> search(int pageNo, int maxResults);
	
	public void settleFines(String cardNo);

}
