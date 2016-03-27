package com.ordermgmt.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ordermgmt.lm.repository.AbstractDao;
import com.ordermgmt.lm.repository.BookLoanDao;
import com.ordermgmt.model.BookLoan;
import com.ordermgmt.model.Borrower;
import com.ordermgmt.model.bean.CheckInSearchBean;

@Repository
@Transactional
public class BookLoanDaoImpl extends AbstractDao<Integer, BookLoan> implements BookLoanDao {

	@Override
	public int noOfCheckOuts(Borrower borrower) {
		Criteria criteria = createEntityCriteria();
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq("borrower.cardNo", borrower.getCardNo()));
		criterions.add(Restrictions.isNull("dateIn"));
		buildWhereClauseAnd(criteria, criterions);
		return criteria.list().size();
	}

	@Override
	public List<BookLoan> search(int pageNo, int maxResults, CheckInSearchBean checkInSearchBean) {
		Criteria criteria = createEntityCriteria(pageNo, maxResults);
		return search(criteria, checkInSearchBean);
	}

	@Override
	public List<BookLoan> search(CheckInSearchBean checkInSearchBean) {
		Criteria criteria = createEntityCriteria();
		return search(criteria, checkInSearchBean);
	}

	@SuppressWarnings("unchecked")
	public List<BookLoan> search(Criteria criteria, CheckInSearchBean bean) {
		// Search only for loans which have not yet been checked in
		criteria.add(Restrictions.isNull("dateIn"));
		if (bean == null)
			return criteria.list();

		List<Criterion> criterions = new ArrayList<>();

		if (!bean.getIsbn().trim().isEmpty())
			criterions.add(Restrictions.eq("bookCopy.id.bookId", bean.getIsbn().trim().trim()));

		if (bean.getBranchId() != 0) {
			criterions.add(Restrictions.eq("bookCopy.id.branchId", bean.getBranchId()));
		}

		if (bean.getCardNo().trim().isEmpty()) {
			criterions.add(Restrictions.eq("borrower.cardNo", bean.getCardNo().trim()));
		}

		String fullname = bean.getBorrowerName().trim();
		List<Criterion> nameCriterions = new ArrayList<>();
		if (!fullname.isEmpty()) {
			for (String component : fullname.split(" ")) {
				if (component.trim().isEmpty())
					continue;
				nameCriterions.add(Restrictions.ilike("borrower.fname", component, MatchMode.ANYWHERE));
				nameCriterions.add(Restrictions.ilike("borrower.lname", component, MatchMode.ANYWHERE));
			}
			criterions.add(buildWhereClauseOr(nameCriterions));
		}

		buildWhereClauseAnd(criteria, criterions);

		return criteria.list();
	}

}
