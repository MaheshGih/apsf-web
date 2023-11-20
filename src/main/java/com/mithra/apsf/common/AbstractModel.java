/**
 *Common properties of all Models
 */

package com.mithra.apsf.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mithra.apsf.util.DateTimeSerializer;


@MappedSuperclass
public class AbstractModel implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_USER = "admin";

	@Column(name = "cr_by", updatable = false)
	private String createdBy;

	@Column(name = "cr_dt", updatable = false)
	//@JsonSerialize(using = DateTimeSerializer.class)
	private Date createdDate;

	@Column(name = "mod_by")
	private String modifiedBy;

	@Column(name = "mod_dt")
	//@JsonSerialize(using = DateTimeSerializer.class)
	private Date modifiedDate;

	/**
	 * If you want set default values in extended class, You must override this
	 * method. After override please call like below otherwise this super class
	 * default values will lose for current object <blockquote>
	 *  
	 * </blockquote>
	 */
	public void setDefaultPros() {
		//createdBy = DEFAULT_USER;
		createdDate = new Date();
		//modifiedBy = DEFAULT_USER;
		modifiedDate = new Date();
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
