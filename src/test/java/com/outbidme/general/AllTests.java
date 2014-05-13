package com.outbidme.general;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.outbidme.authentication.AuthenticationTest;
import com.outbidme.persistance.PersistanceTest;

// specify a runner class: Suite.class
@RunWith(Suite.class)

// specify an array of test classes
@Suite.SuiteClasses({
  AuthenticationTest.class, 
  PersistanceTest.class,
})
public class AllTests{
}
