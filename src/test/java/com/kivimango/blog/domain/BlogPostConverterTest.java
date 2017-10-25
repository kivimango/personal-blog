package com.kivimango.blog.domain;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import com.kivimango.blog.domain.entity.Author;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class BlogPostConverterTest {
	
	BlogPostConverter converter = new BlogPostConverter(new AuthorConverter());
	
	@Test
	public void testBlogPostConverter() {
		// Given
		Integer id = 1;
		String name ="Sample author";
		String avatar = "http://avatarblog.typepad.com/.a/6a0120a6b2c140970c012876d22e0c970c-pi";
		String fbProfile = "http://facebook.com/author";
		String twitterProfile = "http://twitter.com/sample-author";
		String linkedinProfile = "http://www.linkedin.com/en/sample-author";
		
		Author author = new Author();
		author.setId(id);
		author.setName(name);
		author.setAvatar(avatar);
		author.setFbProfile(fbProfile);
		author.setLinkedinProfile(linkedinProfile);
		author.setTwitterProfile(twitterProfile);
		
		BlogPost post = new BlogPost();
		String title = "Sample Title";
		String slug = "sample-title";
		String content = "sample content";
		Date uploaded = new Date();
		Date edited = new Date();
		
		Tag tag = new Tag();
		String testTag = "test-teg";
		tag.setId(23);
		tag.setTag(testTag);
		List<Tag> tags = new ArrayList<Tag>();
		tags.add(tag);
		
		// When
		post.setTitle(title);
		post.setSlug(slug);
		post.setAuthor(author);
		post.setContent(content);
		post.setUploaded(uploaded);
		post.setEdited(edited);
		post.setTags(tags);
		
		BlogPostView convertedPost = converter.convert(post);
		
		// Then
		assertEquals(title, convertedPost.getTitle());
		assertEquals(slug, convertedPost.getSlug());
		assertEquals(content, convertedPost.getContent());
		assertEquals(uploaded, convertedPost.getUploaded());
		assertEquals(edited, convertedPost.getEdited());
		
		assertEquals(true, convertedPost.getAuthor() instanceof AuthorView);
		assertEquals(name, convertedPost.getAuthor().getName());
		assertEquals(avatar, convertedPost.getAuthor().getAvatar());
		assertEquals(fbProfile, convertedPost.getAuthor().getFbProfile());
		assertEquals(twitterProfile, convertedPost.getAuthor().getTwitterProfile());
		assertEquals(linkedinProfile, convertedPost.getAuthor().getLinkedinProfile());
		
		assertEquals(true, convertedPost.getTags().get(0) instanceof TagView);
		assertEquals(1, convertedPost.getTags().size());
		assertEquals(testTag, convertedPost.getTags().get(0).getTag());
	}

}
