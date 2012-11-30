package com.wmp.agents.service;

import java.util.List;

import com.wmp.common.bean.Page;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.userManage.bean.User;

public interface IAgentsManageService
{
    public Page getPage(User queryAgents, Page page) throws Exception;
    
    public List<User> queryAgentsList(User queryAgents, Page page) throws Exception;
    
    public void changeStatus(ActivityInfo activityInfo) throws Exception;
    
    public ActivityInfo queryActivityInfo(int activityId) throws Exception;
}
