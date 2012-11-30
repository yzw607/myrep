package com.wmp.common.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wmp.common.Common;
import com.wmp.common.bean.SysTelNumber;
import com.wmp.common.service.IRegisterService;
import com.wmp.selfService.bean.UserNumber;
import com.wmp.userManage.bean.User;

public class RegisterServiceImpl extends HibernateDaoSupport implements IRegisterService
{
    private static final int BUFFER_SIZE = 16*1024;
    
    /**
     * 查询用户信息
     * @param usercode
     * @param password
     * @return
     */
    @SuppressWarnings("unchecked")
    public User registerUser(User tmpUser, File file, String contextPath)
    {
        String hql = "FROM User o WHERE o.userCode = ?";
        List<User> userList = this.getHibernateTemplate().find(hql, new Object[]{tmpUser.getUserCode()});
        
        if(null != userList && 0 < userList.size())
        {
            return null;
        }
        else
        {
            hql = "FROM SysTelNumber o";
            List<SysTelNumber> telList = this.getHibernateTemplate().find(hql);
            SysTelNumber tel = telList.get(0);
            
            tmpUser.setPort(String.valueOf(tel.getPort()));
            tmpUser.setSysTel(tel.getSysTel());
//            tmpUser.setUserType(Common.ENTERPRISE);
            tmpUser.setNumberLimit(0);
            tmpUser.setNumberLimit2(0);
            tmpUser.setRemainingTime(0);
            tmpUser.setRegisterDate(new Date());
            tmpUser.setErrorCount(0);
            tmpUser.setParentCode("");
            
            if(Common.ENTERPRISE.equals(tmpUser.getUserType()))
            {
                tmpUser.setStatus("0");
                if (null != file)
                {
                    String userCode = tmpUser.getUserCode();
                    String fileName = userCode + ".jpg";
                    InputStream in = null;
                    OutputStream out = null;
                    try
                    {
                        String folderPath = Common.LICENSE_PATH;
                        File dirname = new File(folderPath);
                        if (!dirname.isDirectory())
                        {
                            dirname.mkdir();
                        }

                        in = new BufferedInputStream(new FileInputStream(file),
                                BUFFER_SIZE);
                        out = new BufferedOutputStream(new FileOutputStream(
                                new File(folderPath + fileName)), BUFFER_SIZE);
                        byte[] buffer = new byte[BUFFER_SIZE];
                        int len = 0;
                        while ((len = in.read(buffer)) > 0)
                        {
                            out.write(buffer, 0, len);
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
                        if (null != out)
                        {
                            try
                            {
                                out.close();
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            else
            {
                tmpUser.setStatus("1");
            }
            
            this.getHibernateTemplate().save(tmpUser);
            
            return tmpUser;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<UserNumber> getUserNumbers(String userCode) throws Exception
    {
        String hql = "FROM UserNumber o WHERE o.userCode = ?";
        List<UserNumber> list = this.getHibernateTemplate().find(hql, userCode);
        
        return list;
    }
    
    @SuppressWarnings("unchecked")
    public String checkUserCode(String userCode) throws Exception
    {
        String hql = "FROM User o WHERE o.userCode = ?";
        List<UserNumber> list = this.getHibernateTemplate().find(hql, userCode);
        
        if(null != list && list.size() > 0)
        {
            return "error";
        }
        else
        {
            return "success";
        }
    }
}
