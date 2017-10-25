package com.kivimango.blog.domain;

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

}
