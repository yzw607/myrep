package com.wmp.sms.service;

import com.wmp.selfService.bean.ActivityInfo;
import com.wmp.sms.bean.MsgInBox;

public interface ISynSmsService
{
    public ActivityInfo queryActivityInfo(int id) throws Exception;
    
    public MsgInBox getReadedSMS(String number, String lastSmsId, String recordTime) throws Exception;
}
