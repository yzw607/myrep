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
<script src="<%=path%>/dhtmlx/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/dhtmlx/dhtmlxCalendar/codebase/dhtmlxcalendar.css"></link>
<link rel="stylesheet" type="text/css" href="<%=path%>/dhtmlx/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css"></link>
<script>
function showActivity(id)
{
  window.location.href = "<%=path%>/preview.action?id=" + id;
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
var startDate;
var endDate;
function doOnLoad() 
{
	startDate = new dhtmlXCalendarObject(["startDate"]);
	endDate = new dhtmlXCalendarObject(["endDate"]);
}

function selectAll(obj){
   var array =[];
   var ids = document.getElementsByName("checkbox");
   if(obj.checked){
	   for (var i = 0; i < ids.length; i++){
		   ids[i].checked = true; 
	   }
   }else{
	   for (var i = 0; i < ids.length; i++){
		   ids[i].checked = false; 
	   }
   }
}

function delActivity(){
	 var array =[];
	 var ids = document.getElementsByName("checkbox");
	 for (var i = 0; i < ids.length; i++){
		   if(ids[i].checked){
	       	  array.push(ids[i].value);
		   }
	 }
	 window.location.href = "<%=path%>/delActivity.action?ids=" + array;
}
</script>

<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body class="loggedin" onload="doOnLoad()">

<jsp:include page="header.jsp" />

<!-- START OF MAIN CONTENT -->
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
    <!--mainleft-->
    
    <div class="maincontent noright">
      <div class="maincontentinner">
        <ul class="maintabmenu">
          <li class="current"><a href="#">活动管理</a></li>
        </ul>
        <!--maintabmenu-->
        
        <div class="content">
          <div class="contenttitle radiusbottom0">
            <h2 class="table"><span>活动列表</span></h2>
          </div>
          <!--contenttitle-->
          <div class="dataTables_wrapper" id="dyntable_wrapper">
          
          <s:form action="planList" method="post" theme="simple">
          <s:hidden name="page.indexPage" id="indexPage"></s:hidden>
            <div id="dyntable_length" class="dataTables_length">
            
 
              <input type="button" class="stdbtn btn_yellow" style="width:80px;" value="新增活动" onclick="javascript:window.location.href='<%=path%>/goNewActivity.action';"/>
              <div style="float:right ">
 
             
              活动主题：<s:textfield name="queryActivity.title" cssStyle=" width:150px;"></s:textfield>
             &nbsp;&nbsp;
      活动日期：<s:textfield name="queryActivity.startDate" cssStyle=" width:80px;" id="startDate"></s:textfield>
             &nbsp;&nbsp;
  至：<s:textfield name="queryActivity.endDate" cssStyle=" width:80px;" id="endDate"></s:textfield>
              活动状态：
              <select name="queryActivity.status" cssStyle=" width:150px;">
                <option value="-1" <s:if test='queryActivity.status=="-1"'>selected</s:if>>全部</option>
                <option value="0" <s:if test='queryActivity.status=="0"'>selected</s:if>>新创建</option>
                <option value="1" <s:if test='queryActivity.status=="1"'>selected</s:if>>进行中</option>
                <option value="2" <s:if test='queryActivity.status=="2"'>selected</s:if>>已结束</option>
              </select>

              &nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" value="搜索" class="stdbtn btn_yellow" style="width:80px;" onclick="doSearch()"/>
              </div>
            </div>
          </s:form>
            <table cellpadding="0" cellspacing="0" border="0" class="stdtable">
              <thead>
                <tr>
                  <th class="head0"><input type="checkbox" onclick="selectAll(this)"/>全选&nbsp;&nbsp;<a href="javascript:void(0)"
                   onclick="delActivity()">删除</a></th>
                  <th class="head0" rowspan="1" colspan="1">活动主题</th>
                  <th class="head1" rowspan="1" colspan="1">活动地址</th>
                  <th class="head1" rowspan="1" colspan="1">活动短号</th>
                  <th class="head0" rowspan="1" colspan="1">活动日期</th>
                  <th class="head1" rowspan="1" colspan="1">活动状态</th>
                </tr>
              </thead>
              <tbody>

<s:iterator value="#request.activityList" id="activity">
                        <tr class="gradeX">
                        	<td title="${activity.title}"><input type="checkbox" name="checkbox" value="${activity.id}"/></td>
                            <td title="${activity.title}"><a href="javascript:void(0)" onclick="showActivity('${activity.id}')">${fn:substring(activity.title,0,21)}</a></td>
                            <td title="${activity.address}">${fn:substring(activity.address,0,21)}</td>
                            <td align="center">${activity.number}</td>
                            <td align="center">${activity.holdDate}</td>
                            <td align="center">${activity.statusStr}</td>
                        </tr>
</s:iterator>

<c:forEach begin="1" end="${emptyRow}" step="1">
					   <tr>
					   	   <td>&nbsp;</td>
					       <td>&nbsp;</td>
					       <td></td>
					       <td></td>
					       <td></td>
					       <td></td>
					    </tr>
</c:forEach>

              </tbody>
            </table>
            
            <div class="dataTables_info" id="dyntable_info">当前第 ${page.indexPage} / ${page.maxPage} 页 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总共 ${page.maxRows} 条记录</div>
            <div class="dataTables_paginate paging_full_numbers" id="dyntable_paginate">
              <span class="first paginate_button paginate_button_disabled" id="dyntable_first" onclick="goPage('1')">首页</span>
              
<s:if test="page.hasBefore == true">
              <span class="previous paginate_button paginate_button_disabled" id="dyntable_previous" onclick="goPage('${page.indexPage - 1}')">上页</span>
</s:if>
<s:else>
              <span class="previous paginate_button paginate_button_disabled" id="dyntable_previous">上页</span>
</s:else>

<s:if test="page.hasNext == true">
              <span class="next paginate_button" id="dyntable_next" onclick="goPage('${page.indexPage + 1}')">下页</span>
</s:if>
<s:else>
              <span class="next paginate_button" id="dyntable_next">下页</span>
</s:else>

              <span class="last paginate_button" id="dyntable_last" onclick="goPage('${page.maxPage}')">末页</span>
            </div>
            
          </div>
          
          
          <br clear="all" />
       活动状态说明：<br/>
     

新创建：这场活动已创建成功，活动的任何信息可以修改；<br/>

进行中：这场活动正在进行中；<br/>

已结束：这场活动已成功结束。

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
</div>
<!--mainwrapper--> 
<!-- END OF MAIN CONTENT -->

</body>
</html>