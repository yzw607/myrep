package com.wmp.selfService.bean;

import java.util.Date;

public class AlipayOrder
{
    private int orderId;
    private String orderCode;
    private String userCode;
    private int payNum;
    private int payMoney;
    private Date orderDate;
    private Date payDate;
    private String tradeNo;
    private int status;

    public int getOrderId()
    {
        return orderId;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public int getPayNum()
    {
        return payNum;
    }

    public void setPayNum(int payNum)
    {
        this.payNum = payNum;
    }

    public int getPayMoney()
    {
        return payMoney;
    }

    public void setPayMoney(int payMoney)
    {
        this.payMoney = payMoney;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public Date getPayDate()
    {
        return payDate;
    }

    public void setPayDate(Date payDate)
    {
        this.payDate = payDate;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getTradeNo()
    {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo)
    {
        this.tradeNo = tradeNo;
    }
}
