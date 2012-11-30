package com.wmp.agents.service.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.agents.service.IAgentsManageService;
import com.wmp.common.bean.Page;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.userManage.bean.User;

public class AgentsManageServiceImpl extends HibernateDaoSupport implements IAgentsManageService
{
    @SuppressWarnings("unchecked")
    public Page getPage(User queryAgents, Page page) throws Exception
    {
        StringBuffer sb = new StringBuffer();
        sb.append("select count(*) from user o where o.user_type = 'agents' ");
        if(null != queryAgents)
        {
            String companyName = queryAgents.getCompanyName();
            if(null != companyName && !"".equals(companyName.trim()))
            {
                companyName = companyName.replaceAll("'", "‘");
                companyName = companyName.replaceAll("%", "％");
                sb.append("and o.company_name like '%").append(companyName.trim()).append("%' ");
            }
            
            String userName = queryAgents.getUserName();
            if(null != userName && !"".equals(userName.trim()))
            {
                userName = userName.replaceAll("'", "‘");
                userName = userName.replaceAll("%", "％");
                sb.append("and o.user_name like '%").append(userName.trim()).append("%' ");
            }
        }
        
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
    public List<User> queryAgentsList(User queryAgents, Page page) throws Exception
    {
        List<User> agentList = null;
        StringBuffer sb = new StringBuffer();
        sb.append("FROM User o WHERE o.userType = 'agents' ");
        if(null != queryAgents)
        {
            String companyName = queryAgents.getCompanyName();
            if(null != companyName && !"".equals(companyName.trim()))
            {
                companyName = companyName.replaceAll("'", "‘");
                companyName = companyName.replaceAll("%", "％");
                sb.append("and o.companyName like '%").append(companyName.trim()).append("%' ");
            }
            
            String userName = queryAgents.getUserName();
            if(null != userName && !"".equals(userName.trim()))
            {
                userName = userName.replaceAll("'", "‘");
                userName = userName.replaceAll("%", "％");
                sb.append("and o.userName like '%").append(userName.trim()).append("%' ");
            }
        }
        sb.append("ORDER BY o.userId DESC");
        
        final String hql = sb.toString();
        final int firstResult = page.getStartRows();
        final int maxResults = page.getEndRows();
        agentList = getHibernateTemplate().executeFind(new HibernateCallback()
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
        
        return agentList;
    }
    
    public void changeStatus(ActivityInfo activityInfo) throws Exception
    {
        ActivityInfo activity = (ActivityInfo) this.getHibernateTemplate().get(
                ActivityInfo.class, activityInfo.getId());
        
        activity.setStatus(activityInfo.getStatus());
        this.getHibernateTemplate().update(activity);
    }
    
    public ActivityInfo queryActivityInfo(int activityId) throws Exception
    {
        ActivityInfo activityInfo = (ActivityInfo)this.getHibernateTemplate().get(ActivityInfo.class, activityId);
        
        return activityInfo;
    }
}
