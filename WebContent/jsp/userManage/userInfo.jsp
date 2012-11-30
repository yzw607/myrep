<%@ page language="java" import="com.wmp.userManage.bean.User, com.wmp.common.Common" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
User user = (User)request.getAttribute( "user");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>


<script>
function changeUserType(obj)
{
}
</script>

</head>

<body>


<s:form action="recharge"  method="post" theme="simple">
<s:token></s:token>
<input type="hidden" name="user.userId" value="${user.userId}"/>

联系人：${user.userName}<br/>
联系电话：${user.tel}<br/>
电子邮箱：${user.email}<br/>
用户类型：${user.userTypeStr}<br/>
<% 
if(Common.ENTERPRISE.equals(user.getUserType()))
{
%>
公司名称：${user.companyName}<br/>
<%
}
%>
剩余时间：${user.remainingTimeStr}<br/>


用户充值：<input type="text" name="recharge" />小时<br/>
赠送时间：<input type="text" name="handsel" />小时<br/>
<input type="submit" value="确认" />

</s:form>
</body>
</html>
