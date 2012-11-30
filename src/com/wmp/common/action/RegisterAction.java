package com.wmp.common.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.Common;
import com.wmp.common.service.IRegisterService;
import com.wmp.selfService.bean.UserNumber;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class RegisterAction extends ActionSupport implements SessionAware,
        ServletRequestAware, ServletResponseAware
{
    private User user;
    private Map<String, Object> session;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private IRegisterService registerService;
    private String sysMsg;
    private List<UserNumber> numberList;
    private String menuType;
    private File file;
    private String fileFileName;
    private String fileContentType;

    public String doRegister() throws Exception
    {
        String tmpCode = this.user.getUserCode();
        if(null == tmpCode) tmpCode = "";
        
        if("system".equals(tmpCode.trim()) || "preemption".equals(tmpCode.trim()))
        {
            this.sysMsg = "该用户名已存在";
            return ERROR;
        }
        
        String contextPath = request.getContextPath();
        User user = this.registerService.registerUser(this.user, file, contextPath);
        if(null == user)
        {
            this.sysMsg = "该用户名已存在";
            return ERROR;
        }
        else
        {
            this.sysMsg = "注册成功";
            
            if(Common.PERSONAL.equals(user.getUserType()))
            {
                this.session.put(Common.USERINFO, user);
                numberList = this.registerService.getUserNumbers(tmpCode);
                return SUCCESS;
            }
            else
            {
                return "success2";
            }
        }
    }
    
    public String checkName() throws Exception
    {
        sysMsg = this.registerService.checkUserCode(user.getUserCode());
        
        response.setCharacterEncoding("UTF-8");    
        PrintWriter out = response.getWriter();    

        //直接输入响应的内容    
        out.print(sysMsg);    
        out.flush();    
        out.close();  
        
        return null;
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
    
    public IRegisterService getRegisterService()
    {
        return registerService;
    }

    public void setRegisterService(IRegisterService registerService)
    {
        this.registerService = registerService;
    }

    public String getSysMsg()
    {
        return sysMsg;
    }

    public void setSysMsg(String sysMsg)
    {
        this.sysMsg = sysMsg;
    }

    public List<UserNumber> getNumberList()
    {
        return numberList;
    }

    public void setNumberList(List<UserNumber> numberList)
    {
        this.numberList = numberList;
    }

    public String getMenuType()
    {
        return menuType;
    }

    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
    }

    public HttpServletResponse getResponse()
    {
        return response;
    }

    public void setResponse(HttpServletResponse response)
    {
        this.response = response;
    }

    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public String getFileFileName()
    {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName)
    {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType()
    {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType)
    {
        this.fileContentType = fileContentType;
    }
    
    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }
}
