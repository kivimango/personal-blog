package com.kivimango.blog.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The DTO of the BlogPost entity.
 * Will be used in the presentation layer.
 * BlogPostView DTO's are converted by the BlogPostConverter in the service layer.
 * 
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class BlogPostView {
	
	private String title;
	
	private String slug;
	
	private AuthorView author;
	
	private String content;
	
	private Date uploaded;
	
	private Date edited;
	
	private List<TagView> tags = new ArrayList<TagView>(0);
	
	private boolean hidden;

	public BlogPostView() {	
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

	public AuthorView getAuthor() {
		return author;
	}

	public void setAuthor(AuthorView author) {
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

	public List<TagView> getTags() {
		return tags;
	}

	public void setTags(List<TagView> tags) {
		this.tags = tags;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
}
