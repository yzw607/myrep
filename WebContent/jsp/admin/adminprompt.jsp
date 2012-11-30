<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery.dataTables.min.js"></script>

<script>
function showActivity(id)
{
  window.location.href = "<%=path%>/viewActivity.action?activityInfo.id=" + id;
}

function doSearch()
{
  document.getElementById("indexPage").value = "1";
  document.forms[0].submit();
}

function goPage(pageNum)
{
  document.getElementById("indexPage").value = pageNum;
  document.forms[0].submit();
}
</script>

<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
</head>
  
  <body>
<div class="header radius3">
  <div class="headerinner">
  </div>
  <!--headerinner--> 
</div>
<div class="mainwrapper">
  <div class="mainwrapperinner">
    <div class="mainleft">
      <div class="mainleftinner">
        <jsp:include page="menu.jsp" /> 
        <!--leftmenu-->
        <div id="togglemenuleft"><a></a></div>
      </div>
      <!--mainleftinner--> 
    </div>
    
    <div class="maincontent noright">
      <div class="maincontentinner">
        <!--maintabmenu-->

            <div class="promptmain">
            <table  border="0" cellspacing="0" cellpadding="0" width="600">
              <tr>
                <th>
                <div class="promptpic_alert" style="display:none" >
                </div>
                <div class="promptpic_info" style="display:none"  >
                </div>
                <div class="promptpic_success" style="display:none" >
                </div>
                <div class="promptpic_error">
                </div>
            <div class="prompttxt1">对不起，修改失败！</div>
            </th>
              </tr>
              <tr>
              <td style="padding-left:40px;">
             <div class="prompttxt2">您的祝福短号已经超过了20个，您可以选择替换或者保留！</div>
              </td>
              </tr>
              <tr>
                <td align="center">
                <button class="stdbtn btn_yellow" onClick="">确定</button>
                
                </td>
              </tr>
            </table>
            </div>
        </div>
    </div>
    
    </div>
    </div>
    
    
<div class="prompt">

</div>
<div class="about">
<p>我爱说 &copy; 2012. All Rights Reserved. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;爱说热线：400-101-4595&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;鄂ICP备12008340号</p> </div>

  </body>
</html>
