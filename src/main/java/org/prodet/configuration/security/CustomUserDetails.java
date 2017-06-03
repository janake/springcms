package org.prodet.configuration.security;

import java.util.Collection;

import org.prodet.repository.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends User implements UserDetails {

	private static final long serialVersionUID = 2990666639680934472L;
	
	private User user;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return  AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
	}

	@Override
	public String getPassword() {
		return user.getPasswd();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isStatus();
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.isStatus();
	}

	@Override
	public boolean isEnabled() {
		return user.isStatus();
	}
	
}