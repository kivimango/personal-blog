package com.kivimango.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.kivimango.blog.services.AdminService;
import com.kivimango.blog.services.BlogPostService;
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
	private AdminService admin;
	
	private static final String HOME_PAGE = "home";
	
	@Autowired
	public HomeController(BlogPostService posts, TagRepository tags, AdminService admin) {
		this.posts = posts;
		this.tags = tags;
		this.admin = admin;
	}

	@GetMapping("/")
	public String homePage(Model model, @PageableDefault(value=5, page=0, sort="uploaded", direction=Direction.DESC) Pageable pageable) {
		model.addAttribute("blogTitle", title);
		model.addAttribute("title", title + " - " + description);
		model.addAttribute("bio", bio);
		model.addAttribute("author", admin.findFirst());
		model.addAttribute("tags", tags.findAll());
		model.addAttribute("posts", posts.findAllExcludeHidden(pageable));
		return HOME_PAGE;
	}
	
	/**
	 * Infinite scrolling feature.
	 * Returning the next page of the posts for the jscroll plugin.
	 * Unnecessary html tags of the template removed by Thymeleaf.
	 */
	
	@GetMapping("megtobb")
	public String infiniteScrolling(Model model, @PageableDefault(value=5, page=0, sort="uploaded", direction=Direction.DESC) Pageable pageable) {
		model.addAttribute("posts", posts.findAllExcludeHidden(pageable));
		return "fragments/posts";
	}

}
