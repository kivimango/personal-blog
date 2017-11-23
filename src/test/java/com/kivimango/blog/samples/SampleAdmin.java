package com.kivimango.blog.samples;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.kivimango.blog.domain.entity.Admin;
import com.kivimango.blog.domain.entity.BlogPost;

public class SampleAdmin {
	
	protected Admin admin = createSampleAdmin();
	
	private Admin createSampleAdmin() {
		Admin admin = new Admin();
		Short id = 1;
		String username = "Sample Admin";
		String password = "aVerySeCrEtPaSsWoRd";
		Date lastLogin = new Date();
		String name ="Sample author";
		List<BlogPost> posts = new ArrayList<>();
		String avatar = "http://avatarblog.typepad.com/.a/6a0120a6b2c140970c012876d22e0c970c-pi";
		String fbProfile = "http://facebook.com/author";
		String twitterProfile = "http://twitter.com/sample-author";
		String linkedinProfile = "http://www.linkedin.com/en/sample-author";
		
		admin.setId(id);
		admin.setUsername(username);
		admin.setPassword(password);
		admin.setLastLogin(lastLogin);
		admin.setName(name);
		admin.setPosts(posts);
		admin.setAvatar(avatar);
		admin.setFbProfile(fbProfile);
		admin.setTwitterProfile(twitterProfile);
		admin.setLinkedinProfile(linkedinProfile);
		
		return admin;
	}

}
