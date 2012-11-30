package com.wmp.selfService.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.bean.LotteryNumber;
import com.wmp.selfService.bean.UserNumber;
import com.wmp.selfService.service.ILotteryService;
import com.wmp.userManage.bean.User;

public class LotteryServiceImpl extends HibernateDaoSupport implements ILotteryService
{
    @SuppressWarnings("unchecked")
    public List<ActivityInfo> queryActivityInfoList(User user) throws Exception
    {
        String userCode = user.getUserCode();
        String port = user.getPort();
        String sysTel = user.getSysTel();
        HibernateTemplate ht = this.getHibernateTemplate();
        String hql = "FROM UserNumber o WHERE o.userCode = ? and o.port = ? and o.sysTel = ?";
        List<UserNumber> numberList = ht.find(hql, new Object[] {userCode, port, sysTel});

        List<ActivityInfo> activityList = new ArrayList<ActivityInfo>();
        UserNumber number = null;
        ActivityInfo activity = null;
        List<ActivityInfo> tmpList = null;
        
        hql = "FROM ActivityInfo o WHERE o.userCode = ? AND o.number = ? AND o.port = ? AND o.sysTel = ?";
        for(int i = 0; i < numberList.size(); i++)
        {
            number = numberList.get(i);
            
            tmpList = ht.find(hql, new Object[]{userCode, number.getNumber(), port, sysTel});
            if(null != tmpList && tmpList.size() > 0)
            {
                activity = tmpList.get(0);
//                activity.setUsed(TimingTool.numberIsUsed(userCode, number.getNumber()));
                
                activityList.add(activity);
            }
            else
            {
                activity = new ActivityInfo();
                activity.setUserCode(userCode);
                activity.setNumber(number.getNumber());
                activity.setPort(port);
                activity.setSysTel(sysTel);
                ht.save(activity);
                
//                activity.setUsed(false);
                activityList.add(activity);
            }
        }
        
        return activityList;
    }
    
    @SuppressWarnings("unchecked")
    public List getNumberList(int activityInfoId) throws Exception
    {
        ActivityInfo activity = (ActivityInfo) this.getHibernateTemplate().get(
                ActivityInfo.class, activityInfoId);

        String sql = "select distinct(sender) from msg_inbox o where o.MsgTitle like '"
                + activity.getNumber() + "%'";
        
        
        List tmplist = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();

        return tmplist;
    }
    
    @SuppressWarnings("unchecked")
    public List getFilteredNumberList(User user, String activityId) throws Exception
    {
        String hql = "FROM LotteryNumber o WHERE o.userCode = ? AND o.activityId = ?";
        List list = this.getHibernateTemplate().find(hql,
                new Object[] { user.getUserCode(), activityId});
        
        StringBuffer sb = new StringBuffer();
        if(null != list && list.size() > 0)
        {
            sb.append(" and o.sender not in (");
            
            int size = list.size();
            LotteryNumber number = null;
            for(int i = 0; i < size; i++)
            {
                number = (LotteryNumber)list.get(i);
                sb.append("'").append(number.getShowNumber()).append("'");
                
                if(i != (size - 1))
                {
                    sb.append(", ");
                }
            }
            
            sb.append(")");
        }
        
        ActivityInfo activity = (ActivityInfo) this.getHibernateTemplate().get(
                ActivityInfo.class, Integer.parseInt(activityId));

        String sql = "select distinct(sender) from msg_inbox o where o.MsgTitle like '"
                + activity.getNumber() + "%' " + sb.toString();
        
        
        List tmplist = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();

        return tmplist;
    }
    
    @SuppressWarnings("unchecked")
    public ActivityInfo getActivity(User user, String activityId) throws Exception
    {
        String hql = "FROM ActivityInfo o WHERE o.id = ? AND o.userCode = ?";
        List list = this.getHibernateTemplate().find(hql,
                new Object[] { new Integer(activityId), user.getUserCode() });
        if(null != list && list.size() > 0)
        {
            return (ActivityInfo)list.get(0);
        }
        else
        {
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<LotteryNumber> getLotteryNumber(User user, String activityId) throws Exception
    {
        String hql = "FROM LotteryNumber o WHERE o.userCode = ? AND o.activityId = ?";
        List list = this.getHibernateTemplate().find(hql,
                new Object[] { user.getUserCode(), activityId});

        return list;
    }
    
    public void addLotteryNumber(User user, LotteryNumber number, String activityId) throws Exception
    {
        number.setUserCode(user.getUserCode());
        number.setActivityId(activityId);
        
        this.getHibernateTemplate().save(number);
    }
    
    @SuppressWarnings("unchecked")
    public void delNumber(User user, LotteryNumber number) throws Exception
    {
        String hql = "FROM LotteryNumber o WHERE o.userCode = ? and o.numId = ?";
        List list = this.getHibernateTemplate().find(hql,
                new Object[] { user.getUserCode(), number.getNumId()});
        
        if(null != list && list.size() > 0)
        {
            LotteryNumber n = (LotteryNumber)list.get(0);
            this.getHibernateTemplate().delete(n);
        }
    }
}
