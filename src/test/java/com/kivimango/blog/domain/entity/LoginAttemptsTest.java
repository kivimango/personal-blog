package com.kivimango.blog.domain.entity;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import org.junit.Before;
import org.junit.Test;

public class LoginAttemptsTest {
	
	Integer id;
	String ipAdress;
	Timestamp timestamp;
	LoginAttempt loginAttempt;
	
	@Before
	public void when() {
		id = 42;
		ipAdress = "127.0.0.1";
		timestamp = new Timestamp(System.currentTimeMillis());
		loginAttempt = new LoginAttempt(ipAdress, timestamp);
		loginAttempt.setId(id);
	}
	
	@Test
	public void testLoginAttemptEntityShouldEqual() {
		assertEquals(id,loginAttempt.getId());
		assertEquals(ipAdress, loginAttempt.getIpAdress());
		assertEquals(timestamp, loginAttempt.getAttemptDate());
	}
	
	@Test
	public void testDefaultConstructor() {
		loginAttempt = new LoginAttempt();
		loginAttempt.setId(id);
		loginAttempt.setIpAdress(ipAdress);
		loginAttempt.setAttemptDate(timestamp);
		testLoginAttemptEntityShouldEqual();
	}
}
