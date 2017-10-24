package com.kivimango.blog.domain.entity;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.kivimango.blog.domain.entity.Tag;

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
		
		// When
		Tag tag = new Tag();
		tag.setId(id);
		tag.setTag(testTag);
		
		// Then
		assertEquals(id, tag.getId());
		assertEquals(testTag, tag.getTag());
	}

}
