package com.qiluruanjianyuan.crm.settings.web.controller;

import com.qiluruanjianyuan.crm.settings.domain.User;
import com.qiluruanjianyuan.crm.settings.service.UserService;
import com.qiluruanjianyuan.crm.settings.service.impl.UserServiceImpl;
import com.qiluruanjianyuan.crm.utils.MD5Util;
import com.qiluruanjianyuan.crm.utils.PrintJson;
import com.qiluruanjianyuan.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 一个模块一个servlet。即用户的增删改查放到一个servlet中
 * */
public class UserController  extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到用户控制器");
        String path=request.getServletPath();//拿到的是url-pattern
        if ("/settings/user/login.do".equals(path)){//如果url-pattern，需要创建一个方法来实现功能，并且里边有request和response实现传参数等

      login(request,response);

        }else if ("/settings/user/xxx.do".equals(path)){

            /*  xxx(request,response);*/
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到验证登录操作");
        String loginAct=request.getParameter("loginAct");
        String loginPwd=request.getParameter("loginPwd");
        loginPwd= MD5Util.getMD5(loginPwd);    //将密码的明文形式转换为MD5的密文形式
        String ip=request.getRemoteAddr();  //接受浏览器端ip地址
        System.out.println("ip========="+ip);
        //创建业务层service对象，让他去干活
        //未来的业务层开发，统一使用代理类形态的对象
        UserService us= (UserService) ServiceFactory.getService(new UserServiceImpl());
                    //我是张三（UserServiceImpl）,但是此时创建的是代理对象，是李四，不是我们写的login(),
        //我们写的是张三的UserServiceImpl的login(),走的是工具类中的TransactioninvocationHandler（动态代理）中的invoke
        //即异常被李四处理完了，此时需要在动态代理中的方法中继续往上抛（throw e.getCause();），让controller知道发生了异常
          try{
              User user= us.login(loginAct,loginPwd,ip);
              request.getSession().setAttribute("user",user);//将取得的对象放到作用域中，等到你切换也免得时候user对象还在
              //如果程序执行到此处，说明业务层没有为controller抛出任何的异常
              //表示登陆成功，为前端返回{"success",true}，用到了工具PrintJson
             /*      String str="{\"success\",true}";
                  response.getWriter().print(str);*/
              PrintJson.printJsonFlag(response,true);//转换为json格式，发给前端
          }
          catch (Exception e){
              e.printStackTrace();//登录失败，说明业务层为我们验证失败，为controller抛出异常
              //如果登陆失败，为前端返回{"success",false,"msg","哪错了"}
              String msg=e.getMessage();//错误消息
              /*
              * 我们现在作为控制器需要为ajax请求提供多项信息，可以有两种手段处理：
              * 1 将多项信息打包为map，将map解析为json串
              * 2 创建一个Vo   两个属性
              * private boolean success
              * private String msg
              * 如果对于展现的信息将来还会大量的使用我们创建一个vo类，使用方便
              * 如果对于展现的信息只有在这个需求中能够使用，那我们使用map就可以了
              *
              * */
              Map<String,Object> map=new HashMap<String,Object>();
              map.put("success",false);
              map.put("msg",msg);
              PrintJson.printJsonObj(response,map);//将map解析为json串信息返回给前端
          }
    }
}
