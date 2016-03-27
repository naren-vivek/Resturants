package com.ordermgmt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the restaurant database table.
 * 
 */
@Entity
@NamedQuery(name="Restaurant.findAll", query="SELECT r FROM Restaurant r")
public class Restaurant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="restaurant_id")
	private int restaurantId;

	private String name;

	//bi-directional many-to-one association to RestaurantMenu
	@OneToMany(mappedBy="restaurant")
	private List<RestaurantMenu> restaurantMenus;

	public Restaurant() {
	}

	public int getRestaurantId() {
		return this.restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RestaurantMenu> getRestaurantMenus() {
		return this.restaurantMenus;
	}

	public void setRestaurantMenus(List<RestaurantMenu> restaurantMenus) {
		this.restaurantMenus = restaurantMenus;
	}

	public RestaurantMenu addRestaurantMenus(RestaurantMenu restaurantMenus) {
		getRestaurantMenus().add(restaurantMenus);
		restaurantMenus.setRestaurant(this);

		return restaurantMenus;
	}

	public RestaurantMenu removeRestaurantMenus(RestaurantMenu restaurantMenus) {
		getRestaurantMenus().remove(restaurantMenus);
		restaurantMenus.setRestaurant(null);

		return restaurantMenus;
	}

}