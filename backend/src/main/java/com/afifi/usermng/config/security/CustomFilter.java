package com.afifi.usermng.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CustomFilter extends OncePerRequestFilter {

    private static final String ALLOWED_ORIGIN = "http://localhost:4200";
    private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS";
    private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, " +
            "credential, X-XSRF-TOKEN , Cache-Control, WWW-Authenticate";
    private static final String EXPOSE_HEADERS = "xsrf-token";
    private static final String MAX_AGE = "3600";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
        response.setHeader("Access-Control-Allow-Methods", ALLOWED_METHODS);
        response.setHeader("Access-Control-Allow-Headers", ALLOWED_HEADERS);
        response.addHeader("Access-Control-Expose-Headers", EXPOSE_HEADERS);
        response.addHeader("Access-Control-Max-Age", MAX_AGE);
        chain.doFilter(request, response);
    }

}
