package com.wmp.sms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.common.Common;
import com.wmp.common.TimingTool;
import com.wmp.common.bean.UploadPic;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.bean.OrderRecord;
import com.wmp.selfService.bean.Stencil;
import com.wmp.selfService.bean.UserNumber;
import com.wmp.sms.bean.MsgInBox;
import com.wmp.sms.bean.NamePoint;
import com.wmp.sms.service.ISmsService;
import com.wmp.userManage.bean.User;

public class SmsServiceImpl extends HibernateDaoSupport implements ISmsService
{
    private final static String FIND_NAME = "FROM NamePoint o WHERE o.telNumber = ?"; 
    
    @SuppressWarnings("unchecked")
    public List<UserNumber> queryUserNumber(User user)
    {
        List<UserNumber> numberList = null;
        
        String hql = "FROM UserNumber o WHERE o.userCode = ? order by o.numberId";
        numberList = this.getHibernateTemplate().find(hql, new Object[]{user.getUserCode()});
        
        return numberList;
    }
    
    /**
     * 获得今日还未显示的手机号码
     * @param number
     * @return
     */
    @SuppressWarnings("unchecked")
    public MsgInBox toGetTheLatestSMS(String number, String activityId) throws Exception
    {
        HibernateTemplate ht = this.getHibernateTemplate();
        MsgInBox box = null;
        NamePoint name = null;
        String msg = null;

        StringBuffer hql = new StringBuffer(256);
        hql.append("FROM MsgInBox o WHERE o.msgArrivedTime > curdate() AND (o.msgTitle LIKE '");
        hql.append(number).append("%' OR o.msgTitle LIKE '000%') AND o.msgTitle NOT LIKE '");
        hql.append(number).append("抢答%' AND o.msgType = 0 AND o.status IS NULL ORDER BY o.id");

        List<MsgInBox> list = ht.find(hql.toString());
        List<NamePoint> nList = null;
        String sender = null;
        String newName = null;

        if (null != list)
        {
            int size = list.size();
            for (int i = 0; i < size; i++)
            {
                box = list.get(i);

                msg = box.getMsgTitle();
                sender = box.getSender().trim();

                if (msg.startsWith("000"))
                {
                    box.setStatus("2");
                    ht.update(box);
                    
                    if(msg.length() > 18)
                    {
                        msg = msg.substring(0, 18);
                    }
                    newName = msg.substring(3, msg.length());
                    newName = newName.replaceAll("\\?", "？");
                    newName = newName.replaceAll("'", "‘");
                    newName = newName.replaceAll("%", "％");
                    
                    nList = ht.find(FIND_NAME, sender);
                    if(null != nList && nList.size() > 0)
                    {
                        name = nList.get(0);
                        name.setName(newName);
                        
                        ht.update(name);
                    }
                    else
                    {
                        name = new NamePoint();
                        name.setTelNumber(sender);
                        name.setName(msg.substring(3, msg.length()));
                        ht.save(name);
                    }
                    
                    box = null;

                    continue;
                }
                else
                {
                    box.setStatus("1");
                    box.setActivityId(activityId);
                    this.getHibernateTemplate().update(box);

                    nList = ht.find(FIND_NAME, sender);
                    if(null != nList && nList.size() > 0)
                    {
                        name = nList.get(0);
                        MsgInBox newBox = new MsgInBox();
                        newBox.setId(box.getId());
                        newBox.setSender(name.getName());
                        newBox.setMsgTitle(box.getMsgTitle());
                        newBox.setMsgType(box.getMsgType());
                        newBox.setMMSURL(box.getMMSURL());

                        return newBox;                        
                    }

                    break;
                }
            }
        }

        return box;
    }
    
    public String getUserRemainingTimeFlag(User user) throws Exception
    {
        String flag = null;
        
        int userRemainingTime = ((User)this.getHibernateTemplate().get(User.class, user.getUserId())).getRemainingTime();
        if(userRemainingTime == 0)
        {
            return Common.EMPTY;
        }
        
        flag = TimingTool.getUserRemainingTimeFlag(user.getUserCode(), userRemainingTime);
        
        return flag;
    }
    
    //synchronized
    public void startTiming(User user, String number) throws Exception
    {
        User tmpUser = (User)this.getHibernateTemplate().get(User.class, user.getUserId());
        Date date = new Date();
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String nowDate = formatter.format(date);
        TimingTool.startTiming(tmpUser.getUserCode(), number, nowDate);
        
        String hql = "FROM ActivityInfo o WHERE o.userCode = ? AND o.number = ?";
        ActivityInfo info = (ActivityInfo)this.getHibernateTemplate().find(hql, new Object[]{user.getUserCode(), number}).get(0);
        
        OrderRecord record = new OrderRecord();
        record.setUserCode(tmpUser.getUserCode());
        record.setOrderType(Common.ORDER_OUT);
        record.setCostNumber(number);
        record.setOriginalNum(tmpUser.getRemainingTime());
        record.setOrderDate(date);
        record.setTitle(info.getTitle());
        this.getHibernateTemplate().save(record);
    }
    
    @SuppressWarnings("unchecked")
    public void endTiming(User user, String number) throws Exception
    {
        HibernateTemplate ht = this.getHibernateTemplate();
        OrderRecord record = null;
        User tmpUser = (User)ht.get(User.class, user.getUserId());
        String hql = "FROM OrderRecord o WHERE o.userCode = ? and o.costNumber = ? and o.orderType = '"
             + Common.ORDER_OUT + "' and o.orderDate > curdate() and o.costEnd is null order by o.recordId desc";
        List list = ht.find(hql, new Object[]{tmpUser.getUserCode(), number});
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String nowDate = formatter.format(date);
        int oldRemainingTime = tmpUser.getRemainingTime();
        
        if(null != list && list.size() > 0)
        {
            record = (OrderRecord)list.get(0);
            int costTime = TimingTool.endTiming(record.getUserCode(), number, nowDate);
            
            record.setOrderNum(costTime);
            record.setOriginalNum(oldRemainingTime);
            record.setLastNum(oldRemainingTime - costTime);
            ht.update(record);
            
            tmpUser.setRemainingTime(oldRemainingTime - costTime);
            ht.update(tmpUser);
        }
    }
    
    public ActivityInfo queryActivityInfo(int id) throws Exception
    {
        return (ActivityInfo)this.getHibernateTemplate().get(ActivityInfo.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public List<UploadPic> queryPicList(String userCode, String activityId)
    {
        String hql = "FROM UploadPic o WHERE o.activityId = ? AND o.userCode = ?";
        List<UploadPic> list = this.getHibernateTemplate().find(hql, new Object[]{activityId, userCode});
        if(null != list && list.size() == 0)
        {
            list = null;
        }
        
        return list;
    }
    
    public Stencil queryStencil(String stencilId) throws Exception
    {
        return (Stencil) this.getHibernateTemplate().get(Stencil.class,
                Integer.parseInt(stencilId));
    }
}
