package com.wmp.selfService.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.Common;
import com.wmp.common.bean.UploadPic;
import com.wmp.selfService.service.IUploadService;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class UploadAction extends ActionSupport implements SessionAware, ServletResponseAware
{
    private Map<String, Object> session;
    private HttpServletResponse response;
    private File pic; // 对应文件域
    private String picFileName; // 前面的File属性的名字 + FileName（固定的）
    private String picContentType; 
    private IUploadService uploadService;
    private String activityId;
    private List<UploadPic> picList;
    private String picId;
    
    public String goUploadPic() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        picList = this.uploadService.getUploadPics(user, activityId);
        
        return SUCCESS;
    }
    
    public String uploadPic() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        response.setCharacterEncoding("UTF-8");    
        PrintWriter out = response.getWriter();
        
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("error", "Error! Wrong HTTP method!");
        
        
        if (null != pic)
        {
            this.uploadService.uploadPic(user.getUserCode(), activityId, pic, picFileName);
        }
        
        JSONObject jo = null;
        try
        {
            jo = JSONObject.fromObject(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String result = jo.toString();
        
        //直接输入响应的内容    
        out.print(result);    
        out.flush();    
        out.close();  
        
        return null;
    }
    
    public String deleteUploadPic() throws Exception
    {
        User user = (User)session.get(Common.USERINFO);
        
        this.uploadService.deleteUploadPic(user, picId, activityId);
        
        picList = this.uploadService.getUploadPics(user, activityId);
        
        return SUCCESS;
    }
    
    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }


    public File getPic()
    {
        return pic;
    }

    public void setPic(File pic)
    {
        this.pic = pic;
    }

    public String getPicFileName()
    {
        return picFileName;
    }

    public void setPicFileName(String picFileName)
    {
        this.picFileName = picFileName;
    }

    public String getPicContentType()
    {
        return picContentType;
    }

    public void setPicContentType(String picContentType)
    {
        this.picContentType = picContentType;
    }

    public IUploadService getUploadService()
    {
        return uploadService;
    }

    public void setUploadService(IUploadService uploadService)
    {
        this.uploadService = uploadService;
    }

    public String getActivityId()
    {
        return activityId;
    }

    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }
    
    public void setSession(Map<String, Object> session)
    {
        this.session = session;
    }

    public List<UploadPic> getPicList()
    {
        return picList;
    }

    public void setPicList(List<UploadPic> picList)
    {
        this.picList = picList;
    }

    public String getPicId()
    {
        return picId;
    }

    public void setPicId(String picId)
    {
        this.picId = picId;
    }

}
