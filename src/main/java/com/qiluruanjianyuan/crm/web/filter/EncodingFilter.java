package com.qiluruanjianyuan.crm.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 创建整个项目的过滤器，处理报错时的中文乱码问题，别忘了在web.xml通知
 *
 * */
public class EncodingFilter implements Filter {



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入到过滤字符编码的过滤器");
        //首先过滤post请求中文乱码的问题
        request.setCharacterEncoding("utf-8");
        //过滤响应流响应中文乱码的问题
        response.setContentType("text/html;charset=utf-8");
        //将请求放行
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
