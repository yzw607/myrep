<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="<%=path%>/jsp/admin/css/style.css" type="text/css" />
<title>我爱说-www.say520.cn</title>
<link rel="shortcut icon" href="<%=path%>/ico.ico">

<script>
setTimeout("goHome()", 5000);

function goHome()
{
  window.location.href="<%=path%>/goHome.action";
}
</script>
</head>
  
<body>
<div class="header radius3">
  <div class="headerinner">
  </div>
  <!--headerinner--> 
</div>

<div class="prompt">
<div class="promptmain">
<table  border="0" cellspacing="0" cellpadding="0" width="600">
              <tr>
                <th>
                
                <div class="promptpic_success" ></div>

            <div class="prompttxt1">
                恭喜你，注册成功，系统将在5秒后自动跳转到首页！<br/>
              系统若未跳转，请点击<a href="<%=path%>/goHome.action">此处</a>至首页。
            </div>
            </th>
              </tr>
              <tr>
              <td style="padding-left:40px;">
              <!-- <div class="prompttxt2">系统将在5秒后自动跳转到首页！</div> -->
              </td>
              </tr>
              <tr>
                <td align="center">
                <button class="stdbtn btn_yellow" onClick="goHome()">确定</button>
                
                </td>
              </tr>
            </table>

</div>
</div>
<div class="about">
<p>我爱说 &copy; 2012. All Rights Reserved. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;爱说热线：400-101-4595&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;鄂ICP备12008340号</p> </div>

  </body>
</html>
