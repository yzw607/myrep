package com.wmp.agents.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.agents.service.ISubAccountService;
import com.wmp.common.Common;
import com.wmp.common.bean.Page;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class SubAccountAction extends ActionSupport implements SessionAware
{
    private ISubAccountService subAccountService;
    private Map<String, Object> session;
    private String sysMsg;
    private String menuType;
    private String msgType;
    private String backUrl;
    private int emptyRow;
    private Page page;
    private User subAccount;
    private List<User> subAccountList;
    private int countCost;
    
    public String subAccount() throws Exception
    {
        User user = (User)this.session.get(Common.USERINFO);
        if (null == page)
        {
            page = new Page();
            page.setIndexPage(1);
        }
        
        page = this.subAccountService.getPage(this.subAccount, user, page);
        subAccountList = this.subAccountService.queryAgentsList(this.subAccount, user, page);
        emptyRow = 10 - subAccountList.size();
        
        menuType = "subAccount";
        return SUCCESS;
    }
    
    public String goNewSubAccount() throws Exception
    {
        menuType = "subAccount";
        return SUCCESS;
    }
    
    public String createSubAccount() throws Exception
    {
        User user = (User)this.session.get(Common.USERINFO);
        String tmpCode = this.subAccount.getUserCode();
        
        sysMsg = this.subAccountService.createSubAccount(user, tmpCode);
        menuType = "subAccount";
        if(null != sysMsg && !"".equals(sysMsg))
        {
            return ERROR;
        }
        else
        {
            subAccount = this.subAccountService.getUserInfo(tmpCode);
            countCost = this.subAccountService.getSubAccountCost(tmpCode);
            return SUCCESS;
        }
    }
    
    public String querySubAccountInfo() throws Exception
    {
        int userId = subAccount.getUserId();
        subAccount = this.subAccountService.getUserInfoById(userId);
        User user = (User)this.session.get(Common.USERINFO);
        
        if(null == subAccount || !user.getUserCode().equals(subAccount.getParentCode()))
        {
            menuType = "subAccount";
            msgType = "error";
            sysMsg = "您所查询的信息不存在！";
            backUrl = "subAccount.action";
            return ERROR;
        }
        
        menuType = "subAccount";
        countCost = this.subAccountService.getSubAccountCost(subAccount.getUserCode());
        
        return SUCCESS;
    }
    
    public String initPsw() throws Exception
    {
        int userId = subAccount.getUserId();
        subAccount = this.subAccountService.getUserInfoById(userId);
        User user = (User)this.session.get(Common.USERINFO);
        
        if(null == subAccount || !user.getUserCode().equals(subAccount.getParentCode()))
        {
            menuType = "subAccount";
            msgType = "error";
            sysMsg = "您所查询的信息不存在！";
            backUrl = "subAccount.action";
            return ERROR;
        }
        
        try
        {
            this.subAccountService.initPsw(subAccount);
            sysMsg = "该账户登录密码已经初始化为【say520】";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            sysMsg = "账户密码初始化失败，请联系系统管理员";
        }
        
        return SUCCESS;
    }

    public ISubAccountService getSubAccountService()
    {
        return subAccountService;
    }

    public void setSubAccountService(ISubAccountService subAccountService)
    {
        this.subAccountService = subAccountService;
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

    public User getSubAccount()
    {
        return subAccount;
    }

    public void setSubAccount(User subAccount)
    {
        this.subAccount = subAccount;
    }

    public List<User> getSubAccountList()
    {
        return subAccountList;
    }

    public void setSubAccountList(List<User> subAccountList)
    {
        this.subAccountList = subAccountList;
    }

    public int getCountCost()
    {
        return countCost;
    }

    public void setCountCost(int countCost)
    {
        this.countCost = countCost;
    }
}
