/**
 * 
 * RestAuthenticationFilter.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.mithra.apsf.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.GenericFilterBean;

public class RestAuthenticationFilter extends GenericFilterBean {
	/**
	 * Filter for handling the user related request (to capture UDID and OS)
	 * 
	 * @author Manasa Mantena
	 * 
	 */

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if ( request instanceof HttpServletRequest )
		{
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			
			
			String udid = request.getParameter("udid");
			String os = httpServletRequest.getParameter("os");
			if(udid!=null&&os!=null){
				HttpSession httpSession = httpServletRequest.getSession();
				if(httpSession!=null){
					httpSession.setAttribute("udid", udid);
					httpSession.setAttribute("os", os);
				}
				
			 }
			
			/*if(httpSession!=null){
				if(httpSession.getAttribute("udid") != null&&httpSession.getAttribute("os")!=null){
					udid = (String)httpSession.getAttribute("udid");
					os = (String)httpSession.getAttribute("os");
				} else {
				 udid = request.getParameter("udid");
				 os = httpServletRequest.getParameter("os");
				 if(udid!=null&&os!=null){
					 httpSession.setAttribute("udid", udid);
					 httpSession.setAttribute("os", os);
				 }
				}
				
			}*/
			chain.doFilter( httpServletRequest, httpServletResponse );
		}
		
	}
}
