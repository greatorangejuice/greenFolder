/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

@Component
public class ExceptionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.setHeader("exception", e.getCause().getMessage());
            res.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    @Override
    public void destroy() {
    }
}
