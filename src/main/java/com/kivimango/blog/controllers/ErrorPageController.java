package com.kivimango.blog.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.kivimango.blog.exception.BlogPostNotFoundException;

/**
 * Handling the exceptions thrown by the controllers
 * 
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@ControllerAdvice
public class ErrorPageController {
	
	@Value("${blog.title}")
	private String title;
	
	@Value("${blog.description}")
	private String description;
	
	@ExceptionHandler(BlogPostNotFoundException.class)
	public String postNotFound(Model model) {
		model.addAttribute("blogTitle", title);
		model.addAttribute("title", title + " - " + description);
		return "/error/404";
	}

}
