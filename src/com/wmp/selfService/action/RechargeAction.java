package com.wmp.selfService.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.alipay.util.AlipayNotify;
import com.opensymphony.xwork2.ActionSupport;
import com.wmp.common.Common;
import com.wmp.selfService.bean.AlipayOrder;
import com.wmp.selfService.service.IRechargeService;
import com.wmp.userManage.bean.User;

@SuppressWarnings("serial")
public class RechargeAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware
{
    private IRechargeService rechargeService;
    private Map<String, Object> session;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String sysMsg;
    private String msgType;
    private String rechargeCode;
    private String backUrl;
    private String menuType;
    private int payNum;
    private int payMoney;
    private AlipayOrder payOrder;
    
    public String goRechargeAction() throws Exception
    {
        User tmpUser = (User) session.get(Common.USERINFO);
        if(tmpUser.getErrorCount() >= 5)
        {
            menuType = "myAccount";
            msgType = "error";
            sysMsg = "您已经连续输错5次充值卡号，您的账户已被锁定。<br/>如需帮助，请联系爱说热线 400-101-4595";
            backUrl = "goHome.action";
            return ERROR;
        }
        else
        {
            menuType = "myAccount";
            return SUCCESS;
        }
    }
    
    public String doRecharge() throws Exception
    {
        User tmpUser = (User) session.get(Common.USERINFO);
        if(tmpUser.getErrorCount() >= 5)
        {
            msgType = "error";
            sysMsg = "您已经连续输错5次充值卡号，您的账户已被锁定。<br/>如需帮助，请联系爱说热线 400-101-4595";
            backUrl = "goHome.action";
        }
        
        String userCode = tmpUser.getUserCode();
        int oldValue = tmpUser.getRemainingTime();
        sysMsg = this.rechargeService.doRecharge(rechargeCode, userCode);
        
        User user = this.rechargeService.getUser(userCode);
        int newValue = user.getRemainingTime();
        
        if(newValue > oldValue)
        {
            session.remove(Common.USERINFO);
            session.put(Common.USERINFO, user);
            msgType = "success";
        }
        else
        {
            session.remove(Common.USERINFO);
            session.put(Common.USERINFO, user);
            msgType = "alert"; 
        }
        
        if(user.getErrorCount() >= 5)
        {
            msgType = "error";
            sysMsg = "您已经连续输错5次充值卡号，您的账户已被锁定。<br/>如需帮助，请联系爱说热线 400-101-4595";
            backUrl = "goHome.action";
        }
        else
        {
            backUrl = "goRecharge.action?menuType=myAccount";
            menuType = "myAccount";
        }
        return SUCCESS;
    }
    
    public String goAlipay() throws Exception
    {
        if(0 == payNum)
        {
            msgType = "error";
            sysMsg = "请输入您要充值的次数";
            backUrl = "goRecharge.action";
            return ERROR;
        }
        
        User tmpUser = (User) session.get(Common.USERINFO);
        if(Common.PERSONAL.equals(tmpUser.getUserType()))
        {
            this.payMoney = payNum * 1588;
        }
        else
        {
            this.payMoney = payNum * 1588;
        }
        
        return SUCCESS;
    }
    
    public String doAlipay() throws Exception
    {
        if(0 == payNum || 0 == payMoney)
        {
            msgType = "error";
            sysMsg = "请输入您要充值的次数";
            backUrl = "goRecharge.action";
            return ERROR;
        }
        
        User tmpUser = (User) session.get(Common.USERINFO);
        payOrder = new AlipayOrder();
        payOrder.setPayNum(payNum);
        payOrder.setPayMoney(payMoney);
        payOrder.setUserCode(tmpUser.getUserCode());
        payOrder = this.rechargeService.createAlipayOrder(payOrder);
        
        return SUCCESS;
    }
    
    @SuppressWarnings("unchecked")
    public String payReturn() throws Exception
    {
 
        /*// 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();)
        {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++)
            {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
            params.put(name, valueStr);
        }

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//

        String trade_no = request.getParameter("trade_no"); // 支付宝交易号
        String order_no = request.getParameter("out_trade_no"); // 获取订单号
//        String total_fee = request.getParameter("total_fee"); // 获取总金额
//        String subject = new String(request.getParameter("subject").getBytes(
//                "ISO-8859-1"), "utf-8");// 商品名称、订单名称
//        String body = "";
//        if (request.getParameter("body") != null)
//        {
//            body = new String(request.getParameter("body").getBytes(
//                    "ISO-8859-1"), "utf-8");// 商品描述、订单备注、描述
//        }
//        String buyer_email = request.getParameter("buyer_email"); // 买家支付宝账号
        String trade_status = request.getParameter("trade_status"); // 交易状态

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

        // 计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);

        backUrl = "close";
        if (verify_result)
        {// 验证成功
            // ////////////////////////////////////////////////////////////////////////////////////////
            // 请在这里加上商户的业务逻辑程序代码

            // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            if (trade_status.equals("TRADE_FINISHED")
                    || trade_status.equals("TRADE_SUCCESS"))
            {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序
                
                AlipayOrder order = this.rechargeService.doPayBack(order_no, trade_no);
                if(null != order)
                {
                    String userCode = order.getUserCode();
                    User user = this.rechargeService.getUser(userCode);
                    session.remove(Common.USERINFO);
                    session.put(Common.USERINFO, user);
                    
                    msgType = "success";
                    sysMsg = "充值成功，您可至【账单查询】进行查询充值记录";
                }
                else
                {
                    msgType = "error";
                    sysMsg = "充值失败，订单交易号为【" + order_no + "】，请致电爱说热线进行处理。";
                }
                
            }
            else
            {
                msgType = "error";
                sysMsg = "您所查询的信息不存在，或该交易已完成，请至【账单查询】进行查询充值记录";
            }
            return null;

            // 该页面可做页面美工编辑
            // out.println("验证成功<br />");
            // out.println("trade_no=" + trade_no);

            // ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            // ////////////////////////////////////////////////////////////////////////////////////////
        }
        else
        {
            // 该页面可做页面美工编辑
            // out.println("验证失败");
            return ERROR;
        }
                    */
        
        System.out.println("//////////////////////////////////notify_url///////////////////////////////////////////////");
        
        response.setCharacterEncoding("UTF-8");    
        PrintWriter out = response.getWriter();    

        //直接输入响应的内容    
        out.print("success");    
        out.flush();    
        out.close();  
        
        return null;
    }
    
    public String goAlipayInfo() throws Exception
    {
        return SUCCESS;
    }

    public IRechargeService getRechargeService()
    {
        return rechargeService;
    }

    public void setRechargeService(IRechargeService rechargeService)
    {
        this.rechargeService = rechargeService;
    }

    public Map<String, Object> getSession()
    {
        return session;
    }

    public void setSession(Map<String, Object> session)
    {
        this.session = session;
    }

    public String getSysMsg()
    {
        return sysMsg;
    }

    public void setSysMsg(String sysMsg)
    {
        this.sysMsg = sysMsg;
    }

    public String getRechargeCode()
    {
        return rechargeCode;
    }

    public void setRechargeCode(String rechargeCode)
    {
        this.rechargeCode = rechargeCode;
    }

    public String getMsgType()
    {
        return msgType;
    }

    public void setMsgType(String msgType)
    {
        this.msgType = msgType;
    }

    public String getBackUrl()
    {
        return backUrl;
    }

    public void setBackUrl(String backUrl)
    {
        this.backUrl = backUrl;
    }

    public String getMenuType()
    {
        return menuType;
    }

    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
    }

    public int getPayNum()
    {
        return payNum;
    }

    public void setPayNum(int payNum)
    {
        this.payNum = payNum;
    }

    public int getPayMoney()
    {
        return payMoney;
    }

    public void setPayMoney(int payMoney)
    {
        this.payMoney = payMoney;
    }

    public AlipayOrder getPayOrder()
    {
        return payOrder;
    }

    public void setPayOrder(AlipayOrder payOrder)
    {
        this.payOrder = payOrder;
    }
    
    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }
    
    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }
}
