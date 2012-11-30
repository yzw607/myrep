package com.wmp.selfService.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.common.Common;
import com.wmp.selfService.bean.OrderRecord;
import com.wmp.selfService.service.IOrderService;
import com.wmp.userManage.bean.User;

public class OrderServiceImpl extends HibernateDaoSupport implements IOrderService
{
    /**
     * 按次消费用户订购当天服务
     * @param user
     * @return
     * @throws Exception
     */
    public String orderService(User user) throws Exception
    {
//        if(!Common.COUNT.equals(user.getUserType()))
//        {
//            return "用户支付类型不匹配，不能进行此操作！";
//        }
//        
//        if(user.getCounts() <= 0)
//        {
//            return "服务次数不够，请找系统管理员购买";
//        }
//        
//        OrderRecord record = new OrderRecord();
//        record.setUserCode(user.getUserCode());
//        record.setOrderType(Common.ORDER_OUT);
//        record.setOrderNum(1);
//        record.setOrderDate(Common.changeDateFormat(new Date()));
//        record.setLastNum(user.getCounts() - 1);
//        this.getHibernateTemplate().save(record);
//        
//        User oldUser = (User)this.getHibernateTemplate().get(User.class, user.getUserId());
//        oldUser.setCounts(oldUser.getCounts() - 1);
//        this.getHibernateTemplate().update(oldUser);
        
        return "";
    }
    
}
