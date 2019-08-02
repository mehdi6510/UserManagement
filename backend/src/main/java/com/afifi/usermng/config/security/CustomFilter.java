package com.afifi.usermng.config.security;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CustomFilter implements Filter {

    private static final String ALLOWED_ORIGIN = "http://localhost:4200";
    private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS";
    private static final String ALLOWED_HEADERS = "*";
    private static final String EXPOSE_HEADERS = "xsrf-token";
    private static final String ALLOW_CREDENTIALS = "true";
    private static final String MAX_AGE = "3600";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
        response.setHeader("Access-Control-Allow-Methods", ALLOWED_METHODS);
        response.setHeader("Access-Control-Allow-Headers", ALLOWED_HEADERS);
        response.setHeader("Access-Control-Expose-Headers", EXPOSE_HEADERS);
        response.setHeader("Access-Control-Allow-Credentials", ALLOW_CREDENTIALS);
        response.setHeader("Access-Control-Max-Age", MAX_AGE);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
