package com.wmp.userManage.service;

import java.util.List;

import com.wmp.common.bean.Page;
import com.wmp.userManage.bean.User;

public interface IUserManageService
{
    public Page countUsers(Page page, String condition);
    
    public List<User> queryUserList(Page page, String condition);
    
    public User showUser(int userId) throws Exception;
    
    public void updateUser(User user) throws Exception;
    
    public void recharge(int userId, int recharge, int handsel);
}
