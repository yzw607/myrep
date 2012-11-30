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
  window.location.href = "<%=path%>/subAccount.action";
}

function initPSW()
{
  if(window.confirm("您是否确认初始化该账号的密码？"))
  {
    window.document.forms[0].submit();
  }
}

function queryRecord()
{
  window.location.href = "<%=path%>/querySubAccountRecord.action?user.userId=${subAccount.userId}";
}

function initPage()
{
  var msg = "${sysMsg}";  
  if(msg != "")
  {
    alert(msg);
  }
}
</script>
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body class="loggedin" onload="initPage()">

	<!-- START OF HEADER -->
    <jsp:include page="header.jsp" />
    <!-- END OF HEADER -->
        
    <!-- START OF MAIN CONTENT -->
    <div class="mainwrapper">
     	<div class="mainwrapperinner">
         	
        <div class="mainleft">
          	<div class="mainleftinner">
              	<jsp:include page="menu.jsp" /> 
            	<div id="togglemenuleft"><a></a></div>
            </div><!--mainleftinner-->
        </div><!--mainleft-->
        
        <div class="maincontent noright">
        	<div class="maincontentinner">
            	
                <ul class="maintabmenu">
                	<li class="current"><a href="dashboard.html">子账户管理</a></li>
                </ul><!--maintabmenu-->
                
                <div class="content">
                    
                <div class="contenttitle radiusbottom0">
                	<h2 class="table"><span>子账户信息</span></h2>
                </div><!--contenttitle-->	
                
                <s:form action="initPsw" theme="simple" method="post">
                <s:hidden name="subAccount.userId" />
                <table cellpadding="0" cellspacing="0" border="0" class="stdtable dashtable stdform">
                	<colgroup>
                        <col class="con1" />
                        <col class="con0" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <td>用户账号</td>
                            <td>
                              <s:textfield name="subAccount.userCode" id="userCode" readonly="true" />&nbsp;&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td>用户姓名</td>
                            <td>
                              <s:textfield name="subAccount.userName" id="userName" readonly="true" />&nbsp;&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td>联系电话</td>
                            <td>
                              <s:textfield name="subAccount.tel" id="tel" readonly="true" />&nbsp;&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td>电子邮箱</td>
                            <td>
                              <s:textfield name="subAccount.email" id="email" readonly="true" />&nbsp;&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td>公司名称</td>
                            <td>
                              <s:textfield name="subAccount.companyName" id="companyName" readonly="true" />&nbsp;&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td>公司地址</td>
                            <td>
                              <s:textfield name="subAccount.companyAddress" id="companyAddress" readonly="true" />&nbsp;&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td>剩余使用次数</td>
                            <td>
                              <s:textfield name="subAccount.remainingTime" id="remainingTime" readonly="true" />&nbsp;&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td>总共使用次数</td>
                            <td>
                              <s:textfield name="countCost" id="countCost" readonly="true"/>&nbsp;&nbsp;
                            </td>
                        </tr>
                    </tbody>
                </table>
                <br/>
                <input type="button" class="stdbtn btn_yellow" onclick="initPSW()" value="密码初始化" />&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" class="stdbtn btn_yellow" onclick="queryRecord()" value="&nbsp;&nbsp;账户记录&nbsp;&nbsp;" />&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" class="stdbtn btn_yellow" onclick="goBack()" value=" &nbsp;&nbsp;&nbsp;&nbsp;返&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回&nbsp;&nbsp;&nbsp;&nbsp; " />
                </s:form>
                <br />
                <br />
                </div><!--content-->
                
            </div><!--maincontentinner-->
            
            <jsp:include page="footer.jsp" />
            <!--footer-->
            
        </div><!--maincontent-->
     	</div><!--mainwrapperinner-->
    </div><!--mainwrapper-->
	<!-- END OF MAIN CONTENT -->
    

</body>

</html>

