<%@ page language="java" import="com.wmp.userManage.bean.User" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<script>
function doShow()
{
  window.open("<%=path%>/readyForLottery.action","","fullscreen=1,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1");
}
</script>
</head>

<body>

抽奖设置：

<p/>

<input type="button" value="进入抽奖" onclick="doShow()" />

</body>
</html>
