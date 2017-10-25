package com.kivimango.blog.services;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import com.kivimango.blog.domain.AuthorView;
import com.kivimango.blog.domain.BlogPostView;
import com.kivimango.blog.domain.TagView;
import com.kivimango.blog.domain.entity.Author;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;
import com.kivimango.blog.exception.BlogPostNotFoundException;
import com.kivimango.blog.repositories.BlogPostRepository;

/**
 * To check the Service class, we need to have an instance of Service class created and available as a 
 * @Bean so that we can @Autowire it in our test class. 
 * This configuration is achieved by using the @TestConfiguration annotation.
 * During component scanning, we might find components or configurations created only for specific 
 * tests accidentally get picked up everywhere. To help prevent that, Spring Boot provides 
 * @TestConfiguration annotation that can be used on classes in src/test/java to indicate that they should 
 * not be picked up by scanning.
 * 
 * Another interesting thing here is the use of @MockBean. It creates a Mock for the BlogPostRepository 
 * which can be used to bypass the call to the actual BlogPostRepository
 * .
 * @author kivimango
 */

@RunWith(SpringRunner.class)
public class BlogPostServiceImplTest {
	
	@TestConfiguration
	static class BlogPostServiceImplTestConfiguation {
		@Bean
		public BlogPostService blogPostService() {
			return new BlogPostServiceImpl();
		}
	}
	
	@Autowired
	BlogPostService service;
	
	@MockBean
	BlogPostRepository repo;
	
	// Given
	String slug2 = "non-existent-post";
	
	String authorName = "Sample Author";
	String avatar = "http://google.com/avatar.jpg";
	String fbProfile = "http://facebook.com/sample.author";
	String twitterProfile = "http://twitter.com/sample-author";
	String linkedinProfile = "http://www.linkedin.com/en/sample-author";
	Author author;
	
	String testTag = "test-teg";
	Tag tag = new Tag();
	List<Tag> tags = new ArrayList<Tag>();
	
	String title = "Sample Title";
	String slug = "sample-title";
	String content = "sample content";
	Date uploaded = new Date();
	Date edited = new Date();
	BlogPost post;
	
	Pageable pageable = new PageRequest(0, 30);
	List<BlogPost> list;
	Page<BlogPost> posts;
	
	@Before
	public void setup() {
		// When
		author = new Author();
		author.setName(authorName);
		author.setAvatar(avatar);
		author.setFbProfile(fbProfile);
		author.setTwitterProfile(twitterProfile);
		author.setLinkedinProfile(linkedinProfile);
		
		tag.setTag(testTag);
		tags.add(tag);
		
		post = new BlogPost();
		post.setTitle(title);
		post.setSlug(slug);
		post.setAuthor(author);
		post.setContent(content);
		post.setUploaded(uploaded);
		post.setEdited(edited);
		post.setTags(tags);
		
		list= new ArrayList<BlogPost>(1);
		list.add(post);
		posts = new PageImpl<BlogPost>(list);
		
		Mockito.when(repo.findAll(pageable)).thenReturn(posts);
		Mockito.when(repo.getPostBySlug(slug)).thenReturn(post);
		Mockito.when(repo.getPostBySlug(slug2)).thenReturn(null);
	}
	
	@Test
	public void testFindAllWithPagination() throws BlogPostNotFoundException {
		Page<BlogPostView> page = service.findAll(pageable);
		
		assertEquals(0, page.getNumber());
		assertEquals(30, page.getSize());
		assertEquals(1, page.getNumberOfElements());
		assertEquals(title, page.getContent().get(0).getTitle());
		assertEquals(slug, page.getContent().get(0).getSlug());
		assertEquals(content, page.getContent().get(0).getContent());
		assertEquals(uploaded, page.getContent().get(0).getUploaded());
		assertEquals(edited, page.getContent().get(0).getEdited());
		
		assertEquals(true, page.getContent().get(0).getAuthor() instanceof AuthorView);
		assertEquals(authorName, page.getContent().get(0).getAuthor().getName());
		assertEquals(avatar, page.getContent().get(0).getAuthor().getAvatar());
		assertEquals(fbProfile, page.getContent().get(0).getAuthor().getFbProfile());
		assertEquals(twitterProfile, page.getContent().get(0).getAuthor().getTwitterProfile());
		assertEquals(linkedinProfile, page.getContent().get(0).getAuthor().getLinkedinProfile());
	}
	
	@Test
	public void testGetPostBySlugShouldReturnBlogPostView() throws BlogPostNotFoundException {
		// Then
		BlogPostView postView = service.getPostBySlug(slug);
		
		assertEquals(true, postView != null);
		assertEquals(true, postView instanceof BlogPostView);
		assertEquals(title, postView.getTitle());
		assertEquals(slug, postView.getSlug());
		assertEquals(content, postView.getContent());
		assertEquals(uploaded, postView.getUploaded());
		assertEquals(edited, postView.getEdited());
		
		assertEquals(true, postView.getTags().get(0) instanceof TagView);
		assertEquals(testTag, postView.getTags().get(0).getTag());
		assertEquals(1, postView.getTags().size());
		
		assertEquals(true, postView.getAuthor() instanceof AuthorView);
		assertEquals(authorName, postView.getAuthor().getName());
		assertEquals(avatar, postView.getAuthor().getAvatar());
		assertEquals(fbProfile, postView.getAuthor().getFbProfile());
		assertEquals(twitterProfile, postView.getAuthor().getTwitterProfile());
		assertEquals(linkedinProfile, postView.getAuthor().getLinkedinProfile());
	}
	
	@Test(expected=BlogPostNotFoundException.class)
	public void testBlogPostNotFoundShouldThrowException() throws BlogPostNotFoundException {
		@SuppressWarnings("unused")
		BlogPostView post = service.getPostBySlug(slug2);
	}
	
}
