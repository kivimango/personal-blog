package com.kivimango.blog.services;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.kivimango.blog.domain.AuthorConverter;
import com.kivimango.blog.domain.BlogPostConverter;
import com.kivimango.blog.domain.BlogPostView;
import com.kivimango.blog.domain.entity.Author;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;
import com.kivimango.blog.domain.form.BlogPostForm;
import com.kivimango.blog.exception.AuthorNotFoundException;
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
	
	@Autowired
	private AuthorService authorRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	private BlogPostConverter converter = new BlogPostConverter(new AuthorConverter());
	
	@Override
	public Page<BlogPostView> findAll(Pageable pageable) {
		Page<BlogPost> posts = postRepository.findAll(pageable);
		return converter.convert(posts, pageable);
	}
	
	@Override
	public BlogPostView getPostBySlug(String slug) throws BlogPostNotFoundException {
		BlogPost post = postRepository.getPostBySlug(slug);
		if(post != null) {
			return converter.convert(post);
		} else throw new BlogPostNotFoundException("The requested blog post not found !");	
	}
	
	@Override
	public void save(BlogPostForm form) throws AuthorNotFoundException {
		BlogPost newPost = new BlogPost();
		newPost.setTitle(form.getTitle());
		newPost.setSlug(makeSlugFrom(form.getTitle()));
		newPost.setAuthor(findAuthor(form.getAuthor()));
		newPost.setContent(form.getContent());
		newPost.setUploaded(new Date());
		if(form.getTags() != null) {
			newPost.setTags(makeTagsFromInputField(form.getTags(), newPost));
		}
		postRepository.save(newPost);
	}

	private Author findAuthor(String name) throws AuthorNotFoundException {
		Author author = authorRepository.findByName(name);
		if(author == null) {
			throw new AuthorNotFoundException("Author not found: " + name);
		}
		return author;
	}
	
	public String makeSlugFrom(String title) {
		return Normalizer.normalize(title, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").
			toLowerCase().replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
		    .replaceAll("[^\\p{Alnum}]+", "-");
	}
	
	private List<Tag> makeTagsFromInputField(String value, BlogPost post) {
		String[] array = value.split(",");
		List<Tag> converted = new ArrayList<Tag>();
		for(int i = 0; i<array.length; i++) {
			findTagFromDbOrAddToIt(array[i], post);
		}
		return converted;
	}
	
	private Tag findTagFromDbOrAddToIt(String tag, BlogPost post) {
		Tag tagFromDb = tagRepository.findByTag(tag);
		if(tagFromDb != null) {
			return tagFromDb;
		} else {
			Tag newTag = new Tag();
			newTag.setTag(tag);
			newTag.setPost(Arrays.asList(post));
			tagRepository.save(newTag);
			return newTag;
		}
	}

	@Autowired
	public void setBlogPostRepository(BlogPostRepository repo) {
		this.postRepository = repo;
	}

}
