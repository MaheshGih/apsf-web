/**
 * 
 */
package com.mithra.apsf.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.hibernate.metamodel.source.MappingDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.mithra.apsf.common.Constants.EnumResponseStatus;
import com.mithra.apsf.common.Constants.EnumUserRole;
import com.mithra.apsf.common.Constants.EnumUserStatus;
import com.mithra.apsf.common.Constants.EnumYOrN;
import com.mithra.apsf.common.APSFResponse;
import com.mithra.apsf.common.StatusCode;
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
import com.mithra.apsf.user.repository.UserRepository;
import com.mithra.apsf.user.service.UserService;
import com.mithra.apsf.util.SMSClient;
import com.mithra.apsf.util.Util;

/**
 * @author koti
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepositry;
	
	@Autowired
	MessageSource messageSource;
	
	@Override
	@Transactional
	public void saveOrUpdateUser(User user) {
		
		user.setDefaultPros();
		if(user.getId()==null) {
			user.setStatus(EnumUserStatus.PENDING);
			user.setRole(EnumUserRole.ROLE_USER);
			user.setIsActive(EnumYOrN.N);
			
		}
		//user.setRole(EnumUserRole.ROLE_USER);
		userRepositry.saveOrUpdateUser(user);
	}
	
	
	public List<Country> findAllCountries(){
		return userRepositry.findAllCountries();
	}
	
	
	public List<State> findAllStates(){
		return userRepositry.findAllStates();
	}
	
	
	public List<District> findAllDistricts(){
		return userRepositry.findAllDistricts();
	}
	
	public District findDistrictById(Integer id){
		return userRepositry.findDistrictById(id);
	}
	
	public List<Constituency> findAllConstituencies(){
		return userRepositry.findAllConstituencies();
	}
	
	
	public List<Mandal> findAllMandals(){
		return userRepositry.findAllMandals();
	}
	
	
	public List<Village> findAllVillages(){
		return userRepositry.findAllVillages();
	}
	
	
	public List<Department> findAllDepartments(){
		return userRepositry.findAllDepartments();
	}
	
	
	public List<Qualification> findAllQualifications(){
		return userRepositry.findAllQualifications();
	}
	
	
	public List<Profession> findAllProfessions(){
		return userRepositry.findAllProfessions();
	}
	
	
	public List<Caste> findAllCastes(){
		return userRepositry.findAllCastes();
	}
	
	
	public List<SubCaste> findAllSubCastes(){
		return userRepositry.findAllSubCastes();
	}
	
	public List<User> findAllUsers(){
		return userRepositry.findAllUsers();
	}
	
	public User findUserById(Integer id){
		return userRepositry.findUserById(id);
	}
	
	public Country findCountryById(Integer id) {
		if(id == null) return null;
		return userRepositry.findCountryById(id);
	}
	
	
	public List<State> findAllStatesByCountryId( Integer countryId){
		if(countryId == null) return null;
		return userRepositry.findAllStatesByCountryId(countryId);
	}
	
	
	public State findStateById( Integer id) {
		if(id == null) return null;
		return userRepositry.findStateById(id);
	}
	
	
	public List<Constituency> findAllConstituenciesByDistrictId( Integer districtId){
		if(districtId == null) return null;
		return userRepositry.findAllConstituenciesByDistrictId(districtId);
	}
	
	public List<Constituency> findAllConstituenciesByStateId( Integer stateId){
		if(stateId == null) return null;
		List<Constituency> constituencies = userRepositry.findAllConstituenciesByStateId(stateId);
		List<District> districts = userRepositry.findAllDistrictsByStateId(stateId);
		HashMap<Integer,Integer> distIndexes = new HashMap<Integer,Integer>();
		for(int i=0;i<districts.size();i++) {
			distIndexes.put(districts.get(i).getId(), i);
		}
		for(Constituency constituency: constituencies) {
			int ind = distIndexes.get(constituency.getDistrictId());
			constituency.setDistrict(districts.get(ind));
		}
		return constituencies;
	}
	
	public List<Village> findAllVillagesByConstituencyId( Integer constituencyId){
		if(constituencyId == null) return null;
		List<Village> villages = userRepositry.findAllVillagesByMandalConstituencyId(constituencyId);
		List<Mandal> mandals = userRepositry.findAllMandalsByConstituencyId(constituencyId);
		HashMap<Integer,Integer> mandalIndexes = new HashMap<Integer,Integer>();
		for(int i=0;i<mandals.size();i++) {
			mandalIndexes.put(mandals.get(i).getId(), i);
		}
		for(Village village: villages) {
			int ind = mandalIndexes.get(village.getMandalId());
			village.setMandal(mandals.get(ind));
		}
		return villages;
	}
	
	public Constituency findConstituencyById( Integer id) {
		if(id == null) return null;
		return userRepositry.findConstituencyById(id);
	}
	
	
	public List<Mandal> findAllMandalsByDistrictId( Integer districtId){
		if(districtId == null) return null;
		return userRepositry.findAllMandalsByDistrictId(districtId);
	}
	
	
	public Mandal findMandalById( Integer id) {
		if(id == null) return null;
		return userRepositry.findMandalById(id);
	}
	
	public List<Village> findAllVillagesByMandalId(Integer mandalId){
		if(mandalId == null) return null;
		return userRepositry.findAllVillagesByMandalId(mandalId);
	}
	
	
	public Village findAllVillageById(Integer id) {
		if(id == null) return null;
		return userRepositry.findAllVillageById(id);
	}
	
	
	public List<SubCaste> findAllSubCasteByCasteId( Integer casteId){
		if(casteId == null) return null;
		return userRepositry.findAllSubCasteByCasteId(casteId);
	}
		
	public List<User> findUsersByRegidExpr(String regid){
		if(regid == null) return null;
		return userRepositry.findUsersByRegidExpr(regid);
	}
	
	public List<User> findUsersByReferedRegid(String referedRegid){
		if(referedRegid == null) return null;
		return userRepositry.findUsersByReferedRegid(referedRegid);
	}
	
	public List<User> findAllUsersByRole(EnumUserRole role){
		if(role == null) return null;
		return userRepositry.findAllUsersByRole(role);
	}
	
	
	public User findByPhno(String phno) {
		if(phno == null) return null;
		return userRepositry.findByPhno(phno);
	}
	
	public APSFResponse sendOTP(User user){
		APSFResponse response = new APSFResponse();
		try {
			User persisUser = this.findByPhno(user.getPhno());
			if(persisUser!=null) {
				SMSClient smsClient = new SMSClient();
				String otp = Util.randomNo();
				String res = smsClient.sendSms(user.getPhno(), otp);
				if(EnumResponseStatus.OK.toString().equals(res)) {
					persisUser.setOtp(otp);
					userRepositry.saveOrUpdateUser(persisUser);
					persisUser.setReferedUser(null);
					response.add("user",persisUser);
					response.setMessage("OTP send to your mobile number, Please check..! ");
					response.setStatusCode(StatusCode.OK);
				}else {
					response.setMessage("OTP send to your mobile number failed..! ");
					response.setStatusCode(StatusCode.ERROR);
				}
			}else {
				response.setMessage("Inavlid mobile number..! ");
				response.setStatusCode(StatusCode.ERROR);
			}
	
		}catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(StatusCode.ERROR);
		}
				
		return response;
	}
	
	/**
	 * Generate regid by using state,district,constituency codes 
	 * and followed by random number  
	 * @param user
	 * @return
	 */
	public String genereateRegid(User user) {
		
		String regid = null;
		State state = this.findStateById(user.getStateId());
		District district = this.findDistrictById(user.getDistrictId());
		Constituency constituency = this.findConstituencyById(user.getConstituencyId());
		String randid = Util.sixDigitRandom();
		if(state != null && district != null && constituency != null && randid != null ) {
			regid = state.getCode() + String.format("%03d", constituency.getId()) + district.getCode() + randid;
		}
		return regid; 
	}
	
	public APSFResponse verifyOTP(String phno, String otp)
	{
		APSFResponse response = new APSFResponse();
		try {
			User persisUser = this.findByPhno(phno);
			if(otp!=null && persisUser.getOtp()!=null && otp.equals(persisUser.getOtp()) ) {
				response.setMessage("Verification done..!");
				response.setStatusCode(StatusCode.OK);				
			}else {
				
				response.setMessage("Invalid OTP");
				response.setStatusCode(StatusCode.ERROR);
			}
	
		}catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(StatusCode.ERROR);
		}
				
		return response;
	}
	
	public APSFResponse verifyOTPAndGenerateRegid(User user) {
		APSFResponse response = this.verifyOTP(user.getPhno(),user.getOtp());
		try {
			if( StatusCode.OK.equals(response.getStatusCode()) ) 
			{	
				if(user.getRegid()==null) {
										
					String regid = genereateRegid(user);
					if(regid!=null){
						
						//Generate regid
						user.setRegid(regid);
					}
				}
				user.setIsActive(EnumYOrN.Y);
				user.setDob(null);
				user.setStatus(EnumUserStatus.VERIFIED);
				userRepositry.saveOrUpdateUser(user);
				
				User refUser = user.getReferedUser();
				if(refUser!=null)
					refUser.setReferedUser(null);
				response.add("user",user);
				response.setMessage("OTP verification done.");
				response.setStatusCode(StatusCode.OK);
				
				//Send sms to his regid
				sendRegidSMS(user);
				
			}else {
				
				response.setMessage("Invalid OTP");
				response.setStatusCode(StatusCode.ERROR);
			}
	
		}catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(StatusCode.ERROR);
		}
				
		return response;
	}
	
	public APSFResponse sendRegidSMS(User user){
		APSFResponse response = new APSFResponse();
		try {
			SMSClient smsClient = new SMSClient();
			Object args[] = {user.getRegid()};
			//String msg = messageSource.getMessage("msg.sms.regid.send", args ,null );
			String msg = "APSF Registration Id: "+ user.getRegid();
			String res = smsClient.sendSms(user.getPhno(), msg);
			if(EnumResponseStatus.OK.toString().equals(res)) {
				response.setMessage("SMS Sent success..!");
				response.setMessage(res);
				response.setStatusCode(StatusCode.OK);
			}else {
				response.setMessage("SMS Send failed..!");
				response.setMessage(res);
				response.setStatusCode(StatusCode.ERROR);
			}
	
		}catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(StatusCode.ERROR);
			response.setMessage(e.getMessage());
		}
				
		return response;
	}
	
}
