package com.kivimango.blog.repositories;

import java.util.List;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.page.Page;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public interface BlogPostRepository {
	
	List<BlogPost> findAll();
	Page<BlogPost> findAllByHidden(int pageNum, Boolean status);
	BlogPost getPostBySlug(String slug);
	List<BlogPost> findByTags(String tag);
	List<BlogPost> findByTitleOrSlugOrContentOrTags(String query);
	List<BlogPost> findFirst5ByOrderByUploadedDesc();
	BlogPost save(BlogPost post);
	BlogPost edit(BlogPost post);
	boolean isExists(String slug);
}
