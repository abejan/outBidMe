package com.outbidme.general;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.outbidme.authentication.AuthenticationPersistanceTest;
import com.outbidme.authentication.AuthenticationTest;
import com.outbidme.bidding.BiddingTest;
import com.outbidme.presentation.LoginInterfaceTest;
import com.outbidme.product.ProductTest;

// specify a runner class: Suite.class
@RunWith(Suite.class)

// specify an array of test classes
@Suite.SuiteClasses({
  AuthenticationTest.class, 
  AuthenticationPersistanceTest.class,
  ProductTest.class, 
  LoginInterfaceTest.class,
  BiddingTest.class,
})
public class AllTests{
}
