package com.kivimango.blog.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;
import com.kivimango.blog.domain.page.Page;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Repository
public class BlogPostDaoImpl implements BlogPostRepository {
	
	private JdbcTemplate jdbc;

	@Autowired
	public BlogPostDaoImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Page<BlogPost> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Page<BlogPost> findAllByHidden(int pageNum, Boolean status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlogPost getPostBySlug(String slug) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogPost> findByTags(Tag tag) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<BlogPost> findByTitleOrSlugOrContentOrTags(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogPost> findFirst5ByOrderByUploadedDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(BlogPost post) {
		// TODO Auto-generated method stub
		
	}

}
