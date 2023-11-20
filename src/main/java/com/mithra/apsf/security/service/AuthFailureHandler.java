package com.mithra.apsf.security.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mithra.apsf.common.Constants.EnumResponseStatus;
import com.mithra.apsf.common.APSFResponse;
import com.mithra.apsf.common.StatusCode;
import com.mithra.apsf.security.exception.AccountActivationPendingException;

/**
 * @author mtoluchuri
 *
 */
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthFailureHandler.class);
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		logger.info("Entered into onAuthenticationFailure()");

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		APSFResponse apsfResponse = new APSFResponse();
		if (exception instanceof UsernameNotFoundException) {
			apsfResponse.setMessage(messageSource.getMessage("msg.user.userNameNotFound", null, Locale.ENGLISH));
			apsfResponse.setStatusCode(StatusCode.USER_NOT_FOUND);
			apsfResponse.setResponseStatus(EnumResponseStatus.ERROR);
		} else if (exception instanceof BadCredentialsException) {
			apsfResponse.setMessage(messageSource.getMessage("msg.error.invalidCredentials", null, Locale.ENGLISH));
			apsfResponse.setStatusCode(StatusCode.IN_VALID_CREDENTIALS);
			apsfResponse.setResponseStatus(EnumResponseStatus.ERROR);
		} else if (exception instanceof AccountExpiredException) {
			AccountExpiredException ae = (AccountExpiredException) exception;
			apsfResponse.setMessage(ae.getMessage());
			apsfResponse.setStatusCode(StatusCode.USER_INACTIVE);
			apsfResponse.setResponseStatus(EnumResponseStatus.ERROR);
		} else if (exception instanceof InternalAuthenticationServiceException) {
			if (exception.getCause() instanceof AccountActivationPendingException) {
				AccountActivationPendingException aae = (AccountActivationPendingException) exception.getCause();
				apsfResponse.setMessage(aae.getMessage());
				apsfResponse.setStatusCode(StatusCode.PENDING_ACTIVATION);
				apsfResponse.setResponseStatus(EnumResponseStatus.ERROR);
			} else if (exception.getCause() instanceof DataAccessResourceFailureException) {
				DataAccessResourceFailureException e = (DataAccessResourceFailureException) exception.getCause();
				apsfResponse.setMessage(e.getMessage());
				apsfResponse.setStatusCode(StatusCode.NO_DATABASE_FOUND);
				apsfResponse.setResponseStatus(EnumResponseStatus.ERROR);
			} else {
				apsfResponse.setMessage(exception.getMessage());
				apsfResponse.setStatusCode(StatusCode.UN_AUTHORIZED_USER);
				apsfResponse.setResponseStatus(EnumResponseStatus.ERROR);
			}
		} else {
			apsfResponse.setMessage(exception.getMessage());
			apsfResponse.setStatusCode(StatusCode.UN_AUTHORIZED_USER);
			apsfResponse.setResponseStatus(EnumResponseStatus.ERROR);
		}
		ObjectMapper mapper = new ObjectMapper();
		writer.write(mapper.writeValueAsString(apsfResponse));
		writer.flush();
		
		logger.info("Leaving");
	}
}
