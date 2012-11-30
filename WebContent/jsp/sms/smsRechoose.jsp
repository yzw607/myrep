<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我爱说 - 用户管理平台 系统提示</title>
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<script>
alert("${sysMsg}");
window.close();
</script>
</head>
  
  <body>
  </body>
</html>
