/**
 * 
 */
package com.mithra.apsf.user.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.mithra.apsf.common.APSFResponse;
import com.mithra.apsf.common.Constants.EnumUserRole;
import com.mithra.apsf.user.model.Caste;
import com.mithra.apsf.user.model.Constituency;
import com.mithra.apsf.user.model.Country;
import com.mithra.apsf.user.model.Department;
import com.mithra.apsf.user.model.District;
import com.mithra.apsf.user.model.Mandal;
import com.mithra.apsf.user.model.Profession;
import com.mithra.apsf.user.model.Qualification;
import com.mithra.apsf.user.model.State;
import com.mithra.apsf.user.model.SubCaste;
import com.mithra.apsf.user.model.User;
import com.mithra.apsf.user.model.Village;

/**
 * @author koti
 *
 */
public interface UserService {

	public void saveOrUpdateUser(User user);

	public List<Country> findAllCountries();

	public List<State> findAllStates();

	public List<District> findAllDistricts();
	
	public District findDistrictById(Integer id);

	public List<Constituency> findAllConstituencies();

	public List<Mandal> findAllMandals();

	public List<Village> findAllVillages();

	public List<Department> findAllDepartments();

	public List<Qualification> findAllQualifications();

	public List<Profession> findAllProfessions();

	public List<Caste> findAllCastes();

	public List<SubCaste> findAllSubCastes();

	public List<User> findAllUsers();
	
	public User findUserById(Integer id);

	public Country findCountryById(Integer id);

	public List<State> findAllStatesByCountryId(Integer countryId);

	public State findStateById(Integer id);

	public List<Constituency> findAllConstituenciesByDistrictId(Integer districtId);
	
	public List<Constituency> findAllConstituenciesByStateId( Integer stateId);
	
	public List<Village> findAllVillagesByConstituencyId( Integer constituencyId);

	public Constituency findConstituencyById(Integer id);

	public List<Mandal> findAllMandalsByDistrictId(Integer districtId);

	public Mandal findMandalById(Integer id);

	public List<Village> findAllVillagesByMandalId(Integer mandalId);

	public Village findAllVillageById(Integer id);

	public List<SubCaste> findAllSubCasteByCasteId(Integer casteId);
	
	public List<User> findAllUsersByRole(EnumUserRole role);
	
	public List<User> findUsersByRegidExpr(String regid);
	
	public List<User> findUsersByReferedRegid(String referedRegid);
	
	public User findByPhno(String phno);
	
	public APSFResponse sendOTP(User user);
	
	public APSFResponse verifyOTPAndGenerateRegid(User user);
	
	public APSFResponse verifyOTP(String phno, String otp);
	
	public String genereateRegid(User user);
}
