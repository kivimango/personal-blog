package com.kivimango.blog.domain;

/**
 * The DTO of the Author entity.
 * Will be used in the presentation layer.
 * AuthorView DTO's are converted by the BlogPostConverter's AuthorConverter in the service layer.
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

	public AuthorView(String name, String avatar, String fbProfile, String twitterProfile, String linkedinProfile) {
		this.name = name;
		this.avatar = avatar;
		this.fbProfile = fbProfile;
		this.twitterProfile = twitterProfile;
		this.linkedinProfile = linkedinProfile;
	}

	public String getName() {
		return name;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getFbProfile() {
		return fbProfile;
	}

	public String getTwitterProfile() {
		return twitterProfile;
	}

	public String getLinkedinProfile() {
		return linkedinProfile;
	}

}
