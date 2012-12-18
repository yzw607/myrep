package com.wmp.sendAll.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.Common;
import com.wmp.common.bean.Page;
import com.wmp.msgTemplate.bean.MsgTemplate;
import com.wmp.msgTemplate.service.ITemplateService;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.service.IPlanService;
import com.wmp.sendAll.bean.SelectOption;
import com.wmp.userManage.bean.User;
import com.wmp.util.SendMessage;

@SuppressWarnings("serial")
public class QueryAllAction extends ActionSupport implements SessionAware, ServletRequestAware{

    private Map<String, Object> session;
    private HttpServletRequest request;
    private IPlanService planService;
    private ITemplateService templateService;
    private List<ActivityInfo> activityList;
    private ActivityInfo queryActivity;
    private List<MsgTemplate> templateList;
    private String phoneNumber;
    private String templateContent;
    private String sysMsg;
    private List<SelectOption> optionList = new ArrayList<SelectOption>();
    private int optionId;
    
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
    
    public String sendMsg() throws Exception
    {
    	templateList = templateService.findAllMsgTemplate("");
    	for(MsgTemplate msgTemplate:templateList){
    		SelectOption selectOption = new SelectOption();
    		selectOption.setId(msgTemplate.getTmpltId());
    		selectOption.setName(msgTemplate.getTmpltTitle().trim());  
    		optionList.add(selectOption);
    	}
        menuType = "sendMsg";
        return SUCCESS;
    }
    
    public String doSendMsg() throws Exception
    {
    	SendMessage sendMessage = new SendMessage();
    	sendMessage.sendSMS(phoneNumber, templateContent);
        menuType = "sendMsg";
        sysMsg = "发送成功！";
    	templateList = templateService.findAllMsgTemplate("");
    	for(MsgTemplate msgTemplate2:templateList){
    		SelectOption selectOption = new SelectOption();
    		selectOption.setId(msgTemplate2.getTmpltId());
    		selectOption.setName(msgTemplate2.getTmpltTitle().trim());  
    		optionList.add(selectOption);
    	}
        return SUCCESS;
    }
    
    public String getTemplateById() throws Exception
    {
    	MsgTemplate msgTemplate = templateService.getMsgTemplateById(optionId);
        menuType = "sendMsg";
        templateContent = msgTemplate.getTmpltContent();
        
    	templateList = templateService.findAllMsgTemplate("");
    	for(MsgTemplate msgTemplate2:templateList){
    		SelectOption selectOption = new SelectOption();
    		selectOption.setId(msgTemplate2.getTmpltId());
    		selectOption.setName(msgTemplate2.getTmpltTitle().trim());  
    		optionList.add(selectOption);
    	}
        return SUCCESS;
    }
    
    
    
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getSysMsg() {
		return sysMsg;
	}

	public void setSysMsg(String sysMsg) {
		this.sysMsg = sysMsg;
	}

	public List<SelectOption> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<SelectOption> optionList) {
		this.optionList = optionList;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public List<MsgTemplate> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<MsgTemplate> templateList) {
		this.templateList = templateList;
	}

	public ITemplateService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(ITemplateService templateService) {
		this.templateService = templateService;
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
