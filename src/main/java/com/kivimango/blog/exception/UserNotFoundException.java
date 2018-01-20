package com.kivimango.blog.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1509113984887385539L;
	
	private String msg;
	
	public UserNotFoundException(String message) {
		super(message);
		this.msg = message;
	}

	@Override
	public String toString() {
		return msg;
	}

}
