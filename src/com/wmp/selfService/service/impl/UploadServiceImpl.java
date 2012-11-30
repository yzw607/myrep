package com.wmp.selfService.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.common.Common;
import com.wmp.common.bean.UploadPic;
import com.wmp.selfService.service.IUploadService;
import com.wmp.userManage.bean.User;

public class UploadServiceImpl extends HibernateDaoSupport implements IUploadService
{
    private static final int BUFFER_SIZE = 16*1024;
    
    public void uploadPic(String userCode, String activityId, File pic, String picFileName) throws Exception
    {
        String fileName = picFileName;
        InputStream in = null;
        OutputStream outs = null;
        try
        {
            String folderPath = Common.SAVEPATH + userCode;
            File dirname = new File(folderPath);
            if (!dirname.isDirectory())
            {
                dirname.mkdir();
            }
            
            folderPath = folderPath + "/" + activityId;
            dirname = new File(folderPath);
            if (!dirname.isDirectory())
            {
                dirname.mkdir();
            }
            
            folderPath = folderPath + "/thumbs/";
            dirname = new File(folderPath);
            if (!dirname.isDirectory())
            {
                dirname.mkdir();
            }

            System.out.println(folderPath);
            
            in = new BufferedInputStream(new FileInputStream(pic),
                    BUFFER_SIZE);
            outs = new BufferedOutputStream(new FileOutputStream(
                    new File(folderPath + fileName)), BUFFER_SIZE);
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = in.read(buffer)) > 0)
            {
                outs.write(buffer, 0, len);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != in)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (null != outs)
            {
                try
                {
                    outs.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        
        try
        {
            UploadPic up = new UploadPic();
            up.setFileName(fileName);
            up.setUserCode(userCode);
            up.setActivityId(activityId);
            this.getHibernateTemplate().save(up);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public void deleteUploadPic(User user, String picId, String activityId) throws Exception
    {
        String userCode = user.getUserCode();
        String hql = "FROM UploadPic o WHERE o.picId = ? AND o.userCode = ?";
        List<UploadPic> list = this.getHibernateTemplate().find(hql,
                new Object[] {Integer.parseInt(picId), userCode});
        if (null != list && list.size() > 0)
        {
            UploadPic pic = list.get(0);
            StringBuffer sb = new StringBuffer();
            sb.append(Common.SAVEPATH).append(userCode).append("/").append(activityId);
            sb.append("/thumbs/").append(pic.getFileName());
            
            File file = new File(sb.toString());
            if (file.exists())
            {
                System.gc();
                file.delete();
            }
            
            this.getHibernateTemplate().delete(pic);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<UploadPic> getUploadPics(User user, String activityId) throws Exception
    {
        List<UploadPic> list = null;
        String hql = "FROM UploadPic o WHERE o.activityId = ? AND o.userCode = ?";
        list = this.getHibernateTemplate().find(hql, new Object[]{activityId, user.getUserCode()});
        
        return list;
    }
}
