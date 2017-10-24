package com.kivimango.blog.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Representing an author entity.
 * Author entities will be converted by the AuthorConverter for the view 
 * before they leave the service layer.
 * 
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Entity
@Table(name="blog_authors")
public class Author {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false,unique=true)
	private String name;
	
	@OneToMany(mappedBy="author")
	private List<BlogPost> posts = new ArrayList<BlogPost>(0);
	
	@Column(length=255)
	private String avatar;
	
	@Column(length=255)
	private String fbProfile;
	
	@Column(length=255)
	private String twitterProfile;
	
	@Column(length=255)
	private String linkedinProfile;

	public Author() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BlogPost> getPosts() {
		return posts;
	}

	public void setPosts(List<BlogPost> posts) {
		this.posts = posts;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFbProfile() {
		return fbProfile;
	}

	public void setFbProfile(String fbProfile) {
		this.fbProfile = fbProfile;
	}

	public String getTwitterProfile() {
		return twitterProfile;
	}

	public void setTwitterProfile(String twitterProfile) {
		this.twitterProfile = twitterProfile;
	}

	public String getLinkedinProfile() {
		return linkedinProfile;
	}

	public void setLinkedinProfile(String linkedinProfile) {
		this.linkedinProfile = linkedinProfile;
	}
	
}
