package com.spring.general;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outbidme.general.AbstractTest;

/**
 * This base class will be extended by all tests which require running in a Spring context.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean-dao-tests.xml")
@Transactional
public class AbstractSpringTest extends AbstractTest {
}
