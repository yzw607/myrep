package com.wmp.sendAll.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class QueryAll extends ActionSupport implements SessionAware, ServletRequestAware{

//    private IPlanService planService;
//    private String sysMsg;
    private Map<String, Object> session;
    private HttpServletRequest request;
//    private ActivityInfo activityInfo;
//    private List<ActivityInfo> activityList;
//    private List<UserNumber> numberList;
//    private ActivityInfo queryActivity;
//    private int emptyRow;
//    private Page page;
//    private String menuType;
//    private List<Object> exportList;
//    private String backUrl;
//    private String msgType;
    
    
    
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
