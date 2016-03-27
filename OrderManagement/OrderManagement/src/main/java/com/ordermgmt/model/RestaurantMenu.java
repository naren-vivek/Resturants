package com.ordermgmt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the restaurant_menu database table.
 * 
 */
@Entity
@Table(name="restaurant_menu")
@NamedQuery(name="RestaurantMenu.findAll", query="SELECT r FROM RestaurantMenu r")
public class RestaurantMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RestaurantMenuPK id;

	@Column(name="item_cost")
	private float itemCost;

	@Column(name="item_name")
	private String itemName;

	@Column(name="prep_time")
	private Time prepTime;

	//bi-directional many-to-one association to OrderedItemList
	@OneToMany(mappedBy="restaurantMenu")
	private List<OrderedItemList> orderedItemLists;

	//bi-directional many-to-one association to Restaurant
	@ManyToOne
	@JoinColumn(name="restaurant_id")
	private Restaurant restaurant;

	public RestaurantMenu() {
	}

	public RestaurantMenuPK getId() {
		return this.id;
	}

	public void setId(RestaurantMenuPK id) {
		this.id = id;
	}

	public float getItemCost() {
		return this.itemCost;
	}

	public void setItemCost(float itemCost) {
		this.itemCost = itemCost;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Time getPrepTime() {
		return this.prepTime;
	}

	public void setPrepTime(Time prepTime) {
		this.prepTime = prepTime;
	}

	public List<OrderedItemList> getOrderedItemLists() {
		return this.orderedItemLists;
	}

	public void setOrderedItemLists(List<OrderedItemList> orderedItemLists) {
		this.orderedItemLists = orderedItemLists;
	}

	public OrderedItemList addOrderedItemList(OrderedItemList orderedItemList) {
		getOrderedItemLists().add(orderedItemList);
		orderedItemList.setRestaurantMenu(this);

		return orderedItemList;
	}

	public OrderedItemList removeOrderedItemList(OrderedItemList orderedItemList) {
		getOrderedItemLists().remove(orderedItemList);
		orderedItemList.setRestaurantMenu(null);

		return orderedItemList;
	}

	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}