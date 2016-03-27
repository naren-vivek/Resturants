package com.ordermgmt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the restaurant_menu database table.
 * 
 */
@Embeddable
public class RestaurantMenuPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="restaurant_id", insertable=false, updatable=false)
	private int restaurantId;

	@Column(name="item_id")
	private int itemId;

	public RestaurantMenuPK() {
	}
	public int getRestaurantId() {
		return this.restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public int getItemId() {
		return this.itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RestaurantMenuPK)) {
			return false;
		}
		RestaurantMenuPK castOther = (RestaurantMenuPK)other;
		return 
			(this.restaurantId == castOther.restaurantId)
			&& (this.itemId == castOther.itemId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.restaurantId;
		hash = hash * prime + this.itemId;
		
		return hash;
	}
}