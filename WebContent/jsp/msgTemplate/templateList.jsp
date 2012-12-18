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
function showMsgTemplate(id)
{
  window.location.href = "<%=path%>/getMsgTemplateById.action?msgTemplate.tmpltId=" + id;
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

/**
 * Selet all or unselect all
 */
function selectAll(){
	var selectAll = document.getElementById('selectAll');
	var allMsgTemplates = document.getElementsByName('selectList');
	for(var i=0; i<allMsgTemplates.length;i++){
	if(selectAll.checked){
		allMsgTemplates[i].checked=true;
	}
	else{
		allMsgTemplates[i].checked=false;
	}
	}
}
	
/**
 * Deleted selected templates
 */
function deleteSelectedTemplates(){
	var allMsgTemplates = document.getElementsByName('selectList');
	var selectedTemplateIds = '';
	for(var i=0; i<allMsgTemplates.length;i++){
	if(allMsgTemplates[i].checked){
		selectedTemplateIds += allMsgTemplates[i].value;
		selectedTemplateIds += ",";
	}
	}
	document.getElementById("selectedTemplateIds").value = selectedTemplateIds;
	if(selectedTemplateIds == ''){
		alert('请选中要删除的模板！');
		return false;
	}else{
		if(confirm("确定删除选中的模板?")){
		    var form = document.forms[0];
		    form.action = "<%=path%>/deleteTemplates.action";
		    form.submit();
		}else{
			return false;
		}
	}
}

</script>

<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body class="loggedin">

<jsp:include page="../admin/header.jsp" />

<!-- START OF MAIN CONTENT -->
<div class="mainwrapper">
  <div class="mainwrapperinner">
    <div class="mainleft">
      <div class="mainleftinner">
        <jsp:include page="../admin/menu.jsp" /> 
        <!--leftmenu-->
        <div id="togglemenuleft"><a></a></div>
      </div>
      <!--mainleftinner--> 
    </div>
    <!--mainleft-->
    
    <div class="maincontent noright">
      <div class="maincontentinner">
        <ul class="maintabmenu">
          <li class="current"><a href="#">模板管理</a></li>
        </ul>
        <!--maintabmenu-->
        
        <div class="content">
          <div class="contenttitle radiusbottom0">
            <h2 class="table"><span>模板列表</span></h2>
          </div>
          <!--contenttitle-->
          <div class="dataTables_wrapper" id="dyntable_wrapper">
          
          <s:form action="msgTemplate" method="post" theme="simple">
          <s:hidden name="page.indexPage" id="indexPage"></s:hidden>
          <s:hidden name="selectedTemplateIds" id="selectedTemplateIds"></s:hidden>
            <div id="dyntable_length" class="dataTables_length">
              模板内容：<s:textfield name="msgContent" maxlength="20" cssStyle="width:120px;"></s:textfield>
             &nbsp;&nbsp;
              <input type="button" value="搜索" class="stdbtn btn_yellow" style="width:80px;" onclick="doSearch()"/>
              <input type="button" class="stdbtn btn_yellow" style="width:80px;" value="新增模板" onclick="javascript:window.location.href='<%=path%>/goNewTemplate.action';"/>
            </div>
          </s:form>
            <table cellpadding="0" cellspacing="0" border="0" class="stdtable">
              <thead>
                <tr>
                  <th class="head1" rowspan="1" width="10%" colspan="1">
                   <input type="checkbox" id="selectAll" style="width: 20px;" name="selectAll" onclick="selectAll();"/>全选<br />
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="deleteSelectedTemplates();">删除</a></th>
                  <th class="head1" rowspan="1" width="25%" colspan="1">模板主题</th>
                  <th class="head1" rowspan="1" width="40%" colspan="1">模板内容</th>
                  <th class="head0" rowspan="1" width="25%" colspan="1">模板说明</th>
                </tr>
              </thead>
              <tbody>

<s:iterator value="#request.msgTemplateList" id="template">
                        <tr class="gradeX">
                            <td align="center">
                              <input name="selectList" style="width: 20px;" type="checkbox" value="${template.tmpltId}"/>
                            </td>
                            <td title="${template.tmpltTitle}" align="center" onclick="showMsgTemplate('${template.tmpltId}')">${template.tmpltTitle}</td>
                            <td title="${template.tmpltContent}" align="center" onclick="showMsgTemplate('${template.tmpltId}')">${template.tmpltContent}</td>
                            <td title="${template.tmpltComment}" align="center" onclick="showMsgTemplate('${template.tmpltId}')">${template.tmpltComment}</td>
                        </tr>
</s:iterator>

<!--  
<c:forEach begin="1" end="${emptyRow}" step="1">
					   <tr>
					       <td>&nbsp;</td>
					       <td></td>
					       <td></td>
					    </tr>
</c:forEach>
-->

              </tbody>
            </table>
    
    <!--        
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
        -->       
          </div>
          
          
          <br clear="all" />
        </div>
        <!--content--> 
        
      </div>
      <!--maincontentinner-->
      
      <jsp:include page="../admin/footer.jsp" />
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
