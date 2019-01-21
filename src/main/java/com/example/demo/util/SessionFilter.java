/*
package com.example.demo.util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "loginFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "loginUI", value = "/loginUI"),
                @WebInitParam(name = "register", value = "/register"),
                @WebInitParam(name = "login", value = "/login"),
                @WebInitParam(name = "css", value = ".css"),
                @WebInitParam(name = "js", value = ".js"),
                @WebInitParam(name = "png", value = ".png"),
                @WebInitParam(name = "html", value = ".html"),
                @WebInitParam(name = "loginProcess", value = "home/login"),
                @WebInitParam(name = "encoding", value = "utf-8")
        })
public class SessionFilter implements Filter {
    private FilterConfig config;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        // 获取配置参数
        String loginUI = config.getInitParameter("loginUI");
        String register = config.getInitParameter("register");
        String login = config.getInitParameter("login");
        String css = config.getInitParameter("css"); // 记录.css和.js文件不过滤
        String js  = config.getInitParameter("js");
        String png =config.getInitParameter("png");
        String html  = config.getInitParameter("html");

        String encoding = config.getInitParameter("encoding");


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // 设置请求的字符集（post请求方式有效）
        request.setCharacterEncoding(encoding);

        // 不带http://域名:端口的地址
        String uri = request.getRequestURI();
        if (uri.contains(loginUI) ||uri.contains(login)|| uri.contains(css)||uri.contains(js)||uri.contains(png)||uri.contains(register)) {  //如果是登陆界面或者是.css,.html和.js结尾的放行

            HttpSession session = request.getSession();
            // 请求的登录，放行
            chain.doFilter(request, response);
        } else {
            if (request.getSession().getAttribute("username") == null) {
                // 重定向到登录页面
                response.sendRedirect(request.getContextPath() + loginUI);
            } else {
                // 已经登录，放行
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        this.config = null;
    }
}
*/
