/**
 * 
 */
package com.mithra.apsf.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
public interface UserRepository extends JpaRepository<User, Integer>,UserRepositoryExtension{
	
	@Query("From Country")
	public List<Country> findAllCountries();
	
	@Query("From Country where id=:id")
	public Country findCountryById(@Param("id")Integer id);
	
	@Query("From State where isActive='Y'")
	public List<State> findAllStates();
	
	@Query("From State where countryId=:countryId and isActive='Y'")
	public List<State> findAllStatesByCountryId(@Param("countryId") Integer countryId);
	
	@Query("From State where id=:id")
	public State findStateById(@Param("id") Integer id);
	
	@Query("From District")
	public List<District> findAllDistricts();
	
	@Query("From District where stateId=:stateId")
	public List<District> findAllDistrictsByStateId(@Param("stateId") Integer stateId);
	
	@Query("From District where id=:id")
	public District findDistrictById(@Param("id") Integer id);
	
	@Query("From Constituency")
	public List<Constituency> findAllConstituencies();
	
	@Query("From Constituency where districtId=:districtId")
	public List<Constituency> findAllConstituenciesByDistrictId(@Param("districtId") Integer districtId);
	
	@Query("From Constituency where stateId=:stateId")
	public List<Constituency> findAllConstituenciesByStateId(@Param("stateId") Integer stateId);
	
	@Query("From Constituency where id=:id")
	public Constituency findConstituencyById(@Param("id") Integer id);
	
	@Query("From Mandal")
	public List<Mandal> findAllMandals();
	
	@Query("From Mandal where districtId=:districtId")
	public List<Mandal> findAllMandalsByDistrictId(@Param("districtId") Integer districtId);
	
	@Query("From Mandal where id=:id")
	public Mandal findMandalById(@Param("id") Integer id);
	
	@Query("From Village")
	public List<Village> findAllVillages();
	
	@Query("From Village where mandalId=:mandalId")
	public List<Village> findAllVillagesByMandalId(@Param("mandalId")Integer mandalId);
	
	@Query("From Village where constituencyId=:constituencyId")
	public List<Village> findAllVillagesByMandalConstituencyId(@Param("constituencyId")Integer constituencyId);
	
	@Query("From Mandal where constituencyId=:constituencyId")
	public List<Mandal> findAllMandalsByConstituencyId(@Param("constituencyId") Integer constituencyId);
	
	
	@Query("From Village where id=:id")
	public Village findAllVillageById(@Param("id") Integer id);
	
	@Query("From Department")
	public List<Department> findAllDepartments();
	
	
	@Query("From Qualification")
	public List<Qualification> findAllQualifications();
	
	@Query("From Profession")
	public List<Profession> findAllProfessions();
	
	@Query("From Caste")
	public List<Caste> findAllCastes();
	
	@Query("From SubCaste")
	public List<SubCaste> findAllSubCastes();
	
	@Query("From SubCaste s where s.caste.id = :casteId")
	public List<SubCaste> findAllSubCasteByCasteId(@Param("casteId") Integer casteId);
	
	@Query("From User")
	public List<User> findAllUsers();
	
	@Query("From User where id=:id")
	public User findUserById(@Param("id") Integer id);
	
	@Query("From User where referedRegid=:referedRegid")
	public List<User> findUsersByReferedRegid(@Param("referedRegid")String referedRegid);
	
	@Query("From User where regid like %:regid%")
	public List<User> findUsersByRegidExpr(@Param("regid")String regid);
	
	@Query("From User where regid=:username or phno=:username")
	public User findByPhnoAndNameOrRegid(@Param("username")String username); 
	
	@Query("From User where phno=:phno")
	public User findByPhno(@Param("phno")String phno);
	
	@Query("From User where role=:role")
	public List<User> findAllUsersByRole(@Param("role")EnumUserRole role);
	
}
