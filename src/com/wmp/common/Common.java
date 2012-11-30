package com.wmp.common;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class Common
{
    public final static String USERINFO = "userinfo"; //用户信息
    public final static String CONSOLE_USER = "consoleUser";//系统管理员信息

    public final static int VIP1 = 1;
    public final static int VIP2 = 2;
    public final static int VIP3 = 3;

    public final static String VIP0_STR = "普通用户";
    public final static String VIP1_STR = "VIP1用户";
    public final static String VIP2_STR = "VIP2用户";
    public final static String VIP3_STR = "VIP3用户";

//    public final static String COUNT = "count";
//    public final static String TIME = "time";
//    public final static String COUNT_STR = "计次付费";
//    public final static String TIME_STR = "包时付费";
//    public final static String NULL_STR = "尚未设置";
    
    public final static String PERSONAL = "personal";
    public final static String ENTERPRISE = "enterprise";
    public final static String AGENTS = "agents";
    public final static String SYS_ADMIN = "sys_admin";
    public final static String PERSONAL_STR = "个人用户";
    public final static String ENTERPRISE_STR = "企业用户";
    public final static String AGENTS_STR = "代理商";
    public final static String SYS_ADMIN_STR = "系统管理员";
    public final static int PERSONAL_NUM = 1;
    public final static int ENTERPRISE_NUM = 5;

    public final static String ACTIVE_YES = "已激活";
    public final static String ACTIVE_NO = "未激活";
    
    public final static String ORDER_IN = "order";
    public final static String ORDER_OUT = "cost";
    public final static String ORDER_BACK = "back";
    public final static String ORDER_IN2 = "order2";
    public final static String ORDER_HANDSEL = "handsel";
    public final static String ORDER_IN_STR = "实物卡充值";
    public final static String ORDER_IN2_STR = "支付宝充值";
    public final static String ORDER_OUT_STR = "用户消费";
    public final static String ORDER_HANDSEL_STR = "系统赠送";
    public final static String ORDER_BACK_STR = "用户退订";
    
    public final static Long ALERT_MIN = 10L;
    public static final String EMPTY = "empty";
    public static final String LESS = "less";
    public static final String MORE = "more";
    
    public final static String HANDSEL_CARD = "handsel";
    public final static String RECHARGE_CARD = "recharge";
    
    public static final int IDLE_NUMS = 18;

    public final static int LINESIZE = 10;//每页显示数量
    
    public final static String PERIOD_A = "06:00 — 15:00";
    public final static String PERIOD_B = "15:00 — 24:00";
    public final static String PERIOD_C = "00:00 - 06:00";

//    public final static String SAVEPATH = "c:/Program Files/apache-tomcat-6.0.18/webapps/wmp/upload/";
    public final static String SAVEPATH = "D:/wmp/upload/";
    public final static String LICENSE_PATH = "D:/wmp/license/";
    
    public static java.util.Date getSQLSysTime() throws ParseException
    {
        Calendar c = Calendar.getInstance();
        java.util.Date date = c.getTime();

        return date;
    }

    public static Date StringToDate(String dateStr)
    {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try
        {
            date = sdf.parse(dateStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }
    
    public static String getSysDateStr()
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);

        return dateString;
    }
    
    public static String getSysTimeStr()
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);

        return dateString;
    }
    
    public static Date changeDateFormat(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.format(date);  
        
        return date;
    }
    
    public static String getIpAddr(HttpServletRequest request)
    {
        String ipAddress = null;
        // ipAddress = this.getRequest().getRemoteAddr();
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress))
        {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress))
        {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress))
        {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1"))
            {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try
                {
                    inet = InetAddress.getLocalHost();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15)
        { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0)
            {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }


}
