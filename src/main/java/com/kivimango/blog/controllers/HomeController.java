package com.kivimango.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.kivimango.blog.services.BlogPostService;

/** 
* @author kivimango
* @since 0.1
* @version 0.1
*/

@Controller
public class HomeController {
	
	@Value("${blog.title}")
	private String title;
	
	@Value("${blog.description}")
	private String description;
	
	private BlogPostService posts;
	
	private static final String HOME_PAGE = "home";
	
	@GetMapping("/")
	public String homePage(Model model, @PageableDefault(value = 5, page = 0) Pageable pageable) {
		model.addAttribute("blogTitle", title);
		model.addAttribute("title", title + " - " + description);
		model.addAttribute("posts", posts.findAll(pageable));
		return HOME_PAGE;
	}
	
	/**
	 * Infinite scrolling feature.
	 * Returning the next page of the posts for the jscroll plugin.
	 * Unnecessary html tags of the template removed by Thymeleaf.
	 */
	
	@GetMapping("megtobb")
	public String infiniteScrolling(Model model, @PageableDefault(value = 5, page = 0) Pageable pageable) {
		model.addAttribute("posts", posts.findAll(pageable));
		return "fragments/posts";
	}
	
	@Autowired
	public void setBlogPostService(BlogPostService service) {
		this.posts = service;
	}

}
