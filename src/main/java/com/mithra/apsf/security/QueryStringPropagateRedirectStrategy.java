package com.mithra.apsf.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;

public class QueryStringPropagateRedirectStrategy extends DefaultRedirectStrategy {

    public void sendRedirect(HttpServletRequest request,
            HttpServletResponse response, String url) throws IOException {
        String urlWithOriginalQueryString = url + "?" + request.getQueryString();
        super.sendRedirect(request, response, urlWithOriginalQueryString );
    }

}