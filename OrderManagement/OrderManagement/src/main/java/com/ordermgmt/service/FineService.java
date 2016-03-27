package com.ordermgmt.service;

import java.util.List;

import com.ordermgmt.model.Borrower;

public interface FineService {
	
	public void updateFines();
	
	public void settleFines(String cardNo);

	public List<Borrower> search(int pageNo, int maxResults);

	public List<Borrower> search();

}
