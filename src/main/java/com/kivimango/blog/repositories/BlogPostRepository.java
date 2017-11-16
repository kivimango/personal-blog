package com.kivimango.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.kivimango.blog.domain.entity.BlogPost;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Repository
public interface BlogPostRepository extends PagingAndSortingRepository<BlogPost, Integer> {
	
	Page<BlogPost> findAllByHidden(Pageable pageable, Boolean status);
	BlogPost getPostBySlug(String slug);

}
