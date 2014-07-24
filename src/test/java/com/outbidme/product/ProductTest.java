package com.outbidme.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.outbidme.general.AbstractTest;
import com.outbidme.general.TestUtils;
import com.outbidme.model.product.Product;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.product.ProductDAO;

public class ProductTest extends AbstractTest {

    private final ProductDAO productGateway = persistanceFactory.getProductDataAccessObj();

    @Test
    public void can_create_product(){
        int nextValidId = 0;
		try {
			nextValidId = productGateway.getNextValidId();
		} catch (PersistenceException e) {
			fail(e.getMessage());
		}
        Product product = new Product(nextValidId);
        assertTrue(product != null);
    }

    @Test
    public void can_set_product_details(){
        Product product = null;
		try {
			product = new Product(productGateway.getNextValidId());
		} catch (PersistenceException e) {
			fail(e.getMessage());
		}
        product.setName("Product Name");
        product.setDescription("Product Description");
        product.setPrice(45.6);
        assertEquals(product.getName(), "Product Name");
        assertEquals(product.getDescription(), "Product Description");
        assertTrue(product.getPrice() == 45.6);
    }

    @Test
    public void can_persist_product(){
        Product product = null;
		try {
			product = new Product(productGateway.getNextValidId());
		} catch (PersistenceException e) {
			fail(e.getMessage());
		}
        product.setName("Product Name");
        product.setDescription("Product Description");
        product.setPrice(45.6);

        try {
            productGateway.persist(product);
        } catch (PersistenceException e) {
            fail();
        }
        assertTrue(productGateway.findProduct(product.getId()) != null);
    }
    
	@Test
	public void can_verify_if_product_time_is_expired(){
	    Product expiredProduct = null;
		try {
			expiredProduct = new Product(productGateway.getNextValidId());
		} catch (PersistenceException e) {
			fail(e.getMessage());
		}
		
		expiredProduct.setExpirationTime(TestUtils.getYesterdayTimeObj());
		
		assertTrue(expiredProduct.isExpired());
	}
    
}
