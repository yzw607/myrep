package com.wmp.selfService.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.common.Common;
import com.wmp.common.bean.Page;
import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.selfService.bean.OrderRecord;
import com.wmp.selfService.bean.UserNumber;
import com.wmp.selfService.service.IPlanService;
import com.wmp.userManage.bean.User;

public class PlanServiceImpl extends HibernateDaoSupport implements IPlanService
{
    private static final int BUFFER_SIZE = 16*1024;
    
    @SuppressWarnings("unchecked")
    public Page getPage(User user, ActivityInfo queryActivity, Page page) throws Exception
    {
        StringBuffer sb = new StringBuffer();
        
        String userType = user.getUserType();
        if(userType.equals(Common.SYS_ADMIN))
        {
            sb.append("select count(*) from activity_info o where 1 = 1 ");
        }   
        else
        {
            sb.append("select count(*) from activity_info o where o.user_code = '").append(user.getUserCode());
            sb.append("' ");
        }
        
        
        if(null != queryActivity)
        {
            String title = queryActivity.getTitle();
            if(null != title && !"".equals(title.trim()))
            {
                title = title.replaceAll("'", "‘");
                title = title.replaceAll("%", "％");
                sb.append("and o.title like '%").append(title.trim()).append("%' ");
            }
            
            String status = queryActivity.getStatus();
            if("-1".equals(status))
            {
                status = "";
            }
            if(null != status && !"".equals(status.trim()))
            {
                sb.append("and o.status = '").append(status.trim()).append("' ");
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
    public List<ActivityInfo> queryActivityInfoList(User user, ActivityInfo queryActivity, Page page) throws Exception
    {
        List<ActivityInfo> activityList = null;
        StringBuffer sb = new StringBuffer();
        
        String userType = user.getUserType();
        if(userType.equals(Common.SYS_ADMIN))
        {
            sb.append("FROM ActivityInfo o WHERE 1 = 1 ");
        }
        else
        {
            sb.append("FROM ActivityInfo o WHERE o.userCode = '").append(user.getUserCode());
            sb.append("' ");
        }
        
        if(null != queryActivity)
        {
            String title = queryActivity.getTitle();
            if(null != title && !"".equals(title.trim()))
            {
                title = title.replaceAll("'", "‘");
                title = title.replaceAll("%", "％");
                sb.append("AND o.title LIKE '%").append(title.trim()).append("%' ");
            }
            
            String status = queryActivity.getStatus();
            if("-1".equals(status))
            {
                status = "";
            }
            if(null != status && !"".equals(status.trim()))
            {
                sb.append("and o.status = '").append(status.trim()).append("' ");
            }
        }
        
        sb.append("ORDER BY o.id DESC");
        
        final String hql = sb.toString();
        final int firstResult = page.getStartRows();
        final int maxResults = 10;
        activityList = getHibernateTemplate().executeFind(new HibernateCallback()
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
        
        return activityList;
    }
    
    public ActivityInfo queryActivityInfo(int activityId) throws Exception
    {
        ActivityInfo activityInfo = (ActivityInfo)this.getHibernateTemplate().get(ActivityInfo.class, activityId);
//        activityInfo.setUsed(TimingTool.numberIsUsed(activityInfo.getUserCode(), activityInfo.getNumber()));
        
        return activityInfo;
    }
    
    public User queryUserInfo(int userId) throws Exception
    {
        User user = (User)this.getHibernateTemplate().get(User.class, userId);
        return user;
    }
    
    @SuppressWarnings("unchecked")
    public String updateActivity(User user, ActivityInfo inputActivityInfo,
            File file, String contextPath) throws Exception
    {
        String msg = "";
        HibernateTemplate ht = this.getHibernateTemplate();
        String hql = "FROM ActivityInfo o WHERE o.id = ? and o.status != 3";
        List list = ht.find(hql, inputActivityInfo.getId());
        if (null == list || list.size() == 0)
        {
            msg = "该婚礼信息已结束，请您返回婚礼管理菜单后重新进入婚礼信息页面查看！";
            return msg;
        }

        ActivityInfo activity = (ActivityInfo) list.get(0);
        if (!user.getUserCode().equals(activity.getUserCode()))
        {
            msg = "数据有误，请进入婚礼管理模块刷新页面重试！";
            return msg;
        }

        activity.setNumber(inputActivityInfo.getNumber());
        activity.setTitle(inputActivityInfo.getTitle());
        activity.setPlanners(inputActivityInfo.getPlanners());
        activity.setPlannersTel(inputActivityInfo.getPlannersTel());
        activity.setSite(inputActivityInfo.getSite());
        activity.setSiteTel(inputActivityInfo.getSiteTel());
        activity.setBridegroom(inputActivityInfo.getBridegroom());
        activity.setBridegroomTel(inputActivityInfo.getBridegroomTel());
        activity.setBride(inputActivityInfo.getBride());
        activity.setBrideTel(inputActivityInfo.getBrideTel());
        activity.setStencilId(inputActivityInfo.getStencilId());
        activity.setStencilName(inputActivityInfo.getStencilName());
        activity.setHoldDate(inputActivityInfo.getHoldDate());
        activity.setPeriod(inputActivityInfo.getPeriod());
        activity.setWelcomeMsg(inputActivityInfo.getWelcomeMsg());
        activity.setAddress(inputActivityInfo.getAddress());
        activity.setRemark(inputActivityInfo.getRemark());
        activity.setPostAddress(inputActivityInfo.getPostAddress());
        activity.setPicId(inputActivityInfo.getPicId());

        StringBuffer bgPath = new StringBuffer();
        if (null != file)
        {
            String userCode = user.getUserCode();
            String fileName = inputActivityInfo.getPicFileName();
            InputStream in = null;
            OutputStream out = null;
            try
            {
                String folderPath = Common.SAVEPATH + userCode;
                File dirname = new File(folderPath);
                if (!dirname.isDirectory())
                {
                    dirname.mkdir();
                }

                folderPath = folderPath + "/" + activity.getId() + "/";
                dirname = new File(folderPath);
                if (!dirname.isDirectory())
                {
                    dirname.mkdir();
                }

                in = new BufferedInputStream(new FileInputStream(file),
                        BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(
                        new File(folderPath + fileName)), BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                int len = 0;
                while ((len = in.read(buffer)) > 0)
                {
                    out.write(buffer, 0, len);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (null != in)
                {
                    try
                    {
                        in.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                if (null != out)
                {
                    try
                    {
                        out.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            bgPath.append(contextPath).append("/upload/").append(userCode)
                    .append("/");
            bgPath.append(activity.getId()).append("/").append(fileName);

            activity.setPicFileName(fileName);
            activity.setBgPicPath(bgPath.toString());
        }

        this.getHibernateTemplate().update(activity);
        return msg;
    }
    
    @SuppressWarnings("unchecked")
    public List<UserNumber> queryUserNumbers(String userCode) throws Exception
    {
        String hql = "FROM UserNumber o WHERE o.userCode = ?";
        
        return this.getHibernateTemplate().find(hql, userCode);
    }
    
    public ActivityInfo saveActivity(ActivityInfo activity, File file,
            String contextPath) throws Exception
    {
        activity.setStatus("0");
        this.getHibernateTemplate().save(activity);

        String userCode = activity.getUserCode();
        StringBuffer bgPath = new StringBuffer();
        
        if (null != file)
        {
            String fileName = activity.getPicFileName();
            InputStream in = null;
            OutputStream out = null;
            try
            {
                String folderPath = Common.SAVEPATH + userCode;
                File dirname = new File(folderPath);
                if (!dirname.isDirectory())
                {
                    dirname.mkdir();
                }

                folderPath = folderPath + "/" + activity.getId() + "/";
                dirname = new File(folderPath);
                dirname.mkdir();

                in = new BufferedInputStream(new FileInputStream(file),
                        BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(
                        new File(folderPath + fileName)), BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                int len = 0;
                while ((len = in.read(buffer)) > 0)
                {
                    out.write(buffer, 0, len);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (null != in)
                {
                    try
                    {
                        in.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                if (null != out)
                {
                    try
                    {
                        out.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            bgPath.append(contextPath).append("/upload/").append(userCode)
                    .append("/");
            bgPath.append(activity.getId()).append("/").append(fileName);
        }
        
        activity.setBgPicPath(bgPath.toString());
        this.getHibernateTemplate().update(activity);

        return activity;
    }
    
    public void delPic(ActivityInfo activity) throws Exception
    {
        delActiviytPicFile(activity);
        
        activity.setPicFileName(null);
        activity.setBgPicPath(null);
        this.getHibernateTemplate().update(activity);
    }
    
    private void delActiviytPicFile(ActivityInfo activity) throws Exception
    {
        StringBuffer path = null;
        String fileName = activity.getPicFileName();
        String userCode = activity.getUserCode();
        String id = String.valueOf(activity.getId());
        File file = null;
        if(null != fileName && !"".equals(fileName.trim()))
        {
            path = new StringBuffer();
            path.append(Common.SAVEPATH).append(userCode).append("/");
            path.append(id).append("/").append(fileName);
            
            file = new File(path.toString());
            if(file.exists())
            {
                System.gc();
                file.delete();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public String doActivate(User user, ActivityInfo inputActivityInfo,
            File file, String contextPath) throws Exception
    {
        String result = "";
        HibernateTemplate ht = this.getHibernateTemplate();

        String hql = "FROM ActivityInfo o WHERE o.id = ? and o.status != 3";
        List list = ht.find(hql, inputActivityInfo.getId());
        if (null == list || list.size() == 0)
        {
            result = "该婚礼信息已过期货，请您返回婚礼管理菜单后重新进入婚礼信息页面查看！";
            return result;
        }

        ActivityInfo activity = (ActivityInfo) list.get(0);
        if (!user.getUserCode().equals(activity.getUserCode()))
        {
            result = "数据有误，请进入婚礼管理模块刷新页面重试！";
            return result;
        }
        
        activity.setNumber(inputActivityInfo.getNumber());
        activity.setTitle(inputActivityInfo.getTitle());
        activity.setPlanners(inputActivityInfo.getPlanners());
        activity.setPlannersTel(inputActivityInfo.getPlannersTel());
        activity.setSite(inputActivityInfo.getSite());
        activity.setSiteTel(inputActivityInfo.getSiteTel());
        activity.setBridegroom(inputActivityInfo.getBridegroom());
        activity.setBridegroomTel(inputActivityInfo.getBridegroomTel());
        activity.setBride(inputActivityInfo.getBride());
        activity.setBrideTel(inputActivityInfo.getBrideTel());
        activity.setStencilId(inputActivityInfo.getStencilId());
        activity.setStencilName(inputActivityInfo.getStencilName());
        activity.setHoldDate(inputActivityInfo.getHoldDate());
        activity.setPeriod(inputActivityInfo.getPeriod());
        activity.setWelcomeMsg(inputActivityInfo.getWelcomeMsg());
        activity.setAddress(inputActivityInfo.getAddress());
        activity.setRemark(inputActivityInfo.getRemark());
        activity.setPostAddress(inputActivityInfo.getPostAddress());
        activity.setPicId(inputActivityInfo.getPicId());

        StringBuffer bgPath = new StringBuffer();
        if (null != file)
        {
            String userCode = user.getUserCode();
            String fileName = inputActivityInfo.getPicFileName();
            InputStream in = null;
            OutputStream out = null;
            try
            {
                String folderPath = Common.SAVEPATH + userCode;
                File dirname = new File(folderPath);
                if (!dirname.isDirectory())
                {
                    dirname.mkdir();
                }

                folderPath = folderPath + "/" + activity.getId() + "/";
                dirname = new File(folderPath);
                if (!dirname.isDirectory())
                {
                    dirname.mkdir();
                }

                in = new BufferedInputStream(new FileInputStream(file),
                        BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(
                        new File(folderPath + fileName)), BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                int len = 0;
                while ((len = in.read(buffer)) > 0)
                {
                    out.write(buffer, 0, len);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (null != in)
                {
                    try
                    {
                        in.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                if (null != out)
                {
                    try
                    {
                        out.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            bgPath.append(contextPath).append("/upload/").append(userCode)
                    .append("/");
            bgPath.append(activity.getId()).append("/").append(fileName);

            activity.setPicFileName(fileName);
            activity.setBgPicPath(bgPath.toString());
        }

        String[] periods = inputActivityInfo.getPeriod().split(",");
        StringBuffer sb = new StringBuffer();
        sb
                .append("FROM ActivityInfo o WHERE o.holdDate = ? AND o.number = ? AND o.userCode = ? AND o.status = 1 AND (");
        int len = periods.length;
        for (int i = 0; i < len; i++)
        {
            sb.append("o.period like '%").append(periods[i].trim()).append(
                    "%' ");

            if (i == len - 1)
            {
                sb.append(")");
            }
            else
            {
                sb.append(" or ");
            }
        }

        list = ht.find(sb.toString(), new Object[] {
                inputActivityInfo.getHoldDate(), inputActivityInfo.getNumber(),
                user.getUserCode() });
        if (null != list && list.size() > 0)
        {
            this.getHibernateTemplate().update(activity);
            result = "该祝福短号在您所选的日期及时段中有冲突，请您更换其他的祝福短号！";
            return result;
        }

        User u = (User) ht.get(User.class, user.getUserId());
        int remainingTime = u.getRemainingTime();
        if (remainingTime < len)
        {
            result = "您账户余额不够此次婚礼使用，请您充值！";
            this.getHibernateTemplate().update(activity);
            return result;
        }
        remainingTime = remainingTime - len;

        OrderRecord record = new OrderRecord();
        record.setParentCode(user.getParentCode());
        record.setUserCode(u.getUserCode());
        record.setOrderType(Common.ORDER_OUT);
        record.setOrderNum(len);
        record.setCostNumber(inputActivityInfo.getNumber());
        record.setOriginalNum(u.getRemainingTime());
        record.setOrderDate(new Date());
        record.setLastNum(remainingTime);
        record.setTitle(inputActivityInfo.getTitle());
        record.setActivityId(activity.getId());
        this.getHibernateTemplate().save(record);

        u.setRemainingTime(remainingTime);
        activity.setStatus("1");

        ht.update(u);
        ht.update(activity);
        ht.save(record);

        return result;
    }
    
    
    @SuppressWarnings("unchecked")
    public String doOrderBack(User user, ActivityInfo inputActivityInfo) throws Exception
    {
        String result = "";
        HibernateTemplate ht = this.getHibernateTemplate();
        
        String hql = "FROM ActivityInfo o WHERE o.id = ? and o.status = 1 and o.userCode = ?";
        List list = ht.find(hql, new Object[]{inputActivityInfo.getId(), user.getUserCode()});
        if(null == list || list.size() == 0)
        {
            result = "该婚礼信息不正确，请您返回婚礼管理菜单后重新进入婚礼信息页面查看！";
            return result;
        }
        
        ActivityInfo activity = (ActivityInfo)list.get(0);

        String[] periods = activity.getPeriod().split(",");
        
        int count = periods.length;
        User u = (User)ht.get(User.class, user.getUserId());
        int remainingTime = u.getRemainingTime() + periods.length;
        
        OrderRecord record = new OrderRecord();
        record.setUserCode(u.getUserCode());
        record.setParentCode(user.getParentCode());
        record.setOrderType(Common.ORDER_BACK);
        record.setOrderNum(count);
        record.setCostNumber(activity.getNumber());
        record.setOriginalNum(u.getRemainingTime());
        record.setOrderDate(new Date());
        record.setLastNum(remainingTime);
        record.setTitle(inputActivityInfo.getTitle());
        record.setActivityId(activity.getId());
        this.getHibernateTemplate().save(record);
        
        
        u.setRemainingTime(remainingTime);
        activity.setStatus("0");
        
        ht.update(u);
        ht.update(activity);
        ht.save(record);
        
        result = "退订成功，您的使用次数已返还至您的账户，可到账单查询进行查看记录";
        
        return result;
    }
    
    
    
    

    @SuppressWarnings("unchecked")
    public List<Object> doExport(int activityId) throws Exception
    {
        ActivityInfo activity = (ActivityInfo)this.getHibernateTemplate().get(ActivityInfo.class, activityId);
        String holdDate = activity.getHoldDate();
        String period = activity.getPeriod();
        String startTime = null;
        String endTime = null;
        
        if("a".equals(period))
        {
            startTime = holdDate + " 06:00:00";
            endTime = holdDate + " 15:00:00";
        }
        else if("b".equals(period))
        {
            startTime = holdDate + " 15:00:00";
            endTime = holdDate + " 24:00:00";
        }
        else if("c".equals(period))
        {
            startTime = holdDate + " 00:00:00";
            endTime = holdDate + " 06:00:00";
        }
        else if("a, b".equals(period))
        {
            startTime = holdDate + " 06:00:00";
            endTime = holdDate + " 24:00:00";
        }
        else
        {
            startTime = holdDate + " 00:00:00";
            endTime = holdDate + " 24:00:00";
        }
        
        
//        String sql = "select sender, msgTitle, b.name from msg_backup a left join name_point b on a.sender = b.tel_number and a.activity_id = "
//                + activityId;
        
        
        StringBuffer sql = new StringBuffer();
        sql.append("select a.*, b.name from (select sender, msgTitle from msg_backup a ");
        sql.append("where MsgTitle like '").append(activity.getNumber()).append("%' and MsgArrivedTime > '");
        sql.append(startTime).append("' and MsgArrivedTime < '").append(endTime).append("') a ");
        sql.append("left join name_point b on a.sender = b.tel_number");

        List<Object> tmplist = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createSQLQuery(sql.toString()).list();

        return tmplist;
    }
}
