package com.outbidme.model.product;

public class UserBid implements Comparable<UserBid>{

	private int id;
	private String userName;
	private int productId;
	private double price;

	public UserBid(int id, String username, int productId, double bidPrice) {
		this.id = id;
		this.userName  = username;
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
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserBid [userName=" + userName + ", productId=" + productId
				+ ", price=" + price + "]";
	}
	
}
