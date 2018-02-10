package com.kivimango.blog.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kivimango.blog.domain.Author;

/**
 * Representing a blog post entity.
 * BlogPost entities will be converted by the BlogPostConverter for the view 
 * before they leave the service layer.
 * 
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class BlogPost {
	
	private Integer id;
	private String title;
	private String slug;
	private Author author;
	private String content;
	private Date uploaded;
	private Date edited;
	private List<Tag> tags = new ArrayList<Tag>(0);
	private boolean hidden;

	public BlogPost() {
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUploaded() {
		return uploaded;
	}

	public void setUploaded(Date uploaded) {
		this.uploaded = uploaded;
	}

	public Date getEdited() {
		return edited;
	}

	public void setEdited(Date edited) {
		this.edited = edited;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public boolean isHidden() {
		return hidden;
	}
	
	public void setHidden(boolean status) {
		this.hidden = status;
	}

}
