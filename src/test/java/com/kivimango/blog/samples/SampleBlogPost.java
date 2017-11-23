package com.kivimango.blog.samples;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.kivimango.blog.domain.entity.Admin;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;

public class SampleBlogPost {
	
	protected BlogPost post = createSamplePost();
	
	private BlogPost createSamplePost() {
		// Given
		Short id = 1;
		String name ="Sample author";
		String avatar = "http://avatarblog.typepad.com/.a/6a0120a6b2c140970c012876d22e0c970c-pi";
		String fbProfile = "http://facebook.com/author";
		String twitterProfile = "http://twitter.com/sample-author";
		String linkedinProfile = "http://www.linkedin.com/en/sample-author";
		
		Admin author = new Admin();
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
				
		return post;
	}
}
