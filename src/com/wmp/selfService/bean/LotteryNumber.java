package com.wmp.selfService.bean;

public class LotteryNumber
{
    private int numId;
    private int showIndex;
    private String showNumber;
    private String activityId;
    private String userCode;

    public int getNumId()
    {
        return numId;
    }

    public void setNumId(int numId)
    {
        this.numId = numId;
    }

    public int getShowIndex()
    {
        return showIndex;
    }

    public void setShowIndex(int showIndex)
    {
        this.showIndex = showIndex;
    }

    public String getShowNumber()
    {
        return showNumber;
    }

    public void setShowNumber(String showNumber)
    {
        this.showNumber = showNumber;
    }

    public String getActivityId()
    {
        return activityId;
    }

    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }
}
