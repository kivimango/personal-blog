package com.kivimango.blog.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Admin {
	
	private Short id;
    private String username;
    private String password;
    private Date lastLogin;
	private String name;
	private List<BlogPost> posts = new ArrayList<BlogPost>(0);
	private String avatar;
	private String fbProfile;
	private String twitterProfile;
	private String linkedinProfile;
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
