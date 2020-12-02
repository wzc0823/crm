package com.qiluruanjianyuan.crm.settings.service.impl;

import com.qiluruanjianyuan.crm.exception.LoginException;
import com.qiluruanjianyuan.crm.settings.dao.UserDao;
import com.qiluruanjianyuan.crm.settings.domain.User;
import com.qiluruanjianyuan.crm.settings.service.UserService;
import com.qiluruanjianyuan.crm.utils.DateTimeUtil;
import com.qiluruanjianyuan.crm.utils.SqlSessionUtil;
import com.qiluruanjianyuan.crm.workbench.domain.Activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl  implements UserService {
    /**
     * 在处理业务逻辑的时候，会遇到与数据的交互，所以业务层调用dao层，并以成员变量的形式存在
     * */
    //根据sqlSession对象获取dao层对象
    private UserDao userDao= SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
        Map<String,String> map=new HashMap<String,String>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
      User user=  userDao.login(map);
      if (user==null){
          throw new LoginException("账号密码错误");

      }
      //如果程序能够成功执行到该行，说明账号密码正确
        //需要继续向下验证其他三项信息
        //验证失效时间
        String expireTime=user.getExpireTime();
      String currentTime= DateTimeUtil.getSysTime();//利用工具类获取当前时间
        if (expireTime.compareTo(currentTime)<0){
            throw new LoginException("账号已失效");
        }
        //锁定状态
        String lockState=user.getLockState();
        if ("0".equals(lockState)){
            throw new LoginException("账号已锁定，请联系管理员");
        }


        //判断ip地址
        String allowIps=user.getAllowIps();
        if (!allowIps.contains(ip)){
            throw new LoginException("IP地址受限");
        }
        return user;
    }

    @Override
    public List<User> getUserList() {//在实现类方法中执行操作
        //在业务层方法中，调个dao层就能拿到列表
       List<User> ulist= userDao.getUserList();
        return ulist;
    }

}
