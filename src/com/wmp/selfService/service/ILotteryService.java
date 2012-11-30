package com.wmp.selfService.service;

import java.util.List;

import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.bean.LotteryNumber;
import com.wmp.userManage.bean.User;

public interface ILotteryService
{
    public List<ActivityInfo> queryActivityInfoList(User user) throws Exception;
    
    @SuppressWarnings("unchecked")
    public List getNumberList(int activityInfoId) throws Exception;
    
    @SuppressWarnings("unchecked")
    public List getFilteredNumberList(User user, String activityId) throws Exception;
    
    public ActivityInfo getActivity(User user, String activityId) throws Exception;
    
    public List<LotteryNumber> getLotteryNumber(User user, String activityId) throws Exception;
    
    public void addLotteryNumber(User user, LotteryNumber number, String activityId) throws Exception;
    
    public void delNumber(User user, LotteryNumber number) throws Exception;
}
