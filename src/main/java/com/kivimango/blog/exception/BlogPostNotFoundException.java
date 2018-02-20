package com.kivimango.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BlogPostNotFoundException extends Exception {

	private static final long serialVersionUID = -6109652551757213714L;
	
	private String msg;
	
	public BlogPostNotFoundException(String message) {
		super(message);
		this.msg = message;
	}

	@Override
	public String toString() {
		return msg;
	}

}
