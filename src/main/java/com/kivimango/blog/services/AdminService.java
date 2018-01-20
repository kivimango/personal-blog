package com.kivimango.blog.services;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.kivimango.blog.domain.AuthorView;
import com.kivimango.blog.domain.form.AuthorEditForm;
import com.kivimango.blog.exception.UserNotFoundException;

/** 
* @author kivimango
* @since 0.1
* @version 0.1
*/

@Repository
public interface AdminService {
	
	/**
	 * Find the first admin in the database to display on the home page - hence the project name personal-blog.
	 * Multiple admins allowed, but only with the lowest row number will be displayed on the home page.
	 * @return a converted AuthorView DTO OR null if no admins found in the database table
	 */
	
	AuthorView findFirst();
	
	List<AuthorView> findAll();
	
	AuthorView findByName(String username) throws UserNotFoundException;
	
	void save(String name, AuthorEditForm form) throws UserNotFoundException;
}
