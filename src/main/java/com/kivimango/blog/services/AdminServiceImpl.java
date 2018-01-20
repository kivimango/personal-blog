package com.kivimango.blog.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kivimango.blog.domain.AuthorView;
import com.kivimango.blog.domain.AuthorConverter;
import com.kivimango.blog.domain.entity.Admin;
import com.kivimango.blog.domain.form.AuthorEditForm;
import com.kivimango.blog.exception.UserNotFoundException;
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

	@Override
	public List<AuthorView> findAll() {
		List<Admin> admins = adminRepository.findAll();
		return converter.convert(admins);
	}

	@Override
	public AuthorView findByName(String name) throws UserNotFoundException {
		Admin admin = adminRepository.findByName(name);
		if(admin == null) throw new UserNotFoundException("The requested user " + name + " not found");
		return converter.convert(admin);
	}

	@Override
	public void save(String name, AuthorEditForm form) throws UserNotFoundException {
		Admin author = adminRepository.findByName(name);
		if(author == null) throw new UserNotFoundException("The requested user " + name + " not found");
		author.setName(form.getName());
		author.setAvatar(form.getAvatar());
		author.setFbProfile(form.getFbProfile());
		author.setTwitterProfile(form.getTwitterProfile());
		author.setLinkedinProfile(form.getLinkedinProfile());
		adminRepository.save(author);
	}
	
}
