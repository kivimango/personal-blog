package com.kivimango.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
	
	@Value("${security.user.name}")
	private String root;
	
	@Value("${security.user.password}")
	private String pw;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Don't change the strength parameter in the encoder's constructor, otherwise it will break the login
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder(12));
		// adding a default admin - values comes from the cmdline parameters
		auth.inMemoryAuthentication().withUser(root).password(pw).authorities(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Profile("dev")
	@Override
	protected void configure(HttpSecurity http) throws Exception {
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
