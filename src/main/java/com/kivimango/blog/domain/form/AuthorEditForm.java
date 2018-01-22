package com.kivimango.blog.domain.form;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class AuthorEditForm {
	
	@NotNull
	@Length(min=3, max=30)
	private String name;
	
	@NotNull
	@Length(min=3, max=255)
	private String avatar;
	
	@Length(max=255)
	private String fbProfile;
	
	@Length(max=255)
	private String twitterProfile;
	
	@Length(max=255)
	private String linkedinProfile;
	
	@Length(max=255)
	private String githubProfile;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
