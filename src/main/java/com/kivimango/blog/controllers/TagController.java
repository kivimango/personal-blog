package com.kivimango.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.kivimango.blog.services.BlogPostService;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Controller
public class TagController {
	
	private BlogPostService postService;
	
	@Value("${blog.title}")
	private String title;
	
	@Value("${blog.description}")
	private String description;
	
	@Autowired
	public TagController(BlogPostService bps) {
		this.postService = bps;
	}

	@GetMapping("/cimke/{tag}")
	public String showPostsByTag(@PathVariable String tag, Model model) {
		model.addAttribute("blogTitle", title);
		model.addAttribute("title", title + " - " + description);
		model.addAttribute("posts", postService.findPostsByTag(tag));
		return "postsByTag";
	}

}
