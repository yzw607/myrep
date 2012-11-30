package com.wmp.common.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.wmp.common.Common;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class AgentsInterceptor extends AbstractInterceptor
{
    @SuppressWarnings("unchecked")
    public String intercept(ActionInvocation invocation) throws Exception
    {

        Map session = invocation.getInvocationContext().getSession();
        User user = (User)session.get(Common.USERINFO);

        if (null != user && Common.AGENTS.equals(user.getUserType()))
        {
            return invocation.invoke();
        }
        else
        {
            return Action.LOGIN;
        }
    }
}
