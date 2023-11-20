package com.mithra.apsf.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mithra.apsf.common.Constants.EnumUserRole;
import com.mithra.apsf.user.model.User;


/**
 * @author mtoluchuri
 *
 */
public class APSFUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public APSFUserDetails(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(EnumUserRole.ROLE_ANONYMOUS.name()));
		if (user != null) {
			if (user.getRole() != null
					&& user.getRole().equals(EnumUserRole.ROLE_ADMIN)) {
				authList.add(new SimpleGrantedAuthority(EnumUserRole.ROLE_ADMIN.name()));
				authList.remove(EnumUserRole.ROLE_ANONYMOUS.name());
			} else if (user.getRole() != null
					&& user.getRole().equals(EnumUserRole.ROLE_USER)) {
				authList.add(new SimpleGrantedAuthority(EnumUserRole.ROLE_USER.name()));
				authList.remove(EnumUserRole.ROLE_ANONYMOUS.name());
			}
		}
		return authList;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getRegid();
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

	/**
	 * @return the vicinityUser
	 */
	public User getVicinityUser() {
		return user;
	}

	/**
	 * @param user the vicinityUser to set
	 */
	public void setVicinityUser(User user) {
		this.user = user;
	}	
}
