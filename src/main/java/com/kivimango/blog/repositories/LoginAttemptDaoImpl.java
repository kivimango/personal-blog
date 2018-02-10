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
	public Integer countByAttemptDateBetween(String ipAdress, Timestamp timeWindowStart, Timestamp now) {
		String query = "SELECT COUNT(la_id) as attemptCount FROM BLOG_LOGIN_ATTEMPTS "
				+ "WHERE time_stamp BETWEEN ? AND ? AND ip_adress = ?;";
		return jdbc.queryForObject(query, new Object[] {timeWindowStart, now, ipAdress}, (rs, i) -> rs.getInt("attemptCount"));
	}

	@Override
	public void deleteAllByIpAdress(String ipAdress) {
		String query = "DELETE FROM " + Schema.LOGIN_ATTEMPTS_TABLE + " WHERE ip_adress = ?;";
		jdbc.update(query, new Object[] {ipAdress});
	}

	@Override
	public void save(LoginAttempt attempt) {
		String query = "INSERT INTO " + Schema.LOGIN_ATTEMPTS_TABLE + " (ip_adress, time_stamp) "
				+ "VALUES(?, ?);";
		jdbc.update(query, new Object[] {attempt.getIpAdress(), attempt.getAttemptDate()});
	}

}
