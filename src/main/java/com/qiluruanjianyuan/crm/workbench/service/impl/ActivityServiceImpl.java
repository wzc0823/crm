package com.qiluruanjianyuan.crm.workbench.service.impl;

import com.qiluruanjianyuan.crm.utils.SqlSessionUtil;
import com.qiluruanjianyuan.crm.workbench.dao.ActivityDao;
import com.qiluruanjianyuan.crm.workbench.domain.Activity;
import com.qiluruanjianyuan.crm.workbench.service.ActivityService;
/**
 * 实现类处理市场活动的业务，所以用到了处理市场活动的表，此时用到了dao层
 *
 * */
public class ActivityServiceImpl  implements ActivityService {
    private ActivityDao activityDao= SqlSessionUtil.getSqlSession()
            .getMapper(ActivityDao.class);//获取session，利用getMapper()方法获取dao对象
    @Override
    public boolean save(Activity a) {
        boolean flag=true;
        int count=activityDao.save(a);
        if (count!=1){
            flag=false;
        }
        return flag;
    }
}
