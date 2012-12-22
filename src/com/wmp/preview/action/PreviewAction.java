package com.wmp.preview.action;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.preview.PicSlider;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.service.IPlanService;
import com.wmp.sms.bean.MsgInBox;
import com.wmp.sms.service.ISmsService;
import com.wmp.util.ImageUtil;

public class PreviewAction extends ActionSupport implements SessionAware,
		ServletRequestAware {
	private Map<String, Object> session;
	private HttpServletRequest request;
	
	private ActivityInfo activityInfo;
	private IPlanService planService;
	
	private ISmsService smsService;
	 
	private List picList;
	
	private List<MsgInBox> msgList;
	
	private ByteArrayInputStream imageStream;
	
	public String preview() throws Exception{
		String id = request.getParameter("id");
		activityInfo = this.planService.queryActivityInfo(Integer.parseInt(id));
		PicSlider slider = new PicSlider();
		picList = slider.getPicList(activityInfo.getPicPath());
		
		return SUCCESS;
	}
	
	public String getSnsList() throws Exception{
		String id = request.getParameter("id");
		msgList = smsService.getLatestSMS(id);
		return SUCCESS;
	}
	
	public String getPic() throws Exception {
		String path = request.getParameter("path");
		ImageUtil imageUtil = new ImageUtil();
        imageStream = imageUtil.getStream(path);
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

	public void setPlanService(IPlanService planService) {
		this.planService = planService;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}


	public void setSmsService(ISmsService smsService) {
		this.smsService = smsService;
	}


	public List<MsgInBox> getMsgList() {
		return msgList;
	}


	public void setMsgList(List<MsgInBox> msgList) {
		this.msgList = msgList;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ByteArrayInputStream getImageStream() {
	    return imageStream;
    }

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }
}
