package com.kivimango.blog.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kivimango.blog.domain.entity.Tag;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

	public List<Tag> findTop10ByOrderByTagAsc();

	public Tag findByTag(String tag);
}
