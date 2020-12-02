package com.qiluruanjianyuan.crm.web.filter;

import com.qiluruanjianyuan.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 创建过滤器判断用户有没有登录
 *
 * */
public class LoginFilter implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入到有没有验证过的过滤器");
        //通过session域有没有user,因为ServletRequest没有getSession这个方法，所以需要转换为子类HttpServletRequest
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        String path=request.getServletPath();//获取放行路径
        if ("/login.jsp".equals(path)||"/settings/user/login.do".equals(path)){
           chain.doFilter(req,resp); //不应该被拦截的资源自动放行
        }else {
            //其他资源必须验证
            HttpSession session=request.getSession();
            User user= (User) session.getAttribute("user");//从session中获取共享数据
            if(user!=null){
                //如果user不为空，说明登陆过，将请求放行
                chain.doFilter(req,resp);
            }else {
                //没有登陆过
                //重定向到登录页

                /**
                 * 1 重定向路径怎么写？
                 * 在实际项目开发中对于路径的使用不论前端还是后端都使用绝对路径
                 * 关于转发和重定向路径写法如下
                 * 转发：使用的是一种特殊的绝对路径的使用方式，这种绝对路径前面不加/路径名，这种路径也称之为内部路径
                 * 重定向：传统绝对路径，前边必须以/项目名开头,后边跟具体的资源路径/crm/login.jsp
                 *
                 * 2 为什么使用重定向，使用请求转发方式不行么？
                 * 转发之后路径会停留在老路径上，而不是跳转之后的最新路径，我们应该为用户跳转到登录页的同时
                 * 将浏览器的地址栏应该自动设置为当前的登录页的路径
                 * */
                response.sendRedirect(request.getContextPath()+"/login.jsp");

            }
        }

    }

    @Override
    public void destroy() {

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
