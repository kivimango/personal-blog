package com.kivimango.blog.repositories;

import java.util.List;
import com.kivimango.blog.domain.entity.Admin;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public interface AdminRepository {
	
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
	
	List<Admin> findAll();
	
	void save(Admin admin);

}
