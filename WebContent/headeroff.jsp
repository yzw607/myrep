<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script>
function loginOut()
{
  if(window.confirm("您要确认要退出本系统吗？"))
  {
    window.location.href="<%=path%>/loginOut.action";
  }
}

function setting()
{
  window.location.href="<%=path%>/goUpdateInfo.action";
}

function updatePsw()
{
  window.location.href="<%=path%>/goUpdatePsw.action";
}
</script>
</head>

	<!-- START OF HEADER -->
	<div class="header radius3">
		<div class="headerinner">
        <div class="headlogo"><a href="<%=path%>/index.jsp"><img src="<%=path%>/jsp/admin/images/mainlogo.png"></a></div>
		</div>
	</div>

	<!--header-->
</html>
