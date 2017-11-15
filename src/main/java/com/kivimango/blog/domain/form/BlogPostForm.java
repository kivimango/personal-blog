package com.kivimango.blog.domain.form;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class BlogPostForm {

	@NotNull
	@Length(min=3, max=150)
	private String title;
	
	@NotNull
	@Length(min=3, max=10000)
	private String content;
	
	@Length(max=500)
	private String tags;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}
