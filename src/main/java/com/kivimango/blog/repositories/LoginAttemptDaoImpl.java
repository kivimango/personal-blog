package com.kivimango.blog.repositories;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.kivimango.blog.domain.entity.LoginAttempt;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Repository
public class LoginAttemptDaoImpl implements LoginAttemptRepository {
	
	private JdbcTemplate jdbc;

	@Autowired
	public LoginAttemptDaoImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Integer countByAttemptDateBetween(Timestamp timeWindowStart, Timestamp now) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllByIpAdress(String ipAdress) {
		// TODO Auto-generated method stub
	}

	@Override
	public void save(LoginAttempt attempt) {
		// TODO Auto-generated method stub
		
	}

}
