<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>我爱说-www.say520.cn</title>
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<link href="<%=path%>/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/scripts/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="<%=path%>/scripts/ajax.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="overflow: hidden">
<div class="page" style="background:url(images/bg/bg1.jpg) no-repeat">
    <div class="smsleft">
        <div class="smslogo"></div>
          <div class="probtn">
<img src="images/sms/wuxianzhufu.png">
          </div>
          <div class="smstips">
      <img src="<%=path%>/images/sms/smstips2.png" />
          </div>
    </div>

    <div class="smsright">
       <div class="smstitle">
<img src="<%=path%>/images/sms/tishi${result}.png" />
       <div class="f11"></div>
       <div class="smsout"><img src="<%=path%>/jsp/admin/images/off2.png" onmouseover="javascript:this.src='<%=path%>/jsp/admin/images/off3.png'" onmouseout="javascript:this.src='<%=path%>/jsp/admin/images/off2.png'" title="关闭退出" onclick="javascript:if(window.confirm('确认退出')){window.close();}"/></div>
       </div>
      <div class="smsmain">
        <ul id="smsLi">
          <li style="background:url(<%=path%>/images/sms/tishi4.png) no-repeat; height:365px;">
          </li>
          <li style="background:url(<%=path%>/images/sms/tishi5.png) no-repeat">
          </li>
        </ul>
      </div>
    </div>

    <div class="smsbottom">
        婚礼呈现：${userinfo.companyName} 联系电话：${userinfo.tel}
    </div>

</div>
  </body>
</html>
