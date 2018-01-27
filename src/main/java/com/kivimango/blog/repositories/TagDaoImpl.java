package com.kivimango.blog.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tag> findTop10ByOrderByTagAsc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag findByTag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

}
