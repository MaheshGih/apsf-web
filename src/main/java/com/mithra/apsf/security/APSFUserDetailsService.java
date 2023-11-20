package com.mithra.apsf.security;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mithra.apsf.user.model.User;

/**
 * 
 * Login user information retriever
 * @author mtoluchuri
 *
 */
public interface APSFUserDetailsService extends UserDetailsService {

	public User getUserFromSession();
	
	/**
	 *Checking Any previous Sessions having Active status for current User,Then update to Expired status        
	 * @throws Exception
	 */
	//public void killPerviousSessions() throws Exception;
}
