package com.ordermgmt.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ordermgmt.lm.repository.AbstractDao;
import com.ordermgmt.lm.repository.OrderDao;
import com.ordermgmt.model.OrderedItemList;
import com.ordermgmt.model.PlacedOrder;
import com.ordermgmt.model.Restaurant;
import com.ordermgmt.model.RestaurantMenu;

@Repository
@Transactional
public class OrderDaoImpl extends AbstractDao<Integer, PlacedOrder>implements OrderDao {

	public void placeOrder() {
		Session session = getSession();
		String queryString = " replace into fines "
				+ " select loan_id, (datediff(ifnull(date_in, curdate()), due_date)) * 0.25 as fine_amt, 0 as paid "
				+ " from book_loans where datediff(ifnull(date_in, curdate()), due_date) > 0 "
				+ " and loan_id not in (select f.loan_id from fines f where f.paid = 1)";
		session.createSQLQuery(queryString).executeUpdate();
	}

	public void itemsLookup() {
		Session session = getSession();
		String queryString = " SELECT * FROM restaurant_menu WHERE restaurant_id =  '';";

		session.createSQLQuery(queryString).executeUpdate();
	}

	public void seeOrder() {
		Session session = getSession();
		String queryString = "SELECT rm.item_name, oil.item_count FROM restaurant_menu AS rm, ordered_item_list AS oil WHERE oil.order_id = '' AND oil.item_id = rm.item_id;";

		session.createSQLQuery(queryString).list();
	}

	@Override
	public void placeOrder(PlacedOrder placedorder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RestaurantMenu> itemsLookup(Restaurant restaurant_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderedItemList> seeOrder(int restaurant_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
