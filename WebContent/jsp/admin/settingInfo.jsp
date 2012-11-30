<%@ page language="java" import="com.wmp.common.Common" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>我爱说 - 用户管理平台</title>
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<link rel="stylesheet" href="<%=path%>/jsp/admin/css/style.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/dhtmlx/dhtmlxCalendar/codebase/dhtmlxcalendar.css"></link>
<link rel="stylesheet" type="text/css" href="<%=path%>/dhtmlx/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css"></link>
<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/ie9.css"/>
<![endif]-->

<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/ie8.css"/>
<![endif]-->

<!--[if IE 7]>
    <link rel="stylesheet" media="screen" href="css/ie7.css"/>
<![endif]-->
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery.flot.min.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery.flot.resize.min.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/custom/general.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/custom/dashboard.js"></script>
<script type="text/javascript" src="<%=path%>/scripts/checkNewSetting.js"></script>
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->

<script>
var path = "<%=path%>";
</script>

</head>

<body class="loggedin" onload="doOnLoad();">
	<!-- START OF HEADER -->
	<div class="header radius3">
		<div class="headerinner">
        <div class="headlogo"><img src="<%=path%>/jsp/admin/images/mainlogo.png" /></div>
		</div>
	</div>
    <!-- END OF HEADER -->
    
<s:form action="updateUserInfo" method="post" theme="simple">
<s:hidden name="user.userId"></s:hidden>
<div class="contenttitle radiusbottom0" style="margin:10px;">
  <h2 class="table"><span>用户信息维护</span></h2>
</div>

<table cellpadding="0" cellspacing="0" border="0" class="stdtable dashtable stdform" style=" width:98%; margin:10px; margin-top:0; border-top: 1px solid #dddddd;">
  <colgroup>
  <col class="con1" />
  <col class="con1" />
  <col class="con1" />
  </colgroup>
  <tbody>
    <tr>
      <td width="150">用户名</td>
      <td width="320">
        <s:textfield name="user.userCode" id="userCode" />
      </td>
      <td><span id="userCodeInfo"></span></td>
    </tr>
    <tr>
      <td width="150">姓  名</td>
      <td width="320"><s:textfield name="user.userName" id="userName" onfocus="focusUserName(this)" onblur="checkUserName(this)" maxlength="10" /></td>
      <td><span id="userNameInfo"></span></td>
    </tr>
    <tr>
      <td width="150">公司固定/移动电话</td>
      <td width="320"><s:textfield name="user.tel" id="tel" onfocus="focusTel(this)" onblur="checkTel(this)" maxlength="30" /></td>
      <td><span id="telInfo"></span></td>
    </tr>
    <tr>
      <td width="150">电子邮箱</td>
      <td width="320"><s:textfield name="user.email" id="email" onfocus="focusEmail(this)" onblur="checkEmail(this)" maxlength="100"/></td>
      <td><span id="emailInfo"></span></td>
    </tr>
    <tr>
      <td width="150">公司名称</td>
      <td width="320"><s:textfield name="user.companyName" id="companyName" onfocus="focusCompanyName(this)" onblur="checkCompanyName(this)" maxlength="40"/></td>
      <td><span id="companyNameInfo"></span></td>
    </tr>
    <tr>
      <td width="150">公司地址</td>
      <td width="320"><s:textfield name="user.companyAddress" id="companyAddress" onfocus="focusCompanyAddress(this)" onblur="checkCompanyAddress(this)" maxlength="40"/></td>
      <td><span id="companyAddressInfo"></span></td>
    </tr>
  </tbody>
</table>

<br/><br/>

<div style="margin:10px;">
  <input type="button" id="registerBtn" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="确    定" onclick="formCheck()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
</s:form>

<br/>

<jsp:include page="footer.jsp" />
</body>
</html>
