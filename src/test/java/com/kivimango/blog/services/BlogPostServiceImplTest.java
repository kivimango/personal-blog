package com.kivimango.blog.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.kivimango.blog.domain.AdminDetail;
import com.kivimango.blog.domain.AuthorView;
import com.kivimango.blog.domain.BlogPostView;
import com.kivimango.blog.domain.TagView;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.form.BlogPostForm;
import com.kivimango.blog.exception.BlogPostNotFoundException;
import com.kivimango.blog.repositories.AdminRepository;
import com.kivimango.blog.repositories.BlogPostRepository;
import com.kivimango.blog.repositories.TagRepository;
import com.kivimango.blog.samples.BlogPostFactory;

/**
 * @author kivimango
 */

@RunWith(SpringRunner.class)
public class BlogPostServiceImplTest extends BlogPostFactory {
	
	@Mock
	@Qualifier("blogPostRepository")
	private BlogPostRepository repo;
	
	@Mock
	@Qualifier("adminRepository")
	private AdminRepository adminRepo;
	
	@Mock
	@Qualifier("tagRepository")
	private TagRepository tagRepo;
	
	@InjectMocks
	private BlogPostServiceImpl service;
	
	private Pageable pageable = new PageRequest(0, 30);
	
	@Test
	public void testFindAllExcludeHiddenShouldReturnPublishedPostsOnly() {
		given(repo.findAllByHidden(pageable, false)).willReturn(getPublicPostsListwithPagination());
		Page<BlogPostView> posts = service.findAllExcludeHidden(pageable);
		for(BlogPostView p : posts) {
			assertFalse(p.isHidden());
		}
	}
	
	@Test
	public void testFindAllShouldReturnAllPostsWithPagination() throws BlogPostNotFoundException {
		given(repo.findAll(pageable)).willReturn(getPublicPostsListwithPagination());
		Page<BlogPostView> page = service.findAll(pageable);
		
		assertEquals(0, page.getNumber());
		assertEquals(30, page.getSize());
		assertEquals(3, page.getNumberOfElements());
		assertEquals(title, page.getContent().get(0).getTitle());
		assertEquals(slug, page.getContent().get(0).getSlug());
		assertEquals(content, page.getContent().get(0).getContent());
		assertEquals(uploaded, page.getContent().get(0).getUploaded());
		assertEquals(edited, page.getContent().get(0).getEdited());
		
		assertTrue(page.getContent().get(0).getAuthor() instanceof AuthorView);
		assertEquals(authorName, page.getContent().get(0).getAuthor().getName());
		assertEquals(avatar, page.getContent().get(0).getAuthor().getAvatar());
		assertEquals(fbProfile, page.getContent().get(0).getAuthor().getFbProfile());
		assertEquals(twitterProfile, page.getContent().get(0).getAuthor().getTwitterProfile());
		assertEquals(linkedinProfile, page.getContent().get(0).getAuthor().getLinkedinProfile());
	}
	
	@Test
	public void testGetPostBySlugShouldReturnBlogPostView() throws BlogPostNotFoundException {
		given(repo.getPostBySlug(slug)).willReturn(getSamplePostEntity());
		BlogPostView postView = service.getPostBySlug(slug);
		
		assertTrue(postView != null);
		assertTrue(postView instanceof BlogPostView);
		assertEquals(title, postView.getTitle());
		assertEquals(slug, postView.getSlug());
		assertEquals(content, postView.getContent());
		
		assertTrue(postView.getAuthor() instanceof AuthorView);
		assertEquals(authorName, postView.getAuthor().getName());
		assertEquals(avatar, postView.getAuthor().getAvatar());
		assertEquals(fbProfile, postView.getAuthor().getFbProfile());
		assertEquals(twitterProfile, postView.getAuthor().getTwitterProfile());
		assertEquals(linkedinProfile, postView.getAuthor().getLinkedinProfile());
		
		assertEquals(uploaded, postView.getUploaded());
		assertEquals(edited, postView.getEdited());
		
		assertTrue(postView.getTags().get(0) instanceof TagView);
		assertEquals(testTag, postView.getTags().get(0).getTag());
		assertEquals(1, postView.getTags().size());
	}
	
	@Test(expected=BlogPostNotFoundException.class)
	public void testGetPostBySlugShouldThrowException() throws BlogPostNotFoundException {
		String nonExistingPostSlug = "non-existent-post";
		Mockito.when(repo.getPostBySlug(nonExistingPostSlug)).thenReturn(null);
		@SuppressWarnings("unused")
		BlogPostView nonExistingPost = service.getPostBySlug(nonExistingPostSlug);
	}
	
	@Test(expected=BlogPostNotFoundException.class)
	public void testgetPostBySlugNullParamaterShouldThrowException() throws BlogPostNotFoundException {
		when(repo.getPostBySlug(null)).thenReturn(null);
		@SuppressWarnings("unused")
		BlogPostView post = service.getPostBySlug(null);
	}
	
	@Test
	public void testSave() {
		BlogPostForm form = new BlogPostForm();
		String title = "A sample title";
		String content = "content with [b]bbcode[/b] [i]formatting[/i]";
		String tags = "#tag,#test";
		form.setTitle(title);
		form.setContent(content);
		form.setTags(tags);
		AdminDetail currentAdmin = new AdminDetail(getSampleAdminEntity());
		
		given(adminRepo.findByUsername(currentAdmin.getUsername())).willReturn(getSampleAdminEntity());
		
		BlogPostView result = service.save(form, currentAdmin);
		
		assertEquals("a-sample-title", result.getSlug());
		assertEquals("content with <b>bbcode</b> <i>formatting</i>", result.getContent());
		assertEquals("#tag", result.getTags().get(0).toString());
		assertEquals("#test", result.getTags().get(1).toString());
	}
	
	@Test
	public void testEdit() throws BlogPostNotFoundException {
		BlogPostForm form = new BlogPostForm();
		String editedTitle = "An edited title";
		String editedContent = "Edited content with [b]bbcode[/b] [i]formatting[/i]";
		String expectedEditedContent = "Edited content with <b>bbcode</b> <i>formatting</i>";
		String editedTags = "#tag,#test,#edited";
		form.setTitle(editedTitle);
		form.setContent(editedContent);
		form.setTags(editedTags);
		
		given(repo.getPostBySlug(slug)).willReturn(getSamplePostEntity());
		
		BlogPostView editedPost = service.edit(slug, form);
		
		assertEquals(editedTitle, editedPost.getTitle());
		assertEquals(expectedEditedContent, editedPost.getContent());
		assertEquals("#edited", editedPost.getTags().get(2).toString());
	}
	
	@Test
	public void testMakeSlugFromshouldLowerCaseCapitalsReplaceWhiteSpacesWithDashesAndRemoveSpecialCharacters() {
		String title = "An example Title";
		String expected = "an-example-title";
		String result = service.makeSlugFrom(title);
		assertEquals(expected, result);
	}
	
	@Test
	public void testMakeSlugFromshouldReplaceHungarianCharactersWithTheirNeighborhood() {
		String hungarianTitle = "Árvíztűrő tükörfűrőgép";
		String expected = "arvizturo-tukorfurogep";
		String result = service.makeSlugFrom(hungarianTitle);
		assertEquals(expected, result);
	}
	
	@Test
	public void testMakeSlugFromshouldRemoveSpecialCharacters() {
		String titleWithSpecialChars = "I am a man with a Plan !";
		String expected = "i-am-a-man-with-a-plan";
		String result = service.makeSlugFrom(titleWithSpecialChars);
		assertEquals(expected, result);
	}
	
	@Test
	public void testMakeSlugFromshouldTrim() {
		String titleWithWhiteSpaceAtTheEnd = "I am a man with a Plan ";
		String expected = "i-am-a-man-with-a-plan";
		String result = service.makeSlugFrom(titleWithWhiteSpaceAtTheEnd);
		assertEquals(expected, result);
	}
	
	@Test
	public void testHideOrPublishShouldHidePublishedPost() throws BlogPostNotFoundException {
		given(repo.getPostBySlug(slug)).willReturn(getSamplePostEntity());
		BlogPostView modifiedPost = service.hideOrPublish(slug);
		assertTrue(modifiedPost.isHidden());
	}
	
	@Test
	public void testHideOrPublishShouldPublishHiddenPost() throws BlogPostNotFoundException {
		BlogPost hiddenPost = getSamplePostEntity();
		hiddenPost.setHidden(true);
		given(repo.getPostBySlug(slug)).willReturn(hiddenPost);
		BlogPostView modifiedPost = service.hideOrPublish(slug);
		assertFalse(modifiedPost.isHidden());
	}
}
