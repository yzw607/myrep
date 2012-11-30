package com.wmp.selfService.service;

import com.wmp.selfService.bean.AlipayOrder;
import com.wmp.userManage.bean.User;

public interface IRechargeService
{
    public String doRecharge(String rechargeCode, String userCode)
            throws Exception;

    public User getUser(String userCode) throws Exception;

    public AlipayOrder createAlipayOrder(AlipayOrder payOrder) throws Exception;

    public AlipayOrder doPayBack(String out_trade_no, String trade_no)
            throws Exception;
}
