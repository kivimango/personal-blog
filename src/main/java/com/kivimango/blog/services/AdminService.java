package com.kivimango.blog.services;

import org.springframework.stereotype.Repository;
import com.kivimango.blog.domain.AuthorView;

/** 
* @author kivimango
* @since 0.1
* @version 0.1
*/

@Repository
public interface AdminService {
	
	/**
	 * Find the first admin in the database - hence the project name personal-blog.
	 * Multiple admins allowed, but only with the lowest row number will be displayed on the home page.
	 * @return a converted AuthorView DTO OR null if no admins found in the database table
	 */
	
	AuthorView findFirst();
}
