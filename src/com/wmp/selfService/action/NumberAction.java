package com.wmp.selfService.action;

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
import com.wmp.selfService.bean.UserNumber;
import com.wmp.selfService.service.INumberService;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class NumberAction extends ActionSupport implements SessionAware,
        ServletRequestAware, ServletResponseAware
{
    private INumberService numberService;
    private String sysMsg;
    private List<UserNumber> numberList;
    private List<UserNumber> idleList;
    private List<UserNumber> goodList;
    private int num1;
    private int num2;
    private String numberId;
    private String menuType;
    private String numberInfo;

    private Map<String, Object> session;
    HttpServletRequest request;
    HttpServletResponse response;

    public String queryUserNumber() throws Exception
    {
        User user = (User) session.get(Common.USERINFO);
        
//        if(user.getNumberLimit() > 0)
//        {
//            numberList = this.numberService.queryUserNumber(user);
//            
//            if(user.getNumberLimit() - numberList.size() > 0)
//            {
//                idleList = this.numberService.queryIdleNumber("1");
//                
//                String goodNumber = user.getGoodNumber();
//                if(null == goodNumber) goodNumber = "";
//                if(user.getNumberLimit() >= 10 && "".equals(goodNumber.trim()))
//                {
//                    goodList = this.numberService.queryIdleNumber("2");
//                }
//            }
//        }
//        else
//        {
//            numberList = new ArrayList<UserNumber>();
//        }
//        
//        user = this.numberService.getNewestUser(user.getUserId());
//        session.remove(Common.USERINFO);
//        session.put(Common.USERINFO, user);

        
        numberList = this.numberService.queryUserNumber(user);
        user = this.numberService.getNewestUser(user.getUserId());
        String path = request.getContextPath();
        
        int len = numberList.size();
        int num = this.numberService.getNumberCount(user, "2");
        int limit = user.getNumberLimit();
        int limit2 = user.getNumberLimit2();
        this.num1 = limit - len;
        this.num2 = limit2 - num;
        
        if(num1 > 0)
        {
            idleList = this.numberService.queryIdleNumber("1");
        }
        
        if(num2 > 0)
        {
            goodList = this.numberService.queryIdleNumber("2");
        }
        
        StringBuffer sb = new StringBuffer();
        sb.append("您总共可以设置 ").append(limit).append(" 个<a href=\"#A0\">祝福短号&nbsp;<img src=\"");
        sb.append(path).append("/images/help12.png").append(" \"/></a>，其中包含 ");
        sb.append(limit2).append(" 个吉祥祝福短号。目前您已经设置了 ").append(len);
        sb.append(" 个祝福短号，其中有 ").append(num).append(" 个<a href=\"#A1\">吉祥祝福短号&nbsp;<img src=\"");
        sb.append(path).append("/images/help12.png").append(" \"/></a>。<br/>您可以<a href=\"");
        sb.append("javascript:refreshPage()\">刷新<img src=\"").append(path).append("/images/help12.png \"/></a>本页面随机刷新祝福短号。");
        this.numberInfo = sb.toString();
        
        menuType = "userNumber";
        
        return SUCCESS;
    }

    public String setUserNumber() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
//        String[] tmpNumber = request.getParameterValues("tmpNumber");
        
        sysMsg = this.numberService.setUserNumber(user, Integer.parseInt(numberId));
        
        response.setCharacterEncoding("UTF-8");    
        PrintWriter out = response.getWriter();    

        //直接输入响应的内容    
        out.print(sysMsg);    
        out.flush();    
        out.close();  
        
        return null;
    }
    
    public String delNumber() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
//        String[] tmpNumber = request.getParameterValues("tmpNumber");
        
        sysMsg = this.numberService.delNumber(user, Integer.parseInt(numberId));
        
        response.setCharacterEncoding("UTF-8");    
        PrintWriter out = response.getWriter();    

        //直接输入响应的内容    
        out.print(sysMsg);    
        out.flush();    
        out.close();  
        
        return null;
    }

    public INumberService getNumberService()
    {
        return numberService;
    }

    public void setNumberService(INumberService numberService)
    {
        this.numberService = numberService;
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

    public void setSession(Map<String, Object> map)
    {
        this.session = map;
    }

    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }

    public List<UserNumber> getIdleList()
    {
        return idleList;
    }

    public void setIdleList(List<UserNumber> idleList)
    {
        this.idleList = idleList;
    }

    public String getNumberId()
    {
        return numberId;
    }

    public void setNumberId(String numberId)
    {
        this.numberId = numberId;
    }

    public List<UserNumber> getGoodList()
    {
        return goodList;
    }

    public void setGoodList(List<UserNumber> goodList)
    {
        this.goodList = goodList;
    }

    public String getMenuType()
    {
        return menuType;
    }

    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
    }

    public int getNum1()
    {
        return num1;
    }

    public void setNum1(int num1)
    {
        this.num1 = num1;
    }

    public int getNum2()
    {
        return num2;
    }

    public void setNum2(int num2)
    {
        this.num2 = num2;
    }

    public String getNumberInfo()
    {
        return numberInfo;
    }

    public void setNumberInfo(String numberInfo)
    {
        this.numberInfo = numberInfo;
    }
}
