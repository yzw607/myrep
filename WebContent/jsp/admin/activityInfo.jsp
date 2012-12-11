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
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<title>我爱说 - 用户管理平台</title>
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
<s:if test="activityInfo.status==0">
  myCalendar = new dhtmlXCalendarObject(["holdDate"]);
</s:if>
  var sysMsg = "${sysMsg}";
  if(sysMsg != "")
  {
    alert(sysMsg);
  }
}

<s:if test="activityInfo.status==0">
function setPic()
{
  window.open("<%=path%>/goUploadPic.action?activityId=${activityInfo.id}","","fullscreen=1,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1");
}

function doSetting()
{
  window.open("<%=path%>/settingQuestions.action?activityId=${activityInfo.id}","","fullscreen=1,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1");
}

function doSetting2()
{
  window.open("<%=path%>/settingLottery.action?activityId=${activityInfo.id}","","fullscreen=1,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1");
}

function delPic()
{
  window.location.href = "<%=path%>/delPic.action?activityInfo.id=${activityInfo.id}";
}
</s:if>

function doUpdate()
{
  if(document.getElementById("stencilId").value == "-1" && document.getElementById("file") != null)
  {
    t = /^.+(\.jpg|\.JPG)$/; 
    var fName = document.getElementById("file").value;
    if(!t.test(fName)) 
    {
      alert("自定义背景请上传jpg文件");
      return;
    }
  }

  var form = document.forms[0];
  form.action = "<%=path%>/updateActivity.action";
  form.submit();
}

function doActivate()
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
  
  if(document.getElementById("holdDate").value == "")
  {
    alert("请选择婚礼日期！");
    return;
  }
  
  var hDate = document.getElementById("holdDate").value;
  var sDate = "<%=Common.getSysDateStr()%>";
  var holdDate = hDate.split('-'); //转成成数组，分别为年，月，日，下同 
  var sysDate = sDate.split('-'); 
  var holdDateValue = holdDate[0]+"/" + holdDate[1]+ "/" + holdDate[2]; 
  var sysDateValue = sysDate[0] + "/" + sysDate[1] + "/" + sysDate[2]; 
  if (sysDateValue >= holdDateValue) 
  { 
    alert("婚礼日期必须大于今日！");
    return;
  } 

  var period = document.getElementsByName("activityInfo.period");
  if(!period[0].checked && !period[1].checked && !period[2].checked)
  {
    alert("请选择婚礼时段！");
    return;
  }
  
  if(document.getElementById("bridegroom").value == "")
  {
    alert("请输入新郎姓名！");
    document.getElementById("bridegroom").focus();
    return;
  }
  
  if(document.getElementById("bride").value == "")
  {
    alert("请输入新娘姓名！");
    document.getElementById("bride").focus();
    return;
  }

  if(document.getElementById("stencilId").value == "-1")
  {
    if(document.getElementById("file") != null)
    {
      t = /^.+(\.jpg|\.JPG)$/; 
      var fName = document.getElementById("file").value;
      if(!t.test(fName)) 
      {
        alert("自定义背景请上传jpg文件");
        return;
      }
    }
  }
  
  if(document.getElementById("videoPath").value == "")
  {
    alert("请选择指定视频路径！");
    return;
  }
  
  if(document.getElementById("picPath").value == "")
  {
    alert("请选择滚动图片路径！");
    return;
  }

  if(window.confirm("是否确认该婚礼信息，确认后系统将减去您账户里相应的使用次数，且婚礼信息不能修改！"))
  {
    var form = document.forms[0];
    form.action = "<%=path%>/doActivate.action";
    form.submit();
  }
}

function goBack()
{
  window.location.href="<%=path%>/planList.action";
}

function preViewSMS()
{
  stencilId = document.getElementById("stencilId").value;
  window.open("<%=path%>/preViewSMSPage.action?activityInfo.id=${activityInfo.id}&stencilId=" + stencilId,"","fullscreen=1,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1");
}

<s:if test="activityInfo.status==2">
function showSMS()
{
  stencilId = document.getElementById("stencilId").value;
  window.open("<%=path%>/showSMSPage.action?activityInfo.id=${activityInfo.id}&stencilId=" + stencilId,"","fullscreen=1,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1");
}

function goLottery()
{
  stencilId = document.getElementById("stencilId").value;
  window.open("<%=path%>/goLottery.action?activityId=${activityInfo.id}&stencilId=" + stencilId,"","fullscreen=1,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1");
}
</s:if>

<s:if test="activityInfo.status==3">
function exportSMS()
{
  window.open("<%=path%>/dataExport.action?activityInfo.id=${activityInfo.id}");
}
</s:if>

<s:if test="activityInfo.status==1">
function doOrderBack()
{
  if(window.confirm("是否退订该婚礼信息，退订后系统将返还您账户里相应的使用次数，且该婚礼将不再生效！"))
  {
    var form = document.forms[0];
    form.action = "<%=path%>/doOrderBack.action";
    form.submit();
  }
}
</s:if>

function goQuestions()
{
  stencilId = document.getElementById("stencilId").value;
  window.open("<%=path%>/viewQuestions.action?activityId=${activityInfo.id}&stencilId=" + stencilId,"","fullscreen=1,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1");
}

function preViewLottery()
{
  stencilId = document.getElementById("stencilId").value;
  window.open("<%=path%>/jsp/admin/preViewLottery.action?activityId=${activityInfo.id}&stencilId=" + stencilId,"","fullscreen=1,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1");
}

function setPic()
{
  window.open("<%=path%>/goUploadPic.action?activityId=${activityInfo.id}","","fullscreen=1,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1");
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

<s:if test="#session.userinfo.userType == 'sys_admin'">
function changeStatus()
{
  var form = document.forms[0];
  form.action = "<%=path%>/changeStatus.action";
  form.submit();
}
</s:if>
</script>

</head>

<body class="loggedin" onload="doOnLoad();">
	<!-- START OF HEADER -->
<jsp:include page="header.jsp" />
    <!-- END OF HEADER -->
    
<s:form action="" method="post" theme="simple" enctype ="multipart/form-data">
<s:hidden name="activityInfo.id"></s:hidden>
<div class="contenttitle radiusbottom0" style="margin:10px;">
  <h2 class="table"><span>婚礼信息[${activityInfo.statusStr}]</span></h2>
</div>

<s:if test="activityInfo.status==0">
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
    <td><input type="text" name="activityInfo.holdDate" id="holdDate" value="${activityInfo.holdDate}" readonly /></td>
    <td>婚礼时段：</td>
    <td>
    <s:if test="activityInfo.period.indexOf('a')>=0">
      <input type="checkbox" name="activityInfo.period" value="a" checked/>
    </s:if>
    <s:else>
      <input type="checkbox" name="activityInfo.period" value="a"/>
    </s:else>
       <%=Common.PERIOD_A%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <s:if test="activityInfo.period.indexOf('b')>=0">
      <input type="checkbox" name="activityInfo.period" value="b" checked/>
    </s:if>
    <s:else>
      <input type="checkbox" name="activityInfo.period" value="b"/>
    </s:else>
       <%=Common.PERIOD_B%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <s:if test="activityInfo.period.indexOf('c')>=0">
      <input type="checkbox" name="activityInfo.period" value="c" checked/>
    </s:if>
    <s:else>
      <input type="checkbox" name="activityInfo.period" value="c"/>
    </s:else>
       <%=Common.PERIOD_C%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
    <td>背景模版：</td>
    <td>
      <input type="text" id="tempStencilName" value="${activityInfo.stencilName}" style="cursor:hand" readonly onclick="selStencil()"/>
      <s:hidden name="activityInfo.stencilId" id="stencilId" />
      <s:hidden name="activityInfo.stencilName" id="stencilName" />
    </td>
    <td><span id="fileStr">背景上传：</span></td>
    <td>
<s:if test="activityInfo.picFileName != null && activityInfo.picFileName != ''">
        <span id="fileArea">
          ${activityInfo.picFileName}&nbsp;&nbsp; 
          <img src="<%=path%>/images/cha1.png" title="删除" style='vertical-align:bottom;' onclick="delPic()"/>
        </span>
</s:if>
<s:else>
        <span id="fileArea"><s:file name="file" id="file"/><br/><font color="red">文件大小：不大于2M，分辨率：1024*768，图片格式：JPG</font></span>
</s:else>
    </td>
  </tr>
  <!--  <tr>
    <td>祝福短号：</td>
    <td>
      <select name="activityInfo.number" id="number">
<s:iterator value="#request.numberList" id="userNumber">
       <s:if test="activityInfo.number == #userNumber.number"><option value="${userNumber.number}" selected>${userNumber.number}</option></s:if>
       <s:else><option value="${userNumber.number}">${userNumber.number}</option></s:else>
</s:iterator>
      </select>
    </td>
    <td>设置滚动相片：</td>
    <td><input type="button" value="设置" onclick="setPic()"/></td>
  </tr>-->
  <tr>
    <td>指定视频路径：</td>
    <td>
       <s:textfield name="activityInfo.videoPath" id="videoPath"  maxLength="50"/>
    </td>
    <td>指定滚动图片路径</td>
    <td><s:textfield name="activityInfo.picPath" id="picPath"  maxLength="50"/></td>
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
</s:if>
<s:else>
<table cellpadding="0" cellspacing="0" border="0" class="stdtable dashtable stdform" style=" width:98%; margin:10px; margin-top:0;  border-top:1px #ddd solid; ">
  <colgroup>
  <col class="con1" />
  <col class="con0" />
  <col class="con1" />
  <col class="con0" />
  </colgroup>

  <tr>
    <td width="120px;">婚礼关键字：</td>
    <td width="380px;"><s:textfield name="activityInfo.title" id="title" maxLength="150" readonly="true"/></td>
    <td width="120px;">婚礼地址：</td>
    <td width="420px;"><s:textfield name="activityInfo.address" id="address"  maxLength="150" readonly="true"/></td>
  </tr>  
  <tr>
    <td>婚礼日期：</td>
    <td><input type="text" name="activityInfo.holdDate" id="holdDate" value="${activityInfo.holdDate}" readonly /></td>
    <td>婚礼时段：</td>
    <td>
    <s:if test="activityInfo.period.indexOf('a')>=0">
      <input type="checkbox" name="activityInfo.period" value="a" checked disabled/>
    </s:if>
    <s:else>
      <input type="checkbox" name="activityInfo.period" value="a" disabled/>
    </s:else>
       <%=Common.PERIOD_A%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <s:if test="activityInfo.period.indexOf('b')>=0">
      <input type="checkbox" name="activityInfo.period" value="b" checked disabled/>
    </s:if>
    <s:else>
      <input type="checkbox" name="activityInfo.period" value="b" disabled/>
    </s:else>
       <%=Common.PERIOD_B%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <s:if test="activityInfo.period.indexOf('c')>=0">
      <input type="checkbox" name="activityInfo.period" value="c" checked disabled/>
    </s:if>
    <s:else>
      <input type="checkbox" name="activityInfo.period" value="c" disabled/>
    </s:else>
       <%=Common.PERIOD_C%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
  </tr>
  <tr>
    <td>新郎：</td>
    <td><s:textfield name="activityInfo.bridegroom" id="bridegroom"  maxLength="6" readonly="true"/></td>
    <td>新郎电话：</td>
    <td><s:textfield name="activityInfo.bridegroomTel" id="bridegroomTel"  maxLength="15" readonly="true"/></td>
  </tr>
  <tr>
    <td>新娘：</td>
    <td><s:textfield name="activityInfo.bride" id="bride"  maxLength="6" readonly="true"/></td>
    <td>新娘电话：</td>
    <td><s:textfield name="activityInfo.brideTel" id="brideTel"  maxLength="15" readonly="true"/></td>
  </tr>
  <tr>
    <td>婚礼策划人：</td>
    <td><s:textfield name="activityInfo.planners" id="planners"  maxLength="50" readonly="true"/></td>
    <td>策划人电话：</td>
    <td><s:textfield name="activityInfo.plannersTel" id="plannersTel"  maxLength="15" readonly="true"/></td>
  </tr>
  <tr>
    <td>现场负责人：</td>
    <td><s:textfield name="activityInfo.site" id="site" maxLength="50"  readonly="true"/></td>
    <td>负责人电话：</td>
    <td><s:textfield name="activityInfo.siteTel" id="siteTel"  maxLength="15" readonly="true"/></td>
  </tr>
  <tr>
    <td>背景模版：</td>
    <td>
      <input type="text" id="tempStencilName" value="${activityInfo.stencilName}" style="cursor:hand" readonly onclick="selStencil()"//>
      <s:hidden name="activityInfo.stencilId" id="stencilId" />
      <s:hidden name="activityInfo.stencilName" id="stencilName" />
    </td>
    <td><span id="fileStr">背景上传：</span></td>
    <td>
<s:if test="activityInfo.picFileName != null && activityInfo.picFileName != ''">
        <span id="fileArea">
          ${activityInfo.picFileName}&nbsp;&nbsp; 
        </span>
</s:if>
<s:else>
        <span id="fileArea">未设置背景页面</span>
</s:else>
    </td>
  </tr>
  <tr>
    <td>祝福短号：</td>
    <td>
      <select name="activityInfo.number" id="number" disabled="true">
<s:iterator value="#request.numberList" id="userNumber">
       <s:if test="activityInfo.number == #userNumber.number"><option value="${userNumber.number}" selected>${userNumber.number}</option></s:if>
       <s:else><option value="${userNumber.number}">${userNumber.number}</option></s:else>
</s:iterator>
      </select>
    </td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>欢迎致辞：</td>
    <td colspan="3"><s:textfield name="activityInfo.welcomeMsg" id="site" maxLength="70" cssStyle="width:750px"  readonly="true"/></td>
  </tr>
  <tr>
    <td>爱说语录投递地址：</td>
    <td colspan="3"><s:textfield name="activityInfo.postAddress" id="postAddress"  maxLength="50" cssStyle="width:750px" readonly="true"/></td>
  </tr>
  
<s:if test="activityInfo.status!= 3">  
  <tr>
    <td>无线祝福：</td>
    <td>
      <input type="text" value="http://www.say520.cn/woaishuo.action?hl=${activityInfo.id}" maxLength="50" readonly/>
    </td>
    <td colspan="2">分享给亲朋好友们，随时随地同步显示祝福短信</td>
  </tr>
</s:if>
  
</table>
</s:else>


<s:if test="#session.userinfo.userType != 'sys_admin'">

<s:if test="activityInfo.status==0">
<div style="margin:10px; float:left">

  <input type="button" id="registerBtn" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="保存" onclick="doUpdate()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" id="registerBtn" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="抢答设置" onclick="doSetting()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" id="registerBtn" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="抽奖设置" onclick="doSetting2()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" id="registerBtn" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="确认" onclick="doActivate()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="返回" onclick="goBack()"/>
    <br /><br />

<p style="color:#FF0000">温馨提示：<br />
1、请保存婚礼信息，在婚礼开始前，可以点击右侧的图标进行预览； <br />
2、在婚礼开始时，您可以点击图标正式启用服务！</p>

</div>

<div style="position:absolute; left:650px; margin-top:10px;">
  <input type="button" id="registerBtn" class="stdbtn probtnsms" onclick="preViewSMS()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" id="registerBtn" class="stdbtn probtncj" onclick="preViewLottery()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" id="registerBtn" class="stdbtn probtnqd" onclick="goQuestions()" />
</div>
</s:if>

<s:if test="activityInfo.status==1">
<div style="margin:10px; float:left">

  <input type="button" id="registerBtn" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="退订" onclick="doOrderBack()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="返回" onclick="goBack()"/>
      <br /><br />

<p style="color:#FF0000">温馨提示：<br />
1、请保存婚礼信息，在婚礼开始前，可以点击右侧的图标进行预览； <br />
2、在婚礼开始时,，您可以点击图标正式启用服务！</p>

</div>

<div style="position:absolute; left:500px; margin-top:10px;">
  <input type="button" id="registerBtn" class="stdbtn probtnsms" onclick="preViewSMS()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" id="registerBtn" class="stdbtn probtncj" onclick="preViewLottery()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" id="registerBtn" class="stdbtn probtnqd" onclick="goQuestions()" />

</div>
</s:if>

<s:if test="activityInfo.status==2">
<div style="margin:10px; float:left">
  <input type="button" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="返回" onclick="goBack()"/>
    <br /><br />

<p style="color:#FF0000">温馨提示：<br />
1、请保存婚礼信息，在婚礼开始前，可以点击右侧的图标进行预览； <br />
2、在婚礼开始时,，您可以点击图标正式启用服务！</p>

</div>

<div style="position:absolute; left:500px; margin-top:10px;">
  <input type="button" id="registerBtn" class="stdbtn probtnsms" onclick="showSMS()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" id="registerBtn" class="stdbtn probtncj" onclick="goLottery()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" id="registerBtn" class="stdbtn probtnqd" onclick="goQuestions()" />

</div>
</s:if>

<s:if test="activityInfo.status==3">
<div style="margin:10px; float:left;">
  <input type="button" id="registerBtn" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="短信导出" onclick="exportSMS()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="返回" onclick="goBack()"/>
  <br /><br />

<p style="color:#FF0000">温馨提示：<br />
1、请保存婚礼信息，在婚礼开始前，可以点击右侧的图标进行预览； <br />
2、在婚礼开始时,，您可以点击图标正式启用服务！</p>

</div>

<div style="position:absolute; left:500px; margin-top:10px;">
  <input type="button" id="registerBtn" class="stdbtn probtnsms" onclick="preViewSMS()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" id="registerBtn" class="stdbtn probtncj" onclick="preViewLottery()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" id="registerBtn" class="stdbtn probtnqd" onclick="goQuestions()" />
</div>
</s:if>

</s:if>
<s:else>

<div style="margin:10px; float:left">

调整状态：

             <select name="activityInfo.status">
                <option value="0">未确认</option>
                <option value="1">已确认</option>
                <option value="2">进行中</option>
                <option value="3">已结束</option>
              </select>

  <input type="button" id="registerBtn" class="stdbtn btn_yellow" style="width:100px; height:35px;" value="确认" onclick="changeStatus()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    
    <br /><br />

</div>

</s:else>

</s:form>
</body>
</html>
