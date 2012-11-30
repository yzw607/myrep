<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我爱说-www.say520.com</title>
<link href="<%=path%>/css/16_9.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/scripts/ajax.js"></script>

<script>
setInterval(test, 6000);
var i = 1;
var sender;
function test()
{
  var uu = document.getElementById("smsLi");
  var t = uu.childNodes.length;
  if(t >= 6)
  {
    uu.removeChild(uu.childNodes[5]);
  }
  
  var li= document.createElement("li");
  li.style.display = "none";
          
  var smsStr = "祝贺${activityInfo.bridegroom}、${activityInfo.bride}新婚快乐，百年好合！";
  li.className = "threeline nopic";
          
  if(i % 4 == 0)
  {
    sender = "二哥";
    smsStr = "弟，你媳妇儿不错啊，你一定要好好珍惜";
  }
  else if(i % 4 == 1)
  {
    sender = "奶奶";
    smsStr = "宝贝孙女，一定要让奶奶早点抱重孙啊！";
  }
  else if(i % 4 == 2)
  {
    sender = "贝贝";
    smsStr = "祝贺好姐妹终于找到如意郎君！";
  }
  else if(i % 4 == 3)
  {
    sender = "妈妈";
    smsStr = "祝我宝贝女儿新婚快乐，百年好合！";
  }
  i++;
            
  li.innerHTML = "<h3>" + sender + "：</h3><p>" + smsStr + "</p>";
  uu.appendChild(li);
        
  var $ul = $("#con ul");
  var liHeight = $ul.find("li:last").height();

  $ul.animate({marginTop : liHeight+7 +"px"},1000,function(){
  $ul.find("li:last").prependTo($ul)
  $ul.find("li:first").hide();
  $ul.css({marginTop:0});
  $ul.find("li:first").fadeIn(1000);
  });
}
</script>

</head>
<body>
<div class="page">

  <div class="header">
    <div class="logo"><img src="<%=path%>/images/16_9/logo.png" /></div>
    <div class="tips">
      <p class="tips1"><span class="t1_1">${activityInfo.widthNumberHTML}</span><span class="t1_2"><img src="<%=path%>/images/16_9/text5.png" width="115" height="36" /></span></p>
      <p class="tips2"><span class="t2_1">${activityInfo.widthSysTelHTML}</span></p>
    </div>
    <div class="tools">
      <ul>
        <li class="tools1"><a href="#" title="" class="active">短信许愿墙</a></li>
        <li class="tools2"><a href="<%=path%>/jsp/admin/preViewLottery.action?activityId=${activityInfo.id}&stencilId=${stencilId}" alt="幸运大转盘" title="幸运大转盘">幸运大抽奖</a></li>
        <li class="tools3"><a href="<%=path%>/viewQuestions.action?activityId=${activityInfo.id}&stencilId=${stencilId}" alt="短信抢答" title="短信抢答">短信抢答</a></li>
      </ul>
    </div>
  </div>
  <div class="main">
    <div class="leftcon">
      <div class="picslide">
        <div id="bigPic"> 
<s:if test="upList != null">
<s:iterator value="#request.upList" id="up">
         <img src="<%=path%>${picPath}${up.fileName}" alt="" /> 
</s:iterator>
</s:if>
        </div>
      </div>
      <div class="cd"><img src="<%=path%>/images/16_9/cd.png" width="658" height="163" /></div>
    </div>
    <div class="rightcon">
      <div class="tips3"><img src="<%=path%>/images/16_9/text4.png" width="641" height="36" /></div>
      <div class="content" id="con">
        <ul id="smsLi" class="msg">
<s:if test="activityInfo.welcomeMsg == null || activityInfo.welcomeMsg == ''">
          <li class="threeline nopic">
            <h3>${userinfo.companyName}：</h3>
            <p>祝新郎${activityInfo.bridegroom}，新娘${activityInfo.bride}，新婚快乐，永结同心，美满幸福！</p>
          </li>
</s:if>
<s:elseif test="activityInfo.welcomeMsg.length <= 18">
          <li class="threeline nopic">
            <h3>${userinfo.companyName}：</h3>
            <p>${activityInfo.welcomeMsg}</p>
          </li>
</s:elseif>
<s:elseif test="activityInfo.welcomeMsg.length <= 50">
          <li class="threeline nopic">
            <h3>${userinfo.companyName}：</h3>
            <p>${activityInfo.welcomeMsg}</p>
          </li>
</s:elseif>
<s:else>
          <li class="threeline nopic">
            <h3>${userinfo.companyName}：</h3>
            <p>${activityInfo.welcomeMsg}</p>
          </li>
</s:else>
          <li class="threeline nopic">
            <h3>我爱说-www.say520.cn：</h3>
            <p>发送数字000加您的名字至${activityInfo.sysTel}，可以在屏幕上显示您的姓名哦！^_^<br />例如：000刘德华</p>
          </li>
          <li class="threeline nopic">
            <h3>我爱说-www.say520.cn：</h3>
            <p>发送每条祝福短信仅正常扣取话费0.1元，无任何其他费用。</p>
          </li>
        </ul>
      </div>
    </div>
  </div>
  <div class="footer">
    <p class="copyright">婚礼呈现：${userinfo.companyName} 联系电话：${userinfo.tel}</p>
  </div>
</div>
<script type="text/javascript">
	var currentImage;
    var currentIndex = -1;
    var interval;
    function showImage(index){
        if(index < $('#bigPic img').length){
        	var indexImage = $('#bigPic img')[index]
            if(currentImage){   
            	if(currentImage != indexImage ){
                    $(currentImage).css('z-index',2);
                    clearTimeout(myTimer);
                    $(currentImage).fadeOut(250, function() {
					    myTimer = setTimeout("showNext()", 3000);
					    $(this).css({'display':'none','z-index':1})
					});
                }
            }
            $(indexImage).css({'display':'block', 'opacity':1});
            currentImage = indexImage;
            currentIndex = index;
            $('#thumbs li').removeClass('active');
            $($('#thumbs li')[index]).addClass('active');
        }
    }
    
    function showNext(){
        var len = $('#bigPic img').length;
        var next = currentIndex < (len-1) ? currentIndex + 1 : 0;
        showImage(next);
    }
    
    var myTimer;
    
    $(document).ready(function() {
	    myTimer = setTimeout("showNext()", 3000);
		showNext(); //loads first image
        $('#thumbs li').bind('click',function(e){
        	var count = $(this).attr('rel');
        	showImage(parseInt(count)-1);
        });
	});
	</script>
</body>
</html>
