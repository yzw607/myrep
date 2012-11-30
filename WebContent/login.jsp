<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<jsp:include page="check.jsp" /> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<title>我爱说-www.say520.cn</title>
<link rel="stylesheet" href="<%=path%>/jsp/admin/css/style.css" type="text/css" />
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery.flot.min.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery.flot.resize.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('.loginform button').hover(function(){
		$(this).stop().switchClass('default','hover');
	},function(){
		$(this).stop().switchClass('hover','default');
	});
	
	var msg = "${sysMsg}";
	if(msg != "")
	{
	  jQuery('.loginerror').slideDown();
	}
	
	$('#login').submit(function(){
		var u = jQuery(this).find('#username');
		if(u.val() == '') {
			jQuery('.loginerror').slideDown();
			document.getElementById("loginerrorShow").innerHTML = "用户名不能为空！";
			u.focus();
			return false;	
		}
		
		var p = jQuery(this).find('#password');
		if(p.val() == '') {
			jQuery('.loginerror').slideDown();
			document.getElementById("loginerrorShow").innerHTML = "密码不能为空！";
			u.focus();
			return false;	
		}
	});
	
	$('#username').keypress(function(){
		jQuery('.loginerror').slideUp();
	});
	
	$('#password').keypress(function(){
		jQuery('.loginerror').slideUp();
	});
});


</script>
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body>
<div class="newlogin">
    <div class="login_head">
        <div class="head_logo">
        <table width="100%" height="100%;" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td style="vertical-align:middle;"><a href="<%=path%>/index.jsp"><img src="jsp/admin/images/mainlogo.png" /></a>
</td>
  </tr>
</table>
        </div>
        <div class="head_ad"></div>
    </div>
    <div class="login_about"></div>
        <div class="login_pic">
    </div>
    <div class="login_sub">
        <div class="login_subt"></div>
        <div class="loginerror"><p id="loginerrorShow">${sysMsg}</p></div>
        <div class="login_subl">
        <form id="login" action="<%=path%>/login.action" method="post">
            	<p>
                	<label for="username" class="bebas">用户名：</label>
                    <input type="text" id="username" name="user.userCode" class="radius2 login_txt" />
                </p>
                <p>
                	<label for="password" class="bebas">密&nbsp;&nbsp;&nbsp;码：</label>
                    <input type="password" id="password" name="user.passWord" class="radius2 login_txt" />
                </p>
                
                <br /> 
                	<button class="stdbtn btn_blue" style="width:100px;">登&nbsp;&nbsp;录</button>
                    <input type="button" class="stdbtn btn_blue" style="width:100px;; margin-left:80px;" value="取 消"/>
            </form>       
        
        </div>
    </div>
    <div class="login_about">
    我爱说 &copy; 2012. All Rights Reserved. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;爱说热线：4001-014-595
    </div>
</div>
</body>

</html>
