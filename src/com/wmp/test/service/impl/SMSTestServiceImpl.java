package com.wmp.test.service.impl;

import java.util.Date;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.sms.bean.MsgInBox;
import com.wmp.test.bean.SMSTest;
import com.wmp.test.service.ISMSTestService;

public class SMSTestServiceImpl extends HibernateDaoSupport implements ISMSTestService
{
    public void testSMS(SMSTest test)
    {
        int count = test.getCount();
        if(count == 0) count = 1;
        Date date = new Date();
        HibernateTemplate ht = this.getHibernateTemplate();
        
        MsgInBox box = null;
        String number = test.getNumber();
        String str = test.getContent();
        for(int i =0; i < count; i++)
        {
            box = new MsgInBox();
            box.setSender(number);
            box.setMsgType(0);
            box.setMsgTitle(str);
            box.setMsgArrivedTime(date);
            box.setStatus(null);
            box.setCommPort(1);
            
            ht.save(box);
        }
    }
}