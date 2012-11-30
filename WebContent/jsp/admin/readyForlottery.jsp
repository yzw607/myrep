<%@ page language="java" import="com.wmp.userManage.bean.User, com.wmp.common.Common" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
User user = (User)session.getAttribute(Common.USERINFO);
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
<script>
function goLottery(activityId)
{
  window.open("<%=path%>/goLottery.action?activityId=" + activityId,"","fullscreen=1,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1");
}
</script>

<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body class="loggedin">

	<!-- START OF HEADER -->
	<div class="header radius3">
    	<div class="headerinner">
        </div><!--headerinner-->
	</div><!--header-->
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
                	<li class="current"><a href="#">短信抽奖</a></li>
                </ul><!--maintabmenu-->
                
                <div class="content">
 
                
<% 
if(user.getNumberLimit() == 0)
{
%>
                <div class="contenttitle radiusbottom0">
                	<h2 class="table"><span>系统提示</span></h2>
                </div>
                <br />
                    
                <div class="notification msgalert">
                    <p>没钱就不要使用本系统，先交钱！</p>
                </div><!-- notification msginfo -->
                
<%
}
else
{
%>


                <div class="contenttitle radiusbottom0">
                	<h2 class="table"><span>祝福短号列表</span></h2>
                </div><!--contenttitle-->
                <table cellpadding="0" cellspacing="0" border="0" class="stdtable" id="dyntable">
                    <colgroup>
                        <col class="con0" />
                        <col class="con1" />
                        <col class="con0" />
                        <col class="con1" />
                        <col class="con0" />
                        <col class="con1" />
                        <col class="con0" />
                        <col class="con1" />
                    </colgroup>
                    <thead>
                        <tr>
                            <th class="head0">祝福短号</th>
                            <th class="head1">短号状态 </th>
                            <th class="head0">婚礼主题</th>
                            <th class="head1">婚礼地点</th>
                            <th class="head0">新郎</th>
                            <th class="head1">新娘</th>
                            <th class="head0">操作</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th class="head0">祝福短号</th>
                            <th class="head1">短号状态 </th>
                            <th class="head0">婚礼主题</th>
                            <th class="head1">婚礼地点</th>
                            <th class="head0">新郎</th>
                            <th class="head1">新娘</th>
                            <th class="head0">操作</th>
                        </tr>
                    </tfoot>
                    <tbody>
                    
                    
<s:iterator value="#request.activityList" id="activity">

                        <tr class="gradeX">
                            <td align="center">${activity.number}</td>
                            <td align="center">${activity.isUsedStr}</td>
                            <td title="${activity.title}">${fn:substring(activity.title,0,21)}</td>
                            <td title="${activity.address}">${fn:substring(activity.address,0,21)}</td>
                            <td>${activity.bridegroom}</td>
                            <td>${activity.bride}</td>
<s:if test="#activity.bridegroom != null">
                            <td align="center"><button class="stdbtn btn_red" onclick="goLottery('${activity.id}')">进入抽奖</button></td>
</s:if>
<s:else>
                            <td align="center"></td>
</s:else>
                        </tr>
</s:iterator>
                    
                    </tbody>
                </table>
<%
}
%>       

                <br clear="all" />
                    
                </div><!--content-->
                
            </div><!--maincontentinner-->
            
            <div class="footer">
            	<p>我爱说 &copy; 2012. All Rights Reserved. </p>
            </div><!--footer-->
            
        </div><!--maincontent-->
        
     	</div><!--mainwrapperinner-->
    </div><!--mainwrapper-->
	<!-- END OF MAIN CONTENT -->
    

</body>

</html>
