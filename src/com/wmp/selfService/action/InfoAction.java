package com.wmp.selfService.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.Common;
import com.wmp.common.bean.Page;
import com.wmp.selfService.bean.OrderRecord;
import com.wmp.selfService.service.IInfoService;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class InfoAction extends ActionSupport implements SessionAware
{
    private User user;
    private Map<String, Object> session;
    private IInfoService infoService;
    private List<OrderRecord> recordList;
    private String menuType;
    private int emptyRow;
    private Page page;
    private String backUrl;
    private String sysMsg;
    private String msgType;
    private String oldPsw;
    private String newPsw;

    public String queryInfo() throws Exception
    {
        User tmpUser = (User)session.get(Common.USERINFO);
        this.user = this.infoService.queryInfo(tmpUser.getUserId());
        session.remove(Common.USERINFO);
        session.put(Common.USERINFO, user);
        menuType = "myAccount";
        
        return SUCCESS;
    }
    
    public String queryRecord() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        
        if (null == page)
        {
            page = new Page();
            page.setIndexPage(1);
        }
        
        page = this.infoService.getPage(user, page);
        recordList = this.infoService.queryRecord(user.getUserCode(), page);
        emptyRow = 10 - recordList.size();
        menuType = "queryRecord";
        
        user = this.infoService.queryInfo(user.getUserId());
        session.remove(user);
        session.put(Common.USERINFO, user);
        
        return SUCCESS;
    }
    
    public String goUpdateInfo() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        
        this.user = this.infoService.queryInfo(user.getUserId());
        menuType = "myAccount";
        return SUCCESS;
    }
    
    public String doUpdateInfo() throws Exception
    {
        this.infoService.updateUserInfo(this.user);
        
        menuType = "myAccount";
        backUrl = "goUpdateInfo.action";
        msgType = "success";
        sysMsg = "操作成功";
        return SUCCESS;
    }
    
    public String goUpdatePsw() throws Exception
    {
        menuType = "myAccount";
        return SUCCESS;
    }
    
    public String doUpdatePsw() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        
        if(!this.infoService.updateUserPsw(user, oldPsw, newPsw))
        {
            msgType = "error";
            sysMsg = "修改失败，您所输入的旧密码不正确";
        }
        else
        {
            msgType = "success";
            sysMsg = "操作成功";
        }
        
        menuType = "myAccount";
        backUrl = "goUpdatePsw.action";
        return SUCCESS;
    }
    
    public String querySubAccountRecord() throws Exception
    {
        User agentUser = (User)session.get(Common.USERINFO);
        user = this.infoService.queryInfo(user.getUserId());
        
        if(!agentUser.getUserCode().equals(user.getParentCode()))
        {
            msgType = "error";
            sysMsg = "您所查询的信息不存在";
            menuType = "subAccount";
            backUrl = "subAccount.action";
            return ERROR;
        }
        
        if (null == page)
        {
            page = new Page();
            page.setIndexPage(1);
        }
        
        page = this.infoService.getSubAccountPage(agentUser, user, page);
        recordList = this.infoService.querySubAccountRecord(agentUser, user, page);
        emptyRow = 10 - recordList.size();
        menuType = "subAccount";
        
        return SUCCESS;
    }
    
    public IInfoService getInfoService()
    {
        return infoService;
    }

    public void setInfoService(IInfoService infoService)
    {
        this.infoService = infoService;
    }
    
    public User getUser()
    {
        return user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }
    public Map<String, Object> getSession()
    {
        return session;
    }
    public void setSession(Map<String, Object> session)
    {
        this.session = session;
    }

    public List<OrderRecord> getRecordList()
    {
        return recordList;
    }

    public String getMenuType()
    {
        return menuType;
    }

    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
    }

    public void setRecordList(List<OrderRecord> recordList)
    {
        this.recordList = recordList;
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

    public String getBackUrl()
    {
        return backUrl;
    }

    public void setBackUrl(String backUrl)
    {
        this.backUrl = backUrl;
    }

    public String getSysMsg()
    {
        return sysMsg;
    }

    public void setSysMsg(String sysMsg)
    {
        this.sysMsg = sysMsg;
    }

    public String getMsgType()
    {
        return msgType;
    }

    public void setMsgType(String msgType)
    {
        this.msgType = msgType;
    }

    public String getOldPsw()
    {
        return oldPsw;
    }

    public void setOldPsw(String oldPsw)
    {
        this.oldPsw = oldPsw;
    }

    public String getNewPsw()
    {
        return newPsw;
    }

    public void setNewPsw(String newPsw)
    {
        this.newPsw = newPsw;
    }
}
