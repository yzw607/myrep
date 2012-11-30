<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>我爱说-www.say520.com</title>
</head>

<body class="login">

<form id="login" action="<%=path%>/login.action" method="post">
            	<p>
                	<label for="username" class="bebas">用户名</label>
                    <input type="text" id="username" name="user.userCode" class="radius2" />
                </p>
                <p>
                	<label for="password" class="bebas">密&nbsp;&nbsp;&nbsp;码</label>
                    <input type="password" id="password" name="user.passWord" class="radius2" />
                </p>
                <p>
                	<input type="submit" value="登录" />
                </p>
</form>

</body>

</html>
