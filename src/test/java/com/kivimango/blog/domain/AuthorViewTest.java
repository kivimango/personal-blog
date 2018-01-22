package com.kivimango.blog.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class AuthorViewTest {
	
	@Test
	public void testAuthorView() {
		// Given
		String name = "Sample Author";
		String avatar = "http://picture.com/avatar.jpg";
		String fbProfile = "http://facebook.com/author";
		String twitterProfile = "http://twitter.com/sample-author";
		String linkedinProfile = "http://www.linkedin.com/en/sample-author";
		String githubProfile = "http://www,github.com/";
		
		// When
		AuthorView view = new AuthorView(name, avatar, fbProfile, twitterProfile, linkedinProfile, githubProfile);
		
		// Then
		assertEquals(name, view.getName());
		assertEquals(avatar, view.getAvatar());
		assertEquals(fbProfile, view.getFacebook());
		assertEquals(twitterProfile, view.getTwitter());
		assertEquals(linkedinProfile, view.getLinkedin());
		assertEquals(githubProfile, view.getGithub());
	}

}
