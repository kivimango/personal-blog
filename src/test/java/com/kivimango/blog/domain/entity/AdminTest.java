package com.kivimango.blog.domain.entity;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class AdminTest {
	
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
	Admin admin;
	
	@Before
	public void when() {
		admin = new Admin();
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
	}
	
	@Test
	public void testAdminEntityShouldEqual() {
		assertEquals(id, admin.getId());
		assertEquals(username, admin.getUsername());
		assertEquals(password, admin.getPassword());
		assertEquals(lastLogin, admin.getLastLogin());
		assertEquals(name, admin.getName());
		assertEquals(posts, admin.getPosts());
		assertEquals(posts.size(), admin.getPosts().size());
		assertEquals(avatar, admin.getAvatar());
		assertEquals(fbProfile, admin.getFbProfile());
		assertEquals(linkedinProfile, admin.getLinkedinProfile());
		assertEquals(twitterProfile, admin.getTwitterProfile());
	}

}
