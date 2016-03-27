package com.ordermgmt.lm.repository;

import com.ordermgmt.model.*;

import java.util.List;

public interface OrderDao {

	public void placeOrder(PlacedOrder placedorder);
	
	public List<RestaurantMenu> itemsLookup(Restaurant restaurant_id);
	
	public List<OrderedItemList> seeOrder(int restaurant_id);
}
