package com.kivimango.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.kivimango.blog.domain.AuthorConverter;
import com.kivimango.blog.domain.BlogPostConverter;
import com.kivimango.blog.domain.BlogPostView;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.exception.BlogPostNotFoundException;
import com.kivimango.blog.repositories.BlogPostRepository;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Service
public class BlogPostServiceImpl implements BlogPostService {

	/**
	 * Automatically generated Spring Data JPA Repository
	 */
	
	private BlogPostRepository postRepository;
	
	/**
	 * Converter of the database entities into DTO objects.
	 */
	
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
	
	@Autowired
	public void setBlogPostRepository(BlogPostRepository repo) {
		this.postRepository = repo;
	}

}
