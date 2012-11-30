package com.wmp.selfService.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.alipay.util.UtilDate;
import com.wmp.common.Common;
import com.wmp.selfService.bean.AlipayOrder;
import com.wmp.selfService.bean.Card;
import com.wmp.selfService.bean.OrderRecord;
import com.wmp.selfService.service.IRechargeService;
import com.wmp.userManage.bean.User;

public class RechargeServiceImpl extends HibernateDaoSupport implements IRechargeService
{
    @SuppressWarnings("unchecked")
    public String doRecharge(String rechargeCode, String userCode) throws Exception
    {
        String msg = null;
        HibernateTemplate ht = this.getHibernateTemplate();
        String hql = "FROM Card o WHERE o.cardCode = ?";
        List<Card> list = ht.find(hql, rechargeCode);
        if(null == list || list.size() == 0)
        {
            msg = "卡号不存在，请您核对后重新输入！";
            
            User user = getUser(userCode);
            user.setErrorCount(user.getErrorCount() + 1);
            ht.update(user);
        }
        else
        {
            Card card = list.get(0);
            
            if(card.getStatus() == 0)
            {
                msg = "该卡号尚未激活，请您拨打爱说热线：400-101-4595，联系客服！";
            }
            else if(card.getStatus() == 2)
            {
                msg = "该卡号已经被使用，请您核对后重新输入！";
            }
            else if(card.getStatus() == 1)
            {
                Date date = new Date();
                int cardValue = card.getCardValue();
                User user = getUser(userCode);
                int originalNum = user.getRemainingTime();
                int lastNum = originalNum + cardValue;

                card.setStatus(2);
                card.setUserCode(userCode);
                card.setRechargeDate(date);
                ht.update(card);
                
                OrderRecord record = new OrderRecord();
                record.setUserCode(userCode);
                record.setParentCode(user.getParentCode());
                
                String cardType = card.getCardType();
                if(Common.HANDSEL_CARD.equals(cardType))
                {
                    record.setOrderType(Common.ORDER_HANDSEL);
                }
                else if(Common.RECHARGE_CARD.equals(cardType))
                {
                    record.setOrderType(Common.ORDER_IN);
                    
//                    String sql = "select sum(order_num) as nums from order_record where order_type = '"
//                        + Common.ORDER_IN + "'";
//                    List tmplist = ht.getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
//                    if(null != tmplist && tmplist.size() > 0)
//                    {
//                        Object obj = tmplist.get(0);
//                        int nums = 0;
//                        if(null == obj)
//                        {
//                            nums = 1;
//                        }
//                        else
//                        {
//                            nums = ((BigDecimal)obj).intValue() + 1;
//                            if(nums > 20) nums = 20;
//                        }
//                        user.setNumberLimit(nums);
//                    }
                }
                
                int nums = user.getNumberLimit() + cardValue;
                if(nums > 20) nums = 20;
                user.setNumberLimit(nums);
                
                if(cardValue >= 10)
                {
                    int nums2 = user.getNumberLimit2() + 1;
                    if(nums2 > 20) nums2 = 20;
                    user.setNumberLimit2(nums2);
                }
                
                record.setOrderNum(cardValue);
                record.setCostNumber("");
                record.setOriginalNum(originalNum);
                record.setOrderDate(date);
                record.setLastNum(lastNum);
                record.setTitle("");
                this.getHibernateTemplate().save(record);
               
                user.setRemainingTime(lastNum);
                user.setErrorCount(0);
                ht.update(user); 
                

                if (Common.RECHARGE_CARD.equals(card.getCardType()))
                {
                    msg = "充值成功，您此次成功充值 " + cardValue + "次使用次数";
                }
                else
                {
                    msg = "充值成功，系统赠送给您 " + cardValue + "次使用次数";
                }
            }
        }
        return msg;
    }
    
    @SuppressWarnings("unchecked")
    public User getUser(String userCode) throws Exception
    {
        String hql = "FROM User o WHERE o.userCode = ?";
        List list = this.getHibernateTemplate().find(hql, userCode);
        
        return (User)list.get(0);
    }
    
    public AlipayOrder createAlipayOrder(AlipayOrder payOrder) throws Exception
    {
        String orderCode = UtilDate.getOrderNum();
        payOrder.setOrderCode(orderCode);
        payOrder.setOrderDate(new Date());
        payOrder.setStatus(0);
        
        this.getHibernateTemplate().save(payOrder);
        
        return payOrder;
    }
    
    @SuppressWarnings("unchecked")
    public AlipayOrder doPayBack(String out_trade_no, String trade_no) throws Exception
    {
        HibernateTemplate ht = this.getHibernateTemplate();
        String hql = "FROM AlipayOrder o WHERE o.orderCode = ? AND o.status = 0";
        List list = ht.find(hql, out_trade_no);
        if (null == list || list.size() == 0)
        {
            return null;
        }

        Date date = new Date();

        AlipayOrder payOrder = (AlipayOrder) list.get(0);
        int payNum = payOrder.getPayNum();
        String userCode = payOrder.getUserCode();

        payOrder.setTradeNo(trade_no);
        payOrder.setPayDate(date);
        payOrder.setStatus(1);
        ht.update(payOrder);

        
        User user = this.getUser(userCode);
        int originalNum = user.getRemainingTime();
        int lastNum = originalNum + payNum;

        OrderRecord record = new OrderRecord();
        record.setUserCode(userCode);
        record.setParentCode(user.getParentCode());
        record.setOrderType(Common.ORDER_IN2);

        int nums = user.getNumberLimit() + payNum;
        if (nums > 20)
            nums = 20;
        user.setNumberLimit(nums);

        if (payNum >= 10)
        {
            int nums2 = user.getNumberLimit2() + 1;
            if (nums2 > 20)
                nums2 = 20;
            user.setNumberLimit2(nums2);
        }

        record.setOrderNum(payNum);
        record.setCostNumber("");
        record.setOriginalNum(originalNum);
        record.setOrderDate(date);
        record.setLastNum(lastNum);
        record.setTitle("");
        ht.save(record);

        user.setRemainingTime(lastNum);
        ht.update(user);

        return payOrder;
    }
}
