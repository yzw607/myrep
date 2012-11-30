package com.wmp.test.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.Common;
import com.wmp.test.bean.SMSTest;
import com.wmp.test.service.ISMSTestService;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class SMSTestAction extends ActionSupport implements SessionAware
{
    private ISMSTestService SMSTestService;
    private Map<String, Object> session;
    private String sysMsg;
    private SMSTest SMSTest;

    public String smsTest() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        String userCode = user.getUserCode();
        if (userCode.equals("aaaa") || userCode.equals("yuliangjun")
                || userCode.equals("侯海") || userCode.equals("忠诚卫士")
                || userCode.equals("樊莉"))
        {
            this.SMSTestService.testSMS(this.SMSTest);
            this.sysMsg = "发送成功！";
            return SUCCESS;
        }
        
        return ERROR;
    }
    
    public SMSTest getSMSTest()
    {
        return SMSTest;
    }

    public void setSMSTest(SMSTest test)
    {
        SMSTest = test;
    }

    public ISMSTestService getSMSTestService()
    {
        return SMSTestService;
    }

    public void setSMSTestService(ISMSTestService testService)
    {
        SMSTestService = testService;
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
}
