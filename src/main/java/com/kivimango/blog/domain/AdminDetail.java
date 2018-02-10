package com.kivimango.blog.domain;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class AdminDetail implements UserDetails {
	
	private static final long serialVersionUID = 4936144782124807718L;
	private Author admin;
	private String password;
	private String accountName;
	
	public AdminDetail(Author author, String loginName, String pw) {
		this.admin = author;
		this.accountName = loginName;
		this.password = pw;
	}

	public String getName() {
		return admin.getName();
	}
	
	public String getAvatar() {
		return admin.getAvatar();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_ADMIN");
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return accountName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
