package com.kivimango.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.kivimango.blog.services.BlogPostService;
import com.kivimango.blog.domain.Author;
import com.kivimango.blog.repositories.TagRepository;

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
	
	@Value("${blog.bio}")
	private String bio;
	
	private BlogPostService posts;
	private TagRepository tags;
	private Author author;
	
	private static final String HOME_PAGE = "home";
	
	@Autowired
	public HomeController(BlogPostService posts, TagRepository tags, Author author) {
		this.posts = posts;
		this.tags = tags;
		this.author = author;
	}

	@GetMapping("/")
	public String homePage(Model model, @RequestParam(name="page", defaultValue="1") int pageNum) {
		model.addAttribute("blogTitle", title);
		model.addAttribute("title", title + " - " + description);
		model.addAttribute("bio", bio);
		model.addAttribute("author", author);
		model.addAttribute("tags", tags.findAll());
		model.addAttribute("posts", posts.findAllExcludeHidden(pageNum));
		return HOME_PAGE;
	}
	
	/**
	 * Infinite scrolling feature.
	 * Returning the next page of the posts for the jscroll plugin.
	 * Unnecessary html tags of the template removed by Thymeleaf.
	 */
	
	@GetMapping("megtobb")
	public String infiniteScrolling(Model model, @RequestParam(name="page", defaultValue="1") int pageNum) {
		model.addAttribute("posts", posts.findAllExcludeHidden(pageNum));
		return "fragments/posts";
	}

}
