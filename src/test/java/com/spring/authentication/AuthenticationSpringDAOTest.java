package com.spring.authentication;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outbidme.model.authentication.Account;
import com.outbidme.persistance.dao.authentication.AccountDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean-dao-tests.xml")
public class AuthenticationSpringDAOTest {

	@Autowired
	AccountDAO accountDAO;
	
	@Before
	@Transactional
	@Rollback(false)
	public void setup(){
		Account account = new Account(12, "", "");
	}
	
	@Test
	public void test(){
		
	}
}
