package com.kivimango.blog.services;

import com.kivimango.blog.domain.AuthorView;
import com.kivimango.blog.domain.entity.Author;

public interface AuthorService {
	
	Iterable<AuthorView> findAll();

	Author findByName(String name);

}
