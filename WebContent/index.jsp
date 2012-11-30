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

window.onload = function(){	
	$('body').addClass('js');
	
	initDemopageSlides();
}
function initDemopageSlides(){
	demo_slide_animation_speed = 800;
	demo_slide_animation_timeout = 4000;
	if($('.demo_slide').length <= 1) return;
	$("#slide_next").click(onSlideNextClick);
	$("#slide_prev").click(onSlidePrevClick);
	var first_slide = $(".demo_slide:first");
	first_slide.addClass("current");
	var zIndexNumber = 100;	
	
	$('.demo_slide').each(function() {
		$(this).css('zIndex', zIndexNumber);
		zIndexNumber -= 1;
	});
	onSlideAnimationComplete();
}
 
function shuffleArray(v){
    for(var j, x, i = v.length; i; j = parseInt(Math.random() * i), x = v[--i], v[i] = v[j], v[j] = x);
    return v;
};
 
function onSlideNextClick(event){
	navigateSlides(1);
}
 
function onSlidePrevClick(event){
	navigateSlides(-1);
}
 
function navigateSlides(distance){	
	clearTimeout(time_out);
	if(animationInProgress) return;
	var slides_arr = $(".demo_slide");
	for(var s=0; s < slides_arr.length; s++){
		if($(slides_arr[s]).hasClass("current")){
			var previous_slide = $(slides_arr[s]);
			previous_slide.removeClass("current");
			break;	
		}
	}
	var next_index = s + distance;
	if(next_index < 0) next_index += slides_arr.length;
	if(next_index >= slides_arr.length) next_index = 0;
	var next_slide = $(slides_arr[next_index]);
	next_slide.addClass("current");
	var previous_zIndex = $(previous_slide).css('zIndex');
	next_slide.css('zIndex', Number(previous_zIndex) + 10);
	animationInProgress = true;
	var slide_width = parseFloat(next_slide.css("width"));
	next_slide.css(next_index%2?"right":"left",(slide_width * distance)/4 + "px");
	next_slide.css("display","block");
	next_slide.css("opacity","0");
	if(next_index%2){
		next_slide.animate( {right:0, opacity:1}, demo_slide_animation_speed, onSlideAnimationComplete);
	} else {
		next_slide.animate( {left:0, opacity:1}, demo_slide_animation_speed, onSlideAnimationComplete);	
	}
}
 
function onSlideAnimationComplete(){
	animationInProgress = false;
	time_out = setTimeout (onSlideNextClick,demo_slide_animation_timeout);
}

function doLogin()
{
  var uc = document.getElementById("userCode");
  var pw = document.getElementById("password");
  
  if(uc.value == "")
  {
    alert("请输入用户名");
    uc.focus();
    return;
  }
  
  if(pw.value == "")
  {
    alert("请输入密码");
    pw.focus();
    return;
  }
  
  document.forms[0].submit();
}

function enterIn(evt)
{
  var evt=evt?evt:(window.event?window.event:null);//兼容IE和FF
  if (evt.keyCode==13){
    doLogin();
  }
}
function change(img, url)
{
  img.src = url;
}

function decrease(img, url)
{
  img.src = url;
}

function doLoginOut()
{
  if(window.confirm("您要确认要退出本系统吗？"))
  {
    window.location.href="<%=path%>/loginOut.action";
  }
}
</script>

<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
<script src="scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>

<body style="background:none;">
<jsp:include page="index_menu.jsp" />
<div style="margin:0 auto; width:1000px;;">
<div class="indexnew_mid">
<jsp:include page="index_login.jsp" />
<div id="slide">
<img class="demo_slide" src="images/indexnew/indexnew_flash1.jpg" />
<img class="demo_slide" src="images/indexnew/indexnew_flash2.jpg" />
<img class="demo_slide" src="images/indexnew/indexnew_flash3.jpg" /></div>
<div class="ab"></div>
</div>

<div class="indexnew_pro">
<ul>
<li>
<a href="product.jsp" target="_self"><img src="images/indexnew/indexnew_sms_h.png" /></a>
</li>
<li>
<a href="product.jsp" target="_self"><img src="images/indexnew/indexnew_cj_h.png" /></a>
</li>
<li>
<a href="product.jsp" target="_self"><img src="images/indexnew/indexnew_qd_h.png" /></a>
</li>
</ul>
</div>
<jsp:include page="jsp/admin/footer.jsp" />
</div>
</body>

</html>
