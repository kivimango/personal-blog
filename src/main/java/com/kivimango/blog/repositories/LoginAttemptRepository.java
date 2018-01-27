package com.kivimango.blog.repositories;

import java.sql.Timestamp;
import com.kivimango.blog.domain.entity.LoginAttempt;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public interface LoginAttemptRepository {
	
	Integer countByAttemptDateBetween(Timestamp timeWindowStart, Timestamp now);
	
	void deleteAllByIpAdress(String ipAdress);
	
	void save(LoginAttempt attempt);

}
