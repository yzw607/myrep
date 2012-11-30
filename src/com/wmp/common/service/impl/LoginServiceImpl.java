package com.wmp.common.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.common.Common;
import com.wmp.common.MailUtil;
import com.wmp.common.service.ILoginService;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.userManage.bean.User;

public class LoginServiceImpl extends HibernateDaoSupport implements
        ILoginService
{
    private static final char[] PSW_LETTER = ("23456789abcdefghijkmnpqrstuvwxyz").toCharArray();
    
    /**
     * 查询用户信息
     * @param usercode
     * @param password
     * @return
     */
    @SuppressWarnings("unchecked")
    public User findUserByUsercode(User tmpUser)
    {
        User user = null;
        String hql = "FROM User o WHERE o.userCode = ? and o.passWord = ?";
        List<User> list = this.getHibernateTemplate().find(hql,
                new Object[] { tmpUser.getUserCode(), tmpUser.getPassWord() });

//        if (null != list && 0 != list.size())
//        {
//            user = list.get(0);
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//
//            if (Common.TIME.equals(user.getUserType())
//                    && null != user.getEndDate())
//            {
//                //如果是包时段用户
//                String nowdateStr = formatter.format(new Date()).replaceAll("-", "");
//                long nowdate = Long.parseLong(nowdateStr);
//                long enddate = Long.parseLong(user.getEndDateStr().replaceAll("-", ""));
//
//                long result = nowdate - enddate;
//                if(result==0 || result < 0)
//                {
//                    user.setActive(true);
//                }
//                else
//                {
//                    user.setActive(false);
//                }
//
//            }
//            else if(Common.COUNT.equals(user.getUserType()))
//            {
//                //如果是计次用户
//                String hql2 = "FROM OrderRecord o WHERE o.userCode = ? AND o.orderType = ? ORDER BY o.recordId DESC LIMIT 1, 1";
//                List tmpList = this.getHibernateTemplate().find(hql2, new Object[]{user.getUserCode(), Common.ORDER_OUT});
//                if(null == tmpList || tmpList.size() == 0)
//                {
//                    //该用户还没有消费记录
//                    user.setActive(false);
//                }
//                else
//                {
//                    OrderRecord record = (OrderRecord)tmpList.get(0);
//                    String nowdateStr = formatter.format(new Date()).replaceAll("-", "");
//                    String orderDateStr = formatter.format(record.getOrderDate()).replaceAll("-", "");
//                    long nowdate = Long.parseLong(nowdateStr);
//                    long enddate = Long.parseLong(orderDateStr);
//
//                    long result = nowdate - enddate;
//                    if(result==0)
//                    {
//                        user.setActive(true);
//                    }
//                    else
//                    {
//                        user.setActive(false);
//                    }
//                }
//                
//            }
//        }
        
        
        if (null != list && 0 != list.size())
        {
            user = list.get(0);
        }

        return user;
    }
    
    @SuppressWarnings("unchecked")
    public User findUser(String userCode) throws Exception
    {
        User user = null;
        String hql = "FROM User o WHERE o.userCode = ? ";
        List<User> list = this.getHibernateTemplate().find(hql, userCode);
        
        if (null != list && 0 != list.size())
        {
            user = list.get(0);
        }

        return user;
    }
    
    @SuppressWarnings("unchecked")
    public String getForgetPsw(String userCode) throws Exception
    {
        User user = null;
        String hql = "FROM User o WHERE o.userCode = ?";
        List<User> list = this.getHibernateTemplate().find(hql, userCode);
        if(null != list && list.size() > 0)
        {
            user = list.get(0);
            
            Random randGen = new Random();

            char[] randBuffer = new char[10];
            for (int i = 0; i < 10; i++)
            {
                randBuffer[i] = PSW_LETTER[randGen.nextInt(32)];
            }
            
            String newPsw = new String(randBuffer);
            user.setPassWord(newPsw);
            this.getHibernateTemplate().update(user);
            
            String email = user.getEmail();
            
            StringBuffer sb = new StringBuffer();
            sb.append(user.getUserName()).append("，您好！<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
            sb.append("您在我爱说 www.say520.cn 注册的账号密码已被系统重置，新的密码为：【").append(newPsw).append("】， 请您使用新的密码登陆系统，谢谢！<p/><p/>");
            sb.append("我爱说团队<br/>").append(Common.getSysTimeStr());

            MailUtil themail = new MailUtil("SMTP.139.COM");
            themail.setNeedAuth(true);

            themail.setSubject("您的 我爱说 www.say520.cn 网站登陆密码已被重置，请立刻登陆并更换新密码！");
            themail.setBody(sb.toString());
            themail.setTo(email);
            themail.setFrom("13995548333@139.com");
            themail.setNamePass("13995548333", "ling140");
            themail.sendout();
            
            StringBuffer sb2 = new StringBuffer();
            int index = email.indexOf('@');
            if(index < 1)
            {
                return "";
            }
            
            sb2.append(email.charAt(0)).append("*****").append(email.charAt(index - 1));
            sb2.append(email.substring(index, email.length()));
            
            return sb2.toString();
        }
        else
        {
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<ActivityInfo> querySureActivityInfoList(final  User user) throws Exception
    {
        List<ActivityInfo> activityList = null;
        StringBuffer sb = new StringBuffer();
        sb.append("FROM ActivityInfo o WHERE o.userCode = ? ");
        sb.append(" AND o.status in ('0', '1', '2') ORDER BY o.holdDate");
        
        final String hql = sb.toString();
        final int firstResult = 0;
        final int maxResults = 5;
        activityList = getHibernateTemplate().executeFind(new HibernateCallback()
        {
            public Object doInHibernate(final Session session)
                    throws HibernateException, SQLException
            {
            	final  Query query = session.createQuery(hql);
                query.setParameter(0, user.getUserCode());  
                query.setFirstResult(firstResult);
                query.setMaxResults(maxResults);
                List list = query.list();
                return list;
            }
        });
        
        return activityList;
    }
    
    public User updateUserInfo(User tmpUser) throws Exception
    {
        HibernateTemplate ht = this.getHibernateTemplate();
        User user = (User)ht.get(User.class, tmpUser.getUserId());
        
        user.setUserName(tmpUser.getUserName());
        user.setTel(tmpUser.getTel());
        user.setEmail(tmpUser.getEmail());
        user.setCompanyName(tmpUser.getCompanyName());
        user.setCompanyAddress(tmpUser.getCompanyAddress());
        ht.update(user);
        
        return user;
    }
}
