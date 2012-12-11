package com.wmp.selfService.service;

import java.io.File;
import java.util.List;

import com.wmp.common.bean.Page;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.bean.UserNumber;
import com.wmp.userManage.bean.User;

public interface IPlanService
{
    public Page getPage(User user, ActivityInfo queryActivity, Page page) throws Exception;
    
    public List<ActivityInfo> queryActivityInfoList(User user, ActivityInfo queryActivity, Page page) throws Exception;
    
    public ActivityInfo queryActivityInfo(int activityId) throws Exception;
    
    public User queryUserInfo(int userId) throws Exception;
    
    public String updateActivity(User user, ActivityInfo inputActivityInfo,
            File file, String contextPath) throws Exception;
    
    public List<UserNumber> queryUserNumbers(String userCode) throws Exception;
    
    public ActivityInfo saveActivity(ActivityInfo activity, File file,
            String contextPath) throws Exception;
    
    public void delPic(ActivityInfo activity) throws Exception;
    
    public String doActivate(User user, ActivityInfo inputActivityInfo,
            File file, String contextPath) throws Exception;
    
    public List<Object> doExport(int activityId) throws Exception;
    
    public String doOrderBack(User user, ActivityInfo inputActivityInfo) throws Exception;
    
    public void delActivityInfo(String ids) throws Exception;
}
