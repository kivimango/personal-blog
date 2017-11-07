package com.kivimango.blog.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.kivimango.blog.domain.AdminDetail;
import com.kivimango.blog.services.BlogPostService;

/**
 * 
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DashboardController {
	
	private BlogPostService blogposts;
	
	private static final String POSTS_LIST = "admin/postsList";
	private static final String MAIN_PAGE = "admin/dashboard";
	
	@Autowired
	public DashboardController(BlogPostService blogposts) {
		this.blogposts = blogposts;
	}

	@GetMapping("/dashboard")
	private String mainPage(@AuthenticationPrincipal AdminDetail currentAdmin) {
		return MAIN_PAGE;
	}
	
	@GetMapping("/dashboard/posts")
	public String listPosts(Model model, Pageable pageable, @AuthenticationPrincipal AdminDetail currentAdmin) {
		model.addAttribute("posts", blogposts.findAll(pageable));
		model.addAttribute("admin", currentAdmin);
		return POSTS_LIST;
	}

}
