package com.mithra.apsf.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Authentication entry point for REST services
 */
public final class RestAuthenticationEntryPoint implements
		AuthenticationEntryPoint {
	// it will called when any unauthenticated request came
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		// Get The application URL up to Context Path
		String requestURL = request.getRequestURL().toString();
		String contextPath = request.getContextPath().toString();
		String hostUrl = requestURL.substring(0, requestURL.indexOf(contextPath));

		// Application URL up to Context Path
		String applicationURL = hostUrl + contextPath;

		// Access Denied redirect URI
		String targetURI = "/user/rest/accessdenied";

		// Redirecting URL
		String targetURL = applicationURL + targetURI;

		// Redirecting to specified URL
		response.sendRedirect(targetURL);
	}
}
