package com.kivimango.blog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.kivimango.blog.domain.BlogPostView;
import com.kivimango.blog.exception.BlogPostNotFoundException;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public interface BlogPostService {
	
	/**
	 * Finding posts for the home page ordered by the upload date with pagination.
	 * @param pageable
	 * @return The list of the posts.
	 */
	
	Page<BlogPostView> findAll(Pageable pageable);
	
	 /**
	 * Finding one particular post in the database by the given slug.
	 * @param slug the slug of the post
	 * @return the converted entity as a DTO 
	 */
	
	BlogPostView getPostBySlug(String slug) throws BlogPostNotFoundException;

}