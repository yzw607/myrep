package com.wmp.common.service;

import java.util.List;

import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.userManage.bean.User;

public interface ILoginService
{
    public User findUserByUsercode(User tmpUser);
    
    public String getForgetPsw(String userCode) throws Exception;
    
    public List<ActivityInfo> querySureActivityInfoList(User user) throws Exception;
    
    public User findUser(String userCode) throws Exception;
    
    public User updateUserInfo(User tmpUser) throws Exception;
}
