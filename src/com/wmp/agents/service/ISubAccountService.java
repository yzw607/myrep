package com.wmp.agents.service;

import java.util.List;

import com.wmp.common.bean.Page;
import com.wmp.userManage.bean.User;

public interface ISubAccountService
{
    public Page getPage(User querySubAccount, User user, Page page) throws Exception;
    
    public List<User> queryAgentsList(User querySubAccount, User user, Page page) throws Exception;
    
    public String createSubAccount(User createUser, String subAccountCode) throws Exception;
    
    public User getUserInfo(String userCode) throws Exception;
    
    public int getSubAccountCost(String userCode) throws Exception;
    
    public User getUserInfoById(int userId) throws Exception;
    
    public void initPsw(User user) throws Exception;
}
