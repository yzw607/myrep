<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>

<html>
<head>
<title></title>
</head>

<frameset rows="87,*" frameborder="yes" border="3" framespacing="0">
  <frame src="<%=path%>/jsp/userManage/top.jsp" name="topFrame" scrolling="No" noresize/>
  <frameset cols="200,*" name="bodyFrame" id="bodyFrame" frameborder="yes" border="3" framespacing="0"  >
		<frame src="<%=path%>/jsp/userManage/menu.jsp" name="leftFrame" noresize id="leftFrame" title="leftFrame" scrolling="auto" />
		<frame src="<%=path%>/queryUser.action" name="mainFrame" id="mainFrame" title="mainFrame" scrolling="auto" noresize />
  </frameset>
</frameset>
<noframes><body>您的浏览器不支持框架！
</body>
</noframes></html>
