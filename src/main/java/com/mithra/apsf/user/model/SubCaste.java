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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mithra.apsf.common.Constants.EnumYOrN;

/**
 * @author koti
 *
 */
@Entity
@Table(name="sub_caste")
public class SubCaste {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private String code;
	
	@Enumerated(EnumType.STRING)
	@Column(name="is_active")
	private EnumYOrN isActive;
	
	@ManyToOne
	@JoinColumn(name="caste_id",insertable=true,updatable=true)
	private Caste caste;

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

}
