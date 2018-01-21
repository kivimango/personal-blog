package com.kivimango.blog.controllers.admin;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.kivimango.blog.services.AntiBruteforceService;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Controller
public class AdminAuthController {
	
	private static final String LOGIN_PAGE = "admin/login";
	
	private AntiBruteforceService loginService;
	
	@Value("${blog.title}")
	private String title;
	
	@Autowired
	public AdminAuthController(AntiBruteforceService loginService) {
		this.loginService = loginService;
	}

	/**
	 * Displaying a login form to the unauthenticated user.
	 * If there is an incoming brute-force attack from a particular IP address,
	 * it will render a 403 error page.
	 * @param request
	 * @param response
	 * @return
	 */
	
	@GetMapping("/dashboard/login")
	public String loginPage(Model model,HttpServletRequest request) {
		model.addAttribute("title", title);
		if(loginService.isBlocked(request.getRemoteAddr())) {
			return "/error/banned";
		}
		return LOGIN_PAGE;
	}

}
