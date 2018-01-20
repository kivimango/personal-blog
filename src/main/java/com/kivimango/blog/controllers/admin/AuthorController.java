package com.kivimango.blog.controllers.admin;

import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.kivimango.blog.services.AdminService;
import com.kivimango.blog.domain.AuthorView;
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
	
	private static final String AUTHOR_EDIT_FORM = "/admin/authorEdit";
	private AdminService admins;
	
	public AuthorController(AdminService admins) {
		this.admins = admins;
	}

	@GetMapping("/dashboard/authors")
	public String showAuthors(Model model) {
		model.addAttribute("authors", admins.findAll());
		return "/admin/authorsList";
	}
	
	@GetMapping("/dashboard/authors/edit/{name}")
	public String showEditAuthorForm(Model model, @PathVariable String name) throws UserNotFoundException {
		AuthorView admin = admins.findByName(name);
		AuthorEditForm form = new AuthorEditForm();
		form.setName(admin.getName());
		form.setAvatar(admin.getAvatar());
		form.setFbProfile(admin.getFbProfile());
		form.setTwitterProfile(admin.getTwitterProfile());
		form.setLinkedinProfile(admin.getLinkedinProfile());
		model.addAttribute("author", form);
		return AUTHOR_EDIT_FORM;
	}
	
	@PostMapping("/dashboard/authors/edit/{authorName}")
	public String showEditAuthor(Model model, @PathVariable String authorName, @Valid AuthorEditForm form, BindingResult result, 
			final RedirectAttributes redAttrs) throws UserNotFoundException {
		if(result.hasErrors()) {
			model.addAttribute("form", form);
			return AUTHOR_EDIT_FORM;
		}
		admins.save(authorName, form);
		redAttrs.addFlashAttribute("message", "Szerző profilja sikeresen frissítve!");
		return "redirect:/dashboard/authors/";
	}

}
