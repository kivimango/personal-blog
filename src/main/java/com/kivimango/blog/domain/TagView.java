package com.kivimango.blog.domain;

/**
 * The DTO of the Tag entity.
 * Will be used in the presentation layer.
 * TagView DTO's are converted by the BlogPostConverter in the service layer.
 * 
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class TagView {
	
	private String tag;
	
	public TagView() {
	}

	public TagView(String t) {
		this.tag = t;
	}
	
	public String getTag() {
		return tag;
	}

	@Override
	public String toString() {
		return tag;
	}
	
}
