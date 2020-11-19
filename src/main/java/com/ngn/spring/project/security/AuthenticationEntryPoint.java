
/**
 * ====================================================================
 * Created by nima.yoezer on 23-Sep-18.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
package com.ngn.spring.project.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.ELRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    //region private variable
    private static final RequestMatcher REQUEST_MATCHER = new ELRequestMatcher("hasHeader('X-Requested-With','XMLHttpRequest')");
    //endregion

    //region public method
    public AuthenticationEntryPoint(String loginUrl) {
        super(loginUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        if (REQUEST_MATCHER.matches(request)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "SESSION_TIMED_OUT");
        } else {
            request.setAttribute("targetUrl", request.getRequestURL());
            super.commence(request, response, authException);
        }
    }
    //endregion
}
