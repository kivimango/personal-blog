package com.kivimango.blog.controllers;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Displaying a nice error page with the html error code and message.
 * Overrides the default white label page.
 * (In the application.properties the default white label should be turned off)
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Controller
public class ErrorPageController implements ErrorController {

	private static final String ERR_PATH = "/error";
	
	private ErrorAttributes errorAttributes;
	
	@GetMapping(ERR_PATH)
	public String errorPage(Model model, HttpServletRequest request) {
		RequestAttributes attrs = new ServletRequestAttributes(request);
		Map<String, Object> errors = errorAttributes.getErrorAttributes(attrs, true);
		model.addAttribute("error", errors.get("error"));
		//model.addAttribute("message", errors.get("message"));
		//model.addAttribute("path", errors.get("path"));
		model.addAttribute("status", errors.get("status"));
		return "error";
	}
	
	@Override
	public String getErrorPath() {
		return ERR_PATH;
	}
	
	@Autowired
	public void SetErrorAttributes(ErrorAttributes errAtrrs) {
		this.errorAttributes = errAtrrs;
	}

}
