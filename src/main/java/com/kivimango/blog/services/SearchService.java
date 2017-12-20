package com.kivimango.blog.services;

import java.util.List;
import com.kivimango.blog.domain.BlogPostView;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public interface SearchService {
	
	/**
	 * Search for the list of posts containing the query.
	 * Currently search supported for:
	 * title, slug, content
	 * 
	 * @param query
	 */
	
	List<BlogPostView> search(String query);

}
