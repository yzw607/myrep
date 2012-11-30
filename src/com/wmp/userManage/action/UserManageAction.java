package com.wmp.userManage.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.Common;
import com.wmp.common.bean.Page;
import com.wmp.userManage.bean.User;
import com.wmp.userManage.service.IUserManageService;

@SuppressWarnings("serial")
public class UserManageAction extends ActionSupport implements SessionAware
{
    private static final String tmp1 = "houhai";
    private static final String tmp2 = "fanli";
    private IUserManageService userManageService;
    private Map<String, Object> session;
    private String username;
    private String password;
    private String sysMsg;
    private User user;
    private List<User> userList;
    private Page page;
    private int recharge;
    private int handsel;

    /**
     * 用户从登陆页面登录
     * @return
     * @throws Exception
     */
    public String doLogin() throws Exception
    {
    	System.out.println("111111111111111111111111111: "+username+","+password);
        if(!username.equals(tmp1) || !password.equals(tmp2))
        {
            this.sysMsg = "用户名或密码错误";
            return ERROR;
        }
        else
        {
            session.put("admin", "true");
            return SUCCESS;
        }
        
    }
    
    /**
     * 查询用户列表
     * @return
     * @throws Exception
     */
    public String queryUser() throws Exception
    {
        int currentPage = 1;
        int lineSize = Common.LINESIZE;
        int allRecorders = 0;
        
        StringBuffer sb = new StringBuffer();
        if(null != user)
        {
            if(null != user.getCompanyName() && !"".equals(user.getCompanyName().trim()))
            {
                sb.append("o.companyName like '%").append(user.getCompanyName().trim()).append("%'");
            }
            
            if(null != user.getUserName() && !"".equals(user.getUserName().trim()))
            {
                sb.append("o.userName like '%").append(user.getUserName().trim()).append("%'");
            }
        }
        
//        page = Common.initPage(page);
        page = this.userManageService.countUsers(page, sb.toString());
        userList = this.userManageService.queryUserList(page, sb.toString());

        return SUCCESS;
    }
    
    /**
     * 查看用户信息
     * @return
     * @throws Exception
     */
    public String showUser() throws Exception
    {
        this.user = this.userManageService.showUser(this.user.getUserId());
        
        return SUCCESS;
    }
    
    /**
     * 更新用户信息
     * @return
     * @throws Exception
     */
    public String recharge() throws Exception
    {
//        User tmpUser = this.userManageService.showUser(this.user.getUserId());
//        tmpUser.setUserType(this.user.getUserType());
//        tmpUser.setLevel(this.user.getLevel());
//        tmpUser.setCounts(this.user.getCounts());
//        
//        if(null == this.user.getEndDate())
//        {
//            tmpUser.setEndDate(null);
//        }
//        else
//        {
//            tmpUser.setEndDate(Common.changeDateFormat(this.user.getEndDate()));
//        }
//        
//        
//        this.userManageService.updateUser(tmpUser);
        
        this.userManageService.recharge(this.user.getUserId(), recharge, handsel);
        this.user = this.userManageService.showUser(this.user.getUserId());
        
        return SUCCESS;
    }
    
    public String getSysMsg()
    {
        return sysMsg;
    }

    public void setSysMsg(String sysMsg)
    {
        this.sysMsg = sysMsg;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setSession(Map<String, Object> map)
    {
        this.session = map;
    }

    public IUserManageService getUserManageService()
    {
        return userManageService;
    }

    public void setUserManageService(IUserManageService userManageService)
    {
        this.userManageService = userManageService;
    }

    public List<User> getUserList()
    {
        return userList;
    }

    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }

    public Map<String, Object> getSession()
    {
        return session;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
    

    public Page getPage()
    {
        return page;
    }

    public void setPage(Page page)
    {
        this.page = page;
    }

    public int getRecharge()
    {
        return recharge;
    }

    public void setRecharge(int recharge)
    {
        this.recharge = recharge;
    }

    public int getHandsel()
    {
        return handsel;
    }

    public void setHandsel(int handsel)
    {
        this.handsel = handsel;
    }
}
