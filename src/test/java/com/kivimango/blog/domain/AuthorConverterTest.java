package com.kivimango.blog.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.kivimango.blog.domain.entity.Admin;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class AuthorConverterTest {
	
	AuthorConverter converter = new AuthorConverter();
	
	@Test
	public void testAuthorConverter() {
		// Given
		Short id = 1;
		String name ="Sample author";
		String avatar = "http://avatarblog.typepad.com/.a/6a0120a6b2c140970c012876d22e0c970c-pi";
		String fbProfile = "http://facebook.com/author";
		String twitterProfile = "http://twitter.com/sample-author";
		String linkedinProfile = "http://www.linkedin.com/en/sample-author";
		String githubProfile = "http://www.github.com/";
		
		Admin sample = new Admin();
		sample.setId(id);
		sample.setName(name);
		sample.setAvatar(avatar);
		sample.setFbProfile(fbProfile);
		sample.setLinkedinProfile(linkedinProfile);
		sample.setTwitterProfile(twitterProfile);
		sample.setGithubProfile(githubProfile);
		
		// When
		AuthorView view = converter.convert(sample);
		
		// Then
		assertEquals(name, view.getName());
		assertEquals(avatar, view.getAvatar());
		assertEquals(fbProfile, view.getFacebook());
		assertEquals(twitterProfile, view.getTwitter());
		assertEquals(linkedinProfile, view.getLinkedin());
	}

}
