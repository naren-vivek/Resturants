package com.ordermgmt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ordermgmt.lm.repository.FinesDao;
import com.ordermgmt.model.Borrower;
import com.ordermgmt.service.FineService;

@Service
@Transactional
public class FineServiceImpl implements FineService {

	@Autowired
	FinesDao finesDao;

	@Override
	public List<Borrower> search() {
		return finesDao.search(0, 0);
	}

	@Override
	public List<Borrower> search(int pageNo, int maxResults) {
		return finesDao.search(pageNo, maxResults);
	}

	@Override
	public void updateFines() {
		finesDao.updateFines();
	}

	@Override
	public void settleFines(String cardNo) {
		finesDao.settleFines(cardNo);		
	}

}
