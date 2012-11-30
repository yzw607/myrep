package com.wmp.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TimeTasker extends HibernateDaoSupport
{
    public void setPeriodA()
    {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try
        {
            String sysDate = Common.getSysDateStr();
            System.out.println("==================进入 " + sysDate + "第一档任务====================");
            conn = DBHelper.getConnection();
            st = conn.createStatement();
            
            String sql = "update activity_info o set o.status = '3' where o.status = '2' and o.id > 0";
            st.execute(sql);
            
            sql = "update activity_info o set o.status = '2' where o.status != '0' and o.hold_date = '"
                + sysDate + "' and o.period like '%a%' and o.id > 0";
            st.execute(sql);
            
            sql = "insert into msg_backup(Sender, MsgType, MsgTitle, MMSUrl, MMSContentLocation, MsgArrivedTime, CommPort, activity_id) select Sender, MsgType, MsgTitle, MMSUrl, MMSContentLocation, MsgArrivedTime, CommPort, activity_id from msg_inbox";
            st.execute(sql);
            
            sql = "truncate table msg_inbox";
            st.execute(sql);
            System.out.println("================== " + sysDate + "第一档任务成功执行====================");
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            DBHelper.free(rs, st, conn);
        }
    }
    
    public void setPeriodB()
    {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try
        {
            String sysDate = Common.getSysDateStr();
            System.out.println("==================进入 " + sysDate + "第二档任务====================");
            conn = DBHelper.getConnection();
            st = conn.createStatement();
            
            String sql = "update activity_info o set o.status = '3' where o.status = '2' and o.id > 0";
            st.execute(sql);
            
            sql = "update activity_info o set o.status = '2' where o.status != '0' and o.hold_date = '"
                + sysDate + "' and o.period like '%b%' and o.id > 0";
            st.execute(sql);
            
            sql = "insert into msg_backup(Sender, MsgType, MsgTitle, MMSUrl, MMSContentLocation, MsgArrivedTime, CommPort, activity_id) select Sender, MsgType, MsgTitle, MMSUrl, MMSContentLocation, MsgArrivedTime, CommPort, activity_id from msg_inbox";
            st.execute(sql);
            
            sql = "truncate table msg_inbox";
            st.execute(sql);
            System.out.println("================== " + sysDate + "第二档任务成功执行====================");
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            DBHelper.free(rs, st, conn);
        }
    }
    
    public void setPeriodC()
    {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try
        {
            String sysDate = Common.getSysDateStr();
            System.out.println("==================进入 " + sysDate + "第三档任务====================");
            conn = DBHelper.getConnection();
            st = conn.createStatement();
            
            String sql = "update activity_info o set o.status = '3' where o.status = '2' and o.id > 0";
            st.execute(sql);
            
            sql = "update activity_info o set o.status = '2' where o.status != '0' and o.hold_date = '"
                + sysDate + "' and o.period like '%c%' and o.id > 0";
            st.execute(sql);
            
            sql = "insert into msg_backup(Sender, MsgType, MsgTitle, MMSUrl, MMSContentLocation, MsgArrivedTime, CommPort, activity_id) select Sender, MsgType, MsgTitle, MMSUrl, MMSContentLocation, MsgArrivedTime, CommPort, activity_id from msg_inbox";
            st.execute(sql);
            
            sql = "truncate table msg_inbox";
            st.execute(sql);
            System.out.println("================== " + sysDate + "第三档任务成功执行====================");
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            DBHelper.free(rs, st, conn);
        }
    }
    
    public void updateErrorStatus()
    {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try
        {
            System.out.println("==================进入充值错误状态初始化任务====================");
            conn = DBHelper.getConnection();
            st = conn.createStatement();
            
            String sql = "update user o set o.error_count = 0 where o.error_count > 0";
            st.execute(sql);
            System.out.println("================== 充值错误状态初始化任务成功执行====================");
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            DBHelper.free(rs, st, conn);
        }
    }
}
