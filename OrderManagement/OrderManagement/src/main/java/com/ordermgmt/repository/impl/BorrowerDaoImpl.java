package com.ordermgmt.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ordermgmt.lm.repository.AbstractDao;
import com.ordermgmt.lm.repository.BorrowerDao;
import com.ordermgmt.model.Borrower;

@Repository
@Transactional
public class BorrowerDaoImpl extends AbstractDao<String, Borrower> implements BorrowerDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Borrower> search(int pageNo, int maxResults, Borrower borrower) {
		Criteria criteria = createEntityCriteria(pageNo, maxResults);
		if (borrower == null)
			return criteria.list();

		List<Criterion> criterions = new ArrayList<>();

		if (borrower.getCardNo() != null && !borrower.getCardNo().trim().isEmpty())
			criterions.add(Restrictions.eq("cardNo", borrower.getCardNo().trim()));

		if (borrower.getFname() != null && !borrower.getSsn().trim().isEmpty())
			criterions.add(Restrictions.eq("fname", borrower.getFname().trim()));

		if (borrower.getLname() != null && !borrower.getLname().trim().isEmpty())
			criterions.add(Restrictions.eqProperty("lname", borrower.getLname().trim()));

		System.out.println("Before adding ssn");
		if (borrower.getSsn() != null && !borrower.getSsn().trim().isEmpty())
			criterions.add(Restrictions.eq("ssn", borrower.getSsn().trim()));
		System.out.println("Ssn added");
		buildWhereClauseAnd(criteria, criterions);
		System.out.println("where clause built");
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.utdallas.dbdesign.lm.repository.BorrowerDao#hasUniqueSsn(edu.utdallas
	 * .dbdesign.lm.model.Borrower)
	 */
	@Override
	public boolean hasUniqueSsn(Borrower borrower) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssn", borrower.getSsn()));
		return criteria.list().isEmpty();
	}

}
