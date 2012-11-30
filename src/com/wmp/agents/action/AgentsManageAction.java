package com.wmp.agents.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.agents.service.IAgentsManageService;
import com.wmp.common.bean.Page;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class AgentsManageAction extends ActionSupport implements SessionAware
{
    private IAgentsManageService agentsManageService;
    private Map<String, Object> session;
    private String sysMsg;
    private String menuType;
    private String msgType;
    private String backUrl;
    private int emptyRow;
    private Page page;
    private User agents;
    private List<User> agentsList;
    private ActivityInfo activityInfo;

    public String agentsList() throws Exception
    {
        if (null == page)
        {
            page = new Page();
            page.setIndexPage(1);
        }
        
        page = this.agentsManageService.getPage(this.agents, page);
        agentsList = this.agentsManageService.queryAgentsList(this.agents, page);
        emptyRow = 10 - agentsList.size();
        
        menuType = "accountManage";
        return SUCCESS;
    }
    
    public String changeStatus() throws Exception
    {
        this.agentsManageService.changeStatus(activityInfo);
        
        activityInfo = this.agentsManageService.queryActivityInfo(activityInfo.getId());
        
        return SUCCESS;
    }
    
    public IAgentsManageService getAgentsManageService()
    {
        return agentsManageService;
    }

    public void setAgentsManageService(IAgentsManageService agentsManageService)
    {
        this.agentsManageService = agentsManageService;
    }

    public Map<String, Object> getSession()
    {
        return session;
    }

    public void setSession(Map<String, Object> session)
    {
        this.session = session;
    }

    public String getSysMsg()
    {
        return sysMsg;
    }

    public void setSysMsg(String sysMsg)
    {
        this.sysMsg = sysMsg;
    }

    public String getMenuType()
    {
        return menuType;
    }

    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
    }

    public String getMsgType()
    {
        return msgType;
    }

    public void setMsgType(String msgType)
    {
        this.msgType = msgType;
    }

    public String getBackUrl()
    {
        return backUrl;
    }

    public void setBackUrl(String backUrl)
    {
        this.backUrl = backUrl;
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

    public User getAgents()
    {
        return agents;
    }

    public void setAgents(User agents)
    {
        this.agents = agents;
    }

    public List<User> getAgentsList()
    {
        return agentsList;
    }

    public void setAgentsList(List<User> agentsList)
    {
        this.agentsList = agentsList;
    }

    public ActivityInfo getActivityInfo()
    {
        return activityInfo;
    }

    public void setActivityInfo(ActivityInfo activityInfo)
    {
        this.activityInfo = activityInfo;
    }
}
