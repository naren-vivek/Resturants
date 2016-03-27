package com.ordermgmt.lm.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public T save(T entity) {
		getSession().save(entity);
		return entity;
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

	protected Criteria createEntityCriteria(int pageNo, int maxResults) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.setFirstResult((pageNo - 1) * maxResults);
		criteria.setMaxResults(maxResults);
		return criteria;
	}

	protected Criterion stringMatch(String propertyName, String value) {
		return Restrictions.ilike(propertyName, value, MatchMode.ANYWHERE);
	}

	protected void buildWhereClauseAnd(Criteria criteria, List<Criterion> criterions) {
		if (!criterions.isEmpty()) {
			criteria.add(buildWhereClauseAnd(criterions));
		}
	}

	protected Criterion buildWhereClauseAnd(List<Criterion> criterions) {
		if (!criterions.isEmpty()) {
			Criterion[] predicates = new Criterion[criterions.size()];
			for (int i = 0; i < criterions.size(); i++)
				predicates[i] = criterions.get(i);
			return Restrictions.and(predicates);
		}
		return null;
	}

	protected void buildWhereClauseOr(Criteria criteria, List<Criterion> criterions) {
		if (!criterions.isEmpty()) {
			criteria.add(buildWhereClauseOr(criterions));
		}
	}

	protected Criterion buildWhereClauseOr(List<Criterion> criterions) {
		if (!criterions.isEmpty()) {
			Criterion[] predicates = new Criterion[criterions.size()];
			for (int i = 0; i < criterions.size(); i++)
				predicates[i] = criterions.get(i);
			return Restrictions.or(predicates);
		}
		return null;
	}
}
