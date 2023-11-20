/**
 * 
 */
package com.mithra.apsf.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mithra.apsf.common.AbstractModel;
import com.mithra.apsf.common.Constants.EnumUserGender;
import com.mithra.apsf.common.Constants.EnumUserRole;
import com.mithra.apsf.common.Constants.EnumUserStatus;
import com.mithra.apsf.common.Constants.EnumYOrN;
import com.mithra.apsf.util.DateTimeSerializer;

/**
 * @author koti
 *
 */
@Entity
public class User extends AbstractModel{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private String surname;
	
	@Column(name="father_name")
	private String fatherName;
	
	private String phno;
	
	@Column(name="alter_phno")
	private String alternatePhno;
	
	private String mailid;

	@Column(name="photo_url")
	private String photUrl;
	
	//@JsonSerialize(using = DateTimeSerializer.class)
	private Date dob;
	
	@Enumerated(EnumType.STRING)
	private EnumUserGender gender;

	@Column(name="b_g")
	private String  bloodGroup; 

	private String regid;
	
	@Column(name="pwd")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private EnumUserRole role;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ref_regid",referencedColumnName="regid",insertable=false,updatable=false)
	private User referedUser;
	
	@Column(name="ref_regid",insertable=true,updatable=true)
	private String referedRegid;
	
	@Enumerated(EnumType.STRING)
	private EnumUserStatus status; 
	
	@Column(name="is_active")
	@Enumerated(EnumType.STRING)
	private EnumYOrN isActive;
	
	@Column(name="addr1")
	private String address1;
	
	@Column(name="addr2")
	private String address2;
	
	private String pincode;
	
	@ManyToOne
	@JoinColumn(name="sub_caste_id",insertable=false,updatable=false)
	private SubCaste subCaste;
	
	@ManyToOne
	@JoinColumn(name="caste_id",insertable=false,updatable=false)
	private Caste caste;
	
	@ManyToOne
	@JoinColumn(name="quali_id",insertable=false,updatable=false)
	private Qualification qualification;
	
	@ManyToOne
	@JoinColumn(name="prof_id",insertable=false,updatable=false)
	private Profession profession;
	
	@ManyToOne
	@JoinColumn(name="dept_id",insertable=false,updatable=false)
	private Department department;
	
	
	@Column(name="sub_caste_id",insertable=true,updatable=true)
	private Integer subCasteId;
	
	@Column(name="caste_id",insertable=true,updatable=true)
	private Integer casteId;
	
	@Column(name="quali_id",insertable=true,updatable=true)
	private Integer qualificationId;
	
	@Column(name="prof_id",insertable=true,updatable=true)
	private Integer professionId;
	
	@Column(name="dept_id",insertable=true,updatable=true)
	private Integer departmentId;
	
	
	
	@Column(name="country_id",insertable=true,updatable=true)
	private Integer countryId;
	
	@Column(name="state_id",insertable=true,updatable=true)
	private Integer stateId;
	
	@Column(name="dist_id",insertable=true,updatable=true)
	private Integer districtId;
	
	@Column(name="const_id",insertable=true,updatable=true)
	private Integer constituencyId;
	
	@Column(name="mandal_id",insertable=true,updatable=true)
	private Integer mandalId;
	
	@Column(name="village_id",insertable=true,updatable=true)
	private Integer villageId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="village_id",insertable=false,updatable=false)
	private Village village;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="mandal_id ",insertable=false,updatable=false)
	private Mandal mandal;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="const_id",insertable=false,updatable=false)
	private Constituency constituency;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="dist_id",insertable=false,updatable=false)
	private District district;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="state_id",insertable=false,updatable=false)
	private State state;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="country_id",insertable=false,updatable=false)
	private Country country;

	private String otp;
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}

	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	/**
	 * @return the phno
	 */
	public String getPhno() {
		return phno;
	}

	/**
	 * @param phno the phno to set
	 */
	public void setPhno(String phno) {
		this.phno = phno;
	}

	/**
	 * @return the alternatePhno
	 */
	public String getAlternatePhno() {
		return alternatePhno;
	}

	/**
	 * @param alternatePhno the alternatePhno to set
	 */
	public void setAlternatePhno(String alternatePhno) {
		this.alternatePhno = alternatePhno;
	}

	/**
	 * @return the mailid
	 */
	public String getMailid() {
		return mailid;
	}

	/**
	 * @param mailid the mailid to set
	 */
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	/**
	 * @return the photUrl
	 */
	public String getPhotUrl() {
		return photUrl;
	}

	/**
	 * @param photUrl the photUrl to set
	 */
	public void setPhotUrl(String photUrl) {
		this.photUrl = photUrl;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the gender
	 */
	public EnumUserGender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(EnumUserGender gender) {
		this.gender = gender;
	}

	/**
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * @return the regid
	 */
	public String getRegid() {
		return regid;
	}

	/**
	 * @param regid the regid to set
	 */
	public void setRegid(String regid) {
		this.regid = regid;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the referedUser
	 */
	public User getReferedUser() {
		return referedUser;
	}

	/**
	 * @param referedUser the referedUser to set
	 */
	public void setReferedUser(User referedUser) {
		this.referedUser = referedUser;
	}

	/**
	 * @return the isActive
	 */
	public EnumYOrN getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(EnumYOrN isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the subCaste
	 */
	public SubCaste getSubCaste() {
		return subCaste;
	}

	/**
	 * @param subCaste the subCaste to set
	 */
	public void setSubCaste(SubCaste subCaste) {
		this.subCaste = subCaste;
	}

	/**
	 * @return the caste
	 */
	public Caste getCaste() {
		return caste;
	}

	/**
	 * @param caste the caste to set
	 */
	public void setCaste(Caste caste) {
		this.caste = caste;
	}

	/**
	 * @return the qualification
	 */
	public Qualification getQualification() {
		return qualification;
	}

	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	/**
	 * @return the profession
	 */
	public Profession getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the village
	 */
	public Village getVillage() {
		return village;
	}

	/**
	 * @param village the village to set
	 */
	public void setVillage(Village village) {
		this.village = village;
	}

	/**
	 * @return the mandal
	 */
	public Mandal getMandal() {
		return mandal;
	}

	/**
	 * @param mandal the mandal to set
	 */
	public void setMandal(Mandal mandal) {
		this.mandal = mandal;
	}

	/**
	 * @return the constituency
	 */
	public Constituency getConstituency() {
		return constituency;
	}

	/**
	 * @param constituency the constituency to set
	 */
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	/**
	 * @return the district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the role
	 */
	public EnumUserRole getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(EnumUserRole role) {
		this.role = role;
	}

	/**
	 * @return the status
	 */
	public EnumUserStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EnumUserStatus status) {
		this.status = status;
	}

	/**
	 * @return the countryId
	 */
	public Integer getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the stateId
	 */
	public Integer getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the districtId
	 */
	public Integer getDistrictId() {
		return districtId;
	}

	/**
	 * @param districtId the districtId to set
	 */
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	/**
	 * @return the constituencyId
	 */
	public Integer getConstituencyId() {
		return constituencyId;
	}

	/**
	 * @param constituencyId the constituencyId to set
	 */
	public void setConstituencyId(Integer constituencyId) {
		this.constituencyId = constituencyId;
	}

	/**
	 * @return the mandalId
	 */
	public Integer getMandalId() {
		return mandalId;
	}

	/**
	 * @param mandalId the mandalId to set
	 */
	public void setMandalId(Integer mandalId) {
		this.mandalId = mandalId;
	}

	/**
	 * @return the subCasteId
	 */
	public Integer getSubCasteId() {
		return subCasteId;
	}

	/**
	 * @param subCasteId the subCasteId to set
	 */
	public void setSubCasteId(Integer subCasteId) {
		this.subCasteId = subCasteId;
	}

	/**
	 * @return the casteId
	 */
	public Integer getCasteId() {
		return casteId;
	}

	/**
	 * @param casteId the casteId to set
	 */
	public void setCasteId(Integer casteId) {
		this.casteId = casteId;
	}

	/**
	 * @return the qualificationId
	 */
	public Integer getQualificationId() {
		return qualificationId;
	}

	/**
	 * @param qualificationId the qualificationId to set
	 */
	public void setQualificationId(Integer qualificationId) {
		this.qualificationId = qualificationId;
	}

	/**
	 * @return the professionId
	 */
	public Integer getProfessionId() {
		return professionId;
	}

	/**
	 * @param professionId the professionId to set
	 */
	public void setProfessionId(Integer professionId) {
		this.professionId = professionId;
	}

	/**
	 * @return the departmentId
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the referedRegid
	 */
	public String getReferedRegid() {
		return referedRegid;
	}

	/**
	 * @param referedRegid the referedRegid to set
	 */
	public void setReferedRegid(String referedRegid) {
		this.referedRegid = referedRegid;
	}

	/**
	 * @return the villageId
	 */
	public Integer getVillageId() {
		return villageId;
	}

	/**
	 * @param villageId the villageId to set
	 */
	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	/**
	 * @return the otp
	 */
	public String getOtp() {
		return otp;
	}

	/**
	 * @param otp the otp to set
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
}
