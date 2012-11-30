package com.wmp.selfService.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.bean.Page;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.bean.UserNumber;
import com.wmp.selfService.service.IActService;

@SuppressWarnings("serial")
public class ActAction extends ActionSupport implements SessionAware,
        ServletRequestAware
{
    private IActService actService;
    private String sysMsg;
    private Map<String, Object> session;
    private HttpServletRequest request;
    private ActivityInfo activityInfo;
    private List<ActivityInfo> activityList;
    private List<UserNumber> numberList;
    private ActivityInfo queryActivity;
    private int emptyRow;
    private Page page;
    private String menuType;
    private List<Object> exportList;
    private String backUrl;
    private String msgType;
    private java.util.List<File> pic;
    private java.util.List<String> picFileName;
    private java.util.List<String> picContentType;
    
    public String newAct() throws Exception
    {
        
        return SUCCESS;
    }

    public IActService getActService()
    {
        return actService;
    }

    public void setActService(IActService actService)
    {
        this.actService = actService;
    }

    public String getSysMsg()
    {
        return sysMsg;
    }

    public void setSysMsg(String sysMsg)
    {
        this.sysMsg = sysMsg;
    }

    public Map<String, Object> getSession()
    {
        return session;
    }

    public void setSession(Map<String, Object> session)
    {
        this.session = session;
    }

    public HttpServletRequest getRequest()
    {
        return request;
    }
    
    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public ActivityInfo getActivityInfo()
    {
        return activityInfo;
    }

    public void setActivityInfo(ActivityInfo activityInfo)
    {
        this.activityInfo = activityInfo;
    }

    public List<ActivityInfo> getActivityList()
    {
        return activityList;
    }

    public void setActivityList(List<ActivityInfo> activityList)
    {
        this.activityList = activityList;
    }

    public List<UserNumber> getNumberList()
    {
        return numberList;
    }

    public void setNumberList(List<UserNumber> numberList)
    {
        this.numberList = numberList;
    }

    public ActivityInfo getQueryActivity()
    {
        return queryActivity;
    }

    public void setQueryActivity(ActivityInfo queryActivity)
    {
        this.queryActivity = queryActivity;
    }

    public int getEmptyRow()
    {
        return emptyRow;
    }

    public void setEmptyRow(int emptyRow)
    {
        this.emptyRow = emptyRow;
    }

    public Page getPage()
    {
        return page;
    }

    public void setPage(Page page)
    {
        this.page = page;
    }

    public String getMenuType()
    {
        return menuType;
    }

    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
    }

    public List<Object> getExportList()
    {
        return exportList;
    }

    public void setExportList(List<Object> exportList)
    {
        this.exportList = exportList;
    }

    public String getBackUrl()
    {
        return backUrl;
    }

    public void setBackUrl(String backUrl)
    {
        this.backUrl = backUrl;
    }

    public String getMsgType()
    {
        return msgType;
    }

    public void setMsgType(String msgType)
    {
        this.msgType = msgType;
    }

    public java.util.List<File> getPic()
    {
        return pic;
    }

    public void setPic(java.util.List<File> pic)
    {
        this.pic = pic;
    }

    public java.util.List<String> getPicFileName()
    {
        return picFileName;
    }

    public void setPicFileName(java.util.List<String> picFileName)
    {
        this.picFileName = picFileName;
    }

    public java.util.List<String> getPicContentType()
    {
        return picContentType;
    }

    public void setPicContentType(java.util.List<String> picContentType)
    {
        this.picContentType = picContentType;
    }

}
