package com.outbidme.persistance.dao.impl.product;

import java.util.Collection;
import java.util.List;

import com.outbidme.model.product.Product;
import com.outbidme.persistance.EntityMatcher;
import com.outbidme.persistance.PersistanceManager;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.impl.AbstractEntityDAO;
import com.outbidme.persistance.dao.product.ProductDAO;

public class ProductDAOImpl extends AbstractEntityDAO implements ProductDAO{

	public ProductDAOImpl(PersistanceManager persistanceManager) {
		super(persistanceManager);
	}

	public void persist(Product product) throws PersistenceException {
		getPersistanceManager().persist(product);
	}
	
    public int getNextValidId() throws PersistenceException {
        return getPersistanceManager().getEntityCount(Product.class);
    }

    public Product findProduct(final double id) {
        Collection<Product> results = getPersistanceManager()
        						.findEntities(getProductIdMatcher(id), Product.class);
      return getFirstElement(results);
    }

	@Override
	public List<Product> findAllProducts() {
		 Collection<Product> results = getPersistanceManager().findEntities(getAllEntitiesMatcher(Product.class), Product.class);
		 return toList(results);
	}

	private EntityMatcher<Product> getProductIdMatcher(final double id) {
		return new EntityMatcher<Product>() {
            public boolean matches(Product entity) {
                return entity.getId() == id;
            }
        };
	}
	
}
