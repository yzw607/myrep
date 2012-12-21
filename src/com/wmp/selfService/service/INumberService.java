package com.wmp.selfService.service;

import java.util.List;

import com.wmp.selfService.bean.UserNumber;
import com.wmp.userManage.bean.User;

public interface INumberService
{
    public List<UserNumber> queryUserNumber(User user);
    
    public int getNumberCount(User user, String numberType) throws Exception;
    
//    public String setUserNumber(User user, String[] tmpNumber) throws Exception;
    public String setUserNumber(User user, int numberId) throws Exception;
    
    public String delNumber(User user, int numberId) throws Exception;
    
    public List<UserNumber> queryIdleNumber(String numberType);
    
    public User getNewestUser(int userId) throws Exception;
    
    public int checkNum(String number) throws Exception;
    
    public void updateUserNumber(String numberId,String number) throws Exception;
    
}
