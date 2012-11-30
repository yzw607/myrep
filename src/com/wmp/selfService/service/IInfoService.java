package com.wmp.selfService.service;

import java.util.List;

import com.wmp.common.bean.Page;
import com.wmp.selfService.bean.OrderRecord;
import com.wmp.userManage.bean.User;

public interface IInfoService
{
    public User queryInfo(int userId) throws Exception;

    public Page getPage(User user, Page page) throws Exception;

    public List<OrderRecord> queryRecord(String userCode, Page page)
            throws Exception;

    public void updateUserInfo(User tmpUser) throws Exception;

    public boolean updateUserPsw(User tmpUser, String oldPsw, String newPsw)
            throws Exception;

    public Page getSubAccountPage(User agentUser, User subAccountUser, Page page)
            throws Exception;

    public List<OrderRecord> querySubAccountRecord(User agentUser,
            User subAccountUser, Page page) throws Exception;
}
