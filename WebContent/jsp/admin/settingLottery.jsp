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
<script type="text/javascript" src="<%=path%>/scripts/checkInfo.js"></script>
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->

<script>
function addQ()
{
  if(document.getElementById("showIndex").value == "")
  {
    alert("请输入号码显示的序号");
    document.getElementById("showIndex").focus();
    return;
  }
  
  if(!isNumber(document.getElementById("showIndex").value))
  {
    alert("请输入正确的序号");
    document.getElementById("showIndex").focus();
    return;
  }
  
  if(document.getElementById("showNumber").value == "")
  {
    alert("请输入显示中奖的手机号码");
    document.getElementById("showNumber").focus();
    return;
  }
  
  document.forms[0].submit();
}

function delQ(id)
{
  window.location.href = "<%=path%>/delNumber.action?activityId=${activityId}&number.numId=" + id;
}

function init()
{
  document.getElementById("showIndex").value = "";
  document.getElementById("showNumber").value = "";
}

function goActivity()
{
  window.location.href = "<%=path%>/viewActivity.action?activityInfo.id=${activityId}";
}

function doClose()
{
  window.close();
}
</script>

</head>

<body class="loggedin" onload="init()">
	<!-- START OF HEADER -->
<jsp:include page="header2.jsp" />
    <!-- END OF HEADER -->
    
<s:form action="addLotteryNumber" method="post" theme="simple">
<s:hidden name="activityId"/>
<div class="contenttitle radiusbottom0" style="margin:10px;">
  <h2 class="table"><span>抽奖设置</span></h2>
</div>

<s:if test="activity.status == 0">
<table cellpadding="0" cellspacing="0" border="0" class="stdtable dashtable" style=" width:98%; margin:10px; margin-top:0; border-top:1px #ddd solid; " id="qTab">
  <colgroup>
  <col class="con1" />
  <col class="con0" />
  <col class="con1" />
  <col class="con0" />
  <col class="con0" />
  </colgroup>

  <tr>
    <td width="120px;">序号</td>
    <td width="380px;"><span class="stdform" ><s:textfield name="number.showIndex" id="showIndex" maxLength="3"/></span></td>
    <td width="120px;">号码</td>
    <td width="420px;"><span class="stdform" ><s:textfield name="number.showNumber" id="showNumber"  maxLength="19"/></span></td>
    <td width="420px;"><input type="button" class="stdbtn btn_yellow" style="width:100px; height:35px;" onclick="addQ()" value="保存"/></td>
  </tr>   
</table>

<br/><br/>
</s:if>


<s:if test="setList.size > 0">
<s:if test="activity.status == 0">
<table cellpadding="0" cellspacing="0" border="0" class="stdtable" style="border-top:1px #ddd solid; width:98%;">
  <tr>
    <td align="center">序号</td>
    <td align="center">号码</td>
    <td align="center">操作</td>
  </tr>
<s:iterator value="#request.setList" id="n">
  <tr>
    <td width="380px;" align="center">${n.showIndex}</td>
    <td width="420px;" align="center">${n.showNumber}</td>
    <td width="420px;" align="center"><input type="button" class="stdbtn btn_yellow" style="width:100px; height:35px;" onclick="delQ('${n.numId}')" value="删除"/></td>
  </tr>
</s:iterator>
</table>
</s:if>
<s:else>
<table cellpadding="0" cellspacing="0" border="0" class="stdtable" style="border-top:1px #ddd solid; width:98%;">
  <tr>
    <td>序号</td>
    <td>号码</td>
  </tr>
<s:iterator value="#request.setList" id="n">
  <tr>
    <td width="380px;">${n.showIndex}</td>
    <td width="420px;">${n.showNumber}</td>
  </tr>
</s:iterator>
</table>
</s:else>
</s:if>
<div style="margin:10px;">
抽奖设置规则说明：<br/>
幸运大转盘会根据您在此处设置的抽奖顺序和号码显示；<br/>
在抽奖途中请勿离开幸运大转盘页面，否则抽奖结果将会被清空。
</div>

<br/>
<div style="margin:10px;">
  <input type="button" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="关  闭" onclick="doClose()"/>
</div>
</s:form>

<br/>

<jsp:include page="footer.jsp" />
</body>
</html>
