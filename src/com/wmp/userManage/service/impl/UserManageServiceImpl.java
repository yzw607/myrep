package com.wmp.userManage.service.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.common.Common;
import com.wmp.common.bean.Page;
import com.wmp.userManage.bean.User;
import com.wmp.userManage.service.IUserManageService;


public class UserManageServiceImpl extends HibernateDaoSupport implements IUserManageService
{
    /**
     * 查询用户列表记录总数
     * @param user
     * @return
     */
    @SuppressWarnings("unchecked")
    public Page countUsers(Page page, String condition)
    {
        StringBuffer hql = new StringBuffer();
        hql.append("SELECT COUNT(*) FROM User o WHERE 1 = 1 ").append(condition);

        Long count = (Long)this.getHibernateTemplate().find(hql.toString()).get(0);
        int maxRows = 0;
        maxRows = count.intValue();
        
        page.setMaxRows(maxRows);
        page.setMaxPage(maxRows % Common.LINESIZE + 1);
        
        return page;
    }
    
    /**
     * 查询用户列表
     * @param page
     * @param condition
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<User> queryUserList(Page page, String condition)
    {
        StringBuffer hql = new StringBuffer();
        hql.append("SELECT o FROM User o WHERE 1 = 1 ").append(condition);
        hql.append(" ORDER BY o.userId DESC LIMIT ").append(page.getStartRows()).append(", ");
        hql.append(page.getEndRows());
        
        List<User> list = this.getHibernateTemplate().find(hql.toString());
        
        return list;
    }
    
    /**
     * 查询单个用户信息
     * @param userId
     * @return
     */
    public User showUser(int userId) throws Exception
    {
        User user = (User)this.getHibernateTemplate().get(User.class, userId);
        return user;
    }
    
    /**
     * 更新用户信息
     * @param user
     * @throws Exception
     */
    public void updateUser(User user) throws Exception
    {
        this.getHibernateTemplate().update(user);
    }
    
    /**
     * 用户充值
     * @param userId
     * @param recharge
     * @param handsel
     */
    public void recharge(int userId, int recharge, int handsel)
    {
//        User user = (User)this.getHibernateTemplate().get(User.class, userId);
//        
//        if(recharge != 0)
//        {
//            OrderRecord record = new OrderRecord();
//            record.setUserCode(user.getUserCode());
//            record.setParentCode(user.getParentCode());
//            record.setOrderType(Common.ORDER_IN);
//            record.setOrderNum(recharge * 60);
//            record.setOrderDate(Common.changeDateFormat(new Date()));
//            record.setOriginalNum(user.getRemainingTime());
//            record.setLastNum(user.getRemainingTime() + recharge * 60);
//            this.getHibernateTemplate().save(record);
//            
//            user.setRemainingTime(user.getRemainingTime() + recharge * 60);
//        }
//        
//        if(handsel != 0)
//        {
//            OrderRecord record = new OrderRecord();
//            record.setUserCode(user.getUserCode());
//            record.setOrderType(Common.ORDER_HANDSEL);
//            record.setOrderNum(handsel * 60);
//            record.setOrderDate(Common.changeDateFormat(new Date()));
//            record.setOriginalNum(user.getRemainingTime());
//            record.setLastNum(user.getRemainingTime() + handsel * 60);
//            this.getHibernateTemplate().save(record);
//            
//            user.setRemainingTime(user.getRemainingTime() + handsel * 60);
//        }
//        
//        this.getHibernateTemplate().update(user);
    }
}
