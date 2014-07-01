package com.outbidme.general;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.outbidme.model.authentication.Account;
import com.outbidme.model.product.Product;

public class TestUtils {

	public static final String TEST_USERNAME   = "john";
	public static final String TEST_PASSWORD   = "123";
	public static final double TEST_EXPIRED_PRODUCT_PRICE = 500;
	public static final int    TEST_EXPIRED_PRODUCT_ID = 0;

	
	public static Account getDefaultAccountInstance(){
		return new Account(TEST_USERNAME, TEST_PASSWORD);
	}
	
	public static Product getDefaultProductInstance(){
		Product product = new Product(TEST_EXPIRED_PRODUCT_ID);
		product.setDescription("Just a test product");
		product.setName("Test Product");
		product.setPrice(TEST_EXPIRED_PRODUCT_PRICE);
		product.setExpirationTime(getYesterdayTimeObj());
		return product;
	}

	public static Date getYesterdayTimeObj() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}
	
	
}
