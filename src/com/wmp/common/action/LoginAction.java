package com.wmp.common.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.Common;
import com.wmp.common.service.ILoginService;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements SessionAware 
{
    private User user;
    private Map<String, Object> session;
    private ILoginService loginService;
    private List<ActivityInfo> activityList;
    private String sysMsg;
    private String menuType;
    private String msgType;
    private String backUrl;
    private int emptyRow;

    /**
     * 用户从登陆页面登录
     * @return
     * @throws Exception
     */
    public String doLogin() throws Exception
    {
        user = this.loginService.findUserByUsercode(this.user);
        if(null == user)
        {
            this.sysMsg = "用户名或密码错误";
            return ERROR;
        }
        
        if("0".equals(user.getStatus()))
        {
            this.sysMsg = "您的信息还未审核，请您耐心等待！";
            return ERROR;
        }
        
        String email = user.getEmail();
        this.session.put(Common.USERINFO, user);
        if(null == email || "".equals(email.trim()))
        {
            return "settingInfo";
        }
        else
        {
            this.sysMsg = "登录成功";

            this.activityList = this.loginService.querySureActivityInfoList(user);
            emptyRow = 5 - activityList.size();
//            this.session.put("count", "0");
            menuType = "home";
            
            return SUCCESS;
        }
    }
    
    public String goHome() throws Exception
    {
        User user = (User)this.session.get(Common.USERINFO);
        this.activityList = this.loginService.querySureActivityInfoList(user);
        emptyRow = 5 - activityList.size();
        menuType = "home";
        return SUCCESS;
    }
    
    public String getForgetPsw() throws Exception
    {
        User user = this.loginService.findUser(this.user.getUserCode());
        if(null == user)
        {
            this.sysMsg = "您所输入的用户名不存在";
            msgType = "error";
            return SUCCESS;
        }
        
        String parentCode = user.getParentCode();
        if(null != parentCode && !"".equals(parentCode.trim()))
        {
            String mail = user.getEmail();
            if (null == mail || "".equals(mail.trim()))
            {
                this.sysMsg = "您还未设置邮箱信息，请您联系您的代理商帮助您进行密码初始化";
                msgType = "info";
                return SUCCESS;
            }
        }
        
        String tmpEmail = this.loginService.getForgetPsw(user.getUserCode());
        if(null == tmpEmail)
        {
            this.sysMsg = "您所输入的用户名不存在";
            msgType = "error";
        }
        else if("".equals(tmpEmail.trim()))
        {
            this.sysMsg = "您还未设置邮箱信息，请您联系您的代理商帮助您进行密码初始化";
            msgType = "info";
        }
        else
        {
            this.sysMsg = "您的登录密码已被重置，并发送至：" + tmpEmail;
            msgType = "success";
        }
        
        return SUCCESS;
    }
    
    public String updateUserInfo() throws Exception
    {
        user = this.loginService.updateUserInfo(this.user);
        this.session.remove(Common.USERINFO);
        this.session.put(Common.USERINFO, user);
        
        this.activityList = this.loginService.querySureActivityInfoList(user);
        emptyRow = 5 - activityList.size();
        menuType = "home";
        
        return SUCCESS;
    }
    
    public String loginOut() throws Exception
    {
        session.remove(Common.USERINFO);
        
        return SUCCESS;
    }
    
    public ILoginService getLoginService()
    {
        return loginService;
    }

    public void setLoginService(ILoginService loginService)
    {
        this.loginService = loginService;
    }
    
    public void setSession(Map<String, Object> map)
    {
        this.session = map;
    }
    
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getSysMsg()
    {
        return sysMsg;
    }

    public String getMenuType()
    {
        return menuType;
    }


    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
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

    public String getBackUrl()
    {
        return backUrl;
    }

    public void setBackUrl(String backUrl)
    {
        this.backUrl = backUrl;
    }

    public Map<String, Object> getSession()
    {
        return session;
    }

    public List<ActivityInfo> getActivityList()
    {
        return activityList;
    }

    public void setActivityList(List<ActivityInfo> activityList)
    {
        this.activityList = activityList;
    }

    public int getEmptyRow()
    {
        return emptyRow;
    }

    public void setEmptyRow(int emptyRow)
    {
        this.emptyRow = emptyRow;
    }
    
}
