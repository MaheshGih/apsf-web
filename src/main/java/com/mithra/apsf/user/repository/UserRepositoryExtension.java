/**
 * 
 */
package com.mithra.apsf.user.repository;

import java.util.List;

import com.mithra.apsf.user.model.Constituency;
import com.mithra.apsf.user.model.User;

/**
 * @author koti
 *
 */
public interface UserRepositoryExtension {

	/*public User findByPhnoAndNameOrRegid(String userid);*/
	
	public void saveOrUpdateUser(User user);
	
	//public List<Constituency> findAllConstituenciesByStateId( Integer stateId);
}
