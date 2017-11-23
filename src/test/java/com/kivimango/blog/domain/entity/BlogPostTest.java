package com.kivimango.blog.domain.entity;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class BlogPostTest {
	
	@Test
	public void testBlogPost() {
		// Given
		String title = "Sample Title";
		String slug = "sample-title";
		Admin author = new Admin();
		String content = "sample content";
		Date uploaded = new Date();
		Date edited = new Date();
		boolean hidden = false;
		
		Tag tag = new Tag();
		tag.setId(23);
		tag.setTag("test-teg");
		List<Tag> tags = new ArrayList<Tag>();
		tags.add(tag);
		
		// When
		BlogPost post = new BlogPost();
		post.setTitle(title);
		post.setSlug(slug);
		post.setAuthor(author);
		post.setContent(content);
		post.setUploaded(uploaded);
		post.setEdited(edited);
		post.setTags(tags);
		post.setHidden(hidden);
		
		// Then
		assertEquals(title, post.getTitle());
		assertEquals(slug, post.getSlug());
		assertEquals(author, post.getAuthor());
		assertEquals(true, post.getAuthor() instanceof Admin);
		assertEquals(content, post.getContent());
		assertEquals(uploaded, post.getUploaded());
		assertEquals(edited, post.getEdited());
		assertEquals(tags, post.getTags());
		assertEquals(hidden, post.isHidden());
	}

}
