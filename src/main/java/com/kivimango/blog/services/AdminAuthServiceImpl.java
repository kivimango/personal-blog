package com.kivimango.blog.services;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kivimango.blog.domain.AdminDetail;
import com.kivimango.blog.domain.entity.Admin;
import com.kivimango.blog.domain.entity.LoginAttempt;
import com.kivimango.blog.repositories.AdminRepository;
import com.kivimango.blog.repositories.LoginAttemptRepository;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Service
public class AdminAuthServiceImpl implements UserDetailsService, AntiBruteforceService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${blog.max.login_attempts}")
	private int MAX_ATTEMPT = 3;
	
	@Value("${blog.login_attempts.timewindow}")
	private long timeWindow = 86400;
	
	private AdminRepository admins;
	
	private LoginAttemptRepository loginAttempts;

	@Autowired
	public AdminAuthServiceImpl(AdminRepository ar, LoginAttemptRepository lar) {
		this.admins = ar;
		this.loginAttempts = lar;
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = admins.findByUsername(username);
		if(admin == null) throw new UsernameNotFoundException("No administrator found with the name : " + username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return new AdminDetail(admin);
	}
	
	@Override
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
	public boolean isBlocked(String ipAdress) {
		int attempts = 0;
		long currentTime = System.currentTimeMillis();
		Timestamp timeWindowStart = new Timestamp(currentTime - timeWindow);
		Timestamp now = new Timestamp(currentTime);
		attempts = loginAttempts.countByAttemptDateBetween(timeWindowStart, now);
        return attempts >= MAX_ATTEMPT;
    }

}
