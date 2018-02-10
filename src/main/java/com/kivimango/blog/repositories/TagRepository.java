package com.kivimango.blog.repositories;

import java.util.List;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public interface TagRepository {
	
	List<Tag> findAll();

	Tag findByTag(String tag);
	
	Tag save(Tag tag);
	
	/**
	 * Saves a relationship into the lookup table.
	 * Note: the blog post and the tag must be exists in the database.
	 */
	
	void saveExistingWith(BlogPost post, Tag tag);
	
	boolean isExists(Tag tag);
	
	/**
	 * Removes the relationship of tags with the associated blog post
	 * @param post
	 */

	void deleteFrom(BlogPost post);

}
