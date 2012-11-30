package com.wmp.userManage.bean;

import java.util.Date;

import com.wmp.common.Common;

public class User
{
    private int userId;
    private String userCode;// 用户登录名
    private String userName;//联系人
    private String passWord;// 用户密码
    private String tel;// 联系电话
    private String email;// 电子邮箱
    private String userType;//用户类型 企业或个人
    private String companyName;// 公司名称
    private String companyAddress;
    private int level;// 用户等级
    private int remainingTime;// 剩余的时间
    private Date registerDate;
    private String port;
    private String sysTel;
    private int numberLimit;
    private int numberLimit2;
    private int errorCount;
    private String parentCode;
    private String status;
    
    private String activeStr;
    private boolean isActive;
    private String levelStr;
    private String userTypeStr;
    private String remainingTimeStr;

    public String getRemainingTimeStr()
    {
        remainingTimeStr = "";
        if(remainingTime == 0)
        {
            remainingTimeStr = "0分钟";
        }
        
        int hour = remainingTime / 60;
        if(hour > 0)
        {
            remainingTimeStr = hour + "小时";
        }
        
        int min = remainingTime - hour * 60;
        if(min > 0)
        {
            remainingTimeStr = remainingTimeStr + min + "分钟";
        }
        
        return remainingTimeStr;
    }

    public void setRemainingTimeStr(String remainingTimeStr)
    {
        this.remainingTimeStr = remainingTimeStr;
    }

    public String getUserTypeStr()
    {
//        if (null == userType)
//        {
//            userTypeStr = Common.NULL_STR;
//        }
//        else if (Common.TIME.equals(userType))
//        {
//            userTypeStr = Common.TIME_STR;
//        }
//        else if (Common.COUNT.equals(userType))
//        {
//            userTypeStr = Common.COUNT_STR;
//        }
//        else
//        {
//            userTypeStr = Common.NULL_STR;
//        }

        
        if (Common.PERSONAL.equals(userType))
        {
            userTypeStr = Common.PERSONAL_STR;
        }
        else if (Common.ENTERPRISE.equals(userType))
        {
            userTypeStr = Common.ENTERPRISE_STR;
        }
        
        return userTypeStr;
    }

    public void setUserTypeStr(String userTypeStr)
    {
        this.userTypeStr = userTypeStr;
    }

    public String getLevelStr()
    {
        switch (level)
        {
            case 1:
            {
                levelStr = Common.VIP1_STR;
                break;
            }
            case 2:
            {
                levelStr = Common.VIP2_STR;
                break;
            }
            case 3:
            {
                levelStr = Common.VIP3_STR;
                break;
            }
            default:
            {
                levelStr = Common.VIP0_STR;
            }

        }

        return levelStr;
    }

    public void setLevelStr(String levelStr)
    {
        this.levelStr = levelStr;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyAddress()
    {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress)
    {
        this.companyAddress = companyAddress;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive(boolean isActive)
    {
        this.isActive = isActive;
    }

    public String getActiveStr()
    {
        if (isActive)
        {
            activeStr = Common.ACTIVE_YES;
        }
        else
        {
            activeStr = Common.ACTIVE_NO;
        }

        return activeStr;
    }

    public void setActiveStr(String activeStr)
    {
        this.activeStr = activeStr;
    }

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public int getRemainingTime()
    {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime)
    {
        this.remainingTime = remainingTime;
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

    public int getNumberLimit()
    {
        return numberLimit;
    }

    public void setNumberLimit(int numberLimit)
    {
        this.numberLimit = numberLimit;
    }

    public int getNumberLimit2()
    {
        return numberLimit2;
    }

    public void setNumberLimit2(int numberLimit2)
    {
        this.numberLimit2 = numberLimit2;
    }

    public int getErrorCount()
    {
        return errorCount;
    }

    public void setErrorCount(int errorCount)
    {
        this.errorCount = errorCount;
    }

    public Date getRegisterDate()
    {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate)
    {
        this.registerDate = registerDate;
    }

    public String getParentCode()
    {
        return parentCode;
    }

    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
