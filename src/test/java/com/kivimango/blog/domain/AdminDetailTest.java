package com.kivimango.blog.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.kivimango.blog.samples.SampleAdmin;

public class AdminDetailTest extends SampleAdmin {
	
	String role = "ROLE_ADMIN";
	
	@Test
	public void testAdminDetailShouldEqual() {
		AdminDetail adminDetail = new AdminDetail(admin);
		assertEquals(adminDetail.getPassword(), admin.getPassword());
		assertEquals(adminDetail.getUsername(), admin.getUsername());
		assertEquals(1, adminDetail.getAuthorities().size());
		assertTrue(adminDetail.isAccountNonExpired());
		assertTrue(adminDetail.isAccountNonLocked());
		assertTrue(adminDetail.isCredentialsNonExpired());
		assertTrue(adminDetail.isEnabled());
	}
	
}
