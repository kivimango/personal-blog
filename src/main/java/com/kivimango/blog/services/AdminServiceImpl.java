package com.kivimango.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kivimango.blog.domain.AuthorView;
import com.kivimango.blog.domain.AuthorConverter;
import com.kivimango.blog.domain.entity.Admin;
import com.kivimango.blog.repositories.AdminRepository;

/** 
* @author kivimango
* @since 0.1
* @version 0.1
*/

@Service
public class AdminServiceImpl implements AdminService {
	
	private AdminRepository adminRepository;
	private AuthorConverter converter = new AuthorConverter();

	@Autowired
	public AdminServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public AuthorView findFirst() {
		Admin admin = adminRepository.findFirstByOrderById();
		if( admin != null) {
			return converter.convert(admin);
		}
		return null;
	}
	
}
