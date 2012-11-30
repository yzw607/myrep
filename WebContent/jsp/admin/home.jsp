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

function goRecharge()
{
  window.location.href = "<%=path%>/goRecharge.action";
}

function goNewActivity()
{
  window.location.href = "<%=path%>/goNewActivity.action";
}
</script>

<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body class="loggedin">

<jsp:include page="header.jsp" />
<!-- START OF MAIN CONTENT -->
<div class="mainwrapper">
    <div class="mainleft">
      <div class="mainleftinner">
        <jsp:include page="menu.jsp" /> 
        <!--leftmenu-->
        <div id="togglemenuleft"><a></a></div>
      </div>
      <!--mainleftinner--> 
    </div>
    <!--mainleft-->
    
    <div class="maincontent noright">
      <div class="maincontentinner">
        <!--maintabmenu-->
        
        <div class="content" style="margin-top:1px;">
        <div class="hometop">
        <div class="homenews">
            <div class="contenttitle radiusbottom0">
                    <h2><span>系统公告版</span></h2>
            </div>
<table width="100%" height="150" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;感谢您对“我爱说”的支持，我们是追求极致的爱说帮，您有任何对产品的优化建议或者好的想法，可以随时拨打我们的爱说热线，一经采纳，我们会赠送给您使用次数作为答谢。</td>
  </tr>
</table>

		</div>

        <div class="homehelp">
        <table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="<%=path%>/jsp/admin/images/lc/lc_1.png" /></td>
    <td><img src="<%=path%>/jsp/admin/images/lc/lc_2.png" /></td>
    <td><img src="<%=path%>/jsp/admin/images/lc/lc_3.png" /></td>
  </tr>
  <tr>
    <td><button class="stdbtn btn_yellow" style="margin:0; width:100px;" onclick="goRecharge()">实物卡充值</button></td>
    <td><button class="stdbtn btn_yellow" style="margin:0; width:100px;" onclick="goNewActivity()">新增婚礼</button></td>
    <td><button class="stdbtn" style="margin:0; width:100px; cursor:default">许愿墙</button></td>
  </tr>
  <tr>
    <td><button class="stdbtn" style="margin:0; width:100px; cursor:default">网银充值</button></td>
    <td>&nbsp;</td>
    <td><button class="stdbtn" style="margin:0; width:100px; cursor:default">抽奖</button></td>
  </tr>
</table>
        </div>
          <div class="contenttitle radiusbottom0">
            <h2 class="table"><span>最近5场婚礼</span></h2>
          </div>
          <!--contenttitle-->
          <div class="dataTables_wrapper" id="dyntable_wrapper">

            <table cellpadding="0" cellspacing="0" border="0" class="stdtable">
              <thead>
                <tr>
                  <th class="head0" rowspan="1" colspan="1">婚礼主题</th>
                  <th class="head1" rowspan="1" colspan="1">婚礼地点</th>
                  <th class="head0" rowspan="1" colspan="1">婚礼日期</th>
                  <th class="head1" rowspan="1" colspan="1">婚礼时段</th>
                  <th class="head0" rowspan="1" colspan="1">新郎</th>
                  <th class="head1" rowspan="1" colspan="1">新娘</th>
                  <th class="head0" rowspan="1" colspan="1">祝福短号</th>
                  <th class="head1" rowspan="1" colspan="1">婚礼状态</th>
                </tr>
              </thead>
              <tbody>

<s:iterator value="#request.activityList" id="activity">

                        <tr class="gradeX" onclick="showActivity('${activity.id}')">
                            <td title="${activity.title}">${fn:substring(activity.title,0,21)}<br /></td>
                            <td title="${activity.address}">${fn:substring(activity.address,0,21)}<br /></td>
                            <td align="center">${activity.holdDate}<br /></td>
                            <td>${activity.periodStr}<br /></td>
                            <td>${activity.bridegroom}<br /></td>
                            <td>${activity.bride}<br /></td>
                            <td align="center">${activity.number}<br /></td>
                            <td align="center">${activity.statusStr}<br /></td>
                        </tr>
</s:iterator>

<c:forEach begin="1" end="${emptyRow}" step="1">
					   <tr>
					       <td>&nbsp;<br /></td>
					       <td><br /></td>
					       <td><br /></td>
					       <td><br /></td>
					       <td><br /></td>
					       <td><br /></td>
					       <td><br /></td>
					       <td><br /></td>
					    </tr>
</c:forEach>

              </tbody>
            </table>
          </div>          
          <br clear="all" />

        </div>
        <!--content--> 
        
      </div>
      <!--maincontentinner-->
      
      <jsp:include page="footer.jsp" />
      <!--footer--> 
      
    </div>
    <!--maincontent--> 
    
  </div>
  <!--mainwrapperinner--> 
<!--mainwrapper--> 
<!-- END OF MAIN CONTENT -->

</body>
</html>
