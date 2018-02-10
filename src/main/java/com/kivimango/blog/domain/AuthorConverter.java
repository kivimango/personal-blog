package com.kivimango.blog.domain;

import java.util.ArrayList;
import java.util.List;
import com.kivimango.blog.domain.entity.Admin;

public class AuthorConverter {
	
	public Author convert(final Admin author) {
		return new Author(author.getName(), author.getAvatar(),
				author.getFbProfile(), author.getTwitterProfile(), author.getLinkedinProfile(), author.getGithubProfile());
	}
	
	public List<Author> convert(final List<Admin> authors) {
		List<Author> converted = new ArrayList<>();
		for(int i=0; i<authors.size(); i++) {
			converted.add(convert(authors.get(i)));
		}
		return converted;
	}
}
