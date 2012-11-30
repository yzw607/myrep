package com.wmp.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class TimingTool
{
    private static HashMap<String, HashMap<String, String>> timingMap = new HashMap<String, HashMap<String, String>>();
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @SuppressWarnings("unchecked")
    public static void startTiming(String userCode, String number, String nowDate)
    {
        HashMap<String, String> timeMap = null;
        Object obj = timingMap.get(userCode);
        
        if(null == obj)
        {
            timeMap = new HashMap<String, String>();
            
        }
        else
        {
            timeMap = (HashMap<String, String>)obj;
        }
        
        timeMap.put(number, nowDate);
        timingMap.put(userCode, timeMap);
    }
    
    @SuppressWarnings("unchecked")
    public static int endTiming(String userCode, String number, String nowDate)
    {
        long costTime = 0;
        HashMap<String, String> timeMap = null;
        Object obj = timingMap.get(userCode);
        
        if(null == obj)
        {
            return 0;
        }
        
        timeMap = (HashMap<String, String>)obj;
        obj = timeMap.get(number);
        if(null == obj)
        {
            return 0;
        }
        
        String startDate = (String)obj;
        try
        {
            Date bdate = formatter.parse(startDate);
            Date edate = formatter.parse(nowDate);
            long begin = (bdate.getTime() / 1000);
            long end = (edate.getTime() / 1000);
            
            costTime = (end - begin) / 60;
            if((end - begin) % 60 > 0)
            {
                costTime++;
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        timeMap.remove(number);
        
        if(timeMap.isEmpty())
        {
            timingMap.remove(userCode);
        }
        
        return new Long(costTime).intValue();
    }
    
    @SuppressWarnings("unchecked")
    public static String getUserRemainingTimeFlag(String userCode, long remainingTime)
    {
        String flag = null;
        HashMap<String, String> timeMap = null;
        Object obj = timingMap.get(userCode);
        
        if(null == obj)
        {
            if(remainingTime == 0)
            {
                flag = Common.EMPTY;
            }
            else if(remainingTime <= Common.ALERT_MIN)
            {
                flag = Common.LESS;
            }
            else
            {
                flag = Common.MORE;
            }
            
            return flag;
        }
        
        long now = new Date().getTime() / 1000;
        long start = 0;
        long seconds = 0;
        long mins = 0;
        
        timeMap = (HashMap<String, String>)obj;
        String number = null;
        Date bdate = null;
        Iterator iterator = timeMap.keySet().iterator();
        while(iterator.hasNext())
        {
            number = (String)iterator.next();
            
            try
            {
                bdate = formatter.parse(timeMap.get(number));
                start = bdate.getTime() / 1000;
                
                seconds = now - start;
                mins = seconds / 60;
                if(seconds % 60 > 0)
                {
                    mins++;
                }
                
                remainingTime = remainingTime - mins;
                if(remainingTime <= 0)
                {
                    flag = Common.EMPTY;
                    return flag;
                }
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        
        if(remainingTime <= Common.ALERT_MIN)
        {
            flag = Common.LESS;
        }
        else
        {
            flag = Common.MORE;
        }
        
        return flag;
    }
    
    @SuppressWarnings("unchecked")
    public static boolean numberIsUsed(String userCode, String number)
    {
        boolean flag = false;
        HashMap<String, String> timeMap = null;
        Object obj = timingMap.get(userCode);
        
        if(null == obj)
        {
            return flag;
        }
        
        timeMap = (HashMap<String, String>)obj;
        obj = timeMap.get(number);
        if(null == obj)
        {
            return flag;
        }
        else
        {
            flag = true;
            return flag;
        }
        
    }
}
