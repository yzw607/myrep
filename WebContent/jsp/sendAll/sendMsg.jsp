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
<link rel="stylesheet" href="<%=path%>/jsp/admin/css/style.css" type="text/css" />
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
<script type="text/javascript" src="<%=path%>/scripts/ajax.js"></script>
<script>
function goBack()
{
  window.location.href = "<%=path%>/msgTemplate.action";
}

function initPage()
{
  var msg = "${sysMsg}"; 
  if(msg != "")
  {
    alert(msg);
  }
}

function doSend()
{
	var phoneNumber = document.getElementById('phoneNumber').value;
	if(!isMobilPhone(phoneNumber)){
		alert("手机号不合法，请重新输入！");
		document.getElementById('phoneNumber').focus();
		return false;
	}
	var templateContent = document.getElementById("templateContent").value;
	if(confirm("模板内容：\n" + templateContent + "\n确认发送短信?")){
		document.forms[0].action = "<%=path%>/doSendMsg.action";
		document.forms[0].submit();
	}else{
		return false;
	}

}

function checkPhoneNumber(phoneNumber)
{
  if(!isMobilPhone(phoneNumber.value))
  {
    flag = false;
    document.getElementById("phoneNumberSpan").innerHTML = "<img src='<%=path%>/images/cha1.png' style='vertical-align:middle;'/> 手机号不合法，请重新输入！";
    return;
  }
  document.getElementById("phoneNumberSpan").innerHTML = "";
}

//校验手机号
function isMobilPhone(s)
{
var patrn = /(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
if (!patrn.exec(s))
{
  return false;
} 
  return true; 
} 

function getTemplateById(){
	var optionId = document.getElementById("optionId").value;
	if(optionId != '0' && optionId != ''){
		document.forms[0].action = "<%=path%>/getTemplateById.action";
		document.forms[0].submit();
	}
}
</script>
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body class="loggedin" onload="initPage()">

	<!-- START OF HEADER -->
    <jsp:include page="../admin/header.jsp" />
    <!-- END OF HEADER -->
        
    <!-- START OF MAIN CONTENT -->
    <div class="mainwrapper">
     	<div class="mainwrapperinner">
         	
        <div class="mainleft">
          	<div class="mainleftinner">
              	<jsp:include page="../admin/menu.jsp" /> 
            	<div id="togglemenuleft"><a></a></div>
            </div><!--mainleftinner-->
        </div><!--mainleft-->
        
        <div class="maincontent noright">
        	<div class="maincontentinner">
            	
                <ul class="maintabmenu">
                	<li class="current"><a href="dashboard.html">短信发送</a></li>
                </ul><!--maintabmenu-->
                
                <div class="content">
                    
                <div class="contenttitle radiusbottom0">
                	<h2 class="table"><span>短信发送</span></h2>
                </div><!--contenttitle-->	
                
                <s:form action="saveMsgTemplate" theme="simple" method="post">
                <s:hidden name="msgTemplate.tmpltId"></s:hidden>
                <table cellpadding="0" cellspacing="0" border="0" class="stdtable dashtable stdform">
                	<colgroup>
                        <col class="con1" />
                        <col class="con0" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <td>手机号码</td>
                            <td>
                              <input name="phoneNumber" value="${phoneNumber}" onblur="checkPhoneNumber(this)" id="phoneNumber" maxlength="20" style="width:160px;"/>
                              <span id="phoneNumberSpan"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>模板内容</td>
                            <td>
                              <s:select name="optionId" id="optionId" list="optionList" listKey="id" cssStyle="width:20px;"  listValue="name" 
                              headerKey="0" headerValue="--请选择短信模板--" onchange="getTemplateById();"/><br /><br />
                              <textarea rows="6" cols="40" onkeyup="this.value = this.value.substring(0,200)" 
                               name="templateContent" id="templateContent" style="width:260px;" >${templateContent}</textarea>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <br/>
                <input type="button" class="stdbtn btn_yellow" onclick="doSend()" value="发送" />&nbsp;&nbsp;&nbsp;&nbsp;
                </s:form>
                <br />
                <br />
                </div><!--content-->
                
            </div><!--maincontentinner-->
            
            <jsp:include page="../admin/footer.jsp" />
            <!--footer-->
            
        </div><!--maincontent-->
     	</div><!--mainwrapperinner-->
    </div><!--mainwrapper-->
	<!-- END OF MAIN CONTENT -->
    

</body>

</html>

