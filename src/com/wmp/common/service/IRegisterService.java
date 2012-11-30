package com.wmp.common.service;

import java.io.File;
import java.util.List;

import com.wmp.selfService.bean.UserNumber;
import com.wmp.userManage.bean.User;

public interface IRegisterService
{
    public User registerUser(User tmpUser, File file, String contextPath);
    
    public List<UserNumber> getUserNumbers(String userCode) throws Exception;
    
    public String checkUserCode(String userCode) throws Exception;
}
