<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/console/main.css" />
<style type="text/css">
</style>

<script language="javascript">

</script>
<title></title>
</head>

<body>

  <ul>
    <li><a href="<%=path%>/queryInfo.action" target="mainFrame">用户信息</a></li>
    <li><a href="<%=path%>/queryNumber.action" target="mainFrame">特殊号码设置</a></li>
    <li><a href="<%=path%>/goSms.action" target="mainFrame">进入亲友短信</a></li>
    <li><a href="<%=path%>/jsp/selfService/lotterySetting.jsp" target="mainFrame">进入短信抽奖</a></li>
    <li><a href="<%=path%>/queryRecord.action" target="mainFrame">使用记录查询</a></li>
  </ul>
</body>
</html>
