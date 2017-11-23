package com.kivimango.blog.domain.entity;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class TagTest {
	
	@Test
	public void testTag() {
		// Given
		Integer id = 23;
		String testTag = "test-tag";
		
		BlogPost post = new BlogPost();
		List<BlogPost> posts = Arrays.asList(post);
		
		// When
		Tag tag = new Tag();
		tag.setId(id);
		tag.setTag(testTag);
		tag.setPost(posts);
		
		// Then
		assertEquals(id, tag.getId());
		assertEquals(testTag, tag.getTag());
		assertEquals(posts, tag.getPost());
	}

}
