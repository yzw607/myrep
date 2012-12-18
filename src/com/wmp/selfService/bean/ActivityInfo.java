package com.wmp.selfService.bean;

import com.wmp.common.Common;

public class ActivityInfo
{
    private int id;
    private String userCode;
    private String number;
    private String title;
    private String planners;
    private String plannersTel;
    private String site;
    private String siteTel;
    private String bridegroom;
    private String bridegroomTel;
    private String bride;
    private String brideTel;
    private String address;
    private String welcomeMsg;
    private String postAddress;
    private String remark;
    private int picId;
    private String picFileName;
    private String bgPicPath;
    private int stencilId;
    private String stencilName;
    private String holdDate;
    private String period;
    private String port;
    private String sysTel;
    private String status;
    private String flag;

    private String numberHTML;
    private String sysTelHTML;
    private String widthNumberHTML;
    private String widthSysTelHTML;
    private String numberHTML2;
    private String sysTelHTML2;
    private String periodStr;
    private String statusStr;
    
    private String startDate;
    private String endDate;
    
    private String videoPath;
    private String picPath;
    
    public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatusStr()
    {
        if ("0".equals(status))
        {
            statusStr = "新创建";
        }
        else if ("1".equals(status))
        {
            statusStr = "进行中";
        }
        else if ("2".equals(status))
        {
            statusStr = "已结束";
        }
        else if ("3".equals(status))
        {
            statusStr = "已结束";
        }

        return statusStr;
    }

    public void setStatusStr(String statusStr)
    {
        this.statusStr = statusStr;
    }

    public String getPeriodStr()
    {
        StringBuffer sb = new StringBuffer();
        if (null != period && !"".equals(period.trim()))
        {
            if (period.indexOf("a") != -1)
            {
                sb.append(Common.PERIOD_A).append("；");
            }

            if (period.indexOf("b") != -1)
            {
                sb.append(Common.PERIOD_B).append("；");
            }

            if (period.indexOf("c") != -1)
            {
                sb.append(Common.PERIOD_C).append("；");
            }
        }

        periodStr = sb.toString();
        return periodStr;
    }

    public void setPeriodStr(String periodStr)
    {
        this.periodStr = periodStr;
    }

    public String getNumberHTML()
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            if (null == number)
                number = "";

            for (int i = 0; i < number.length(); i++)
            {
                sb.append("<img src=\"images/").append(
                        String.valueOf(number.charAt(i)));
                sb.append(".png\" height=\"40\" />");
            }
        }
        catch (Exception ex)
        {
            ex.toString();
        }

        numberHTML = sb.toString();
        return numberHTML;
    }

    public void setNumberHTML(String numberHTML)
    {
        this.numberHTML = numberHTML;
    }

    public String getSysTelHTML()
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            if (null == sysTel)
                sysTel = "";

            for (int i = 0; i < sysTel.length(); i++)
            {
                sb.append("<img src=\"images/").append(
                        String.valueOf(sysTel.charAt(i)));
                sb.append(".png\" height=\"40\" />");
            }
        }
        catch (Exception ex)
        {
            ex.toString();
        }

        sysTelHTML = sb.toString();

        return sysTelHTML;
    }

    public String getWidthNumberHTML()
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            if (null == number)
                number = "";

            for (int i = 0; i < number.length(); i++)
            {
                sb.append("<img src=\"images/16_9/").append(
                        String.valueOf(number.charAt(i)));
                sb.append(".png\" height=\"30\" />");
            }
        }
        catch (Exception ex)
        {
            ex.toString();
        }

        widthNumberHTML = sb.toString();
        return widthNumberHTML;
    }

    public void setWidthNumberHTML(String widthNumberHTML)
    {
        this.widthNumberHTML = widthNumberHTML;
    }

    public String getWidthSysTelHTML()
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            if (null == sysTel)
                sysTel = "";

            for (int i = 0; i < sysTel.length(); i++)
            {
                sb.append("<img src=\"images/16_9/").append(
                        String.valueOf(sysTel.charAt(i)));
                sb.append(".png\" height=\"36\" />");
            }
        }
        catch (Exception ex)
        {
            ex.toString();
        }

        widthSysTelHTML = sb.toString();

        return widthSysTelHTML;
    }

    public void setWidthSysTelHTML(String widthSysTelHTML)
    {
        this.widthSysTelHTML = widthSysTelHTML;
    }

    public void setSysTelHTML(String sysTelHTML)
    {
        this.sysTelHTML = sysTelHTML;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPlanners()
    {
        return planners;
    }

    public void setPlanners(String planners)
    {
        this.planners = planners;
    }

    public String getPlannersTel()
    {
        return plannersTel;
    }

    public void setPlannersTel(String plannersTel)
    {
        this.plannersTel = plannersTel;
    }

    public String getSite()
    {
        return site;
    }

    public void setSite(String site)
    {
        this.site = site;
    }

    public String getSiteTel()
    {
        return siteTel;
    }

    public void setSiteTel(String siteTel)
    {
        this.siteTel = siteTel;
    }

    public String getBridegroom()
    {
        return bridegroom;
    }

    public void setBridegroom(String bridegroom)
    {
        this.bridegroom = bridegroom;
    }

    public String getBridegroomTel()
    {
        return bridegroomTel;
    }

    public void setBridegroomTel(String bridegroomTel)
    {
        this.bridegroomTel = bridegroomTel;
    }

    public String getBride()
    {
        return bride;
    }

    public void setBride(String bride)
    {
        this.bride = bride;
    }

    public String getBrideTel()
    {
        return brideTel;
    }

    public void setBrideTel(String brideTel)
    {
        this.brideTel = brideTel;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getHoldDate()
    {
        return holdDate;
    }

    public void setHoldDate(String holdDate)
    {
        this.holdDate = holdDate;
    }

    public String getPeriod()
    {
        return period;
    }

    public void setPeriod(String period)
    {
        this.period = period;
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

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getWelcomeMsg()
    {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg)
    {
        this.welcomeMsg = welcomeMsg;
    }

    public String getPostAddress()
    {
        return postAddress;
    }

    public void setPostAddress(String postAddress)
    {
        this.postAddress = postAddress;
    }

    public int getPicId()
    {
        return picId;
    }

    public void setPicId(int picId)
    {
        this.picId = picId;
    }

    public String getBgPicPath()
    {
        return bgPicPath;
    }

    public void setBgPicPath(String bgPicPath)
    {
        this.bgPicPath = bgPicPath;
    }

    public String getPicFileName()
    {
        return picFileName;
    }

    public void setPicFileName(String picFileName)
    {
        this.picFileName = picFileName;
    }

    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public int getStencilId()
    {
        return stencilId;
    }

    public void setStencilId(int stencilId)
    {
        this.stencilId = stencilId;
    }

    public String getStencilName()
    {
        return stencilName;
    }

    public void setStencilName(String stencilName)
    {
        this.stencilName = stencilName;
    }

    public String getNumberHTML2()
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            if (null == number)
                number = "";

            for (int i = 0; i < number.length(); i++)
            {
                sb.append("<img src=\"images/4_3_2/").append(
                        String.valueOf(number.charAt(i)));
                sb.append(".png\"/>");
            }
        }
        catch (Exception ex)
        {
            ex.toString();
        }

        numberHTML2 = sb.toString();
        return numberHTML2;
    }

    public void setNumberHTML2(String numberHTML2)
    {
        this.numberHTML2 = numberHTML2;
    }

    public String getSysTelHTML2()
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            if (null == sysTel)
                sysTel = "";

            for (int i = 0; i < sysTel.length(); i++)
            {
                sb.append("<img src=\"images/4_3_2/").append(
                        String.valueOf(sysTel.charAt(i)));
                sb.append(".png\"/>");
            }
        }
        catch (Exception ex)
        {
            ex.toString();
        }

        sysTelHTML2 = sb.toString();

        return sysTelHTML2;
    }

    public void setSysTelHTML2(String sysTelHTML2)
    {
        this.sysTelHTML2 = sysTelHTML2;
    }

}
