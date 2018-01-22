package com.kivimango.blog.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Representing a blog post entity.
 * BlogPost entities will be converted by the BlogPostConverter for the view 
 * before they leave the service layer.
 * 
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Entity
@Table(name="blog_posts")
public class BlogPost {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(length=150)
	private String title;
	
	@Column(length=255)
	private String slug;
	
	@ManyToOne
	private Admin author;
	
	@Column(length=100000)
	private String content;
	
	private Date uploaded;
	
	private Date edited;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Tag> tags = new ArrayList<Tag>(0);

	private boolean hidden;

	public BlogPost() {
	}
	
	public Integer getId() {
		return id;
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

	public Admin getAuthor() {
		return author;
	}

	public void setAuthor(Admin author) {
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
