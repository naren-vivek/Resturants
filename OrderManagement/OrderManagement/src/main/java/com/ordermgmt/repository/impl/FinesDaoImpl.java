package com.ordermgmt.repository.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ordermgmt.lm.repository.AbstractDao;
import com.ordermgmt.lm.repository.FinesDao;
import com.ordermgmt.model.Borrower;
import com.ordermgmt.model.Fine;

@Repository
@Transactional
public class FinesDaoImpl extends AbstractDao<String, Fine> implements FinesDao {

	@Override
	public void updateFines() {
		Session session = getSession();
		String queryString = " replace into fines "
				+ " select loan_id, (datediff(ifnull(date_in, curdate()), due_date)) * 0.25 as fine_amt, 0 as paid "
				+ " from book_loans where datediff(ifnull(date_in, curdate()), due_date) > 0 "
				+ " and loan_id not in (select f.loan_id from fines f where f.paid = 1)";
		session.createSQLQuery(queryString).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Borrower> search(int pageNo, int maxResults) {
		Session session = getSession();
		String queryString = " select  b.card_no as cardNo, b.ssn as ssn, b.Fname as fname, b.Lname as lname, "
				+ " b.email as email, b.address as address, b.city as city, b.state as state, "
				+ " b.phone as phone, sum(f.fine_amt) as fine, IFNULL(p.payable, 0) payable "
				+ " from fines f join book_loans bl on f.loan_id = bl.loan_id "
				+ " join borrower b on bl.card_no = b.card_no "
				+ " left join (select inbl.card_no, sum(inf.fine_amt) as payable "
				+ " 	from fines inf join book_loans inbl on inf.loan_id = inbl.loan_id "
				+ "     where inf.paid = 0 and inbl.date_in is not null group by inbl.card_no) p "
				+ " on p.card_no = bl.card_no where f.paid = 0 group by b.card_no ";
		SQLQuery sqlQuery = session.createSQLQuery(queryString);
		sqlQuery.addScalar("fine", StandardBasicTypes.FLOAT).addScalar("payable", StandardBasicTypes.FLOAT)
				.addScalar("cardNo", StandardBasicTypes.STRING).addScalar("ssn", StandardBasicTypes.STRING)
				.addScalar("fname", StandardBasicTypes.STRING).addScalar("lname", StandardBasicTypes.STRING)
				.addScalar("email", StandardBasicTypes.STRING).addScalar("address", StandardBasicTypes.STRING)
				.addScalar("city", StandardBasicTypes.STRING).addScalar("state", StandardBasicTypes.STRING)
				.addScalar("phone", StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(new AliasToBeanResultTransformer(Borrower.class));

		if (maxResults != 0) {
			sqlQuery.setFirstResult((pageNo - 1) * maxResults);
			sqlQuery.setMaxResults(maxResults);
		}

		List<Borrower> borrowers = sqlQuery.list();
		return borrowers;
	}

	@Override
	public void settleFines(String cardNo) {
		Session session = getSession();
		String queryString = "update fines set paid = 1 where loan_id in "
				+ "( select loan_id from book_loans where date_in is not null and card_no = :cardNo)";
		session.createSQLQuery(queryString).setParameter("cardNo", cardNo).executeUpdate();
	}
}
