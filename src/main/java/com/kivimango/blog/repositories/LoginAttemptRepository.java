package com.kivimango.blog.repositories;

import java.sql.Timestamp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kivimango.blog.domain.entity.LoginAttempt;

@Repository
public interface LoginAttemptRepository extends CrudRepository<LoginAttempt, Integer> {
	
	Integer countByAttemptDateBetween(Timestamp timeWindowStart, Timestamp now);

	void deleteAllByIpAdress(String ipAdress);

}
