<%@ page language="java" import="java.util.List,com.wmp.userManage.bean.User,com.wmp.common.Common" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
List numberList = null;
int size = 0;
int count = 0;
Object obj = request.getAttribute("numberList");
if(null != obj) 
{
    numberList = (List)obj;
    size = numberList.size();
}

User user = (User)session.getAttribute("userinfo");
String userType = user.getUserType();
if(Common.ENTERPRISE.equals(userType))
{
    count = Common.ENTERPRISE_NUM - size;
}
else 
{
    count = Common.PERSONAL_NUM - size;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>My JSP 'MyJsp.jsp' starting page</title>
  
<script>
function doCheck()
{
  var numbers = document.getElementsByName("tmpNumber");
  
  for(var i = 0; i < numbers.length; i++)
  {
    if(numbers[i].value != "" && numbers[i].value.length < 4)
    {
      alert("特殊号码必须为4位英文字母！");
      numbers[i].focus();
      return;
    }
  }
  
  document.forms[0].submit();
}

function init()
{
  var msg = "${sysMsg}";
  if(msg != "")
  {
    alert(msg + "已被占用，请您重新设置");
  }
}
</script>
</head>
  
<body onload="init()">
  
您是${userinfo.userTypeStr}，您每日可以设置<%=count%>个特殊号码！<p/>

<s:form action="setNumber.action" method="post">

<s:iterator value="#request.numberList" id="userNumber">
  <input type="text" name="tmpNumber" value="<s:property value="#userNumber.userNumber"/>" maxlength="4"/>&nbsp;&nbsp;
</s:iterator>

<%

for(int i = 0; i < count; i++) 
{
%>
  <input type="text" name="tmpNumber" value="" maxlength="4"/>&nbsp;&nbsp;
<%
}
%>

<p/>

<input type="button" value="确定" onclick="doCheck()"/>

</s:form>
  </body>
</html>
