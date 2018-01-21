package com.kivimango.blog.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Repository
public interface BlogPostRepository extends PagingAndSortingRepository<BlogPost, Integer> {
	
	Page<BlogPost> findAllByHidden(Pageable pageable, Boolean status);
	BlogPost getPostBySlug(String slug);
	List<BlogPost> findByTags(Tag tag);
	//List<BlogPost> findByTitleOrSlugOrContentOrTags(String query, String query2, String query3, List<Tag> tags);
	List<BlogPost> findByTitleIgnoreCaseContaining(String searchedTitle);
	List<BlogPost> findBySlugIgnoreCaseContaining(String searchedSlug);
	List<BlogPost> findByContentIgnoreCaseContaining(String searchedContent);
	List<BlogPost> findFirst5ByOrderByUploadedDesc();
}
