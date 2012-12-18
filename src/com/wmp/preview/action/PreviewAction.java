package com.wmp.preview.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.preview.PicSlider;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.service.IPlanService;

public class PreviewAction extends ActionSupport implements SessionAware,
		ServletRequestAware {
	private Map<String, Object> session;
	private HttpServletRequest request;
	
	private ActivityInfo activityInfo;
	private IPlanService planService;
	
	private List picList;
	
	public String picList() throws Exception{
		String id = request.getParameter("id");
		activityInfo = this.planService.queryActivityInfo(Integer.parseInt(id));
		PicSlider slider = new PicSlider();
		picList = slider.getPicList(activityInfo.getPicPath());
		return SUCCESS;
	}
	

	public List getPicList() {
		return picList;
	}

	public void setPicList(List picList) {
		this.picList = picList;
	}

	public ActivityInfo getActivityInfo() {
		return activityInfo;
	}

	public void setActivityInfo(ActivityInfo activityInfo) {
		this.activityInfo = activityInfo;
	}

	public IPlanService getPlanService() {
		return planService;
	}

	public void setPlanService(IPlanService planService) {
		this.planService = planService;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
