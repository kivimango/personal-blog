package com.kivimango.blog.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.kivimango.blog.domain.entity.Admin;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Repository
public class AdminDaoImpl implements AdminRepository {
	
	private JdbcTemplate jdbc;

	@Autowired
	public AdminDaoImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Admin findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin findFirstByOrderById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Admin admin) {
		// TODO Auto-generated method stub
		
	}

}
