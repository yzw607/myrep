package com.wmp.selfService.service.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.common.Common;
import com.wmp.selfService.bean.UserNumber;
import com.wmp.selfService.service.INumberService;
import com.wmp.userManage.bean.User;

public class NumberServiceImpl extends HibernateDaoSupport implements INumberService
{
    @SuppressWarnings("unchecked")
    public List<UserNumber> queryUserNumber(User user)
    {
        List<UserNumber> numberList = null;
        
        String hql = "FROM UserNumber o WHERE o.userCode = ? order by o.numberId";
        numberList = this.getHibernateTemplate().find(hql, new Object[]{user.getUserCode()});
        
//        if(null != numberList && numberList.size() > 0)
//        {
//            UserNumber number = null;
//            String userCode = user.getUserCode();
//            
//            for(int i = 0; i < numberList.size(); i++)
//            {
//                number = numberList.get(i);
//                
//                number.setUsed(TimingTool.numberIsUsed(userCode, number.getNumber()));
//            }
//        } 
           
        return numberList;
    }
    
    @SuppressWarnings("unchecked")
    public int getNumberCount(User user, String numberType) throws Exception
    {
        StringBuffer sb = new StringBuffer();
        sb.append("select count(*) from user_number o where o.user_code = '").append(user.getUserCode());
        sb.append("' and o.port = '").append(user.getPort()).append("' and o.sys_tel = '");
        sb.append(user.getSysTel()).append("' and o.number_type = '").append(numberType).append("'");
        
        List tmplist = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sb.toString()).list();
        int count = 0;
        if(null != tmplist && tmplist.size() > 0)
        {
            count = ((BigInteger)tmplist.get(0)).intValue();
        }
        
        return count;
    }
    
    @SuppressWarnings("unchecked")
    public List<UserNumber> queryIdleNumber(String numberType)
    {
        final String hql = "FROM UserNumber o WHERE o.numberType = '" + numberType + "' ORDER BY RAND() ";
        final int firstResult = 0;
        List list = null;
        
        if(numberType.equals("1"))
        {
            final int maxResults = Common.IDLE_NUMS;
            list = getHibernateTemplate().executeFind(new HibernateCallback()
            {
                public Object doInHibernate(Session session)
                        throws HibernateException, SQLException
                {
                    Query query = session.createQuery(hql);
                    query.setFirstResult(firstResult);
                    query.setMaxResults(maxResults);
                    List list = query.list();
                    return list;
                }
            });
        }
        else
        {
            final int maxResults = 30;
            list = getHibernateTemplate().executeFind(new HibernateCallback()
            {
                public Object doInHibernate(Session session)
                        throws HibernateException, SQLException
                {
                    Query query = session.createQuery(hql);
                    query.setFirstResult(firstResult);
                    query.setMaxResults(maxResults);
                    List list = query.list();
                    return list;
                }
            });
        }
        
        return list;
    }
    
    /**
     * 设置用户设置的特殊号码
     * @param user
     * @param tmpNumber
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public synchronized String setUserNumber(User user, int numberId) throws Exception
    {
//        StringBuffer sb = new StringBuffer();
//        String userCode = user.getUserCode();
//        String tmp = null;
//        String hql1 = "FROM UserNumber o WHERE o.userCode = ?";
//        String hql2 = "FROM UserNumber o WHERE o.userNumber like ?";
//        List list = null;
//        UserNumber userNumber = null;
//        HibernateTemplate ht = this.getHibernateTemplate();
//        
//        list = ht.find(hql1, new Object[]{user.getUserCode()});
//        UserNumber un = null;
//        if(null != list && list.size() > 0)
//        {
//            for(int i = 0; i < list.size(); i++)
//            {
//                un = (UserNumber)list.get(i);
//                ht.delete(un);
//            }
//        }
//        
//        
//        for(int i = 0; i < tmpNumber.length; i++)
//        {
//            tmp = tmpNumber[i];
//            if(null == tmp || "".equals(tmp.trim()))
//            {
//                continue;
//            }
//            
//            list = ht.find(hql2, new Object[]{tmp + "%"});
//            if(null != list && list.size() > 0)//这个号码已经存在或者有类似的号码
//            {
//                sb.append(tmp).append(",");
//            }
//            else
//            {
//                //没有类似的号码
//                userNumber = new UserNumber();
//                userNumber.setUserCode(userCode);
////                userNumber.setUserNumber(tmp);
//                ht.save(userNumber);
//            }
//        }
//        
//        String msg = null;
//        if(sb.length() > 0)
//        {
//            msg = sb.substring(0, sb.length() - 1);
//        }
        
        
        String sql = "select count(*) from user_number o where o.user_code = '" + user.getUserCode() + "'";
        List tmplist = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
        int len = ((BigInteger)tmplist.get(0)).intValue();
        if(len >= 20)
        {
            return "您的祝福短号已达到20个上限，请先删除原有的祝福短号后重新选择。";
        }
        
        UserNumber number = (UserNumber)this.getHibernateTemplate().get(UserNumber.class, numberId);
        if(number.getUserCode() != null)
        {
            return "该号码已被其他用户占用，请您刷新本页面重新选择其他号码！";
        }
        else
        {
            number.setUserCode(user.getUserCode());
            number.setPort(user.getPort());
            number.setSysTel(user.getSysTel());
            this.getHibernateTemplate().update(number);
            
            if(number.getNumberType().equals("2"))
            {
                String hql = "FROM User o WHERE o.userCode = ?";
                User tmpUser = (User)this.getHibernateTemplate().find(hql, user.getUserCode()).get(0);
//                tmpUser.setGoodNumber(number.getNumber());
                this.getHibernateTemplate().update(tmpUser);
            }
        }
        
        return "";
    }
    
    @SuppressWarnings("unchecked")
    public String delNumber(User user, int numberId) 
    {
        String sql = "select count(*) from user_number o where o.user_code = '" + user.getUserCode() + "'";
        List tmplist = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
        int len1 = ((BigInteger)tmplist.get(0)).intValue();
        if(len1 < 20)
        {
            return "您的祝福短号还未达到20个上限，不能进行此操作。";
        }
        
        sql = "select count(*) from user_number o where o.user_code = '" + user.getUserCode() + "' and o.number_type = '2'";
        tmplist = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
        int len2 = ((BigInteger)tmplist.get(0)).intValue();
        
        int nums2 = user.getNumberLimit2();
        if(nums2 <= len2)
        {
            return "您还没有多余的优质短号额度，不能进行此操作。";
        }
        
        String hql = "FROM UserNumber o WHERE o.numberId = ? AND o.userCode = ? and o.numberType = '1'";
        List list = this.getHibernateTemplate().find(hql, new Object[]{numberId, user.getUserCode()});
        if(list != null && list.size() > 0)
        {
            UserNumber number = (UserNumber)list.get(0);
            number.setUserCode(null);
            number.setPort(null);
            number.setSysTel(null);
            this.getHibernateTemplate().update(number);
        }
        
        return "";
    }
    
    public User getNewestUser(int userId) throws Exception
    {
        return (User)this.getHibernateTemplate().get(User.class, userId);
    }

	@Override
	public int checkNum(String number) throws Exception {
	    String hql = "FROM UserNumber o WHERE o.number = ? and o.numberType=?";
	    List<UserNumber> numberList = this.getHibernateTemplate().find(hql, new Object[]{number,"2"});
	    if(numberList!=null){
	    	return numberList.size();
	    }
	           
		return 0;
	}

	@Override
	public void updateUserNumber(String numberId,String number) throws Exception {
		UserNumber userNumber = (UserNumber)this.getHibernateTemplate().get(UserNumber.class, Integer.parseInt(numberId));
		if(userNumber == null){
			String hql = "FROM UserNumber o WHERE o.number = ? and o.numberType=?";
		    List<UserNumber> numberList = this.getHibernateTemplate().find(hql, new Object[]{number,"1"});
		    if(numberList!=null){
		    	userNumber = numberList.get(0);
		    	userNumber.setNumberType("2");
				this.getHibernateTemplate().update(userNumber);		
		    }else{
		    	userNumber = new UserNumber();
		    	userNumber.setNumber(number);
		    	userNumber.setNumberType("2");
		    	this.getHibernateTemplate().persist(userNumber);
		    }
		}else{
			userNumber.setNumberType("2");
			this.getHibernateTemplate().update(userNumber);		
		}
	}
}
