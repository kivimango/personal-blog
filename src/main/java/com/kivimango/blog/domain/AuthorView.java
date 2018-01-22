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
	private String facebook;
	private String twitter;
	private String linkedin;
	private String github;

	public AuthorView(String name, String avatar, String fbProfile, String twitterProfile, String linkedinProfile, String githubProfile) {
		this.name = name;
		this.avatar = avatar;
		this.facebook = fbProfile;
		this.twitter = twitterProfile;
		this.linkedin = linkedinProfile;
		this.github = githubProfile;
	}

	public String getName() {
		return name;
	}

	public String getAvatar() {
		return avatar;
	}
	
	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

}
