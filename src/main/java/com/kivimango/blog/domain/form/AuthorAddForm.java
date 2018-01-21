package com.kivimango.blog.domain.form;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class AuthorAddForm extends AuthorEditForm {
	
	/**
	 * The username used at login.Do not confuse with the display name on articles.
	 */
	
	@NotNull
	@Length(min=3, max=30)
	private String username;
	
	@NotNull
	@Length(min=5)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
