package com.kivimango.blog.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.kefirsf.bb.BBProcessorFactory;
import org.kefirsf.bb.TextProcessor;
import com.kivimango.blog.samples.SampleBlogPost;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class BlogPostConverterTest extends SampleBlogPost {
	
	private BlogPostConverter converter = new BlogPostConverter(new AuthorConverter());
	
	private TextProcessor bbCodeConverter = BBProcessorFactory
			.getInstance().create();
	
	@Test
	public void testBlogPostConverter() {
		BlogPostView convertedPost = converter.convert(post);
		
		String covertedContent = bbCodeConverter.process(post.getContent());
		
		// Then
		assertEquals(post.getTitle(), convertedPost.getTitle());
		assertEquals(post.getSlug(), convertedPost.getSlug());
		assertEquals(covertedContent, convertedPost.getContent());
		assertEquals(post.getUploaded(), convertedPost.getUploaded());
		assertEquals(post.getEdited(), convertedPost.getEdited());
		
		assertEquals(true, convertedPost.getAuthor() instanceof AuthorView);
		assertEquals(post.getAuthor().getName(), convertedPost.getAuthor().getName());
		assertEquals(post.getAuthor().getAvatar(), convertedPost.getAuthor().getAvatar());
		assertEquals(post.getAuthor().getFbProfile(), convertedPost.getAuthor().getFacebook());
		assertEquals(post.getAuthor().getTwitterProfile(), convertedPost.getAuthor().getTwitter());
		assertEquals(post.getAuthor().getLinkedinProfile(), convertedPost.getAuthor().getLinkedin());
		assertEquals(post.getAuthor().getGithubProfile(), convertedPost.getAuthor().getGithub());
		
		
		assertEquals(true, convertedPost.getTags().get(0) instanceof TagView);
		assertEquals(1, convertedPost.getTags().size());
		assertEquals(post.getTags().get(0).getTag(), convertedPost.getTags().get(0).getTag());
	}

}
