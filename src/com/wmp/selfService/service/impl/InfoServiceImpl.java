package com.wmp.selfService.service.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.common.bean.Page;
import com.wmp.selfService.bean.OrderRecord;
import com.wmp.selfService.service.IInfoService;
import com.wmp.userManage.bean.User;

public class InfoServiceImpl extends HibernateDaoSupport implements IInfoService
{
    public User queryInfo(int userId) throws Exception
    {
        return (User)this.getHibernateTemplate().get(User.class, userId);
    }
    
    @SuppressWarnings("unchecked")
    public Page getPage(User user, Page page) throws Exception
    {
        StringBuffer sb = new StringBuffer();
        sb.append("select count(*) from order_record o where o.user_code = '").append(user.getUserCode());
        sb.append("' ");
        
        List tmplist = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sb.toString()).list();
        int maxRows = 0;
        int maxPage = 0;
        if(null != tmplist && tmplist.size() > 0)
        {
            maxRows = ((BigInteger)tmplist.get(0)).intValue();
        }
        
        if(maxRows > 0)
        {
            maxPage = maxRows / 10;
            if(maxRows % 10 > 0)
            {
                maxPage++;
            }
        }
        
        page.setMaxPage(maxPage);
        page.setMaxRows(maxRows);
        
        int currentPage = page.getIndexPage();
        page.setStartRows((currentPage - 1) * 10);
        page.setEndRows(currentPage * 10);
        
        if(currentPage > 1 && maxPage > 1)
        {
            page.setHasBefore(true);
        }
        else
        {
            page.setHasBefore(false);
        }
        
        if(maxPage > 1 && currentPage < maxPage)
        {
            page.setHasNext(true);
        }
        else
        {
            page.setHasNext(false);
        }
        
        return page;
    }

    @SuppressWarnings("unchecked")
    public List<OrderRecord> queryRecord(String userCode, Page page) throws Exception
    {
        List<OrderRecord> list = null;
        StringBuffer sb = new StringBuffer();
        sb.append("FROM OrderRecord o WHERE o.userCode = '").append(userCode);
        sb.append("' ORDER BY o.id DESC");
        
        final String hql = sb.toString();
        final int firstResult = page.getStartRows();
        final int maxResults = page.getEndRows();
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
        
        return list;
        
        
        
        
//        StringBuffer hql = new StringBuffer();
//        hql.append("SELECT o FROM OrderRecord o WHERE o.userCode = '").append(userCode);
//        hql.append("' ORDER BY o.recordId");
//        
//        List<OrderRecord> list = this.getHibernateTemplate().find(hql.toString());
//        
//        return list;
    }
    
    @SuppressWarnings("unchecked")
    public Page getSubAccountPage(User agentUser, User subAccountUser, Page page)
            throws Exception
    {
        StringBuffer sb = new StringBuffer();
        sb.append("select count(*) from order_record o where o.user_code = '");
        sb.append(subAccountUser.getUserCode()).append(
                "' and o.parent_code = '");
        sb.append(agentUser.getUserCode()).append("' ");

        List tmplist = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createSQLQuery(sb.toString()).list();
        int maxRows = 0;
        int maxPage = 0;
        if (null != tmplist && tmplist.size() > 0)
        {
            maxRows = ((BigInteger) tmplist.get(0)).intValue();
        }

        if (maxRows > 0)
        {
            maxPage = maxRows / 10;
            if (maxRows % 10 > 0)
            {
                maxPage++;
            }
        }

        page.setMaxPage(maxPage);
        page.setMaxRows(maxRows);

        int currentPage = page.getIndexPage();
        page.setStartRows((currentPage - 1) * 10);
        page.setEndRows(currentPage * 10);

        if (currentPage > 1 && maxPage > 1)
        {
            page.setHasBefore(true);
        }
        else
        {
            page.setHasBefore(false);
        }

        if (maxPage > 1 && currentPage < maxPage)
        {
            page.setHasNext(true);
        }
        else
        {
            page.setHasNext(false);
        }

        return page;
    }
    
    @SuppressWarnings("unchecked")
    public List<OrderRecord> querySubAccountRecord(User agentUser,
            User subAccountUser, Page page) throws Exception
    {
        List<OrderRecord> list = null;
        StringBuffer sb = new StringBuffer();
        sb.append("FROM OrderRecord o WHERE o.userCode = '").append(
                subAccountUser.getUserCode());
        sb.append("' AND o.parentCode = '").append(agentUser.getUserCode());
        sb.append("' ORDER BY o.id DESC");

        final String hql = sb.toString();
        final int firstResult = page.getStartRows();
        final int maxResults = page.getEndRows();
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

        return list;
    }
    
    public void updateUserInfo(User tmpUser) throws Exception
    {
        User user = (User)this.getHibernateTemplate().get(User.class, tmpUser.getUserId());
        
        user.setUserName(tmpUser.getUserName());
        user.setTel(tmpUser.getTel());
        user.setCompanyName(tmpUser.getCompanyName());
        user.setCompanyAddress(tmpUser.getCompanyAddress());
        
        this.getHibernateTemplate().update(user);
    }
    
    public boolean updateUserPsw(User tmpUser, String oldPsw, String newPsw) throws Exception
    {
        User user = (User)this.getHibernateTemplate().get(User.class, tmpUser.getUserId());
        String psw = user.getPassWord();
        if(!psw.equals(oldPsw.trim()))
        {
            return false;
        }
        
        user.setPassWord(newPsw.trim());
        this.getHibernateTemplate().update(user);
        
        return true;
    }
}
