package com.kivimango.blog.domain;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class Author {
	
	private String name = "";
	private String avatar = "";
	private String facebook = "";
	private String twitter = "";
	private String linkedin = "";
	private String github = "";
	
	public Author() {}

	public Author(String name, String avatar, String fbProfile, String twitterProfile, String linkedinProfile, String githubProfile) {
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

	public String getTwitter() {
		return twitter;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public String getGithub() {
		return github;
	}

}
