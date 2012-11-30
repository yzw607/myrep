<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我爱说-www.say520.cn</title>
<link rel="stylesheet" href="<%=path%>/jsp/admin/css/style.css" type="text/css" />
<title>我爱说-www.say520.cn</title>
<link rel="shortcut icon" href="<%=path%>/ico.ico">

<script>
function getPsw()
{
  var code = document.getElementById("userCode").value;
  var info = document.getElementById("info");

  if(code == "")
  {
    info.innerHTML = "<img src='<%=path%>/images/cha1.png' style='vertical-align:middle;'/>&nbsp;&nbsp;请输入您的用户名";
    return;
  }
  else
  {
    document.forms[0].submit();
  }
}
</script>
</head>
  
  <body>
<div class="header radius3">
  <div class="headerinner110">
  </div>
</div>


<div class="forget">
<form id="login" action="<%=path%>/getForgetPsw.action" method="post">
               	账户名:
                   <input type="text" id="userCode" name="user.userCode" class="radius2 login_txt" />
</form>
   <div class="forgetprompt" id="info"></div>
   </div>
  <div class="forgetbtn">
                   <button class="stdbtn btn_yellow" style="width:100px; margin-left:60px;" onclick="getPsw()">发&nbsp;&nbsp;送</button>
                   <button class="stdbtn btn_yellow" style="width:100px; margin-left:60px;" onclick="javascript:window.history.go(-1);">返&nbsp;&nbsp;回</button>   
</div>



<div class="about">
<jsp:include page="jsp/admin/footer.jsp" />

  </body>
</html>
