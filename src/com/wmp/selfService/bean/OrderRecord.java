package com.wmp.selfService.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.wmp.common.Common;

public class OrderRecord
{
    private int recordId;//
    private String userCode;// 用户账号
    private String orderType;// 订购类型 消费或充值
    private int orderNum;// 订购数量
    private String costNumber;
    private Date orderDate;// 订购日期
    private int originalNum;
    private int lastNum;// 剩余数量
    private int activityId;
    private String title;
    private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private String orderTypeStr;
    private String orderDateStr;
    private String parentCode;

    public String getOrderDateStr()
    {
        if(null == orderDate) orderDateStr = "";
        
        
        orderDateStr = formatter.format(orderDate);
        
        return orderDateStr;
    }

    public void setOrderDateStr(String orderDateStr)
    {
        this.orderDateStr = orderDateStr;
    }

    public String getOrderTypeStr()
    {
        if(null == orderType) return "";
        
        if(Common.ORDER_HANDSEL.equals(orderType))
        {
            orderTypeStr = Common.ORDER_HANDSEL_STR;
        }
        else if(Common.ORDER_IN.equals(orderType))
        {
            orderTypeStr = Common.ORDER_IN_STR;
        }
        else if(Common.ORDER_IN2.equals(orderType))
        {
            orderTypeStr = Common.ORDER_IN2_STR;
        }
        else if(Common.ORDER_OUT.equals(orderType))
        {
            orderTypeStr = Common.ORDER_OUT_STR;
        }
        else if(Common.ORDER_BACK.equals(orderType))
        {
            orderTypeStr = Common.ORDER_BACK_STR;
        }
        
        return orderTypeStr;
    }

    public void setOrderTypeStr(String orderTypeStr)
    {
        this.orderTypeStr = orderTypeStr;
    }

    public int getRecordId()
    {
        return recordId;
    }

    public void setRecordId(int recordId)
    {
        this.recordId = recordId;
    }

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public String getOrderType()
    {
        return orderType;
    }

    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public int getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(int orderNum)
    {
        this.orderNum = orderNum;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public int getLastNum()
    {
        return lastNum;
    }

    public void setLastNum(int lastNum)
    {
        this.lastNum = lastNum;
    }

    public String getCostNumber()
    {
        return costNumber;
    }

    public void setCostNumber(String costNumber)
    {
        this.costNumber = costNumber;
    }

    public int getOriginalNum()
    {
        return originalNum;
    }

    public void setOriginalNum(int originalNum)
    {
        this.originalNum = originalNum;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getActivityId()
    {
        return activityId;
    }

    public void setActivityId(int activityId)
    {
        this.activityId = activityId;
    }

    public String getParentCode()
    {
        return parentCode;
    }

    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }

}
