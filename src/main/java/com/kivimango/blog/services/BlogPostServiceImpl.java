package com.kivimango.blog.services;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kivimango.blog.domain.AdminDetail;
import com.kivimango.blog.domain.AuthorConverter;
import com.kivimango.blog.domain.BlogPostConverter;
import com.kivimango.blog.domain.BlogPostView;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;
import com.kivimango.blog.domain.form.BlogPostForm;
import com.kivimango.blog.domain.page.Page;
import com.kivimango.blog.exception.BlogPostNotFoundException;
import com.kivimango.blog.repositories.AdminRepository;
import com.kivimango.blog.repositories.BlogPostRepository;
import com.kivimango.blog.repositories.TagRepository;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Service
public class BlogPostServiceImpl implements BlogPostService {
	
	private BlogPostRepository postRepository;
	private AdminRepository admins;
	private TagRepository tagRepository;
	
	private BlogPostConverter converter = new BlogPostConverter(new AuthorConverter());
	
	@Autowired
	public BlogPostServiceImpl(BlogPostRepository postRepository, AdminRepository admins, TagRepository tagRepository) {
		this.postRepository = postRepository;
		this.admins = admins;
		this.tagRepository = tagRepository;
	}

	@Override
	public Page<BlogPostView> findAllExcludeHidden(int pageNum) {
		Page<BlogPost> posts = postRepository.findAllByHidden(pageNum, false);
		return converter.convert(posts);
	}
	
	@Override
	public Page<BlogPostView> findAll() {
		Page<BlogPost> posts = postRepository.findAll();
		return converter.convert(posts);
	}
	
	@Override
	public List<BlogPostView> findRecentPosts() {
		List<BlogPost> recentPosts = postRepository.findFirst5ByOrderByUploadedDesc();
		return converter.convert(recentPosts);
	}
	
	@Override
	public BlogPostView getPostBySlug(String slug) throws BlogPostNotFoundException {
		BlogPost post = postRepository.getPostBySlug(slug);
		if(post == null || post.isHidden()) {
			throw new BlogPostNotFoundException("The requested blog post not found !");
		} else return converter.convert(post);
	}
	
	@Override
	public BlogPostView save(BlogPostForm form, AdminDetail author) {
		BlogPost newPost = new BlogPost();
		newPost.setTitle(form.getTitle());
		newPost.setSlug(makeSlugFrom(form.getTitle()));
		newPost.setAuthor(admins.findByUsername(author.getUsername()));
		newPost.setContent(form.getContent());
		newPost.setUploaded(new Date());
		newPost.setTags(makeTagsFromInputField(form.getTags(), newPost));
		postRepository.save(newPost);
		return converter.convert(newPost);
	}
	
	@Override
	public BlogPostView edit(String slug, BlogPostForm form) throws BlogPostNotFoundException {
		BlogPost edited = postRepository.getPostBySlug(slug);
		if(edited == null) throw new BlogPostNotFoundException("The requested blog post not found !");
		else {
			if(!edited.getTitle().equals(form.getTitle())) {
				edited.setTitle(form.getTitle());
				edited.setSlug(makeSlugFrom(form.getTitle()));
			}
			if(!edited.getContent().equals(form.getContent())) edited.setContent(form.getContent());
			edited.setEdited(new Date());
			edited.setTags(makeTagsFromInputField(form.getTags(), edited));
			postRepository.save(edited);
			return converter.convert(edited);
		}
	}

	public String makeSlugFrom(String title) {
		return Normalizer.normalize(title, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
		.replaceAll("[^\\w-]+", " ").trim().toLowerCase().replaceAll("\\s", "-");
	}
	
	private List<Tag> makeTagsFromInputField(String value, BlogPost post) {
		String[] tags = value.split(",");
		if(tags.length > 0) {
			List<Tag> converted = new ArrayList<Tag>(tags.length);
			for(int i = 0; i<tags.length; i++) {
				converted.add(findTagFromDbOrAddToIt(tags[i], post));
			}
			return converted;
		}
		return new ArrayList<Tag>(0);
	}
	
	private Tag findTagFromDbOrAddToIt(String tag, BlogPost post) {
		Tag existingTag = tagRepository.findByTag(tag);
		if(existingTag != null) {
			return addExistingTagToPost(post, existingTag);
		} else {
			return addNewTagToDbAndToPost(tag);
		}
	}

	private Tag addExistingTagToPost(BlogPost post, Tag existingTag) {
		List<BlogPost> postsOfExistingTag = existingTag.getPost();
		postsOfExistingTag.add(post);
		existingTag.setPost(postsOfExistingTag);
		return existingTag;
	}
	
	private Tag addNewTagToDbAndToPost(String tag) {
		Tag newTag = new Tag();
		newTag.setTag(tag);
		List<BlogPost> taggedPost = new ArrayList<BlogPost>(1);
		newTag.setPost(taggedPost);
		tagRepository.save(newTag);
		return newTag;
	}
	
	@Override
	public BlogPostView hideOrPublish(String slug) throws BlogPostNotFoundException {
		BlogPost post = postRepository.getPostBySlug(slug);
		if(post == null) throw new BlogPostNotFoundException("The requested blog post not found !");
		else {
			if(!post.isHidden()) hide(post); else publish(post);
			postRepository.save(post);
		}
		return converter.convert(post);
	}

	private void hide(BlogPost post) {
		post.setHidden(true);
	}
	
	private void publish(BlogPost post) {
		post.setHidden(false);
	}
	
	@Override
	public List<BlogPostView> findPostsByTag(String t) {
		Tag tagFromDb = tagRepository.findByTag(t);
		return converter.convert(postRepository.findByTags(tagFromDb));
	}

}
