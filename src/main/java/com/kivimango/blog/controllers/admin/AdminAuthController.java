package com.kivimango.blog.controllers.admin;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.kivimango.blog.services.AdminService;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Controller
public class AdminAuthController {
	
	private static final String LOGIN_PAGE = "admin/login";
	
	@Autowired
	private AdminService loginService;
	
	/**
	 * Displaying a login form to the unauthenticated user.
	 * If there is an incoming brute-force attack from a particular IP address,
	 * it will render a 403 error page.
	 * @param request
	 * @param response
	 * @return
	 */
	
	@GetMapping("/dashboard/login")
	public String loginPage(HttpServletRequest request) {
		if(loginService.isBlocked(request.getRemoteAddr())) {
			return "/error/banned";
		}
		return LOGIN_PAGE;
	}

}
