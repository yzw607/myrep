package com.wmp.sms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.sms.bean.MsgInBox;
import com.wmp.sms.bean.NamePoint;
import com.wmp.sms.service.ISynSmsService;

public class SynSmsServiceImpl extends HibernateDaoSupport implements ISynSmsService
{
    private final static String FIND_NAME = "FROM NamePoint o WHERE o.telNumber = ?"; 
    
    public ActivityInfo queryActivityInfo(int id) throws Exception
    {
        return (ActivityInfo)this.getHibernateTemplate().get(ActivityInfo.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public MsgInBox getReadedSMS(String number, String lastSmsId, String recordTime) throws Exception
    {
        HibernateTemplate ht = this.getHibernateTemplate();
        MsgInBox box = null;
        NamePoint name = null;

        StringBuffer hql = new StringBuffer(256);
        hql.append("FROM MsgInBox o WHERE o.msgTitle LIKE '").append(number);
        hql.append("%' AND o.msgType = 0 AND o.status = 1 AND o.msgArrivedTime > ?");
        
        if(null != lastSmsId && !"".equals(lastSmsId.trim()))
        {
            int smsId = Integer.parseInt(lastSmsId.trim());
            hql.append("AND o.id > ").append(smsId);
        }
        
        hql.append(" ORDER BY o.id");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(recordTime);
        List<MsgInBox> list = ht.find(hql.toString(), date);
        List<NamePoint> nList = null;
        String sender = null;
        
        if (null != list && list.size() > 0)
        {
            box = list.get(0);
            
            sender = box.getSender().trim();
            nList = ht.find(FIND_NAME, sender);
            if(null != nList && nList.size() > 0)
            {
                name = nList.get(0);
                // box.setSender(name.getName());
                MsgInBox newBox = new MsgInBox();
                newBox.setId(box.getId());
                newBox.setSender(name.getName());
                newBox.setMsgTitle(box.getMsgTitle());
                newBox.setMsgType(1);
                newBox.setMMSURL(box.getMMSURL());
                
                return newBox;
            }
            else
            {
                return box;
            }
        }

        return box;
    }
}
