package com.wmp.sms.bean;

import java.util.Date;

public class MsgInBox
{
    private int id;
    private String sender;
    private int msgType;
    private String msgTitle;
    private String MMSURL;
    private String MMSContentLocation;
    private int MMSDownloadedState;
    private Date msgArrivedTime;
    private int commPort;
    private String status;
    private String activityId;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSender()
    {
        return sender;
    }

    public void setSender(String sender)
    {
        this.sender = sender;
    }

    public String getMsgTitle()
    {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle)
    {
        this.msgTitle = msgTitle;
    }

    public String getMMSURL()
    {
        return MMSURL;
    }

    public void setMMSURL(String mmsurl)
    {
        MMSURL = mmsurl;
    }

    public String getMMSContentLocation()
    {
        return MMSContentLocation;
    }

    public void setMMSContentLocation(String contentLocation)
    {
        MMSContentLocation = contentLocation;
    }

    public int getMsgType()
    {
        return msgType;
    }

    public void setMsgType(int msgType)
    {
        this.msgType = msgType;
    }

    public Date getMsgArrivedTime()
    {
        return msgArrivedTime;
    }

    public void setMsgArrivedTime(Date msgArrivedTime)
    {
        this.msgArrivedTime = msgArrivedTime;
    }

    public int getCommPort()
    {
        return commPort;
    }

    public void setCommPort(int commPort)
    {
        this.commPort = commPort;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getActivityId()
    {
        return activityId;
    }

    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }

    public int getMMSDownloadedState()
    {
        return MMSDownloadedState;
    }

    public void setMMSDownloadedState(int downloadedState)
    {
        MMSDownloadedState = downloadedState;
    }
}
