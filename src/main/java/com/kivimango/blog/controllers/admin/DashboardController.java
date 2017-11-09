package com.kivimango.blog.controllers.admin;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.kivimango.blog.domain.AdminDetail;
import com.kivimango.blog.domain.form.BlogPostForm;
import com.kivimango.blog.exception.AuthorNotFoundException;
import com.kivimango.blog.exception.BlogPostNotFoundException;
import com.kivimango.blog.services.AuthorService;
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
	private AuthorService authors;
	
	private static final String POSTS_LIST = "admin/postsList";
	private static final String MAIN_PAGE = "admin/dashboard";
	private static final String POST_COMPOSE_FORM = "admin/compose";
	private static final String POST_EDIT_FORM = "admin/edit";
	
	@Autowired
	public DashboardController(BlogPostService blogposts, AuthorService authors) {
		this.blogposts = blogposts;
		this.authors = authors;
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
	
	@GetMapping("/dashboard/posts/compose")
	public String newPostForm(Model model, @AuthenticationPrincipal AdminDetail currentAdmin) {
		model.addAttribute("admin", currentAdmin);
		model.addAttribute("authors", authors.findAll());
		model.addAttribute("post", new BlogPostForm());
		return POST_COMPOSE_FORM;
	}
	
	@PostMapping("/dashboard/posts/compose")
	public String addPost(@Valid BlogPostForm form, BindingResult result, final RedirectAttributes redAttrs, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("authors", authors.findAll());
			model.addAttribute("post", form);
			return POST_COMPOSE_FORM;
		}
		try { 
			blogposts.save(form);
		} catch (AuthorNotFoundException e) {
			model.addAttribute("authors", authors.findAll());
			result.rejectValue("author", e.getMessage());
			model.addAttribute("post", form);
			return POST_COMPOSE_FORM;
		}
		redAttrs.addFlashAttribute("message", "Blogbejegyzés sikeresen feltöltve !");
		return "redirect:/dashboard/posts";
	}
	
	@GetMapping("/dashboard/posts/edit/{slug}")
	public String editPostForm(@PathVariable String slug, Model model, @AuthenticationPrincipal AdminDetail currentAdmin) throws BlogPostNotFoundException {
		model.addAttribute("admin", currentAdmin);
		model.addAttribute("authors", authors.findAll());
		model.addAttribute("post", blogposts.getPostBySlug(slug));
		model.addAttribute("convertHtmlToBB", true);
		return POST_EDIT_FORM;
	}

}
