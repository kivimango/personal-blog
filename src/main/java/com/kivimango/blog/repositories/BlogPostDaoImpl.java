package com.kivimango.blog.repositories;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import com.kivimango.blog.domain.Author;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.page.Page;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Repository
public class BlogPostDaoImpl implements BlogPostRepository {
	
	private static final int pageSize = 5;
	private JdbcTemplate jdbc;
	private Author author;

	@Autowired
	public BlogPostDaoImpl(JdbcTemplate jdbc, Author author) {
		this.jdbc = jdbc;
		this.author = author;
	}

	// TODO: implement exist checking before insert
	
	@Override
	public List<BlogPost> findAll() {
		List<BlogPost> posts = new ArrayList<BlogPost>();
		String query = "SELECT blogpost_id, content, slug, title, edited, uploaded, hidden, GROUP_CONCAT("+Schema.TAGS_TABLE+".tag) as tags FROM " + Schema.POSTS_TABLE
				+ " LEFT JOIN " + Schema.POST_TAGS_LOOKUP_TABLE + " ON "+Schema.POST_TAGS_LOOKUP_TABLE+".blog_post_id = " +Schema.POSTS_TABLE+".blogpost_id"
				+ " LEFT JOIN " + Schema.TAGS_TABLE+" ON "+Schema.POST_TAGS_LOOKUP_TABLE+".tags_id = " +Schema.TAGS_TABLE+".t_id"
				+ " GROUP BY content, uploaded ORDER BY uploaded DESC;";
		posts = jdbc.query(query, new BlogPostRowMapper(author));
		System.out.println(posts);
		return posts;
	}
	
	@Override
	public Page<BlogPost> findAllByHidden(int pageNum, Boolean status) {
		int postCount = 0;
		List<BlogPost> posts = new ArrayList<BlogPost>();
		
		String countQuery = "SELECT COUNT(blogpost_id) AS postCount FROM " + Schema.POSTS_TABLE;
		postCount = jdbc.queryForObject(countQuery, (rs, i) -> rs.getInt("postCount"));
		
		if((pageNum-1) * pageSize > postCount) {
			return new Page<BlogPost>(pageNum, postCount, posts);
		}
		
		String query = "SELECT blogpost_id, SUBSTRING(content, 0, 100) as content, slug, title, edited, uploaded, hidden, GROUP_CONCAT("+Schema.TAGS_TABLE+".tag) as tags FROM " + Schema.POSTS_TABLE
				+ " LEFT JOIN " + Schema.POST_TAGS_LOOKUP_TABLE + " ON "+Schema.POST_TAGS_LOOKUP_TABLE+".blog_post_id = " +Schema.POSTS_TABLE+".blogpost_id"
				+ " LEFT JOIN " + Schema.TAGS_TABLE+" ON "+Schema.POST_TAGS_LOOKUP_TABLE+".tags_id = " +Schema.TAGS_TABLE+".t_id"
				+ " WHERE hidden = 0"
				+ " GROUP BY content, uploaded ORDER BY uploaded DESC"
				+ " LIMIT ? OFFSET ?;";
		posts = jdbc.query(query, new Object[] {pageSize, (pageNum-1) * pageSize}, new BlogPostRowMapper(author));
		
		return new Page<BlogPost>(pageNum, postCount, posts);
	}

	@Override
	public BlogPost getPostBySlug(String slug) {
		String query = "SELECT blogpost_id, content, slug, title, edited, uploaded, hidden, GROUP_CONCAT("+Schema.TAGS_TABLE+".tag) as tags FROM " + Schema.POSTS_TABLE 
				+ " LEFT JOIN " + Schema.POST_TAGS_LOOKUP_TABLE + " ON "+Schema.POSTS_TABLE+".blogpost_id = "+Schema.POST_TAGS_LOOKUP_TABLE+".blog_post_id" 				
				+ " LEFT JOIN "+Schema.TAGS_TABLE+" ON "+Schema.TAGS_TABLE+".t_id = "+Schema.POST_TAGS_LOOKUP_TABLE+".tags_id"
				+ " WHERE "+Schema.POSTS_TABLE+".slug = ? AND hidden = 0 GROUP BY content, uploaded LIMIT 1;";
		BlogPost post = null;
		try {
			post =  jdbc.queryForObject(query, new Object[]{slug}, new BlogPostRowMapper(author));
		} catch (EmptyResultDataAccessException ignore) {}
		return post;
	}

	// TODO: tags not shown up in the results
	
	@Override
	public List<BlogPost> findByTags(String tag) {
		List<BlogPost> posts = new ArrayList<BlogPost>();
		String query = "SELECT blogpost_id, content, edited, slug, title, uploaded, hidden, tags FROM "+Schema.POSTS_TABLE+" P"
				+ " INNER JOIN " 
				+ "(SELECT "+Schema.POST_TAGS_LOOKUP_TABLE+".BLOG_POST_ID, tag, GROUP_CONCAT(tag) as tags FROM "+Schema.TAGS_TABLE
				+ " INNER JOIN "+Schema.POST_TAGS_LOOKUP_TABLE+" ON "+Schema.TAGS_TABLE+".T_ID = "+Schema.POST_TAGS_LOOKUP_TABLE+".TAGS_ID AND tag = ?"
				+ " GROUP BY BLOG_POST_ID ) T ON T.BLOG_POST_ID = P.BLOGPOST_ID AND hidden = 0;";
		posts = jdbc.query(query, new Object[]{tag}, new BlogPostRowMapper(author));
		return posts;
	}
	
	// TODO: tags not shown up in the results
	
	@Override
	public List<BlogPost> findByTitleOrSlugOrContentOrTags(String query) {
		String like = "%" + query + "%";
		String sql = "SELECT blogpost_id, content, slug, title, edited, uploaded, hidden, GROUP_CONCAT("+Schema.TAGS_TABLE+".tag) as tags FROM " + Schema.POSTS_TABLE
				+ " LEFT JOIN " + Schema.POST_TAGS_LOOKUP_TABLE + " ON "+Schema.POST_TAGS_LOOKUP_TABLE+".blog_post_id = " +Schema.POSTS_TABLE+".blogpost_id"
				+ " LEFT JOIN " + Schema.TAGS_TABLE+" ON "+Schema.POST_TAGS_LOOKUP_TABLE+".tags_id = " +Schema.TAGS_TABLE+".t_id"
				+ " WHERE (content LIKE ? OR slug LIKE ? OR title LIKE ?) AND hidden = 0"
				+ " GROUP BY content, uploaded ORDER BY uploaded DESC;";
		return jdbc.query(sql, new Object[] {like, like, like}, new BlogPostRowMapper(author));
	}

	@Override
	public List<BlogPost> findFirst5ByOrderByUploadedDesc() {
		String query = "SELECT title, slug FROM " + Schema.POSTS_TABLE + " WHERE hidden = 0 ORDER BY uploaded DESC LIMIT 5;";
		return jdbc.query(query, (rs, i) -> {
			BlogPost post = new BlogPost();
			post.setAuthor(author);
			post.setContent("");
			post.setTitle(rs.getString("title"));
			post.setSlug(rs.getString("slug"));
			return post;
		});
	}
	
	@Override
	public BlogPost save(BlogPost post) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc).withTableName(Schema.POSTS_TABLE).usingGeneratedKeyColumns("blogpost_id");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("content", post.getContent());
		params.put("edited", null);
		params.put("slug", post.getSlug());
		params.put("title", post.getTitle());
		params.put("uploaded", Timestamp.valueOf(LocalDateTime.now()));
		params.put("hidden", false);
		post.setId(insert.executeAndReturnKey(params).intValue());
		return post;
	}
	
	@Override
	public BlogPost edit(BlogPost post) {
		String query = "UPDATE " + Schema.POSTS_TABLE + " SET content = ?, edited = ?, slug = ?, title = ?, hidden = ?"
				+ "WHERE blogpost_id = ?;";
		jdbc.update(query, post.getContent(), Timestamp.valueOf(LocalDateTime.now()), post.getSlug(), 
				post.getTitle(), post.isHidden(), post.getId());
		return post;
	}
	
	@Override
	public boolean isExists(String slug) {
		String query = "COUNT(blogpost_id) as bid FROM " + Schema.POSTS_TABLE + " WHERE slug = ?;";
		int count = jdbc.queryForObject(query, new Object[]{slug}, (rs, i) -> {
			return rs.getInt("bid");
		});
		return count == 1;
	}

}
