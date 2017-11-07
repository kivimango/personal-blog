package com.kivimango.blog.domain.entity;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

public class AdminTest {
	
	Short id = 1;
	String username = "Sample Admin";
	String password = "aVerySeCrEtPaSsWoRd";
	Date lastLogin = new Date();
	Admin admin;
	
	@Before
	public void when() {
		admin = new Admin();
		admin.setId(id);
		admin.setUsername(username);
		admin.setPassword(password);
		admin.setLastLogin(lastLogin);
	}
	
	@Test
	public void testAdminEntityShouldEqual() {
		assertEquals(id, admin.getId());
		assertEquals(username, admin.getUsername());
		assertEquals(password, admin.getPassword());
		assertEquals(lastLogin, admin.getLastLogin());
	}

}
