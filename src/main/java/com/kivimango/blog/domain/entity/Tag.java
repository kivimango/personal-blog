package com.kivimango.blog.domain.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class Tag {
	
	private Integer id;
	private List<BlogPost> post = new ArrayList<BlogPost>();
	private String tag;

	public Tag() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<BlogPost> getPost() {
		return post;
	}

	public void setPost(List<BlogPost> post) {
		this.post = post;
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
