<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我爱说-www.say520.cn</title>
<link rel="stylesheet" href="<%=path%>/jsp/admin/css/style.css" type="text/css" />
<link rel="shortcut icon" href="<%=path%>/ico.ico">

<script>
function goBack()
{
  window.location.href = "<%=path%>/login.jsp";
}

</script>
</head>
  
<body>
<div class="header radius3">
<jsp:include page="headeroff.jsp" />
  <!--headerinner--> 
</div>

<div class="prompt">
<div class="promptmain">
<table  border="0" cellspacing="0" cellpadding="0" width="600">
              <tr>
                <th>
                <div class="promptpic_alert"></div>
                <div class="prompttxt1">系统超时或您尚未登录，请重新登录后再操作！</div>
            </th>
              </tr>
              <tr>
              <td style="padding-left:40px;">
              </td>
              </tr>
              <tr>
                <td align="center">
                <button class="stdbtn btn_yellow" onClick="goBack()">确定</button>
                </td>
              </tr>
            </table>

</div>
</div>
<div class="about">

  </body>
</html>
