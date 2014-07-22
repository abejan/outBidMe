package com.outbidme.model.product;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	
    private static final int DEFAULT_EXPIRATION_DAYS = 10;
    
    @Id
	private int id;
    private String name;
    private String description;
    private double price;
    
    //default to 10 days after creation date
	private Date expirationTime;
    
    public Product(int id) {
        this.id = id;
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.DATE, DEFAULT_EXPIRATION_DAYS);
		this.expirationTime = calendar.getTime();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

	public void setExpirationTime(Date date) {
		this.expirationTime = date;
	}

	public boolean isExpired() {
		return new Date().after(expirationTime);
	}

}
