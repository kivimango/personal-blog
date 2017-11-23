package com.kivimango.blog.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import com.kivimango.blog.domain.entity.Admin;
import com.kivimango.blog.domain.entity.BlogPost;
import com.kivimango.blog.domain.entity.Tag;

public class BlogPostFactory {

	protected String title = "Sample Title";
	protected String slug = "sample-title";
	protected String username = "root";
	protected String authorName = "Sample Author";
	protected String avatar = "http://google.com/avatar.jpg";
	protected String fbProfile = "http://facebook.com/sample.author";
	protected String twitterProfile = "http://twitter.com/sample-author";
	protected String linkedinProfile = "http://www.linkedin.com/en/sample-author";
	protected Admin author;
	protected String content = "sample content";
	protected Date uploaded = new Date();
	protected Date edited;
	protected String testTag = "test-teg";
	protected Tag tag = new Tag();
	protected List<Tag> tags = new ArrayList<Tag>();
	protected boolean hidden = false;
	
	public BlogPost getSamplePostEntity() {
		BlogPost post = new BlogPost(); 
		post.setTitle(title);
		post.setSlug(slug);
		post.setAuthor(getSampleAdminEntity());
		post.setContent(content);
		post.setUploaded(uploaded);
		post.setEdited(edited);
		post.setTags(getSampleTagEntities());
		post.setHidden(hidden);
		return post;
	}
	
	public Admin getSampleAdminEntity() {
		Admin author = new Admin();
		author.setUsername(username);
		author.setName(authorName);
		author.setAvatar(avatar);
		author.setFbProfile(fbProfile);
		author.setTwitterProfile(twitterProfile);
		author.setLinkedinProfile(linkedinProfile);
		return author;
	}
	
	private List<Tag> getSampleTagEntities() {
		tag.setTag(testTag);
		tags.add(tag);
		return tags;
	}
	
	public Page<BlogPost> getPublicPostsListwithPagination() {
		BlogPost postOne = getSamplePostEntity();
		BlogPost postTwo = getSamplePostEntity();
		BlogPost postThree = getSamplePostEntity();
		return new PageImpl<BlogPost>(Arrays.asList(postOne, postTwo, postThree));
	}

}
