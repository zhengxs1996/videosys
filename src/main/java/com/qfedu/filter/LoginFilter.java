package com.qfedu.filter;

import com.qfedu.utils.StrUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = "/*")
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        String requestURI = request.getRequestURI();

        // 这些资源是登录相关的资源，不应该被过滤，可以允许用户之间访问
        if (requestURI.contains("login") || requestURI.contains("Login") || requestURI.contains("/css/")
                || requestURI.contains("/js/") || requestURI.contains("/img/") || requestURI.contains("/layui")
                || requestURI.contains("/images") || requestURI.contains("/before/") || requestURI.contains("/course/list.do")) {
            chain.doFilter(request, response);
        } else if (req.getParameter(StrUtil.LONGIN_USER) == null && (null == session || session.getAttribute(StrUtil.LONGIN_USER) == null)) {
            // 没有session信息
            response.sendRedirect(request.getContextPath() + "/login.html");
        } else if (session.getAttribute(StrUtil.LONGIN_USER) != null) {
            // 访问的不是的登录资源，并且在服务器上已经存在登录记录，直接放行
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
