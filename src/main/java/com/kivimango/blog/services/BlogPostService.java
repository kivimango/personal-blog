package com.kivimango.blog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.kivimango.blog.domain.AdminDetail;
import com.kivimango.blog.domain.BlogPostView;
import com.kivimango.blog.domain.form.BlogPostForm;
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
	
	/**
	 * Saves a new blog post to the database
	 * @param form
	 * @throws AuthorNotFoundException 
	 */
	
	void save(BlogPostForm form, AdminDetail author);
	
	/**
	 * Modifies an existing blog post in the database
	 * @param slug
	 * @param form
	 * @throws BlogPostNotFoundException
	 */
	
	void edit(String slug, BlogPostForm form) throws BlogPostNotFoundException;
	
	/**
	 * Makes an URL slice from the title.Posts can be reached by their slug, 
	 * @param title
	 * @return
	 */
	
	String makeSlugFrom(String title);

}
