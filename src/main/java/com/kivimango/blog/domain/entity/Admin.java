package com.kivimango.blog.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="blog_admins")
public class Admin {
	
	@Id
	@GeneratedValue
	private Short id;
	
	@Column(nullable = false, unique = true)
    private String username;
 
	@Column(nullable = false, unique = true)
    private String password;
    
    private Date lastLogin;
    
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
	
	@Column(length=255)
	private String githubProfile;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

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

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
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
	
	public String getGithubProfile() {
		return githubProfile;
	}

	public void setGithubProfile(String githubProfile) {
		this.githubProfile = githubProfile;
	}

}
