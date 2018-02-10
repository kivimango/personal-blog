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
	private Author author;
	private String content;
	private Date uploaded;
	private Date edited;
	private List<TagView> tags = new ArrayList<TagView>(0);
	private boolean hidden;
	
	// TODO : consider using the Builder pattern

	public BlogPostView(String title, String slug, Author author, String content, Date uploaded, Date edited,
			List<TagView> tags, boolean hidden) {
		this.title = title;
		this.slug = slug;
		this.author = author;
		this.content = content;
		this.uploaded = uploaded;
		this.edited = edited;
		this.tags = tags;
		this.hidden = hidden;
	}

	public String getTitle() {
		return title;
	}

	public String getSlug() {
		return slug;
	}

	public Author getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public Date getUploaded() {
		return uploaded;
	}

	public Date getEdited() {
		return edited;
	}

	public List<TagView> getTags() {
		return tags;
	}

	public boolean isHidden() {
		return hidden;
	}
	
}
