package com.wmp.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.wmp.common.Common;

public class ActionFilter implements Filter
{
    private static final String[] ALLOW_PATH = {"login.action", 
            "register.action", "adminLogin.action", "checkName.action", "getForgetPsw.action", 
            "loginOut.action", "woaishuo.action", "loadSynSms.action", "payReturn.action"};
    
    
    public void destroy()
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        String url = request.getRequestURI();
        
        if (!isNeedFilter(url))
        {
            chain.doFilter(req, resp);
        }
        else
        {
            if (isAllow(request, url))
            {
                chain.doFilter(req, resp);
            }
            else
            {
                req.setAttribute("flag", "login");
                req.getRequestDispatcher("/timeOut.jsp").forward(req, resp);
            }
        }
    }

    public void init(FilterConfig arg0) throws ServletException
    {
    }
    
    private boolean isNeedFilter(String url)
    {
        boolean flag = true;
        
        int len = ALLOW_PATH.length;
        for(int i = 0; i < len; i++)
        {
            if(url.endsWith(ALLOW_PATH[i]))
            {
                flag = false;
                break;
            }
        }
        
        return flag;
    }
    
    private boolean isAllow(HttpServletRequest request, String url)
    {
        boolean flag = false;
        
        if (request.getSession().getAttribute(Common.USERINFO) != null 
                || "true".equals(request.getSession().getAttribute("admin")))
        {
            flag = true;
        }
        
        return flag;
    }
}
