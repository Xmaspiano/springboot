package com.springboot.common.config.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginFilter extends FormAuthenticationFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return Boolean.TRUE;
    }
}
