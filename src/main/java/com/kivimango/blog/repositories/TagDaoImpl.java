package com.kivimango.blog.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Repository
public class TagDaoImpl implements TagRepository {
	
	private JdbcTemplate jdbc;

	@Autowired
	public TagDaoImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public List<Tag> findAll() {
		String query = "SELECT tag FROM " + Schema.TAGS_TABLE + " ORDER BY TAG ASC;";
		return jdbc.query(query, (rs, i) -> 
			new Tag(rs.getString("tag")));
	}
	
	@Override
	public Tag findByTag(String tag) {
		String query = "SELECT * FROM " + Schema.TAGS_TABLE
				+ " WHERE tag = ?;";
		return jdbc.queryForObject(query, new Object[] {tag}, (rs,i) -> {
			Tag t = new Tag(rs.getString("tag"));
			t.setId(rs.getInt("t_id"));
			return t;
		});
	}

	@Override
	public Tag save(Tag tag) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc).withTableName(Schema.TAGS_TABLE).usingGeneratedKeyColumns("t_id");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tag", tag.getTag());
		int id = insert.executeAndReturnKey(params).intValue();
		tag.setId(id);
		return tag;
	}
	
	@Override
	public void saveExistingWith(BlogPost post, Tag tag) {
		String query = "INSERT INTO " + Schema.POST_TAGS_LOOKUP_TABLE
				+ " (blog_post_id, tags_id) VALUES(?,?);";
		jdbc.update(query, new Object[] {post.getId(), tag.getId()});
	}
	
	@Override
	public void deleteFrom(BlogPost post) {
		String query = "DELETE FROM " + Schema.POST_TAGS_LOOKUP_TABLE
				+ " WHERE blog_post_id = ?;";
		jdbc.update(query, post.getId());
	}

	@Override
	public boolean isExists(Tag tag) {
		String query = "SELECT COUNT(t_id) as tid FROM " + Schema.TAGS_TABLE + " WHERE tag = ?;";
		int count = jdbc.queryForObject(query, new Object[]{tag.getTag()}, (rs, i) -> {
			return rs.getInt("tid");
		});
		return count == 1;
	}

}
