<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<title>我爱说 - 用户管理平台</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/ie9.css"/>
<![endif]-->

<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/ie8.css"/>
<![endif]-->

<!--[if IE 7]>
    <link rel="stylesheet" media="screen" href="css/ie7.css"/>
<![endif]-->
<script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.flot.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.flot.resize.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript" src="js/custom/dashboard.js"></script>
<script type="text/javascript" src="<%=path%>/scripts/ajax.js"></script>

<script type="text/javascript" src="<%=path%>/scripts/checkRegister.js"></script>
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->

<script>
function checkNameIsOk(name)
{
  url = "<%=path%>/checkName.action";
  var result = "user.userCode=" + name;
  send_request_post(url, result);
}
</script>

</head>

<body class="loggedin" onload="pageInit()">

	<!-- START OF HEADER -->
<div class="header radius3">
<jsp:include page="../../headeroff.jsp" />
  <!--headerinner--> 
</div>
    <!-- END OF HEADER -->
    
<s:form action="register" method="post" theme="simple" enctype ="multipart/form-data">
<div class="contenttitle radiusbottom0" style="margin:10px;">
  <h2 class="table"><span>新用户注册</span></h2>
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
        <s:textfield name="user.userCode" id="userCode" onfocus="focusUserCode(this)" onblur="checkUserCode(this)"/>
      </td>
      <td><span id="userCodeInfo"></span></td>
    </tr>
    <tr>
      <td width="150">密码</td>
      <td width="320"><s:password name="user.passWord" id="passWord" onfocus="focusPsw(this)" onblur="checkPsw(this)"/></td>
      <td><span id="pswInfo"></span></td>
    </tr>
    <tr>
      <td width="150">确认密码</td>
      <td width="320"><input type="password" name="password2" id="passWord2" onfocus="focusPsw2(this)" onblur="checkPsw2(this)" /></td>
      <td><span id="pswInfo2"></span></td>
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
      <td width="150">用户类型</td>
      <td width="320">
        <select name="user.userType" id="userType" onchange="selUserType(this)">
          <option value="enterprise">企业用户</option>
          <option value="personal">个人用户</option>
        </select>
      </td>
      <td>&nbsp;</td>
    </tr>
    <tr id="cr1">
      <td width="150">公司名称</td>
      <td width="320"><s:textfield name="user.companyName" id="companyName" onfocus="focusCompanyName(this)" onblur="checkCompanyName(this)" maxlength="40"/></td>
      <td><span id="companyNameInfo"></span></td>
    </tr>
    <tr id="cr2">
      <td width="150">公司地址</td>
      <td width="320"><s:textfield name="user.companyAddress" id="companyAddress" onfocus="focusCompanyAddress(this)" onblur="checkCompanyAddress(this)" maxlength="40"/></td>
      <td><span id="companyAddressInfo"></span></td>
    </tr>
    <tr id="cr3">
      <td width="150">营业执照</td>
      <td width="320"><s:file name="file" id="file" readonly="true" onblur="checkFile(this)"/></td>
      <td><span id="fileInfo"></span></td>
    </tr>
  </tbody>
</table>
<br/><br/>
<div style="margin:10px;">
  <input type="checkbox" id="isAgree" onclick="bbb(this)" />&nbsp;&nbsp;&nbsp;&nbsp;同意<a href="<%=path%>/agreement.jsp" target="_blank">我爱说“服务条款”</a><br/><br/>
  <input type="button" id="registerBtn" class="stdbtn" style="width:100px; height:35px;" value="注册" onclick="doRegister()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="返回" onclick="javascript:window.history.back();"/>
</div>
</s:form>
</body>
</html>
