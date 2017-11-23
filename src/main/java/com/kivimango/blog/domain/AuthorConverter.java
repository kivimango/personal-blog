package com.kivimango.blog.domain;

import com.kivimango.blog.domain.entity.Admin;

public class AuthorConverter {
	
	public AuthorView convert(final Admin author) {
		return new AuthorView(author.getName(), author.getAvatar(),
				author.getFbProfile(), author.getTwitterProfile(), author.getLinkedinProfile());
	}
}
