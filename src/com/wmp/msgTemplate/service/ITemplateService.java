package com.wmp.msgTemplate.service;

import java.util.List;

import com.wmp.msgTemplate.bean.MsgTemplate;

public interface ITemplateService {

	public List<MsgTemplate> findAllMsgTemplate(String msgContent) throws Exception;
	
	public void saveMsgTemplate(MsgTemplate msgTemplate) throws Exception;
	
	public void deleteMsgTemplate(int tmpltId) throws Exception;
	
	public MsgTemplate getMsgTemplateById(int tmpltId) throws Exception;
}
