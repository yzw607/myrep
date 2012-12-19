<%@ page language="java" import="com.wmp.common.Common" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我爱说 - 用户管理平台</title>
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<link rel="stylesheet" href="<%=path%>/jsp/admin/css/style.css" />

<script>
function selStencil(id, name)
{
  var stencil = {
    id:id,
    name:name
  }
  
  window.parent.document.getElementById("tempStencilName").value = name;
  window.parent.document.getElementById("stencilId").value = id;
  window.parent.document.getElementById("stencilName").value = name;
  window.parent.stencil.dialog('close');
}
</script>

</head>

<body class="loggedin" >
    
<div class="contenttitle radiusbottom0" style="margin:10px;">
  <h2 class="table"><span>婚礼模版选择</span></h2>
</div>


<div class="newli">
<ul>
  <li>
    <div class="lipic"><img src="<%=path%>/images/stencil/111.jpg" width="155"/></div>
    <div class="litxt"><a href="javascript:void(0)"
      onclick="javascript:selStencil(1, '普通版本（1024*768）')">普通版本（1024*768）</a></div>
  </li>
  
  <li>
    <div class="lipic"><img src="<%=path%>/images/stencil/222.jpg" width="155"/></div>
    <div class="litxt"><a href="javascript:void(0)"
      onclick="javascript:selStencil(2, '宽屏版本（16:10）')">宽屏版本（16:10）</a></div>
  </li>  
  
  <li>
    <div class="lipic"><img src="<%=path%>/images/stencil/222.jpg" width="155"/></div>
    <div class="litxt"><a href="javascript:void(0)"
     onclick="javascript:selStencil(3, '普通版本-图片联动（1024*768）')">普通版本-图片联动（1024*768）</a></div>
  </li>
</ul>
</div>

首页 上一页 下一页 尾页

<input type="button" value="关闭" onclick="javacsript:window.close()"/>

<br/>

</body>
</html>
