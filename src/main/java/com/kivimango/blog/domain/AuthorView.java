package com.kivimango.blog.domain;

/**
 * The DTO of the Author entity.
 * Will be used in the presentation layer.
 * AuthorView DTO's are converted by the BlogPostConverter in the service layer.
 * 
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class AuthorView {
	
	private String name;
	
	private String avatar;
	
	private String fbProfile;
	
	private String twitterProfile;
	
	private String linkedinProfile;

	public AuthorView() {
	}

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

}
