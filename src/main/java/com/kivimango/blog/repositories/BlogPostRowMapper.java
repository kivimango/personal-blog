package com.kivimango.blog.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import com.kivimango.blog.domain.Author;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

class BlogPostRowMapper implements RowMapper<BlogPost> {
	
	private Author author;
	
	public BlogPostRowMapper(Author author) {
		this.author = author;
	}

	@Override
	public BlogPost mapRow(ResultSet rs, int rowNum) throws SQLException {
		BlogPost post = new BlogPost();
		post.setId(rs.getInt("blogpost_id"));
		post.setAuthor(author);
		post.setContent(rs.getString("content"));
		post.setEdited(rs.getDate("edited"));
		post.setHidden(rs.getBoolean("hidden"));
		post.setSlug(rs.getString("slug"));
		post.setTitle(rs.getString("title"));
		post.setUploaded(rs.getDate("uploaded"));
		post.setTags(getTags(rs.getString("tags")));
		return post;
	}
	
	private List<Tag> getTags(String column) {
		List<Tag> tagList = new ArrayList<Tag>();
		if(column != null) {
			for(String s : column.split(",")) {
				tagList.add(new Tag(s));
			}
		}
		return tagList;
	}
}
