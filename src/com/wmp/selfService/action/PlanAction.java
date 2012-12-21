package com.wmp.selfService.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.Common;
import com.wmp.common.bean.Page;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.bean.UserNumber;
import com.wmp.selfService.service.INumberService;
import com.wmp.selfService.service.IPlanService;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class PlanAction extends ActionSupport implements SessionAware, ServletRequestAware
{
    private IPlanService planService;
    private INumberService numberService;
    private String sysMsg;
    private Map<String, Object> session;
    private HttpServletRequest request;
    private ActivityInfo activityInfo;
    private List<ActivityInfo> activityList;
    private List<UserNumber> numberList;
    private ActivityInfo queryActivity;
    private int emptyRow;
    private Page page;
    private String menuType;
    private List<Object> exportList;
    private String backUrl;
    private String msgType;
    private File file; // 对应文件域
    private String fileFileName; // 前面的File属性的名字 + FileName（固定的）
    private String fileContentType; // 前面的File属性的名字 + Content

    public String planList() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        
        if (null == page)
        {
            page = new Page();
            page.setIndexPage(1);
        }
        
        page = this.planService.getPage(user, queryActivity, page);
        activityList = this.planService.queryActivityInfoList(user, queryActivity, page);
        emptyRow = 10 - activityList.size();
        
        menuType = "weddingManage";
        
        return SUCCESS;
    }
    
    public String goNewActivity() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        
        numberList = this.planService.queryUserNumbers(user.getUserCode());
        return SUCCESS;
    }
    
    public String saveActivity() throws Exception
    {
        User user = (User) session.get(Common.USERINFO);
        this.activityInfo.setUserCode(user.getUserCode());
        this.activityInfo.setSysTel(user.getSysTel());
        this.activityInfo.setPort(user.getPort());
        if(this.activityInfo.getStencilId() == -1 && null != this.file)
        {
            this.activityInfo.setPicFileName(this.fileFileName);
        }

        String contextPath = request.getContextPath();
        this.activityInfo = this.planService.saveActivity(this.activityInfo,
                file, contextPath);

        numberList = this.planService.queryUserNumbers(user.getUserCode());
        String numberId = request.getParameter("userNumberId");
        numberId = (numberId==null || "".equals(numberId))?"0":numberId;
        numberService.updateUserNumber(numberId,activityInfo.getNumber());
        this.sysMsg = "保存成功！";
        return SUCCESS;
    }
    
    public String delActivity() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        String ids = request.getParameter("ids");
        this.planService.delActivityInfo(ids);
        
        numberList = this.planService.queryUserNumbers(user.getUserCode());
        return SUCCESS;
    }
    
    public String delPic() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        activityInfo = this.planService.queryActivityInfo(activityInfo.getId());
        if(null == activityInfo || !activityInfo.getUserCode().equals(user.getUserCode()))
        {
            menuType = "weddingManage";
            backUrl = "planList.action";
            msgType = "error";
            sysMsg = "数据有误，请进入婚礼管理模块刷新页面重试";
            
            return ERROR;
        }
        
        this.planService.delPic(activityInfo);
        activityInfo = this.planService.queryActivityInfo(activityInfo.getId());
        numberList = this.planService.queryUserNumbers(user.getUserCode());
        return SUCCESS;
    }
    
    public String updateActivity() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        
        if(null != this.file)
        {
            this.activityInfo.setPicFileName(this.fileFileName);
        }

        String contextPath = request.getContextPath();
        this.sysMsg = this.planService.updateActivity(user, activityInfo,
                this.file, contextPath);
        activityInfo = this.planService.queryActivityInfo(activityInfo.getId());
        numberList = this.planService.queryUserNumbers(user.getUserCode());
        this.sysMsg = "保存成功！";
        return SUCCESS;
    }
    
    public String viewActivity() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        activityInfo = this.planService.queryActivityInfo(activityInfo.getId());
        
        if(null == activityInfo || !activityInfo.getUserCode().equals(user.getUserCode()))
        {
            String userType = user.getUserType();
            if(!userType.equals(Common.SYS_ADMIN))
            {
                menuType = "weddingManage";
                backUrl = "planList.action";
                msgType = "error";
                sysMsg = "您所查询的婚礼信息不存在，请进入婚礼管理模块刷新页面重试";

                return ERROR;
            }
        }
        
        numberList = this.planService.queryUserNumbers(user.getUserCode());
        return SUCCESS;
    }
    
    public String doActivate() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        if(null != this.file)
        {
            this.activityInfo.setPicFileName(this.fileFileName);
        }

        String contextPath = request.getContextPath();
        sysMsg = this.planService.doActivate(user, activityInfo, this.file, contextPath);
        if("".equals(sysMsg))
        {
            sysMsg = "婚礼确认成功，请到该婚礼举办日期的相应时段进入本系统进行操作！";
        }
        
        activityInfo = this.planService.queryActivityInfo(activityInfo.getId());
        user = this.planService.queryUserInfo(user.getUserId());
        numberList = this.planService.queryUserNumbers(user.getUserCode());
        session.remove(Common.USERINFO);
        session.put(Common.USERINFO, user);
        
        return SUCCESS;
    }
    
    public String doOrderBack() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        sysMsg = this.planService.doOrderBack(user, activityInfo);
        
        activityInfo = this.planService.queryActivityInfo(activityInfo.getId());
        user = this.planService.queryUserInfo(user.getUserId());
        numberList = this.planService.queryUserNumbers(user.getUserCode());
        session.remove(Common.USERINFO);
        session.put(Common.USERINFO, user);
        
        return SUCCESS;
    }
    
    public String dataExport() throws Exception
    {
        this.exportList = this.planService.doExport(activityInfo.getId());
        
        return SUCCESS;
    }
    
    public IPlanService getPlanService()
    {
        return planService;
    }

    public void setPlanService(IPlanService planService)
    {
        this.planService = planService;
    }

    public String getSysMsg()
    {
        return sysMsg;
    }

    public void setSysMsg(String sysMsg)
    {
        this.sysMsg = sysMsg;
    }

    public Map<String, Object> getSession()
    {
        return session;
    }

    public void setSession(Map<String, Object> session)
    {
        this.session = session;
    }

    public List<ActivityInfo> getActivityList()
    {
        return activityList;
    }

    public void setActivityList(List<ActivityInfo> activityList)
    {
        this.activityList = activityList;
    }

    public ActivityInfo getActivityInfo()
    {
        return activityInfo;
    }

    public void setActivityInfo(ActivityInfo activityInfo)
    {
        this.activityInfo = activityInfo;
    }

    public List<UserNumber> getNumberList()
    {
        return numberList;
    }

    public void setNumberList(List<UserNumber> numberList)
    {
        this.numberList = numberList;
    }

    public ActivityInfo getQueryActivity()
    {
        return queryActivity;
    }

    public void setQueryActivity(ActivityInfo queryActivity)
    {
        this.queryActivity = queryActivity;
    }

    public Page getPage()
    {
        return page;
    }

    public void setPage(Page page)
    {
        this.page = page;
    }

    public int getEmptyRow()
    {
        return emptyRow;
    }

    public void setEmptyRow(int emptyRow)
    {
        this.emptyRow = emptyRow;
    }

    public String getMenuType()
    {
        return menuType;
    }

    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
    }

    public List<Object> getExportList()
    {
        return exportList;
    }

    public void setExportList(List<Object> exportList)
    {
        this.exportList = exportList;
    }

    public String getBackUrl()
    {
        return backUrl;
    }

    public void setBackUrl(String backUrl)
    {
        this.backUrl = backUrl;
    }

    public String getMsgType()
    {
        return msgType;
    }

    public void setMsgType(String msgType)
    {
        this.msgType = msgType;
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

	public INumberService getNumberService() {
		return numberService;
	}

	public void setNumberService(INumberService numberService) {
		this.numberService = numberService;
	}
}
