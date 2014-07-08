package com.outbidme.general;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.outbidme.model.authentication.Account;
import com.outbidme.model.product.Product;

public class TestUtils {

	public static final int TEST_ACCOUNT_ID = 0;
	public static final String TEST_USERNAME   = "John";
	public static final String TEST_PASSWORD   = "123";
	
	public static final int TEST_ACCOUNT_ID_2 = 1;
	public static final String TEST_USERNAME_2   = "Anita";
	public static final String TEST_PASSWORD_2   = "1234";
	
	public static final double TEST_PRODUCT_PRICE = 500;
	public static final int TEST_PRODUCT_ID = 0;
    public static final int TEST_EXPIRED_PRODUCT_ID = 1;

	
	public static Account getDefaultAccountInstance(){
		return new Account(TEST_ACCOUNT_ID, TEST_USERNAME, TEST_PASSWORD);
	}
	
	public static Product getDefaultProductInstance(){
		Product product = new Product(TEST_PRODUCT_ID);
		product.setDescription("Just a test product");
		product.setName("Test Product");
		product.setPrice(TEST_PRODUCT_PRICE);
		return product;
	}

    public static Product getExpiredProductInstance(){
        Product product = new Product(TEST_EXPIRED_PRODUCT_ID);
        product.setDescription("Just an expired test product");
        product.setName("Test Expired Product");
        product.setPrice(TEST_PRODUCT_PRICE);
        product.setExpirationTime(getYesterdayTimeObj());
        return product;
    }

	public static Date getYesterdayTimeObj() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}
	
	
}
