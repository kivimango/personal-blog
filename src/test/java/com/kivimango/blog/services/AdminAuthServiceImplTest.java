package com.kivimango.blog.services;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import java.sql.Timestamp;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import com.kivimango.blog.domain.entity.Admin;
import com.kivimango.blog.repositories.AdminRepository;
import com.kivimango.blog.repositories.LoginAttemptRepository;
import com.kivimango.blog.samples.BlogPostFactory;

@RunWith(SpringRunner.class)
public class AdminAuthServiceImplTest extends BlogPostFactory {
	
	@Mock
	@Qualifier("adminRepository")
	private AdminRepository adminRepo;
	
	@Mock
	@Qualifier("loginattemptRepository")
	private LoginAttemptRepository loginAttemptRepo;
	
	@InjectMocks
	private AdminAuthServiceImpl service;
	
	@Rule
    public OutputCapture capture = new OutputCapture();
	
	@Test
	public void testLoadUserByUserName() {
		Admin admin = getSampleAdminEntity();
		
		given(adminRepo.findByUsername(username)).willReturn(admin);
		UserDetails expected = service.loadUserByUsername(username);
		assertEquals(expected.getUsername(), username);
		assertTrue(expected.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
	}
	
	@Test
	public void testLoginFailedShouldLog() {
		String ip = "127.0.0.1";
		service.loginFailed(ip);
		assertThat(capture.toString(), containsString("Admin Login failed from"));
	}
	
	@Test
	public void testLoginSuccessShouldLog() {
		String ip = "127.0.0.1";
		service.loginSucceeded(ip);
		assertThat(capture.toString(), containsString("Admin Login success from"));
	}
	
	@Test
	public void testisBlocked() {
		String ip = "127.0.0.1";
		long currentTime = System.currentTimeMillis();
		long timeWindow = 86400;
		Timestamp timeWindowStart = new Timestamp(currentTime - timeWindow);
		Timestamp now = new Timestamp(currentTime);
		
		given(loginAttemptRepo.countByAttemptDateBetween(timeWindowStart, now)).willReturn(0);
		assertFalse(service.isBlocked(ip));
	}
	
	@Test
	public void testisBlockedShouldBeTrueAfterMaxLoginAttemptLimitReached() {
		String ip = "127.0.0.1";
		long currentTime = System.currentTimeMillis();
		long timeWindow = 86400;
		Timestamp timeWindowStart = new Timestamp(currentTime - timeWindow);
		Timestamp now = new Timestamp(currentTime);
		
		given(loginAttemptRepo.countByAttemptDateBetween(timeWindowStart, now)).willReturn(6);
		assertTrue(service.isBlocked(ip));
	}

}
