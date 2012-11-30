package com.wmp.selfService.service;

import java.io.File;
import java.util.List;

import com.wmp.common.bean.UploadPic;
import com.wmp.userManage.bean.User;

public interface IUploadService
{
    public void uploadPic(String userCode, String activityId, File pic, String picFileName) throws Exception;
    
    public List<UploadPic> getUploadPics(User user, String activityId) throws Exception;
    
    public void deleteUploadPic(User user, String picId, String activityId) throws Exception;
}
