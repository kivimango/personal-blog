package com.kivimango.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kivimango.blog.domain.AuthorConverter;
import com.kivimango.blog.domain.AuthorView;
import com.kivimango.blog.domain.entity.Author;
import com.kivimango.blog.repositories.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	private AuthorRepository repo;
	
	private AuthorConverter converter = new AuthorConverter();
	
	@Autowired
	public AuthorServiceImpl(AuthorRepository repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<AuthorView> findAll() {
		return converter.convert(repo.findAll());
	}

	@Override
	public Author findByName(String name) {
		return repo.findByName(name);
	}

}
