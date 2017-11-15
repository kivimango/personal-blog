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
	
	Admin findByUsername(String username);

}
