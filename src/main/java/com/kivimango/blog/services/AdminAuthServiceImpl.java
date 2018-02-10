package com.kivimango.blog.services;

import java.sql.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kivimango.blog.domain.AdminDetail;
import com.kivimango.blog.domain.Author;
import com.kivimango.blog.domain.entity.LoginAttempt;
import com.kivimango.blog.repositories.LoginAttemptRepository;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Service
public class AdminAuthServiceImpl implements UserDetailsService, AntiBruteforceService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${security.user.name}")
	private String root;
	
	@Value("${security.user.password}")
	private String pw;
	
	@Value("${blog.max.login_attempts}")
	private int MAX_ATTEMPT = 3;
	
	@Value("${blog.login_attempts.timewindow}")
	private long timeWindow = 86400;
	
	private Author author;
	
	private LoginAttemptRepository loginAttempts;

	@Autowired
	public AdminAuthServiceImpl(LoginAttemptRepository lar, Author author) {
		this.loginAttempts = lar;
		this.author = author;
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(root.equals(username)) {
			return new AdminDetail(author, root, pw);
		}
		throw new UsernameNotFoundException("No administrator found with the name : " + username);
	}
	
	@Override
	@Transactional
	public void loginFailed(String ipAdress) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		loginAttempts.save(new LoginAttempt(ipAdress, now));
        log.warn("Admin Login failed from {}", ipAdress);
	}
	
	@Override
	@Transactional
	public void loginSucceeded(String ipAdress) {
		loginAttempts.deleteAllByIpAdress(ipAdress);
		log.info("Admin Login success from {}, resetting login attempt cache", ipAdress);
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean isBlocked(String ipAdress) {
		int attempts = 0;
		long currentTime = System.currentTimeMillis();
		Timestamp timeWindowStart = new Timestamp(currentTime - timeWindow);
		Timestamp now = new Timestamp(currentTime);
		attempts = loginAttempts.countByAttemptDateBetween(ipAdress, timeWindowStart, now);
        return attempts >= MAX_ATTEMPT;
    }

}
