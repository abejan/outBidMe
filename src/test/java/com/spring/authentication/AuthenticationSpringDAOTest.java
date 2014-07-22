package com.spring.authentication;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.outbidme.general.TestUtils;
import com.outbidme.model.authentication.Account;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.authentication.AccountDAO;
import com.spring.general.AbstractSpringTest;

public class AuthenticationSpringDAOTest extends AbstractSpringTest{

	@Autowired
	AccountDAO accountDAO;
	
	@Before
	@Transactional
	@Rollback(false)
	public void setupOwn(){
		Account account = new Account(TestUtils.TEST_ACCOUNT_ID, TestUtils.TEST_USERNAME, TestUtils.TEST_PASSWORD);
		try {
			accountDAO.persist(account);
		} catch (PersistenceException e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	@Transactional
	public void can_find_account(){
		assertNotNull(accountDAO.findAccountById(TestUtils.TEST_ACCOUNT_ID));
		assertNotNull(accountDAO.findAccountByUserName(TestUtils.TEST_USERNAME));
	}
	
	@Test
	@Transactional
	public void can_find_next_valid_id(){
		assertTrue(accountDAO.getNextValidId() >=0 );
	}
}
