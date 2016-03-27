package com.ordermgmt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ordermgmt.lm.repository.BorrowerDao;
import com.ordermgmt.model.Borrower;
import com.ordermgmt.service.BorrowerService;

@Service
@Transactional
public class BorrowerServiceImpl implements BorrowerService {

	@Autowired
	BorrowerDao dao;

	public Borrower save(Borrower borrower) {
		if (dao.hasUniqueSsn(borrower))
			return dao.save(borrower);
		return null;
	}

	@Override
	public List<Borrower> search(int pageNo, int maxResults, Borrower borrower) {
		return dao.search(pageNo, maxResults, borrower);
	}

}
