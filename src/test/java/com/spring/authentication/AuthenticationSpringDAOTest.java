package com.spring.authentication;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outbidme.general.TestUtils;
import com.outbidme.model.authentication.Account;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.authentication.AccountDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean-dao-tests.xml")
public class AuthenticationSpringDAOTest {

	@Autowired
	AccountDAO accountDAO;
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void can_persist_account(){
		Account account = new Account(0, TestUtils.TEST_USERNAME, TestUtils.TEST_PASSWORD);
		try {
			accountDAO.persist(account);
		} catch (PersistenceException e) {
			fail(e.getMessage());
		}
		assertNotNull(accountDAO.findAccountById(account.getId()));
	}
}
