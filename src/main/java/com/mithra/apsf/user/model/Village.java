/**
 * 
 */
package com.mithra.apsf.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.mithra.apsf.common.Constants.EnumYOrN;

/**
 * @author koti
 *
 */
@Entity
public class Village {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private String code;
	
	@Column(name="country_id")
	private Integer countryId;
	
	@Column(name="state_id")
	private Integer stateId;
	
	@Column(name="dist_id")
	private Integer districtId;
	
	@Column(name="const_id")
	private Integer constituencyId;
	
	@Column(name="mandal_id")
	private Integer mandalId;
	
	@Transient
	private Mandal mandal;

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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	

}
