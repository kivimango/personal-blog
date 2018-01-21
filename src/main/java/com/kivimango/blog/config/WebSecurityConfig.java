package com.kivimango.blog.config;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.kivimango.blog.domain.AdminDetail;
import com.kivimango.blog.domain.entity.Admin;

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
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder(12))
		.and().userDetailsService(new UserDetailsService() {	
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Set<GrantedAuthority> auths = new HashSet<>();
				auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				Admin adm = new Admin();
				adm.setAvatar("/image/user.jpg");
				adm.setName(root);
				adm.setUsername(root);
				adm.setPassword(pw);
				AdminDetail rot = new AdminDetail(adm);
				return rot;
			}
		});
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
