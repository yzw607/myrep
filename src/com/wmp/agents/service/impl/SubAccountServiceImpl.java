package com.wmp.agents.service.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.agents.service.ISubAccountService;
import com.wmp.common.Common;
import com.wmp.common.bean.Page;
import com.wmp.common.bean.SysTelNumber;
import com.wmp.userManage.bean.User;

public class SubAccountServiceImpl extends HibernateDaoSupport implements ISubAccountService
{
    private static final String DEFAULT_PASSWORD = "say520";
    
    @SuppressWarnings("unchecked")
    public Page getPage(User querySubAccount, User user, Page page) throws Exception
    {
        StringBuffer sb = new StringBuffer();
        sb.append("select count(*) from user o where o.parent_code = '").append(user.getUserCode()).append("' ");
        if(null != querySubAccount)
        {
            String userCode = querySubAccount.getUserCode();
            if(null != userCode && !"".equals(userCode.trim()))
            {
                userCode = userCode.replaceAll("'", "‘");
                userCode = userCode.replaceAll("%", "％");
                sb.append("and o.user_code like '%").append(userCode.trim()).append("%' ");
            }
            
            String companyName = querySubAccount.getCompanyName();
            if(null != companyName && !"".equals(companyName.trim()))
            {
                companyName = companyName.replaceAll("'", "‘");
                companyName = companyName.replaceAll("%", "％");
                sb.append("and o.company_name like '%").append(companyName.trim()).append("%' ");
            }
            
            String userName = querySubAccount.getUserName();
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
    public List<User> queryAgentsList(User querySubAccount, User user, Page page) throws Exception
    {
        List<User> agentList = null;
        StringBuffer sb = new StringBuffer();
        sb.append("FROM User o WHERE o.parentCode = '").append(user.getUserCode()).append("' ");
        if(null != querySubAccount)
        {
            String userCode = querySubAccount.getUserCode();
            if(null != userCode && !"".equals(userCode.trim()))
            {
                userCode = userCode.replaceAll("'", "‘");
                userCode = userCode.replaceAll("%", "％");
                sb.append("and o.userCode like '%").append(userCode.trim()).append("%' ");
            }
            
            String companyName = querySubAccount.getCompanyName();
            if(null != companyName && !"".equals(companyName.trim()))
            {
                companyName = companyName.replaceAll("'", "‘");
                companyName = companyName.replaceAll("%", "％");
                sb.append("and o.companyName like '%").append(companyName.trim()).append("%' ");
            }
            
            String userName = querySubAccount.getUserName();
            if(null != userName && !"".equals(userName.trim()))
            {
                userName = userName.replaceAll("'", "‘");
                userName = userName.replaceAll("%", "％");
                sb.append("and o.userName like '%").append(userName.trim()).append("%' ");
            }
        }
        sb.append("ORDER BY o.userCode");
        
        
        final String hql = sb.toString();
        final int firstResult = page.getStartRows();
        final int maxResults = 10;
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
    
    @SuppressWarnings("unchecked")
    public String createSubAccount(User createUser, String subAccountCode) throws Exception
    {
        String hql = "FROM User o WHERE o.userCode = ?";
        List list = this.getHibernateTemplate().find(hql, subAccountCode);
        if(null != list && list.size() > 0)
        {
            return "创建子账户失败，该账号在系统中已存在，请更换其他账号";
        }
        
        hql = "FROM SysTelNumber o";
        List<SysTelNumber> telList = this.getHibernateTemplate().find(hql);
        SysTelNumber tel = telList.get(0);
        
        User user = new User();
        user.setUserCode(subAccountCode);
        user.setPassWord(DEFAULT_PASSWORD);
        user.setLevel(0);
        user.setUserType(Common.ENTERPRISE);
        user.setRemainingTime(0);
        user.setRegisterDate(new Date());
        user.setPort(String.valueOf(tel.getPort()));
        user.setSysTel(tel.getSysTel());
        user.setNumberLimit(0);
        user.setNumberLimit2(0);
        user.setErrorCount(0);
        user.setParentCode(createUser.getUserCode());
        user.setStatus("1");
        this.getHibernateTemplate().save(user);
        
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public User getUserInfo(String userCode) throws Exception
    {
        User user = null;
        String hql = "FROM User o WHERE o.userCode = ?";
        List list = this.getHibernateTemplate().find(hql, userCode);
        if(null != list && list.size() > 0)
        {
            user = (User)list.get(0);
        }
        
        return user;
    }
    
    @SuppressWarnings("unchecked")
    public User getUserInfoById(int userId) throws Exception
    {
        User user = (User)this.getHibernateTemplate().get(User.class, new Integer(userId));
        
        return user;
    }
    
    @SuppressWarnings("unchecked")
    public int getSubAccountCost(String userCode) throws Exception
    {
        int count = 0;
        int back = 0;
        StringBuffer sql = new StringBuffer();
        sql.append("select count(order_num) from order_record o where o.user_code = '");
        sql.append(userCode).append("' and o.order_type = '");
        sql.append(Common.ORDER_OUT).append("'");
        List tmplist = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createSQLQuery(sql.toString()).list();
        if (null != tmplist && tmplist.size() > 0)
        {
            count = ((BigInteger) tmplist.get(0)).intValue();
        }
        
        sql = new StringBuffer();
        sql.append("select count(order_num) from order_record o where o.user_code = '");
        sql.append(userCode).append("' and o.order_type = '");
        sql.append(Common.ORDER_BACK).append("'");
        tmplist = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createSQLQuery(sql.toString()).list();
        if (null != tmplist && tmplist.size() > 0)
        {
            back = ((BigInteger) tmplist.get(0)).intValue();
        }
        
        count = count - back;
        
        return count;
    }
    
    public void initPsw(User user) throws Exception
    {
        user.setPassWord(DEFAULT_PASSWORD);
        this.getHibernateTemplate().update(user);
    }
}
