package com.example.c0423i1module3.filter;

import com.example.c0423i1module3.model.Login;
import com.example.c0423i1module3.model.enums.ERole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@javax.servlet.annotation.WebFilter("/users/*")
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        var session = ((HttpServletRequest)servletRequest).getSession();
//        if(session.getAttribute("login") == null){
//            ((HttpServletResponse)servletResponse).sendRedirect("/auths");
//            return;
//        }
//        if(!((Login)session.getAttribute("login")).getRole().equals(ERole.USER)){
//            ((HttpServletResponse)servletResponse).sendRedirect("/auths?action=403");
//            return;
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
