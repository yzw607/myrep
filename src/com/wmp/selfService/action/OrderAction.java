package com.wmp.selfService.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.bean.Page;
import com.wmp.selfService.service.IOrderService;

@SuppressWarnings("serial")
public class OrderAction extends ActionSupport implements SessionAware
{
    private IOrderService orderService;
    private Map<String, Object> session;
    private String sysMsg;
    private int emptyRow;
    private Page page;
    
//    public String orderService() throws Exception
//    {
//        User user = (User)session.get(Common.USERINFO);
//        this.sysMsg = this.orderService.orderService(user);
//        
//        if(null == sysMsg || "".equals(sysMsg.trim()))
//        {
//            //订购成功
//            sysMsg = "订购成功，若未能正常服务请重新登录本系统！";
//            user.setActive(true);
//            user.setCounts(user.getCounts() - 1);
//            session.put(Common.USERINFO, user);
//        }
//        
//        return SUCCESS;
//    }

    public IOrderService getOrderService()
    {
        return orderService;
    }

    public void setOrderService(IOrderService orderService)
    {
        this.orderService = orderService;
    }

    public void setSession(Map<String, Object> map)
    {
        this.session = map;
    }

    public String getSysMsg()
    {
        return sysMsg;
    }

    public void setSysMsg(String sysMsg)
    {
        this.sysMsg = sysMsg;
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
}
