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

function goUpdate()
{
	document.forms[0].submit();
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
                	<li class="current"><a href="dashboard.html">模板管理</a></li>
                </ul><!--maintabmenu-->
                
                <div class="content">
                    
                <div class="contenttitle radiusbottom0">
                	<h2 class="table"><span>模板信息</span></h2>
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
                            <td>模板主题</td>
                            <td>
                              <input name="msgTemplate.tmpltTitle" value="${msgTemplate.tmpltTitle}" id="tmpltTitle" maxlength="20" style="width:160px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td>模板内容</td>
                            <td>
                              <textarea rows="6" cols="40" onkeyup="this.value = this.value.substring(0,200)" 
                               name="msgTemplate.tmpltContent" id="tmpltContent" style="width:260px;" >${msgTemplate.tmpltContent}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>模板说明</td>
                            <td>
                              <input type="text" name="msgTemplate.tmpltComment" value="${msgTemplate.tmpltComment}" id="tmpltComment" maxlength="20" style="width:160px;"/>&nbsp;&nbsp;
                            </td>
                        </tr>

                    </tbody>
                </table>
                <br/>
                <input type="button" class="stdbtn btn_yellow" onclick="goUpdate()" value="确 定" />&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" class="stdbtn btn_yellow" onclick="goBack()" value="返 回" />
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

