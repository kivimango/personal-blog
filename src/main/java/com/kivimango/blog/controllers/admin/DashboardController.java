package com.kivimango.blog.controllers.admin;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.kivimango.blog.domain.AdminDetail;
import com.kivimango.blog.domain.form.BlogPostForm;
import com.kivimango.blog.exception.BlogPostNotFoundException;
import com.kivimango.blog.services.BlogPostService;

/**
 * BlogPost CRUD operations.
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
	private static final String POST_COMPOSE_FORM = "admin/compose";
	private static final String POST_EDIT_FORM = "admin/edit";
	private static final String REDIRECT_TO_DASHBOARD_POSTS = "redirect:/dashboard/posts";
	
	@Autowired
	public DashboardController(BlogPostService blogposts) {
		this.blogposts = blogposts;
	}

	@GetMapping("/dashboard")
	private String mainPage(@AuthenticationPrincipal AdminDetail currentAdmin) {
		return MAIN_PAGE;
	}
	
	@GetMapping("/dashboard/posts")
	public String listPosts(@ModelAttribute("message") String flashMessage, Model model, 
			@PageableDefault(value=25, page=0, sort="uploaded", direction=Direction.DESC) Pageable pageable, RedirectAttributes attr, @AuthenticationPrincipal AdminDetail currentAdmin) {
		if(flashMessage != null) {
			model.addAttribute("flashMessage", flashMessage);
		}
		model.addAttribute("posts", blogposts.findAll(pageable));
		model.addAttribute("admin", currentAdmin);
		return POSTS_LIST;
	}
	
	@GetMapping("/dashboard/posts/compose")
	public String newPostForm(Model model, @AuthenticationPrincipal AdminDetail currentAdmin) {
		model.addAttribute("admin", currentAdmin);
		model.addAttribute("post", new BlogPostForm());
		return POST_COMPOSE_FORM;
	}
	
	@PostMapping("/dashboard/posts/compose")
	public String addPost(@Valid BlogPostForm form, BindingResult result, final RedirectAttributes redAttrs, Model model, 
			@AuthenticationPrincipal AdminDetail currentAdmin) {
		System.out.println(currentAdmin.getUsername());
		if(result.hasErrors()) {
			model.addAttribute("post", form);
			return POST_COMPOSE_FORM;
		}
		blogposts.save(form, currentAdmin);
		redAttrs.addFlashAttribute("message", "Blogbejegyzés sikeresen feltöltve !");
		return REDIRECT_TO_DASHBOARD_POSTS;
	}
	
	@GetMapping("/dashboard/posts/edit/{slug}")
	public String editPostForm(@PathVariable String slug, Model model, @AuthenticationPrincipal AdminDetail currentAdmin) throws BlogPostNotFoundException {
		model.addAttribute("admin", currentAdmin);
		model.addAttribute("post", blogposts.getPostBySlug(slug));
		return POST_EDIT_FORM;
	}
	
	@PostMapping("/dashboard/posts/edit/{slug}")
	public String editPost(@PathVariable String slug, Model model, @Valid BlogPostForm form, 
			BindingResult result, RedirectAttributes redAttrs, @AuthenticationPrincipal AdminDetail currentAdmin) throws BlogPostNotFoundException {
		if(result.hasErrors()) {
			model.addAttribute("post", form);
			return POST_EDIT_FORM;
		}
		blogposts.edit(slug, form);
		redAttrs.addFlashAttribute("message", "Blogbejegyzés sikeresen szerkesztve !");
		return REDIRECT_TO_DASHBOARD_POSTS;
	}
	
	@PostMapping("/dashboard/posts/hide/{slug}")
	public String hidePost(@PathVariable String slug, RedirectAttributes redAttrs) throws BlogPostNotFoundException {
		blogposts.hideOrPublish(slug);
		redAttrs.addFlashAttribute("message", "Blogbejegyzés láthatósága megváltoztatva!");
		return REDIRECT_TO_DASHBOARD_POSTS;
	}

}
