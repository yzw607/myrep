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
<s:if test="backUrl == null">
function goBack()
{
  window.history.go(-1);
}
</s:if>
<s:else>
function goBack()
{
  window.location.href = "<%=path%>/${backUrl}";
}
</s:else>
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
                
                <div class="promptpic_error"></div>
                <div class="prompttxt1">您所上传的图片尺寸过大，请控制在2M以内！</div>
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
<p>我爱说 鄂ICP备12008340号</p> </div>

  </body>
</html>
