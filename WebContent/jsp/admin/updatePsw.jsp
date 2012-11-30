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
<script type="text/javascript" src="<%=path%>/scripts/checkPsw.js"></script>
<script>
function recordQuery()
{
  window.location.href = "<%=path%>/queryRecord.action";
}

function recharge()
{
  window.location.href = "<%=path%>/goRecharge.action";
}
</script>
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body class="loggedin">

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
                	<li class="current"><a href="dashboard.html">用户信息</a></li>
                </ul><!--maintabmenu-->
                
                <div class="content">
                    
                <div class="contenttitle radiusbottom0">
                	<h2 class="table"><span>用户密码修改</span></h2>
                </div><!--contenttitle-->	
                
                <s:form action="doUpdatePsw" theme="simple" method="post">
                <s:hidden name="user.userId" />
                <table cellpadding="0" cellspacing="0" border="0" class="stdtable dashtable stdform">
                	<colgroup>
                        <col class="con1" width="100px"/>
                        <col class="con0" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <td>旧密码</td>
                            <td>
                              <s:password name="oldPsw" id="oldPsw" onblur="checkOldPsw()" cssStyle="width:240px;" maxlength="20" />&nbsp;&nbsp;
                              <span id="oldPswInfo"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>输入新密码</td>
                            <td>
                              <s:password name="newPsw" id="newPsw" cssStyle="width:240px;" onblur="checkNewPsw()" maxlength="20" />&nbsp;&nbsp;
                              <span id="newPswInfo"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>确认新密码</td>
                            <td>
                              <s:password name="newPsw2" id="newPsw2" cssStyle="width:240px;" onblur="checkNewPsw2()" maxlength="20"/>&nbsp;&nbsp;
                              <span id="newPsw2Info"></span>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <br/>
                <input type="button" class="stdbtn btn_yellow" onclick="goUpdate()" value="确 定" />
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

