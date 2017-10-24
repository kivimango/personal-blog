package com.kivimango.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Personal web Blog Application
 * Features :
 * - upload post
 * - edit uploaded post
 * - preview post before upload 
 * - format post content with bbCode
 * - tag post
 * - administration dashboard
 * - little SEO (friendly urls and og tags)
 * 
 * - read post
 * - search post
 * - comment with disquos or facebook
 * 
 * @author kivimango
 * @since 0.1
 * @version 0.1 
 */

@SpringBootApplication
public class PersonalBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalBlogApplication.class, args);
	}
}
