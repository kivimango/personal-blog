package com.kivimango.blog.domain;

import java.util.ArrayList;
import java.util.List;

import com.kivimango.blog.domain.entity.Author;

public class AuthorConverter {
	
	public AuthorView convert(final Author author) {
		AuthorView converted = new AuthorView();
		converted.setName(author.getName());
		converted.setAvatar(author.getAvatar());
		converted.setFbProfile(author.getFbProfile());
		converted.setTwitterProfile(author.getTwitterProfile());
		converted.setLinkedinProfile(author.getLinkedinProfile());
		return converted;
	}

	public Iterable<AuthorView> convert(Iterable<Author> list) {
		List<AuthorView> converted = new ArrayList<AuthorView>();
		for(Author a : list) {
			converted.add(convert(a));
		}
		return converted;
	}

}
