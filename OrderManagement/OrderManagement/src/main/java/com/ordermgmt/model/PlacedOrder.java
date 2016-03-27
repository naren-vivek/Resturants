package com.ordermgmt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the placed_order database table.
 * 
 */
@Entity
@Table(name="placed_order")
@NamedQuery(name="PlacedOrder.findAll", query="SELECT p FROM PlacedOrder p")
public class PlacedOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="order_id")
	private int orderId;

	@Column(name="expected_time")
	private Time expectedTime;

	@Column(name="restaurant_id")
	private int restaurantId;

	private int status;

	@Column(name="total_cost")
	private float totalCost;

	//bi-directional many-to-one association to OrderedItemList
	@OneToMany(mappedBy="placedOrder")
	private List<OrderedItemList> orderedItemLists;

	public PlacedOrder() {
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Time getExpectedTime() {
		return this.expectedTime;
	}

	public void setExpectedTime(Time expectedTime) {
		this.expectedTime = expectedTime;
	}

	public int getRestaurantId() {
		return this.restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getTotalCost() {
		return this.totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public List<OrderedItemList> getOrderedItemLists() {
		return this.orderedItemLists;
	}

	public void setOrderedItemLists(List<OrderedItemList> orderedItemLists) {
		this.orderedItemLists = orderedItemLists;
	}

	public OrderedItemList addOrderedItemList(OrderedItemList orderedItemList) {
		getOrderedItemLists().add(orderedItemList);
		orderedItemList.setPlacedOrder(this);

		return orderedItemList;
	}

	public OrderedItemList removeOrderedItemList(OrderedItemList orderedItemList) {
		getOrderedItemLists().remove(orderedItemList);
		orderedItemList.setPlacedOrder(null);

		return orderedItemList;
	}

}