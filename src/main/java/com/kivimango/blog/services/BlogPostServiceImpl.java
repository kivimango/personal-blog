package com.kivimango.blog.services;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kivimango.blog.domain.BlogPostConverter;
import com.kivimango.blog.domain.BlogPostView;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;
import com.kivimango.blog.domain.form.BlogPostForm;
import com.kivimango.blog.domain.page.Page;
import com.kivimango.blog.exception.BlogPostNotFoundException;
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
	private TagRepository tagRepository;
	private BlogPostConverter converter = new BlogPostConverter();
	
	@Autowired
	public BlogPostServiceImpl(BlogPostRepository postRepository, TagRepository tagRepository) {
		this.postRepository = postRepository;
		this.tagRepository = tagRepository;
	}

	@Override
	public Page<BlogPostView> findAllExcludeHidden(int pageNum) {
		Page<BlogPost> posts = postRepository.findAllByHidden(pageNum, false);
		return converter.convert(posts);
	}
	
	@Override
	public List<BlogPostView> findAll() {
		return converter.convert(postRepository.findAll());
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
	@Transactional
	public BlogPostView save(BlogPostForm form) {
		BlogPost newPost = savePost(form);
		newPost.setTags(saveTags(form.getTags(), newPost));
		return converter.convert(newPost);
	}

	private BlogPost savePost(BlogPostForm form) {
		BlogPost newPost = new BlogPost();
		newPost.setTitle(form.getTitle());
		newPost.setSlug(makeSlugFrom(form.getTitle()));
		newPost.setContent(form.getContent());
		newPost.setUploaded(new Date());
		newPost = postRepository.save(newPost);
		return newPost;
	}
	
	@Override
	@Transactional
	public BlogPostView edit(String slug, BlogPostForm form) throws BlogPostNotFoundException {
		BlogPost post = postRepository.getPostBySlug(slug);
		BlogPost edited = post;
		if(post == null) throw new BlogPostNotFoundException("The requested blog post not found !");
		else {
			if(!post.getTitle().equals(form.getTitle())) {
				edited.setTitle(form.getTitle());
				edited.setSlug(makeSlugFrom(form.getTitle()));
			}
			if(!post.getContent().equals(form.getContent())) edited.setContent(form.getContent());
			post.setEdited(new Date());
			post.setTags(makeTagsFromInputField(form.getTags()));
			post = postRepository.edit(post);
			tagRepository.deleteFrom(post);
			post.setTags(saveTags(form.getTags(), post));
			return converter.convert(post);
		}
	}

	public String makeSlugFrom(String title) {
		return Normalizer.normalize(title, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
		.replaceAll("[^\\w-]+", " ").trim().toLowerCase().replaceAll("\\s", "-");
	}
	
	private List<Tag> saveTags(String tagField, BlogPost post) {
		List<Tag> tags = makeTagsFromInputField(tagField);
		if(!tags.isEmpty()) {
			List<BlogPost> postList = new ArrayList<BlogPost>();
			postList.add(post);
			for(int i = 0; i<tags.size(); i++) {
				Tag t = tags.get(i);
				t.setPost(postList);
				findTagOrSave(t, post);
			}
		}
		return tags;
	}
	
	private List<Tag> makeTagsFromInputField(String value) {
		List<Tag> converted = new ArrayList<Tag>();
		String[] tags = value.split(",");
		if(tags.length > 0) {
			for(int i = 0; i<tags.length; i++) {
				converted.add(new Tag(tags[i]));
			}
		}
		return converted;
	}
	
	private void findTagOrSave(Tag tag, BlogPost post) {
		if(tagRepository.isExists(tag)) {
			Tag existingTag = tagRepository.findByTag(tag.getTag());
			tag.setId(existingTag.getId());
			tagRepository.saveExistingWith(post, tag);
		} else {
			 addNewTagToDbWithPost(tag, post);
		}
	}
	
	private void addNewTagToDbWithPost(Tag tag, BlogPost post) {
		Tag newTag = tag;
		newTag = tagRepository.save(newTag);
		tagRepository.saveExistingWith(post, newTag);
	}
	
	@Override
	@Transactional
	public BlogPostView hideOrPublish(String slug) throws BlogPostNotFoundException {
		BlogPost post = postRepository.getPostBySlug(slug);
		if(post == null) throw new BlogPostNotFoundException("The requested blog post not found !");
		else {
			if(!post.isHidden()) hide(post); else publish(post);
		}
		return converter.convert(post);
	}

	private void hide(BlogPost post) {
		post.setHidden(true);
		postRepository.edit(post);
	}
	
	private void publish(BlogPost post) {
		post.setHidden(false);
		postRepository.edit(post);
	}
	
	@Override
	public List<BlogPostView> findPostsByTag(String t) {
		return converter.convert(postRepository.findByTags(t));
	}

}
