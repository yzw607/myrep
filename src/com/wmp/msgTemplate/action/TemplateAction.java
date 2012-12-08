package com.wmp.msgTemplate.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.msgTemplate.bean.MsgTemplate;
import com.wmp.msgTemplate.service.ITemplateService;

public class TemplateAction extends ActionSupport implements SessionAware, ServletRequestAware{
	
	protected static final Logger logger = Logger.getLogger(TemplateAction.class.getName());
    private ITemplateService templateService;
    private String sysMsg;
    private Map<String, Object> session;
    private HttpServletRequest servletRequest;
    private MsgTemplate msgTemplate;
    private List<MsgTemplate> msgTemplateList;
    private String menuType;
    private String msgContent;
    private String selectedTemplateIds;
    
    
    public String findAllMsgTemplate() throws Exception{
    	
    	logger.info("findAllMsgTemplate start");
    	msgTemplateList = templateService.findAllMsgTemplate(msgContent);
    	menuType = "msgTemplate";
    	
    	return SUCCESS;
    }
    
    public String saveMsgTemplate() throws Exception{
    	menuType = "msgTemplate";
    	if(msgTemplate.getTmpltId() != 0){
    		//It's a pre-existing msg template
    		MsgTemplate existingMsgTemplate = new MsgTemplate();
    		existingMsgTemplate = templateService.getMsgTemplateById(msgTemplate.getTmpltId());
    		existingMsgTemplate.setTmpltComment(msgTemplate.getTmpltComment());
    		existingMsgTemplate.setTmpltContent(msgTemplate.getTmpltContent());
    		existingMsgTemplate.setTmpltTitle(msgTemplate.getTmpltTitle());
    		templateService.saveMsgTemplate(existingMsgTemplate);
    	}else{
    		//It's a new added msg template
    		templateService.saveMsgTemplate(msgTemplate);
    	}
    	sysMsg = "保存成功！";
    	return SUCCESS;
    }
    
    public String deleteTemplates() throws Exception{
    	logger.info("deleteMsgTemplate start");
    	if(selectedTemplateIds != null 
    			&& !"".equals(selectedTemplateIds)){
    		selectedTemplateIds = selectedTemplateIds.substring(0, selectedTemplateIds.length() -1);
    	}
    	String[] templateIds = selectedTemplateIds.split(",");
    	for(int i = 0;i < templateIds.length;i++){
    		String tmplateId = templateIds[i];
    		templateService.deleteMsgTemplate(Integer.parseInt(tmplateId));
    	}
//    	int tmpltId = msgTemplate.getTmpltId();
//    	templateService.deleteMsgTemplate(tmpltId);
    	menuType = "msgTemplate";
    	
    	return SUCCESS;
    }
    
    public String addNewTemplate() throws Exception{
    	menuType = "msgTemplate";
    	return SUCCESS;
    }
    
	public String getMsgTemplateById() throws Exception{
		msgTemplate = templateService.getMsgTemplateById(msgTemplate.getTmpltId());
		menuType = "msgTemplate";
		return SUCCESS;
	}
	
    
	public String getSelectedTemplateIds() {
		return selectedTemplateIds;
	}

	public void setSelectedTemplateIds(String selectedTemplateIds) {
		this.selectedTemplateIds = selectedTemplateIds;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public ITemplateService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(ITemplateService templateService) {
		this.templateService = templateService;
	}

	public String getSysMsg() {
		return sysMsg;
	}
	public void setSysMsg(String sysMsg) {
		this.sysMsg = sysMsg;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
	public MsgTemplate getMsgTemplate() {
		return msgTemplate;
	}
	public void setMsgTemplate(MsgTemplate msgTemplate) {
		this.msgTemplate = msgTemplate;
	}
	public List<MsgTemplate> getMsgTemplateList() {
		return msgTemplateList;
	}
	public void setMsgTemplateList(List<MsgTemplate> msgTemplateList) {
		this.msgTemplateList = msgTemplateList;
	}
    
}
