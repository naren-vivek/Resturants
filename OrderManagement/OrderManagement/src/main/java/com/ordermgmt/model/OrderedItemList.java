package com.ordermgmt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ordered_item_list database table.
 * 
 */
@Entity
@Table(name="ordered_item_list")
@NamedQuery(name="OrderedItemList.findAll", query="SELECT o FROM OrderedItemList o")
public class OrderedItemList implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderedItemListPK id;

	@Column(name="item_count")
	private int itemCount;

	//bi-directional many-to-one association to RestaurantMenu
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="item_id", referencedColumnName="item_id"),
		@JoinColumn(name="restaurant_id", referencedColumnName="restaurant_id")
		})
	private RestaurantMenu restaurantMenu;

	//bi-directional many-to-one association to PlacedOrder
	@ManyToOne
	@JoinColumn(name="order_id")
	private PlacedOrder placedOrder;

	public OrderedItemList() {
	}

	public OrderedItemListPK getId() {
		return this.id;
	}

	public void setId(OrderedItemListPK id) {
		this.id = id;
	}

	public int getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public RestaurantMenu getRestaurantMenu() {
		return this.restaurantMenu;
	}

	public void setRestaurantMenu(RestaurantMenu restaurantMenu) {
		this.restaurantMenu = restaurantMenu;
	}

	public PlacedOrder getPlacedOrder() {
		return this.placedOrder;
	}

	public void setPlacedOrder(PlacedOrder placedOrder) {
		this.placedOrder = placedOrder;
	}

}