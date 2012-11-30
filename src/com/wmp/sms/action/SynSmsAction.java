package com.wmp.sms.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.Common;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.sms.bean.MsgInBox;
import com.wmp.sms.service.ISynSmsService;

@SuppressWarnings("serial")
public class SynSmsAction extends ActionSupport implements ServletResponseAware
{
    private ActivityInfo activityInfo;
    private String hl;
    private String lastSmsId;
    private String number;
    private String result;
    private String recordTime;
    private HttpServletResponse response;
    private ISynSmsService synSmsService;
    
    public String woaishuo() throws Exception
    {
        int activityId = 0;
        if(null == hl || "".equals(hl.trim()))
        {
            result = "2";
            return ERROR;
        }
        
        try
        {
            activityId = Integer.parseInt(hl);
        }
        catch (Exception e1)
        {
            result = "2";
            return ERROR;
        }
        
        
        activityInfo = this.synSmsService.queryActivityInfo(activityId);
        if(null == activityInfo)
        {
            result = "2";
            return ERROR;
        }
        
        int status = 0;
        try
        {
            status = Integer.parseInt(activityInfo.getStatus());
        }
        catch (RuntimeException e)
        {
            result = "2";
            return ERROR;
        }
        
        if(status == 1)
        {
            result = "1";
            return ERROR;
        }
        else if(status == 3)
        {
            result = "3";
            return ERROR;
        }
        else if(status == 2)
        {
            this.recordTime = Common.getSysTimeStr();
            return SUCCESS;
        }
        else
        {
            result = "2";
            return ERROR;
        }
    }
    
    public String loadSynSms() throws Exception
    {
        MsgInBox box = null;
        try
        {
            box = this.synSmsService.getReadedSMS(number, lastSmsId, recordTime);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        if(null != box)
        {
            JSONObject jo = JSONObject.fromObject(box);
            result = jo.toString();
        }
        else
        {
            result = "";
        }  
        
        
        response.setCharacterEncoding("UTF-8");    
        PrintWriter out = response.getWriter();    

        //直接输入响应的内容    
        out.print(result);    
        out.flush();    
        out.close();  
        
        return null;
    }

    public ActivityInfo getActivityInfo()
    {
        return activityInfo;
    }

    public void setActivityInfo(ActivityInfo activityInfo)
    {
        this.activityInfo = activityInfo;
    }

    public ISynSmsService getSynSmsService()
    {
        return synSmsService;
    }

    public void setSynSmsService(ISynSmsService synSmsService)
    {
        this.synSmsService = synSmsService;
    }

    public HttpServletResponse getResponse()
    {
        return response;
    }

    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }

    public String getHl()
    {
        return hl;
    }

    public void setHl(String hl)
    {
        this.hl = hl;
    }

    public String getLastSmsId()
    {
        return lastSmsId;
    }

    public String getRecordTime()
    {
        return recordTime;
    }

    public void setRecordTime(String recordTime)
    {
        this.recordTime = recordTime;
    }

    public void setLastSmsId(String lastSmsId)
    {
        this.lastSmsId = lastSmsId;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }
}
