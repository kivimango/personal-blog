package com.kivimango.blog.domain;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class BlogPostViewTest {
	
	@Test
	public void testBlogPostView() {
		// Given
		String title = "Sample Title";
		String slug = "sample-title";
		String content = "sample content";
		Date uploaded = new Date();
		Date edited = new Date();
		
		AuthorView author = new AuthorView();
		String authorName = "Sample Author";
		String avatar = "http:/google.com/avatar.jpg";
		String fbProfile = "http://facebook.com/sample.author";
		String twitterProfile = "http://twitter.com/sample-author";
		String linkedinProfile = "http://www.linkedin.com/en/sample-author";
		
		String testTag = "test-teg";
		TagView tag = new TagView(testTag);
		
		// When
		author.setName(authorName);
		author.setAvatar(avatar);
		author.setFbProfile(fbProfile);
		author.setTwitterProfile(twitterProfile);
		author.setLinkedinProfile(linkedinProfile);
		
		List<TagView> tags = new ArrayList<TagView>();
		tags.add(tag);
		
		BlogPostView post = new BlogPostView();
		post.setTitle(title);
		post.setSlug(slug);
		post.setAuthor(author);
		post.setContent(content);
		post.setUploaded(uploaded);
		post.setEdited(edited);
		post.setTags(tags);
		
		// Then
		assertEquals(title, post.getTitle());
		assertEquals(slug, post.getSlug());
		assertEquals(content, post.getContent());
		assertEquals(uploaded, post.getUploaded());
		assertEquals(edited, post.getEdited());
		
		assertEquals(author, post.getAuthor());
		assertEquals(true, post.getAuthor() instanceof AuthorView);
		assertEquals(authorName, post.getAuthor().getName());
		assertEquals(avatar, post.getAuthor().getAvatar());
		assertEquals(fbProfile, post.getAuthor().getFbProfile());
		assertEquals(twitterProfile, post.getAuthor().getTwitterProfile());
		assertEquals(linkedinProfile, post.getAuthor().getLinkedinProfile());
		
		assertEquals(tags, post.getTags());
		assertEquals(1, post.getTags().size());
		assertEquals(testTag, post.getTags().get(0).getTag());
		assertEquals(true, post.getTags().get(0) instanceof TagView);
	}

}
