package com.kivimango.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import com.kivimango.blog.services.AntiBruteforceService;

@Component
public class AuthenticationFailureListener 
	implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	@Autowired
	private AntiBruteforceService service;
	
	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		WebAuthenticationDetails auth = (WebAuthenticationDetails) event.getAuthentication().getDetails();
		service.loginFailed(auth.getRemoteAddress());
	}

}
