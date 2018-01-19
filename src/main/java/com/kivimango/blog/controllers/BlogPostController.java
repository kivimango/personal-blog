package com.kivimango.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.kivimango.blog.exception.BlogPostNotFoundException;
import com.kivimango.blog.services.BlogPostService;
import com.kivimango.blog.services.TagService;

/** 
 * Displaying a full post to the user.
 * Posts can be queried by their slug.
 * 
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Controller
public class BlogPostController {
	
	@Value("${blog.title}")
	private String title;
	
	@Value("${blog.description}")
	private String description;
	
	private static final String DETAIL_PAGE = "blogPost";
	
	private BlogPostService posts;
	private TagService tags;
	
	@Autowired
	public BlogPostController(BlogPostService service, TagService tags) {
		this.posts = service;
		this.tags = tags;
	}

	@GetMapping("poszt/{slug}")
	public String readBlogPost(@PathVariable(name="slug") String slug, Model model) throws BlogPostNotFoundException {
		model.addAttribute("blogTitle", title);
		model.addAttribute("title", title + " - " + description);
		model.addAttribute("post", posts.getPostBySlug(slug));
		model.addAttribute("recentPosts", posts.findRecentPosts());
		model.addAttribute("tags", tags.getFirstTenTags());
		return DETAIL_PAGE;
	}

}
