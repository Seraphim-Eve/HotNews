package com.springapp.mvc.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Xr on 2016/9/1.
 */
public class SessionFilter implements Filter {

    private static final String[] IGNORE_URL = new String[]{"/", "/login.do", "/index.do", "/registerJump.do", "/forgotJump.do", "/register.do", "/forgot.do", "/logout.do", "/check.do", "/reset.do"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //nothing
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        HttpSession session = req.getSession();
        String uri = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/"));

        boolean flag = true;

        for (String suffix : IGNORE_URL) {
            if (uri.equals(suffix)) {
                flag = false;
                break;
            }
        }

        //validate user
        if (flag) {
            Object username = session.getAttribute("username");
            if (null == username) {
                resp.sendRedirect("index.do");
            } else {
                filterChain.doFilter(req, resp);
            }
        } else {
            filterChain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {
        //nothing
    }
}
