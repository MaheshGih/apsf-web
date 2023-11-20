/**
 * 
 */
package com.mithra.apsf.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mithra.apsf.common.Constants.EnumUserRole;
import com.mithra.apsf.common.APSFResponse;
import com.mithra.apsf.common.StatusCode;
import com.mithra.apsf.security.APSFUserDetailsService;
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
import com.mithra.apsf.user.service.UserService;
import com.mithra.apsf.util.Util;

/**
 * @author koti
 *
 */
@Controller
@RequestMapping("/user/")
public class UserController {

	/**
	 * Reference to the VicinityUserDetailsService
	 */
	@Autowired
	private APSFUserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/rest/signup",method=RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<User> save(@RequestBody User user) {
		userService.saveOrUpdateUser(user);
		ResponseEntity<User> res= new ResponseEntity<User>(user,HttpStatus.OK);
		return res;
	}
	
	@RequestMapping(value="/rest/countries",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Country>> findAllCountries(){
		return new ResponseEntity<List<Country>>(userService.findAllCountries(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/states",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<State>> findAllStates(){
		
		return new ResponseEntity<List<State>>(userService.findAllStates(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/districts",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<District>> findAllDistricts(){
		return new ResponseEntity<List<District>>(userService.findAllDistricts(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/constituencies",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Constituency>> findAllConstituencies(){
		return new ResponseEntity<List<Constituency>>(userService.findAllConstituencies(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/mandals",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Mandal>> findAllMandals(){
		return new ResponseEntity<List<Mandal>>(userService.findAllMandals(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/villages",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Village>> findAllVillages(){
		return new ResponseEntity<List<Village>>(userService.findAllVillages(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/departments",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Department>> findAllDepartments(){
		return new ResponseEntity<List<Department>>(userService.findAllDepartments(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/qualifications",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Qualification>> findAllQualifications(){
		return new ResponseEntity<List<Qualification>>(userService.findAllQualifications(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/professions",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Profession>> findAllProfessions(){
		return new ResponseEntity<List<Profession>>(userService.findAllProfessions(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/castes",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Caste>> findAllCastes(){
		return new ResponseEntity<List<Caste>>(userService.findAllCastes(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/subcastes",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<SubCaste>> findAllSubCastes(){
		return new ResponseEntity<List<SubCaste>>(userService.findAllSubCastes(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/country/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Country> findCountryById(@PathVariable("id")Integer id){
		return new ResponseEntity<Country>(userService.findCountryById(id),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/states/{countryId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<State>> findAllStatesByCountryId(@PathVariable("countryId")Integer countryId){
		return new ResponseEntity<List<State>>(userService.findAllStatesByCountryId(countryId),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/state/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<State> findStateById(@PathVariable("id")Integer id){
		return new ResponseEntity<State>(userService.findStateById(id),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/constsbydistrict/{districtId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Constituency>> findAllConstituenciesByDistrictId(@PathVariable("districtId")Integer districtId){
		return new ResponseEntity<List<Constituency>>(userService.findAllConstituenciesByDistrictId(districtId),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/villagesbyconstid/{constituencyId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Village>> findAllVillagesByConstituencyId(@PathVariable("constituencyId")Integer constituencyId){
		return new ResponseEntity<List<Village>>(userService.findAllVillagesByConstituencyId(constituencyId),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/rest/constsbystate/{stateId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Constituency>> findAllConstituenciesByStateId(@PathVariable("stateId")Integer stateId){
		return new ResponseEntity<List<Constituency>>(userService.findAllConstituenciesByStateId(stateId),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/rest/constituency/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Constituency> findConstituencyById(@PathVariable("id")Integer id){
		return new ResponseEntity<Constituency>(userService.findConstituencyById(id),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/mandals/{districtId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Mandal>> findAllMandalsByDistrictId(@PathVariable("districtId")Integer districtId){
		return new ResponseEntity<List<Mandal>>(userService.findAllMandalsByDistrictId(districtId),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/mandal/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Mandal> findMandalById(@PathVariable("id")Integer id){
		return new ResponseEntity<Mandal>(userService.findMandalById(id),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/villages/{mandalId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Village>> findAllVillagesByMandalId(@PathVariable("mandalId")Integer mandalId){
		return new ResponseEntity<List<Village>>(userService.findAllVillagesByMandalId(mandalId),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/village/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Village> findAllVillageById(@PathVariable("id")Integer id){
		return new ResponseEntity<Village>(userService.findAllVillageById(id),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/subCastes/{casteId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<SubCaste>> findAllSubCasteByCasteId(@PathVariable("casteId")Integer casteId){
		return new ResponseEntity<List<SubCaste>>(userService.findAllSubCasteByCasteId(casteId),HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/login/success")
	@ResponseBody
	public APSFResponse authSuccess(HttpServletRequest request, Locale locale)  {
			
		User currentUser = userDetailsService.getUserFromSession();
		currentUser.setReferedUser(null);
		APSFResponse response =new APSFResponse();
		response.add("userDetails", currentUser);
		
		return response;
	}
	
	@RequestMapping(value="/rest/users",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<User>> findAllUsers(){
		List<User> users= userService.findAllUsers();
		for(User user:users) {
			User refUser = user.getReferedUser();
			if(refUser!=null)
				refUser.setReferedUser(null);
		}
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/users/{referedRegid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<User>> findAllUsersByReferenceId(@PathVariable("referedRegid") String referedRegid){
		List<User> users= userService.findUsersByReferedRegid(referedRegid);
		for(User user:users) {
			User refUser = user.getReferedUser();
			if(refUser!=null)
				refUser.setReferedUser(null);
		}
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/user/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<User> findUserById(@PathVariable("id")Integer id){
		User user=userService.findUserById(id);
		User refUser = user.getReferedUser();
		if(refUser!=null)
			refUser.setReferedUser(null);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/rest/searchregid/{regid}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<User>> searchUserByRegidExpr(@PathVariable("regid")String regid){
		List<User> users=userService.findUsersByRegidExpr(regid);
		for(User user : users) {
			user.setReferedUser(null);
		}
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	
	@RequestMapping( value="employees_temp" )
	public String employeesTemp() {
		
		return "/user/employees";
	}
	
	@RequestMapping( value="/rest/employees" )
	@ResponseBody
	public ResponseEntity<HashMap<String,Object>> employees() {
		List<User> users = userService.findAllUsersByRole(EnumUserRole.ROLE_EMP);
		for(User user : users) {
			user.setReferedUser(null);
		}
		HashMap<String,Object> map= new HashMap<String,Object>();
		map.put("roles", Util.userRoleList());
		map.put("employees", users);
		return new ResponseEntity<HashMap<String,Object>>( map, HttpStatus.OK);
	}
	
	@RequestMapping( value="/rest/sendotp", method=RequestMethod.POST )
	@ResponseBody
	public APSFResponse sendOTP(@RequestBody User user) {
		return userService.sendOTP(user);
	}
	
	@RequestMapping( value="/rest/verify_reg_otp", method=RequestMethod.POST )
	@ResponseBody
	public APSFResponse verifyOTP(@RequestBody User user) {
		
		return userService.verifyOTPAndGenerateRegid(user);
	}
	
	@RequestMapping( value="/profile" )
	public String profile(){	
		return "/user/profile";
	}
	
	@RequestMapping( value="/rest/verifyotp", method=RequestMethod.POST )
	@ResponseBody
	public APSFResponse verifyForgotPasswordOTP(@RequestBody User user) {
		APSFResponse res = userService.verifyOTP(user.getPhno(), user.getOtp());
		return res;
	}
	
	@RequestMapping( value="/rest/updatepassword", method=RequestMethod.POST )
	@ResponseBody
	public APSFResponse updatePassword(@RequestBody User user) {
		APSFResponse response = new APSFResponse();
		try {
			String pass = null;
			String phno = null;
			if(user!=null && !StringUtils.isEmpty(phno = user.getPhno()) 
					&& !StringUtils.isEmpty( pass = user.getPassword())) {
				User persistedUser = userService.findByPhno(phno);
				persistedUser.setPassword(pass);
				userService.saveOrUpdateUser(persistedUser);
				response.setMessage("Save password successfully");
				response.setStatusCode(StatusCode.OK);
			}else {
				response.setMessage("Save password failed");
				response.setStatusCode(StatusCode.ERROR);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			response.setMessage("Save password failed..!");
			response.setStatusCode(StatusCode.ERROR);
		}
		return response;
	}
}
