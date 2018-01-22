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
		boolean hidden = false;
		
		String authorName = "Sample Author";
		String avatar= "http:/google.com/avatar.jpg";
		String fbProfile = "http://facebook.com/sample.author";
		String twitterProfile = "http://twitter.com/sample-author";
		String linkedinProfile = "http://www.linkedin.com/en/sample-author";
		String githubProfile = "http://www,github.com/";
		
		String testTag = "test-teg";
		TagView tag = new TagView(testTag);
		
		// When
		AuthorView author = new AuthorView(authorName, avatar, fbProfile, twitterProfile, linkedinProfile, githubProfile);
		
		List<TagView> tags = new ArrayList<TagView>();
		tags.add(tag);
		
		BlogPostView post = new BlogPostView(title, slug, author, content, uploaded, edited, tags, hidden);
		
		// Then
		assertEquals(title, post.getTitle());
		assertEquals(slug, post.getSlug());
		assertEquals(content, post.getContent());
		assertEquals(uploaded, post.getUploaded());
		assertEquals(edited, post.getEdited());
		assertEquals(hidden, post.isHidden());
		
		assertEquals(author, post.getAuthor());
		assertEquals(true, post.getAuthor() instanceof AuthorView);
		assertEquals(authorName, post.getAuthor().getName());
		assertEquals(avatar, post.getAuthor().getAvatar());
		assertEquals(fbProfile, post.getAuthor().getFacebook());
		assertEquals(twitterProfile, post.getAuthor().getTwitter());
		assertEquals(linkedinProfile, post.getAuthor().getLinkedin());
		assertEquals(githubProfile, post.getAuthor().getGithub());
		
		assertEquals(tags, post.getTags());
		assertEquals(1, post.getTags().size());
		assertEquals(testTag, post.getTags().get(0).getTag());
		assertEquals(true, post.getTags().get(0) instanceof TagView);
	}

}
