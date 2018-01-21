package com.kivimango.blog.controllers.admin;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
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
import com.kivimango.blog.services.AdminService;
import com.kivimango.blog.domain.AdminDetail;
import com.kivimango.blog.domain.AuthorView;
import com.kivimango.blog.domain.form.AuthorAddForm;
import com.kivimango.blog.domain.form.AuthorEditForm;
import com.kivimango.blog.exception.UserNotFoundException;

/**
* @author kivimango
* @since 0.1
* @version 0.1
*/

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AuthorController {
	
	private static final String AUTHORS_LIST = "/admin/authorsList";
	private static final String REDIRECT_TO_AUTHORS = "redirect:/dashboard/authors";
	private static final String AUTHOR_ADD_FORM = "/admin/authorAdd";
	private static final String AUTHOR_EDIT_FORM = "/admin/authorEdit";
	
	private AdminService admins;
	
	@Value("${blog.title}")
	private String title;
	
	public AuthorController(AdminService admins) {
		this.admins = admins;
	}

	@GetMapping("/dashboard/authors")
	public String showAuthors(Model model, @AuthenticationPrincipal AdminDetail currentAdmin, 
			@ModelAttribute("message") String flashMessage) {
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("name", currentAdmin.getUsername());
		model.addAttribute("avatar", currentAdmin.getAvatar());
		model.addAttribute("authors", admins.findAll());
		model.addAttribute("title", title);
		return AUTHORS_LIST;
	}
	
	@GetMapping("/dashboard/authors/edit/{name}")
	public String showEditAuthorForm(Model model, @PathVariable String name, 
			@AuthenticationPrincipal AdminDetail currentAdmin) throws UserNotFoundException, AccessDeniedException {
		AuthorView admin = admins.findByName(name);
		model.addAttribute("name", currentAdmin.getUsername());
		model.addAttribute("avatar", currentAdmin.getAvatar());
		if(currentAdmin.getName().equals(admin.getName())) {
			AuthorEditForm form = new AuthorEditForm();
			form.setName(admin.getName());
			form.setAvatar(admin.getAvatar());
			form.setFbProfile(admin.getFbProfile());
			form.setTwitterProfile(admin.getTwitterProfile());
			form.setLinkedinProfile(admin.getLinkedinProfile());
			model.addAttribute("author", form);
			model.addAttribute("title", title);
		} else throw new AccessDeniedException("You are not allowed to edit someone else's profile!");
		return AUTHOR_EDIT_FORM;
	}
	
	@PostMapping("/dashboard/authors/edit/{authorName}")
	public String showEditAuthor(Model model, @PathVariable String authorName, @Valid AuthorEditForm form, BindingResult result, 
			final RedirectAttributes redAttrs) throws UserNotFoundException {
		if(result.hasErrors()) {
			model.addAttribute("form", form);
			return AUTHOR_EDIT_FORM;
		}
		admins.edit(authorName, form);
		redAttrs.addFlashAttribute("message", "Szerző profilja sikeresen frissítve!");
		return REDIRECT_TO_AUTHORS;
	}
	
	@GetMapping("/dashboard/authors/add")
	public String showAddAuthorForm(Model model, @AuthenticationPrincipal AdminDetail currentAdmin) {
		model.addAttribute("title", title);
		model.addAttribute("name", currentAdmin.getUsername());
		model.addAttribute("avatar", currentAdmin.getAvatar());
		model.addAttribute("author", new AuthorAddForm());
		return AUTHOR_ADD_FORM;
	}
	
	@PostMapping("/dashboard/authors/add")
	public String addauthor(Model model, @Valid AuthorAddForm form, BindingResult result, 
			final RedirectAttributes redAttrs, @AuthenticationPrincipal AdminDetail currentAdmin) {
		if(result.hasErrors()) {
			model.addAttribute("name", currentAdmin.getUsername());
			model.addAttribute("avatar", currentAdmin.getAvatar());
			model.addAttribute("author", form);
			return AUTHOR_ADD_FORM;
		}
		admins.save(form);
		redAttrs.addFlashAttribute("message", "Szerző sikeresen hozzáadva!");
		return REDIRECT_TO_AUTHORS;
	}

}
