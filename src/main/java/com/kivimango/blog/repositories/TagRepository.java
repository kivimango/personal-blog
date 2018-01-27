package com.kivimango.blog.repositories;

import java.util.List;
import com.kivimango.blog.domain.entity.Tag;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public interface TagRepository {
	
	List<Tag> findAll();

	List<Tag> findTop10ByOrderByTagAsc();

	Tag findByTag(String tag);
	
	void save(Tag tag);
}
