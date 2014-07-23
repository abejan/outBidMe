package com.spring.persistance.dao.product;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.outbidme.model.product.Product;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.product.ProductDAO;

@Repository
public class ProductSpringDAO implements ProductDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void persist(Product product) throws PersistenceException {
		em.persist(product);
	}

	@Override
	public int getNextValidId() throws PersistenceException {
		Query query = em.createQuery("SELECT MAX(product.id) FROM Product product");
		return (Integer)query.getSingleResult() + 1;
	}

	@Override
	public Product findProduct(double id) {
		return em.find(Product.class, id);
	}

	@Override
	public List<Product> findAllProducts() {
		TypedQuery<Product> query = em.createQuery("SELECT * FROM Product p", Product.class);
		return query.getResultList();
	}

}
