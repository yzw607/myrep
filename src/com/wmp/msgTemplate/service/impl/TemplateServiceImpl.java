package com.wmp.msgTemplate.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.msgTemplate.bean.MsgTemplate;
import com.wmp.msgTemplate.service.ITemplateService;
import com.wmp.selfService.bean.ActivityInfo;

public class TemplateServiceImpl extends HibernateDaoSupport implements ITemplateService{

	protected static final Logger logger = Logger.getLogger(TemplateServiceImpl.class.getName());
	
	/**
	 * Get all msg templates according to the msg content
	 * @param msgTemplate
	 * @return 
	 */
	public List<MsgTemplate> findAllMsgTemplate(String msgContent){
		logger.info("fincAllMsgTemplate start");
		List<MsgTemplate> msgTemplateList = new ArrayList<MsgTemplate>();
		StringBuffer sb = new StringBuffer("from MsgTemplate mt where 1= 1 ");
		if(msgContent != null 
				&& !"".equals(msgContent)){
			sb.append(" and mt.tmpltContent like '%").append(msgContent).append("%' ");
		}
		sb.append(" order by mt.tmpltId asc ");
		msgTemplateList = getHibernateTemplate().find(sb.toString());
		logger.info("fincAllMsgTemplate end");
		return msgTemplateList;
	}
	
	/**
	 * Save a msg template
	 * @param msgTemplate
	 * @return 
	 */
	public void saveMsgTemplate(MsgTemplate msgTemplate) throws Exception{
		logger.info("saveMsgTemplate start");
		getHibernateTemplate().saveOrUpdate(msgTemplate);
	}
	
	/**
	 * Delete a msg template according to a tmpltId
	 * @param msgTemplate
	 * @return 
	 */
	public void deleteMsgTemplate(int tmpltId){
		logger.info("deleteMsgTemplate start");
		String sql = " delete from MSG_TEMPLATE where tmplt_id = " + tmpltId;
	    this.getSession().createSQLQuery(sql).executeUpdate();		
	}
	
	/**
	 * Get a msg template by template Id
	 * @param msgTemplate
	 * @return 
	 */
	public MsgTemplate getMsgTemplateById(int tmpltId){
		
		MsgTemplate msgTemplate = (MsgTemplate)this.getHibernateTemplate().get(MsgTemplate.class, tmpltId);
		return msgTemplate;
	}
}
