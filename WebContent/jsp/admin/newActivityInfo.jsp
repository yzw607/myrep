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
<script src="<%=path%>/dhtmlx/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->

<script>
var myCalendar;
function doOnLoad() 
{
  myCalendar = new dhtmlXCalendarObject(["holdDate"]);
}

function doSave()
{
  var number = document.getElementById("number");
  if(number.options.length == 0)
  {
    if(window.confirm("您还没有祝福短号，请先至【祝福短号】页面维护您的短号！\n是否前往【祝福短号】页面？"))
    {
      window.location.href="<%=path%>/queryNumber.action";
      return;
    }
    else
    {
      return;
    }
  }
  
  if(document.getElementById("title").value == "")
  {
    alert("请输入婚礼关键字！");
    document.getElementById("title").focus();
    return;
  }
  
  if(document.getElementById("bridegroom").value == "")
  {
    alert("请输入新郎名字！");
    document.getElementById("bridegroom").focus();
    return;
  }
  
  if(document.getElementById("bride").value == "")
  {
    alert("请输入新娘名字！");
    document.getElementById("bride").focus();
    return;
  }
  
  if(document.getElementById("stencilId").value == "")
  {
    alert("请选择背景模版类型！");
    return;
  }
  
  
  if(document.getElementById("file").value != "")
  {
    t = /^.+(\.jpg|\.JPG)$/; 
    var fName = document.getElementById("file").value;
    if(!t.test(fName)) 
    {
      alert("自定义背景请上传jpg文件");
      return;
    }
  }
  
  document.forms[0].submit();
}

function goQuestions()
{
  alert("请先保存该婚礼信息！");
}

function selBG(obj)
{
  if(obj.value == 0)
  {
    document.getElementById("fileStr").style.display = "";
    document.getElementById("fileArea").style.display = "";
  }
  else
  {
    document.getElementById("fileStr").style.display = "none";
    document.getElementById("fileArea").style.display = "none";
  }
}

function selStencil()
{
  var sFeatures="dialogHeight: 700px;dialogWidth:1000px";
  var stencil = window.showModalDialog("<%=path%>/queryStencil.action", "", sFeatures);
  if(stencil != null)
  {
    document.getElementById("tempStencilName").value = stencil.name;
    document.getElementById("stencilId").value = stencil.id;
    document.getElementById("stencilName").value = stencil.name;
  }
}
</script>

</head>

<body class="loggedin" onload="doOnLoad();">
	<!-- START OF HEADER -->
<jsp:include page="header.jsp" />
    <!-- END OF HEADER -->
    
<s:form action="saveActivity" method="post" theme="simple" enctype ="multipart/form-data">
<div class="contenttitle radiusbottom0" style="margin:10px;">
  <h2 class="table"><span>新增婚礼</span></h2>
</div>

<table cellpadding="0" cellspacing="0" border="0" class="stdtable dashtable stdform" style=" width:98%; margin:10px; margin-top:0; border-top:1px #ddd solid; ">
  <colgroup>
  <col class="con1" />
  <col class="con0" />
  <col class="con1" />
  <col class="con0" />
  </colgroup>

  <tr>
    <td width="120px;">婚礼关键字：</td>
    <td width="380px;"><s:textfield name="activityInfo.title" id="title" maxLength="150"/></td>
    <td width="120px;">婚礼地点：</td>
    <td width="420px;"><s:textfield name="activityInfo.address" id="address"  maxLength="150"/></td>
  </tr>   
  <tr>
    <td>婚礼日期：</td>
    <td><input type="text" name="activityInfo.holdDate" id="holdDate" value="<s:date name="activityInfo.holdDate" format="yyyy-MM-dd"/>" readonly /></td>
    <td>婚礼时段：</td>
    <td>
      <input type="checkbox" name="activityInfo.period" value="a"/> <%=Common.PERIOD_A%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="checkbox" name="activityInfo.period" value="b"/> <%=Common.PERIOD_B%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="checkbox" name="activityInfo.period" value="c"/> <%=Common.PERIOD_C%>
    </td>
  </tr>
  <tr>
    <td>新郎：</td>
    <td><s:textfield name="activityInfo.bridegroom" id="bridegroom"  maxLength="6"/></td>
    <td>新郎电话：</td>
    <td><s:textfield name="activityInfo.bridegroomTel" id="bridegroomTel"  maxLength="15"/></td>
  </tr>
  <tr>
    <td>新娘：</td>
    <td><s:textfield name="activityInfo.bride" id="bride"  maxLength="6"/></td>
    <td>新娘电话：</td>
    <td><s:textfield name="activityInfo.brideTel" id="brideTel"  maxLength="15"/></td>
  </tr>
  <tr>
    <td>婚礼策划人：</td>
    <td><s:textfield name="activityInfo.planners" id="planners"  maxLength="50"/></td>
    <td>策划人电话：</td>
    <td><s:textfield name="activityInfo.plannersTel" id="plannersTel"  maxLength="15"/></td>
  </tr>
  <tr>
    <td>现场负责人：</td>
    <td><s:textfield name="activityInfo.site" id="site" maxLength="50" /></td>
    <td>负责人电话：</td>
    <td><s:textfield name="activityInfo.siteTel" id="siteTel"  maxLength="15"/></td>
  </tr>
  <tr>
    <td>背景模版：${activityInfo.picId}</td>
    <td>
      <input type="text" id="tempStencilName" value="尚未选择背景模版，请点此处进行设置" style="cursor:hand" readonly onclick="selStencil()"/>
      <s:hidden name="activityInfo.stencilId" id="stencilId" maxLength="50" />
      <s:hidden name="activityInfo.stencilName" id="stencilName" maxLength="50" />
    </td>
    <td><span id="fileStr">背景上传：</span></td>
    <td><span id="fileArea"><s:file name="file" id="file"/><br/><font color="red">文件大小：不大于2M，分辨率：1024*768，图片格式：JPG</font></span></td>
  </tr>
  <tr>
    <td>祝福短号：</td>
    <td>
      <select name="activityInfo.number" id="number">
<s:iterator value="#request.numberList" id="userNumber">
                  <option value="${userNumber.number}">${userNumber.number}</option>
</s:iterator>
      </select>
    </td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>欢迎致辞：</td>
    <td colspan="3"><s:textfield name="activityInfo.welcomeMsg" id="site" maxLength="70" cssStyle="width:750px"/></td>
  </tr>
  <tr>
    <td>爱说语录投递地址：</td>
    <td colspan="3"><s:textfield name="activityInfo.postAddress" id="postAddress"  maxLength="50" cssStyle="width:750px"/></td>
  </tr>
</table>

<br/><br/>

<div style="margin:10px;">
  <input type="button" id="registerBtn" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="保   存" onclick="doSave()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="返   回" onclick="javascript:window.history.back();"/>
</div>
</s:form>

<br/>

<jsp:include page="footer.jsp" />
</body>
</html>
