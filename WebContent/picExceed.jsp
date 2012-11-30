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
  window.history.go(-1);
}
</script>
</head>
  
<body>
<jsp:include page="headeroff.jsp" />
  <!--headerinner--> 

<div class="prompt">
<div class="promptmain">
<table  border="0" cellspacing="0" cellpadding="0" width="600">
              <tr>
                <th>
                
                <div class="promptpic_error"></div>

            <div class="prompttxt1">请将您所上传图片尺寸控制在2M以内！</div>
            </th>
              </tr>
              <tr>
              <td style="padding-left:40px;">
              <!-- <div class="prompttxt2">系统将在5秒后自动跳转到首页！</div> -->
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
<p>我爱说 &copy; 2012. All Rights Reserved. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;爱说热线：400-101-4595&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;鄂ICP备12008340号</p> </div>

  </body>
</html>
