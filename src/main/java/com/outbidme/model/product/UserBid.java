package com.outbidme.model.product;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserBid implements Comparable<UserBid>{

	@Id
	private int id;
	private int accountId;
	private int productId;
	private double price;

	public UserBid(int id, int accountId, int productId, double bidPrice) {
		this.id = id;
		this.accountId  = accountId;
		this.productId = productId;
		this.price = bidPrice;
	}

	public int getId() {
		return id;
	}

	public int getProductId() {
		return productId;
	}

	public double getPrice() {
		return price;
	}

	public int getAccountId() {
		return accountId;
	}
	
	public int compareTo(UserBid o) {
		return (-1) * Double.valueOf(this.getPrice()).compareTo(Double.valueOf(o.getPrice()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + productId;
		result = prime * result * accountId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBid other = (UserBid) obj;
		if (id != other.id)
			return false;
		if (productId != other.productId)
			return false;
		if (accountId != other.accountId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserBid [accountId=" + accountId + ", productId=" + productId + ", price=" + price + "]";
	}
	
}
