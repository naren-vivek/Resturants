package com.ordermgmt.repository.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ordermgmt.lm.repository.AbstractDao;
import com.ordermgmt.lm.repository.BookCopyDao;
import com.ordermgmt.model.BookCopy;
import com.ordermgmt.model.BookCopyPK;
import com.ordermgmt.model.bean.CheckOutSearchBean;

@Repository
@Transactional
public class BookCopyDaoImpl extends AbstractDao<BookCopyPK, BookCopy> implements BookCopyDao {

	@Override
	public List<BookCopy> search(int pageNo, int maxResults, CheckOutSearchBean bean) {
		Criteria criteria = createEntityCriteria(pageNo, maxResults);
		return search(criteria, bean);
	}

	@Override
	public List<BookCopy> search(CheckOutSearchBean bean) {
		Criteria criteria = createEntityCriteria();
		return search(criteria, bean);
	}

	@SuppressWarnings("unchecked")
	public List<BookCopy> search(Criteria criteria, CheckOutSearchBean bean) {
		// Eliminate all search results where branch does not own a copy
		criteria.add(Restrictions.ne("noOfCopies", 0));

		if (bean == null)
			return criteria.list();

		List<Criterion> criterions = new LinkedList<>();

		if (bean.getIsbn() != null && !bean.getIsbn().trim().isEmpty())
			criterions.add(Restrictions.eq("id.bookId", bean.getIsbn()));

		if (bean.getTitle() != null && !bean.getTitle().trim().isEmpty()) {
			List<Criterion> titleCriterions = new LinkedList<>();
			for (String component : bean.getIsbn().trim().split(" ")) {
				if (component.trim().isEmpty())
					continue;
				titleCriterions.add(stringMatch("book.title", component.trim()));
			}
			criterions.add(buildWhereClauseOr(titleCriterions));
		}

		if (bean.getAuthors() != null && !bean.getAuthors().isEmpty()) {
			criteria.createAlias("book.authors", "a");
			List<Criterion> authorCriterions = new LinkedList<>();
			for (String author : bean.getAuthors()) {
				for (String component : author.trim().split(" ")) {
					if (component.trim().isEmpty())
						continue;
					authorCriterions.add(Restrictions.ilike("a.fullname", component.trim(), MatchMode.ANYWHERE));
				}
			}
			criterions.add(buildWhereClauseOr(authorCriterions));
		}

		buildWhereClauseAnd(criteria, criterions);
		return criteria.list();
	}
}
