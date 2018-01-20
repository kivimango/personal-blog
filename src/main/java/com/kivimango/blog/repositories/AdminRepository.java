package com.kivimango.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kivimango.blog.domain.entity.Admin;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Repository
public interface AdminRepository extends JpaRepository<Admin, Short> {
	
	/**
	 * Find an admin by its login name - used at authentication
	 * @param username
	 * @return
	 */
	
	Admin findByUsername(String username);
	
	Admin findFirstByOrderById();
	
	/**
	 * Find an author by its displayed name - not to mix with username
	 * @param name
	 * @return
	 */
	
	Admin findByName(String name);

}
