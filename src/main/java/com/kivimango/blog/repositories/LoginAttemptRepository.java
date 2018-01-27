package com.kivimango.blog.repositories;

import java.sql.Timestamp;

public interface LoginAttemptRepository {
	
	Integer countByAttemptDateBetween(Timestamp timeWindowStart, Timestamp now);
	
	void deleteAllByIpAdress(String ipAdress);

}
