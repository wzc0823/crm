package com.qiluruanjianyuan.crm.settings.service;

import com.qiluruanjianyuan.crm.exception.LoginException;
import com.qiluruanjianyuan.crm.settings.domain.User;
import com.qiluruanjianyuan.crm.workbench.domain.Activity;

import java.util.List;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();//在市场模块查询用户信息


}
