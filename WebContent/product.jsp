<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<jsp:include page="check.jsp" /> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<title>我爱说-www.say520.cn</title>
<link rel="stylesheet" href="<%=path%>/jsp/admin/css/style.css" type="text/css" />
<link rel="stylesheet" href="<%=path%>/css/main.css" type="text/css" />
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery.flot.min.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery.flot.resize.min.js"></script>
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
<script src="scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script>
function showProduct(id)
{
  var url;
  
  if(id == 1)
  {
    url = "<%=path%>/customSms.jsp";
  }
  else if(id == 2)
  {
    url = "<%=path%>/customLottery.jsp";
  }
  else if(id == 3)
  {
    url = "<%=path%>/customQuestion.jsp";
  }

  window.open(url,"","fullscreen=1,menubar=0,toolbar=0,directories=0,location=0,status=0,scrollbars=1");
}
</script>
</head>

<body style="background:none;">
<jsp:include page="index_menu.jsp" />
<div class="pro_flash">

</div>
<div class="pro_pro">
<ul>
<li>
<div class="pro_pic">
<img src="images/product/por_sms_s.png" />
</div>
<div class="pro_txt">
<h1>&nbsp;&nbsp;&nbsp;&nbsp;通过投影展示，随时互动，引爆现场；现场亲朋好友无需注册，想发就发；远在异地也能发，人未到，祝福到，心意到；仅收取正常短信费用0.1元。
</h1>
<h2><a href="javascript:showProduct(1);">点击预览产品>></a></h2>
</div>
</li>
<li>
<div class="pro_pic">
<img src="images/product/por_cj_s.png" />
</div>
<div class="pro_txt">
<h1>&nbsp;&nbsp;&nbsp;&nbsp;在婚礼中的不同时段，根据发送人的号码，抽取幸运大奖；奖项级别，抽取人数任意设置；随到随抽，幸运大转盘和许愿墙应用可任意切换；分享二位新人的幸福和幸运！起到活跃气氛的效果，瞬间引爆全场，气氛high到最高点！。
</h1>
<h2><a href="javascript:showProduct(2);">点击预览产品>></a></h2>
</div>
</li>
<li>
<div class="pro_pic">
<img src="images/product/por_qd_s.png" />
</div>
<div class="pro_txt">
<h1>&nbsp;&nbsp;&nbsp;&nbsp;可以任意设置问题，及答案，可以多个题目；根据发送时间依次展示短信答案；现场灵活互动，乐趣无限！
</h1>
<h2><a href="javascript:showProduct(3);">点击预览产品>></a></h2>
</div>
</li>
</ul>
<jsp:include page="jsp/admin/footer.jsp" />
</div>
</body>

</html>



