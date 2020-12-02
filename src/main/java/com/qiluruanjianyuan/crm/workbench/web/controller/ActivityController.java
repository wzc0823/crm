package com.qiluruanjianyuan.crm.workbench.web.controller;
import com.qiluruanjianyuan.crm.settings.domain.User;
import com.qiluruanjianyuan.crm.settings.service.UserService;
import com.qiluruanjianyuan.crm.settings.service.impl.UserServiceImpl;
import com.qiluruanjianyuan.crm.utils.DateTimeUtil;
import com.qiluruanjianyuan.crm.utils.PrintJson;
import com.qiluruanjianyuan.crm.utils.ServiceFactory;
import com.qiluruanjianyuan.crm.utils.UUIDUtil;
import com.qiluruanjianyuan.crm.workbench.domain.Activity;
import com.qiluruanjianyuan.crm.workbench.service.ActivityService;
import com.qiluruanjianyuan.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 一个模块一个servlet。即用户的增删改查放到一个servlet中
 * */
public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入市场活动控制器");
        String path=request.getServletPath();//拿到的是url-pattern
        if ("/workbench/activity/getUserList.do".equals(path)){//如果url-pattern，需要创建一个方法来实现功能，并且里边有request和response实现传参数等

             getUserList(request,response);

        }else if ("/workbench/activity/save.do".equals(path)){
                save(request,response);

        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入创建市场模块");
        String createBy=((User)request.getSession().getAttribute("user")).getName();//创建人，当前登录用户
        String id= UUIDUtil.getUUID();
        String owner=request.getParameter("owner");
        String name=request.getParameter("name");
        String startDate=request.getParameter("startDate");
        String endDate=request.getParameter("endDate");
        String cost=request.getParameter("cost");
        String description=request.getParameter("description");
        String createTime= DateTimeUtil.getSysTime();//获取当前时间即市场表的创建时间
        Activity a=new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);
        ActivityService as= (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());//传张三取李四，获取代理对象，us是李四
        boolean flag= as.save(a);//传进去a对象
        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("取得用户信息列表");
        //需要注意的是：虽然在市场模块，但是在业务层实际上与市场活动无关，没有用到市场活动相关的表，业务层需要用用户的
        UserService us= (UserService) ServiceFactory.getService(new UserServiceImpl());//传张三取李四，获取代理对象，us是李四
         List<User> ulist=  us.getUserList();
      //把ulist解析为json格式传到前台
        PrintJson.printJsonObj(response,ulist);//将集合转换为json数组，传到前台
    }

}
