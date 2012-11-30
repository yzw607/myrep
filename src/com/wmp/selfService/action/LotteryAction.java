package com.wmp.selfService.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.Common;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.bean.LotteryNumber;
import com.wmp.selfService.service.ILotteryService;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class LotteryAction extends ActionSupport implements SessionAware
{
    private ILotteryService lotteryService;
    private String sysMsg;
    private List<ActivityInfo> activityList;
    private String activityId;
    private String stencilId;
    private ActivityInfo activity;
    @SuppressWarnings("unchecked")
    private List numberList;
    private List<LotteryNumber> setList;
    private String msgType;
    private LotteryNumber number;

    private Map<String, Object> session;
    
    public String readyForLottery() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        activityList = this.lotteryService.queryActivityInfoList(user);
        
        return SUCCESS;
    }
    
    public String goLottery() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        
        this.numberList = this.lotteryService.getFilteredNumberList(user, activityId);
        this.setList = this.lotteryService.getLotteryNumber(user, activityId);
        return SUCCESS;
    }
    
    public String preViewLottery() throws Exception
    {
        //this.numberList = this.lotteryService.getNumberList(Integer.parseInt(activityId));
        User user = (User)session.get(Common.USERINFO);
        this.setList = this.lotteryService.getLotteryNumber(user, activityId);
        return SUCCESS;
    }
    
    public String settingLottery() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        this.activity = this.lotteryService.getActivity(user, activityId);
        if(null == activity)
        {
            msgType = "error";
            sysMsg = "您所查询的信息不存在，请进入婚礼管理模块刷新页面重试";
            return ERROR;
        }
        
        this.setList = this.lotteryService.getLotteryNumber(user, activityId);
        return SUCCESS;
    }
    
    public String addLotteryNumber() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        
        this.lotteryService.addLotteryNumber(user, number, activityId);

        this.activity = this.lotteryService.getActivity(user, activityId);
        this.setList = this.lotteryService.getLotteryNumber(user, activityId);
        return SUCCESS;
    }
    
    public String delNumber() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        
        this.lotteryService.delNumber(user, number);
        
        this.activity = this.lotteryService.getActivity(user, activityId);
        this.setList = this.lotteryService.getLotteryNumber(user, activityId);
        return SUCCESS;
    }

    public ILotteryService getLotteryService()
    {
        return lotteryService;
    }

    public void setLotteryService(ILotteryService lotteryService)
    {
        this.lotteryService = lotteryService;
    }

    public void setSession(Map<String, Object> map)
    {
        this.session = map;
    }

    public String getSysMsg()
    {
        return sysMsg;
    }

    public List<ActivityInfo> getActivityList()
    {
        return activityList;
    }

    public void setActivityList(List<ActivityInfo> activityList)
    {
        this.activityList = activityList;
    }

    public void setSysMsg(String sysMsg)
    {
        this.sysMsg = sysMsg;
    }

    public String getActivityId()
    {
        return activityId;
    }

    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }

    @SuppressWarnings("unchecked")
    public List getNumberList()
    {
        return numberList;
    }

    @SuppressWarnings("unchecked")
    public void setNumberList(List numberList)
    {
        this.numberList = numberList;
    }

    public String getStencilId()
    {
        return stencilId;
    }

    public void setStencilId(String stencilId)
    {
        this.stencilId = stencilId;
    }

    public ActivityInfo getActivity()
    {
        return activity;
    }

    public void setActivity(ActivityInfo activity)
    {
        this.activity = activity;
    }

    public String getMsgType()
    {
        return msgType;
    }

    public void setMsgType(String msgType)
    {
        this.msgType = msgType;
    }

    public List<LotteryNumber> getSetList()
    {
        return setList;
    }

    public void setSetList(List<LotteryNumber> setList)
    {
        this.setList = setList;
    }

    public LotteryNumber getNumber()
    {
        return number;
    }

    public void setNumber(LotteryNumber number)
    {
        this.number = number;
    }    
}
