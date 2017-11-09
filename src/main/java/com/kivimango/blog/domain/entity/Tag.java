package com.kivimango.blog.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Entity
@Table(name="blog_tags")
public class Tag {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<BlogPost> post;

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
