package com.wmp.sms.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.Common;
import com.wmp.common.bean.UploadPic;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.bean.Stencil;
import com.wmp.sms.bean.MsgInBox;
import com.wmp.sms.service.ISmsService;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class SmsAction extends ActionSupport implements SessionAware, ServletResponseAware
{
    private ISmsService smsService;
    private Map<String, Object> session;
    private HttpServletResponse response;
    private ActivityInfo activityInfo;
    private String restTime;
    private String number;
    private String activityId;
    private String result;
    private String sysMsg;
    private String stencilId;
    private List<UploadPic> upList;
    private String picPath;
    private Stencil stencil;
    
    public String goSms() throws Exception
    {
        return SUCCESS;
    }
    
    /**
     * 后台加载短信信息
     * @return
     * @throws Exception
     */
    public String loadSMS() throws Exception
    {
/*        int count = Integer.parseInt((String)session.get("count"));
        if(count == 0)
        {
            count = 10;
            session.remove("count");
            session.put("count", String.valueOf(count));
            
            User user = (User)session.get(Common.USERINFO);
            userRemainingTimeFlag = this.smsService.getUserRemainingTimeFlag(user);
            if(Common.LESS.equals(userRemainingTimeFlag))
            {
                result = Common.LESS;
            }
            else if(Common.EMPTY.equals(userRemainingTimeFlag))
            {
                result = Common.EMPTY;
            }
            else
            {
                session.put("count", String.valueOf(count));
                result = "";
            }
        }
        else
        {
            MsgInBox box = null;
            try
            {
                box = this.smsService.getTheLatestSMS(number);
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
            
            count--;
            session.put("count", String.valueOf(count));
        }
*/
        
        MsgInBox box = null;
        try
        {
            box = this.smsService.toGetTheLatestSMS(number, activityId);
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
    
    public String showSMSPage() throws Exception
    {
        activityInfo = this.smsService.queryActivityInfo(activityInfo.getId());
        
        User user = (User)session.get(Common.USERINFO);
        if(!activityInfo.getUserCode().equals(user.getUserCode()))
        {
            this.sysMsg = "您所查询的婚礼信息不存在！";
            return "number_error";
        }
        
        if(!"2".equals(activityInfo.getStatus()))
        {
            this.sysMsg = "该婚礼尚未激活或未到启动时段！";
            return "number_error";
        }

        String flag = activityInfo.getFlag();
        if(null == flag || "".equals(flag.trim()))
        {
            String sysTime = Common.getSysTimeStr();
            String sysDate = Common.getSysDateStr();
            String endTime = null;
            int hour = Integer.parseInt(sysTime.substring(11, 13));

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = df.parse(sysTime);
            Date endDate = null;
            long l = 0;

            if (hour >= 6 && hour < 15)
            {
                endTime = sysDate + " 15:00:00";
                endDate = df.parse(endTime);
                l = endDate.getTime() - startDate.getTime();
            }
            else if (hour >= 15 && hour < 24)
            {
                endTime = sysDate + " 24:00:00";
                endDate = df.parse(endTime);
                l = endDate.getTime() - startDate.getTime();
            }
            else
            {
                endTime = sysDate + " 06:00:00";
                endDate = df.parse(endTime);

                // if(hour < 8)
                // {
                // l = endDate.getTime()- startDate.getTime();
                // }
                // else
                // {
                // l = endDate.getTime()- startDate.getTime() + 86400000;
                //            }

                l = endDate.getTime() - startDate.getTime();
            }

            this.restTime = String.valueOf(l);
        }
        
//        if(null != isWidth && "true".equals(isWidth.trim()))
//        {
//            upList = this.smsService.queryPicList(user.getUserCode(), String.valueOf(activityInfo.getId()));
//            picPath = "/upload/" + user.getUserCode() + "/" + String.valueOf(activityInfo.getId()) + "/thumbs/"; 
//                
//            return "success_width";
//        }
//        else
//        {
//            return SUCCESS;
//        }
        
        upList = this.smsService.queryPicList(user.getUserCode(), String.valueOf(activityInfo.getId()));
        picPath = "/upload/" + user.getUserCode() + "/" + String.valueOf(activityInfo.getId()) + "/thumbs/"; 
            
        String returnPath = null;
        if("-1".equals(stencilId))
        {
            returnPath = "sms-1";
        }
        else if("-2".equals(stencilId))
        {
            returnPath = "sms-2";
        }
        else
        {
            stencil = this.smsService.queryStencil(stencilId);
            returnPath = stencil.getDirections();
        }
        
        return returnPath;
    }
    
    public String preViewSMSPage() throws Exception
    {
        activityInfo = this.smsService.queryActivityInfo(activityInfo.getId());
        
        String returnPath = null;
        stencil = this.smsService.queryStencil(stencilId);
        returnPath = stencil.getDirections();
        
        User user = (User)session.get(Common.USERINFO);
        upList = this.smsService.queryPicList(user.getUserCode(), String.valueOf(activityInfo.getId()));
        picPath = "/upload/" + user.getUserCode() + "/" + String.valueOf(activityInfo.getId()) + "/thumbs/";
        
        return returnPath;
    }
    
    public String startTiming() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        this.smsService.startTiming(user, number);
        return SUCCESS;   
    }
    
    public String endTiming() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        this.smsService.endTiming(user, number);
        return SUCCESS;
    }

    public void setSession(Map<String, Object> map)
    {
        this.session = map;
    }

    public ISmsService getSmsService()
    {
        return smsService;
    }

    public void setSmsService(ISmsService smsService)
    {
        this.smsService = smsService;
    }

    public String getSysMsg()
    {
        return sysMsg;
    }

    public void setSysMsg(String sysMsg)
    {
        this.sysMsg = sysMsg;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }
    
    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public ActivityInfo getActivityInfo()
    {
        return activityInfo;
    }

    public void setActivityInfo(ActivityInfo activityInfo)
    {
        this.activityInfo = activityInfo;
    }

    public String getRestTime()
    {
        return restTime;
    }

    public void setRestTime(String restTime)
    {
        this.restTime = restTime;
    }

    public String getActivityId()
    {
        return activityId;
    }

    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }
    
    public List<UploadPic> getUpList()
    {
        return upList;
    }

    public void setUpList(List<UploadPic> upList)
    {
        this.upList = upList;
    }

    public String getPicPath()
    {
        return picPath;
    }

    public void setPicPath(String picPath)
    {
        this.picPath = picPath;
    }

    public String getStencilId()
    {
        return stencilId;
    }

    public void setStencilId(String stencilId)
    {
        this.stencilId = stencilId;
    }

    public Stencil getStencil()
    {
        return stencil;
    }

    public void setStencil(Stencil stencil)
    {
        this.stencil = stencil;
    }
}
