<%@ page language="java" import="com.wmp.userManage.bean.User, com.wmp.common.Common" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<script>
function init()
{
  var msg = "${sysMsg}";
  if(msg != "")
  {
    alert(msg);
  }
}
</script>
</head>

<body onload="init()">



联系人：${user.userName}<br/>
联系电话：${user.tel}<br/>
电子邮箱：${user.email}<br/>
用户类型：${user.userTypeStr}<br/>

<s:if test="user.userType == 'enterprise'">
公司名称：${user.companyName}<br/>
</s:if>
剩余时间：${user.remainingTimeStr}<br/>

</body>
</html>
