package com.kivimango.blog.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.kivimango.blog.domain.entity.Author;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class AuthorViewConverterTest {
	
	AuthorConverter converter = new AuthorConverter();
	
	@Test
	public void testAuthorConverter() {
		// Given
		Integer id = 1;
		String name ="Sample author";
		String avatar = "http://avatarblog.typepad.com/.a/6a0120a6b2c140970c012876d22e0c970c-pi";
		String fbProfile = "http://facebook.com/author";
		String twitterProfile = "http://twitter.com/sample-author";
		String linkedinProfile = "http://www.linkedin.com/en/sample-author";
		
		Author sample = new Author();
		sample.setId(id);
		sample.setName(name);
		sample.setAvatar(avatar);
		sample.setFbProfile(fbProfile);
		sample.setLinkedinProfile(linkedinProfile);
		sample.setTwitterProfile(twitterProfile);
		
		// When
		AuthorView view = converter.convert(sample);
		
		// Then
		assertEquals(name, view.getName());
		assertEquals(avatar, view.getAvatar());
		assertEquals(fbProfile, view.getFbProfile());
		assertEquals(twitterProfile, view.getTwitterProfile());
		assertEquals(linkedinProfile, view.getLinkedinProfile());
	}

}
