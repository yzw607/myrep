package com.wmp.selfService.bean;

import java.util.Date;

public class Card
{
    private int cardId;
    private String cardCode;
    private String cardType;
    private int cardValue;
    private String userCode;
    private Date rechargeDate;
    private int status;

    public int getCardId()
    {
        return cardId;
    }

    public void setCardId(int cardId)
    {
        this.cardId = cardId;
    }

    public String getCardCode()
    {
        return cardCode;
    }

    public void setCardCode(String cardCode)
    {
        this.cardCode = cardCode;
    }

    public String getCardType()
    {
        return cardType;
    }

    public void setCardType(String cardType)
    {
        this.cardType = cardType;
    }

    public int getCardValue()
    {
        return cardValue;
    }

    public void setCardValue(int cardValue)
    {
        this.cardValue = cardValue;
    }

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public Date getRechargeDate()
    {
        return rechargeDate;
    }

    public void setRechargeDate(Date rechargeDate)
    {
        this.rechargeDate = rechargeDate;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
}
