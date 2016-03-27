package com.ordermgmt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ordered_item_list database table.
 * 
 */
@Embeddable
public class OrderedItemListPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="order_id", insertable=false, updatable=false)
	private int orderId;

	@Column(name="item_id", insertable=false, updatable=false)
	private int itemId;

	public OrderedItemListPK() {
	}
	public int getOrderId() {
		return this.orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
		if (!(other instanceof OrderedItemListPK)) {
			return false;
		}
		OrderedItemListPK castOther = (OrderedItemListPK)other;
		return 
			(this.orderId == castOther.orderId)
			&& (this.itemId == castOther.itemId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orderId;
		hash = hash * prime + this.itemId;
		
		return hash;
	}
}