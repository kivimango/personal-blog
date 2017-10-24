package com.kivimango.blog.domain.entity;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.kivimango.blog.domain.entity.Author;
import com.kivimango.blog.domain.entity.BlogPost;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class AuthorTest {

	@Test
	public void testAuthor() {
		// Given
		Integer id = 1;
		String name ="Sample author";
		List<BlogPost> posts = new ArrayList<>();
		String avatar = "http://avatarblog.typepad.com/.a/6a0120a6b2c140970c012876d22e0c970c-pi";
		String fbProfile = "http://facebook.com/author";
		String twitterProfile = "http://twitter.com/sample-author";
		String linkedinProfile = "http://www.linkedin.com/en/sample-author";
						
		// When
		Author author = new Author();
		author.setId(id);
		author.setName(name);
		author.setPosts(posts);
		author.setAvatar(avatar);
		author.setFbProfile(fbProfile);
		author.setLinkedinProfile(linkedinProfile);
		author.setTwitterProfile(twitterProfile);
		author.setPosts(posts);
		
		// Then
		assertEquals(id, author.getId());
		assertEquals(name, author.getName());
		assertEquals(posts, author.getPosts());
		assertEquals(posts.size(), author.getPosts().size());
		assertEquals(avatar, author.getAvatar());
		assertEquals(fbProfile, author.getFbProfile());
		assertEquals(linkedinProfile, author.getLinkedinProfile());
		assertEquals(twitterProfile, author.getTwitterProfile());
	}
}
