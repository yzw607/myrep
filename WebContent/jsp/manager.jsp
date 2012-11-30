<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>首页</title>

<script>
function pageInit()
{
  var msg = "${sysMsg}";
  if(msg != "")
  {
    alert(msg);
  }
}

function checkLogin(form)
{
  if(form.username.value == "")
  {
    alert("请输入用户名！");
    form.username.focus();
    return false;
  }
  
  if(form.password.value == "")
  {
    alert("请输入密码！");
    form.password.focus();
    return false;
  }
  
  return true;
}

</script>
</head>

<body onload="pageInit()">
<h1>管理员登陆页面</h1>
<p/>

<s:form action="adminLogin" onsubmit="return checkLogin(this)" method="post" theme="simple">
用户名：<s:textfield name="username" /><br>
密  码：<s:password name="password" /><br>
<p/>
<input type="submit" value="登录" >
</s:form>
<p/>

</body>
</html>