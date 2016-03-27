package com.ordermgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ordermgmt.lm.repository.OrderDao;
import com.ordermgmt.model.OrderedItemList;

@RestController
@RequestMapping("order")
public class OrderController {

	@Autowired
	OrderDao orderDao;
	
	public void placeOrder(){
		//orderDao.placeOrder(placedorder);
	}
	public List<OrderedItemList> seeOrder(@RequestParam(value = "restaurantID", required = true) int restaurant_id)
	{
		return null;
	}
}
