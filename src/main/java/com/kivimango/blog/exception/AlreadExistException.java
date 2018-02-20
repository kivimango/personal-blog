package com.kivimango.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadExistException extends Exception {
	
	private static final long serialVersionUID = 1509113984887385539L;
	
	private String msg;
	
	public AlreadExistException(String message) {
		super(message);
		this.msg = message;
	}

	@Override
	public String toString() {
		return msg;
	}

}
