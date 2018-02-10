package com.kivimango.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService service;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Don't change the strength parameter in the encoder's constructor, otherwise it will break the login
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder(12));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable();
		//http.headers().frameOptions().sameOrigin();
		http
			.authorizeRequests()
				.antMatchers("/dashboard/**").hasRole("ADMIN")
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/**").permitAll()
				.and()
			.formLogin()
				.loginPage("/dashboard/login")
				.loginProcessingUrl("/security_check")
				.defaultSuccessUrl("/dashboard")
				.permitAll()
			.and()
				.logout()
				.logoutUrl("/dashboard/logout")
				.logoutSuccessUrl("/dashboard/login?logout")
				.permitAll();
	}

}
