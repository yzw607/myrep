package com.wmp.selfService.bean;

public class UserNumber
{
    private int numberId;
    private String number;
    private String numberType;
    private String userCode;
    private String port;
    private String sysTel;

    private boolean isUsed;

    public boolean isUsed()
    {
        return isUsed;
    }

    public void setUsed(boolean isUsed)
    {
        this.isUsed = isUsed;
    }

    public int getNumberId()
    {
        return numberId;
    }

    public void setNumberId(int numberId)
    {
        this.numberId = numberId;
    }

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getNumberType()
    {
        return numberType;
    }

    public void setNumberType(String numberType)
    {
        this.numberType = numberType;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public String getSysTel()
    {
        return sysTel;
    }

    public void setSysTel(String sysTel)
    {
        this.sysTel = sysTel;
    }
}
