package com.wmp.sms.service;

import java.util.List;

import com.wmp.common.bean.UploadPic;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.bean.Stencil;
import com.wmp.selfService.bean.UserNumber;
import com.wmp.sms.bean.MsgInBox;
import com.wmp.userManage.bean.User;

public interface ISmsService
{
    public List<UserNumber> queryUserNumber(User user);
    
    public MsgInBox toGetTheLatestSMS(String number, String activityId) throws Exception;
    
    public String getUserRemainingTimeFlag(User user) throws Exception;
    
    public void startTiming(User user, String number) throws Exception;
    
    public void endTiming(User user, String number) throws Exception;
    
    public ActivityInfo queryActivityInfo(int id) throws Exception;
    
    public List<UploadPic> queryPicList(String userCode, String activityId);
    
    public Stencil queryStencil(String stencilId) throws Exception;
}
