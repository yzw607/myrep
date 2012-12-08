package com.wmp.sendAll.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.Common;
import com.wmp.common.bean.Page;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.service.IPlanService;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class QueryAllAction extends ActionSupport implements SessionAware, ServletRequestAware{

    private Map<String, Object> session;
    private HttpServletRequest request;
    private IPlanService planService;
    private List<ActivityInfo> activityList;
    private ActivityInfo queryActivity;
    
    private int emptyRow;
    private Page page;
    private String menuType;
    
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
        
        menuType = "sendAll";
        return SUCCESS;
    }
    
    
	public List<ActivityInfo> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ActivityInfo> activityList) {
		this.activityList = activityList;
	}

	public ActivityInfo getQueryActivity() {
		return queryActivity;
	}

	public void setQueryActivity(ActivityInfo queryActivity) {
		this.queryActivity = queryActivity;
	}

	public IPlanService getPlanService() {
		return planService;
	}

	public void setPlanService(IPlanService planService) {
		this.planService = planService;
	}

	public int getEmptyRow() {
		return emptyRow;
	}
	public void setEmptyRow(int emptyRow) {
		this.emptyRow = emptyRow;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = request;
	}
}
