package com.wmp.common.bean;

public class UploadPic
{
    private int picId;
    private String fileName;
    private String activityId;
    private String userCode;

    public int getPicId()
    {
        return picId;
    }

    public void setPicId(int picId)
    {
        this.picId = picId;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
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
