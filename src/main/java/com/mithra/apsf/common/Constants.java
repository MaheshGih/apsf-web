package com.mithra.apsf.common;

import javax.persistence.EnumType;

public interface Constants 
{
	
	static final String PREFIX_HTTP = "http://";
	
	public enum EnumResponseStatus
	{
		OK,  ERROR, REDIRECT
	}
	
	public enum EnumYOrN
	{
		Y, N
	}
	
	public enum EnumUserRole {
		ROLE_USER, ROLE_EMP, ROLE_ADMIN, ROLE_ANONYMOUS
	}

	// Enum for UserStatus
	public enum EnumUserStatus {
		PENDING, VERIFIED, DELETED, SUSPENDED
	}
	
	public enum EnumUserGender {
		M, F
	}
	
	/**
	 * Job Status
	 * @author mtoluchuri
	 *
	 */
	public enum EnumJobStatus
	{
		RUNNING( "Running" ), SUCCEEDED("Succeeded"), SUSSPENDED( "Susspended" ), KILLED( "Killed" ), 
		FAILED( "Failed" ), PREP( "Prep" );
		
		private String camlcase;
		
		EnumJobStatus(String camlcase){
			this.camlcase=camlcase;
		}
	}

}
