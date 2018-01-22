package com.kivimango.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalBlogApplicationTests {
	
	@Value("${security.user.name}")
	private String username;
	
	@Value("${security.user.password}")
	private String password;

	@Test
	public void contextLoads() {
	}

}
