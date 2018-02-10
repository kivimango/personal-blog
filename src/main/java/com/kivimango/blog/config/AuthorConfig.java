package com.kivimango.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.kivimango.blog.domain.Author;

@Configuration
public class AuthorConfig {
	
	@Value("${blog.author.name}")
	private String name;
	
	@Value("${blog.author.avatar}")
	private String avatar;
	
	@Value("${blog.author.fb}")
	private String fbProfile;
	
	@Value("${blog.author.twitter}")
	private String twitterProfile;
	
	@Value("${blog.author.linkedin}")
	private String linkedinProfile;
	
	@Value("${blog.author.github}")
	private String githubProfile;
	
	@Bean
	public Author makeAuthor() {
		return new Author(name, avatar, fbProfile, twitterProfile, linkedinProfile, githubProfile);
	}

}
