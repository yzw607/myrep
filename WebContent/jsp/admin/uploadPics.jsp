<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String activityId = request.getParameter("activityId");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<title>我爱说 - 用户管理平台</title>
<link rel="stylesheet" href="<%=path%>/jsp/test/assets/css/styles.css" />
<link rel="stylesheet" href="<%=path%>/jsp/admin/css/style.css" />

<script src="<%=path%>/scripts/jquery-1.7.2.min.js"></script>
<script src="<%=path%>/scripts/jquery.filedrop.js"></script>
<script src="<%=path%>/scripts/script.js"></script>

<script>
function delPic(picId)
{
  if(window.confirm("是否确认删除该图片？"))
  {
    window.location.href="<%=path%>/deleteUploadPic.action?activityId=${activityId}&picId=" + picId;
  }
}
</script>
</head>
    
<body>
<input type="hidden" id="activityId" value="<%=activityId%>" />		
<div id="dropbox">
    &nbsp;<p/>&nbsp;<p/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>请将您要上传的图片拖拽至蓝色区域部分。</b><br />
    <i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、图片文件名不能使用中文</i> <br />
    <i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、图片请控制在2M以内</i> <br />
    <i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、一次性最多上传10张图片</i> <br />
</div>

已上传照片列表：
<div class="newli">
<ul>
<s:iterator value="#request.picList" id="pic">
  <li>
    <div class="lipic"><img src="<%=path%>/upload/${userinfo.userCode}/${activityId}/thumbs/${pic.fileName}" width="178"/></div>
    <div class="litxt"><a href="javascript:delPic('${pic.picId}')">删除</a></div>
  </li>
</s:iterator>
</ul>
</div>
    
    </body>
</html>

