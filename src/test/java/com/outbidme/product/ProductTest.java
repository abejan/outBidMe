package com.outbidme.product;

import com.outbidme.general.AbstractTest;
import com.outbidme.model.product.Product;
import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.product.ProductGateway;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ProductTest extends AbstractTest {

    private final static ProductGateway productGateway = PersistanceFactory.getProductGateway();

    @Test
    public void can_create_product(){
        double nextValidId = productGateway.getNextValidId();
        Product product = new Product(nextValidId);
        assertTrue(product != null);
    }

    @Test
    public void can_set_product_details(){
        Product product = new Product(productGateway.getNextValidId());
        product.setName("Product Name");
        product.setDescription("Product Description");
        product.setPrice(45.6);
        assertEquals(product.getName(), "Product Name");
        assertEquals(product.getDescription(), "Product Description");
        assertTrue(product.getPrice() == 45.6);
    }

    @Test
    public void can_persist_product(){
        Product product = new Product(productGateway.getNextValidId());
        product.setName("Product Name");
        product.setDescription("Product Description");
        product.setPrice(45.6);

        try {
            productGateway.persist(product);
        } catch (PersistenceException e) {
            fail();
        }
        assertTrue(productGateway.findEntity(product.getId()) != null);
    }
}
