/**
 * 
 */
package com.mithra.apsf.user.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mithra.apsf.user.model.Constituency;
import com.mithra.apsf.user.model.District;
import com.mithra.apsf.user.model.User;
import com.mithra.apsf.user.repository.UserRepositoryExtension;

/**
 * @author koti
 *
 */
@Service
public class UserRepositoryImpl implements UserRepositoryExtension {

	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public void saveOrUpdateUser(User user) {
		
		if(user.getId()==null) {
			entityManager.persist(user);
		}else {
			entityManager.merge(user);
		}
		
	}
	
	/*public List<Constituency> findAllConstituenciesByStateId( Integer stateId){
		
		Query query=entityManager.createQuery("Select c,d from Constituency c , District d, State s "
				+ "where c.districtId=d.id and c.stateId=s.id and d.stateId=s.id and s.id =:stateId");
		query.setParameter("stateId", stateId);
		List<Object[]> Constituencys = query.getResultList();
		List<Constituency> consts = new ArrayList<Constituency>();
		for(Object obj[]:Constituencys) {
			Constituency cons = (Constituency) obj[0];
			cons.setDistrict((District) obj[1]);
			consts.add(cons);
		}
		return consts;
	}*/
}
