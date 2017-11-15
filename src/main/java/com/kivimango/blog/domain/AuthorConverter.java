package com.kivimango.blog.domain;

import com.kivimango.blog.domain.entity.Admin;

public class AuthorConverter {
	
	public AuthorView convert(final Admin author) {
		AuthorView converted = new AuthorView();
		converted.setName(author.getName());
		converted.setAvatar(author.getAvatar());
		converted.setFbProfile(author.getFbProfile());
		converted.setTwitterProfile(author.getTwitterProfile());
		converted.setLinkedinProfile(author.getLinkedinProfile());
		return converted;
	}
}
