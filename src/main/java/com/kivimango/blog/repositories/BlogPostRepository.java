package com.kivimango.blog.repositories;

import java.util.List;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;
import com.kivimango.blog.domain.page.Page;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public interface BlogPostRepository {
	
	Page<BlogPost> findAllByHidden(int pageNum, Boolean status);
	BlogPost getPostBySlug(String slug);
	List<BlogPost> findByTags(Tag tag);
	//List<BlogPost> findByTitleOrSlugOrContentOrTags(String query, String query2, String query3, List<Tag> tags);
	List<BlogPost> findByTitleIgnoreCaseContaining(String searchedTitle);
	List<BlogPost> findBySlugIgnoreCaseContaining(String searchedSlug);
	List<BlogPost> findByContentIgnoreCaseContaining(String searchedContent);
	List<BlogPost> findFirst5ByOrderByUploadedDesc();
	void save(BlogPost post);
}
