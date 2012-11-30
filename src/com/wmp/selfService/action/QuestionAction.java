package com.wmp.selfService.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.Common;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.bean.Question;
import com.wmp.selfService.service.IQuestionService;
import com.wmp.sms.bean.MsgInBox;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class QuestionAction extends ActionSupport implements SessionAware, ServletResponseAware
{
    private IQuestionService questionService;
    private Map<String, Object> session;
    private HttpServletResponse response;
    private Question question;
    private List<Question> qList;
    private ActivityInfo activity;
    private String activityId;
    private String questionId;
    private String number;
    private String sysMsg;
    private String msgType;
    private String stencilId;
    
    public String viewQuestions() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        
        this.activity = this.questionService.getActivity(user, activityId);
        if(null == activity)
        {
            msgType = "error";
            sysMsg = "您所查询的信息不存在，请进入婚礼管理模块刷新页面重试";
            return ERROR;
        }
        
        number = activity.getNumber();
        if("2".equals(activity.getStatus()))
        {
            return SUCCESS;
        }
        else
        {
            this.qList = this.questionService.getQuestions(user, activityId);
            return "preViewQuestion";
        }
    }
    
    public String settingQuestions() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        this.activity = this.questionService.getActivity(user, activityId);
        if(null == activity)
        {
            msgType = "error";
            sysMsg = "您所查询的信息不存在，请进入婚礼管理模块刷新页面重试";
            return ERROR;
        }
        
        this.qList = this.questionService.getQuestions(user, activityId);
        return SUCCESS;
    }
    
    public String addQuestion() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        
        this.questionService.addQuestion(user, question, activityId);
        this.qList = this.questionService.getQuestions(user, activityId);
        
        return settingQuestions();
    }
    
    public String delQuestion() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        
        this.questionService.delQuestion(user, question);
        this.qList = this.questionService.getQuestions(user, activityId);
        
        return settingQuestions();
    }
    
    public String getNewestQuestion() throws Exception
    {
        String result = null;
        User user = (User) session.get(Common.USERINFO);
        question = this.questionService.getQuestion(questionId, activityId,
                user.getUserCode(), number);
        
        if(null != question)
        {
            JSONObject jo = JSONObject.fromObject(question);
            result = jo.toString();
        }
        else
        {
            result = "";
        }
        
        PrintWriter out = null;
        try
        {
            response.setCharacterEncoding("UTF-8");    
            out = response.getWriter();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }    

        //直接输入响应的内容    
        out.print(result);    
        out.flush();    
        out.close();  
        
        return null;
    }
    
    public String getTheLatestAnswer() throws Exception
    {
        String result = null;
        MsgInBox box  = this.questionService.toGetTheLatestAnswer(number);
        
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
    
    public IQuestionService getQuestionService()
    {
        return questionService;
    }
    public void setQuestionService(IQuestionService questionService)
    {
        this.questionService = questionService;
    }
    public Map<String, Object> getSession()
    {
        return session;
    }
    public void setSession(Map<String, Object> session)
    {
        this.session = session;
    }

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }

    public List<Question> getQList()
    {
        return qList;
    }

    public void setQList(List<Question> list)
    {
        qList = list;
    }

    public ActivityInfo getActivity()
    {
        return activity;
    }

    public void setActivity(ActivityInfo activity)
    {
        this.activity = activity;
    }

    public String getActivityId()
    {
        return activityId;
    }

    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }

    public String getSysMsg()
    {
        return sysMsg;
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

    public String getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(String questionId)
    {
        this.questionId = questionId;
    }

    public HttpServletResponse getResponse()
    {
        return response;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }

    public String getStencilId()
    {
        return stencilId;
    }

    public void setStencilId(String stencilId)
    {
        this.stencilId = stencilId;
    }
}
