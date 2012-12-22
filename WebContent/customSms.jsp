﻿<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String id = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我爱说-www.say520.cn</title>
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<link href="<%=path%>/css/main4_3_2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/scripts/jquery-1.7.2.min.js"></script>
<script language="javascript">
setInterval(test, 6000);

var i = 1;
var sender="";
 

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
          
  var smsStr = "祝贺新婚快乐，百年好合！";
  li.className = "threeline nopic";
 
  $.ajax({
	  type: 'POST',
	  url: "<%=path%>/getSnsList.action",
	  data: "id=${activityInfo.id}",
	  success: function(data){
		  if(i % 4 == 0)
		  {
		    sender = data[0].sender;
		    smsStr = data[0].msgTitle;
		  }
		  else if(i % 4 == 1)
		  {
			  sender = data[1].sender;
			  smsStr = data[1].msgTitle;
		  }
		  else if(i % 4 == 2)
		  {
			  sender = data[2].sender;
			   smsStr = data[2].msgTitle;
		  }
		  else if(i % 4 == 3)
		  {
			  sender = data[3].sender;
			  smsStr = data[3].msgTitle;
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
	  },
	  dataType: "json"
  });
  
  

}

function activatePic(index, obj)
{
  if(index == 1)
  {
    obj.src = "<%=path%>/images/sms/smsbtn.png";
  } 
  else if(index == 2)
  {
    obj.src = "<%=path%>/images/sms/cjbtn.png";
  }
  else if(index == 3)
  {
    obj.src = "<%=path%>/images/sms/dtbtn.png";
  }
}

function bgPic(index, obj)
{
  if(index == 1)
  {
    obj.src = "<%=path%>/images/sms/smsbtn_gray.png";
  } 
  else if(index == 2)
  {
    obj.src = "<%=path%>/images/sms/cjbtn_gray.png";
  }
  else if(index == 3)
  {
    obj.src = "<%=path%>/images/sms/dtbtn_gray.png";
  }
}
</script>
</head>

<body>

<div class="page" 
<s:if test="activityInfo.bgPicPath != null && activityInfo.bgPicPath != ''">
style="background:url(${activityInfo.bgPicPath}); "
</s:if>
<s:else>
style="background:url(<%=path%>/images/bg/bg7.jpg);"
</s:else>
>

    <div class="smstop">
    <div class="probtn">
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>
                <a href="#"><img src="<%=path%>/images/sms/smsbtn.png" alt="短信许愿墙" title="短信许愿墙" /></a>
              </td>
              <td>
                <a href="<%=path%>/customLottery.jsp"><img src="<%=path%>/images/sms/cjbtn_gray.png" alt="幸运大转盘" title="幸运大转盘" onmouseover="activatePic(2, this)" onmouseout="bgPic(2, this)"/></a>
              </td>
              <td>
                <a href="<%=path%>/customQuestion.jsp"><img src="<%=path%>/images/sms/dtbtn_gray.png" alt="短信抢答" title="短信抢答"  onmouseover="activatePic(3, this)" onmouseout="bgPic(3, this)"/></a>
              </td>
            </tr>
          </table>
          </div>
    <div class="banner"></div>
           <div class="Preview"></div>
       </div>

    <div class="smsleft">
    <div class="smstitle1">
    <p><img src="<%=path%>/images/4_3_2/dyb.png" /></p>
    <p><img src="<%=path%>/images/4_3_2/1.png" /><img src="<%=path%>/images/4_3_2/5.png" /><img src="<%=path%>/images/4_3_2/0.png" /><img src="<%=path%>/images/4_3_2/7.png" /><img src="<%=path%>/images/4_3_2/1.png" /><img src="<%=path%>/images/4_3_2/3.png" /><img src="<%=path%>/images/4_3_2/1.png" /><img src="<%=path%>/images/4_3_2/4.png" /><img src="<%=path%>/images/4_3_2/5.png" /><img src="<%=path%>/images/4_3_2/2.png" /><img src="<%=path%>/images/4_3_2/0.png" /></p>
    <p><img src="<%=path%>/images/4_3_2/zhuyi.png" /></p>
    <br/>
    <p><img src="<%=path%>/images/4_3_2/deb.png" /><img src="<%=path%>/images/4_3_2/6.png" /><img src="<%=path%>/images/4_3_2/6.png" /><img src="<%=path%>/images/4_3_2/6.png" /><img src="<%=path%>/images/4_3_2/neirong.png" /></p>
    <p><img src="<%=path%>/images/4_3_2/1.png" /><img src="<%=path%>/images/4_3_2/5.png" /><img src="<%=path%>/images/4_3_2/0.png" /><img src="<%=path%>/images/4_3_2/7.png" /><img src="<%=path%>/images/4_3_2/1.png" /><img src="<%=path%>/images/4_3_2/3.png" /><img src="<%=path%>/images/4_3_2/1.png" /><img src="<%=path%>/images/4_3_2/4.png" /><img src="<%=path%>/images/4_3_2/5.png" /><img src="<%=path%>/images/4_3_2/2.png" /><img src="<%=path%>/images/4_3_2/0.png" /></p>
    </div>
    <div class="pic1024">
        <div id="bigPic"> 
         <s:iterator value="#request.picList" id="pic">
          <img src="<%=path%>/getPic.action?path=${pic}"/>
         </s:iterator>
        </div>
      </div>
    <div class="happy"></div>
    </div>

      <div class="smsmain" id="con">
        <ul id="smsLi">
          <li class="threeline nopic">
            <h3>我爱说-www.say520.cn：</h3>
            <p>祝新郎杨过，新娘小龙女，新婚快乐，永结同心，美满幸福！</p>
          </li>
          <li class="threeline nopic">
            <h3>我爱说-www.say520.cn：</h3>
            <p>快拿起您的手机，发送祝福吧！用手机编织幸福，让短信传递心声。</p>
          </li>
          <li class="threeline nopic">
            <h3>我爱说-www.say520.cn：</h3>
            <p>发送数字000加您的名字至1061314520，<br />可以在屏幕上显示您的姓名哦！^_^<br />例如：000刘德华</p>
          </li>
          <li class="threeline nopic">
            <h3>我爱说-www.say520.cn：</h3>
            <p>发送每条祝福短信仅正常扣取话费0.1元，无任何其他费用。</p>
          </li>
         </ul>
      </div>

    <div class="smsbottom">
      婚礼呈现：我爱说&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 联系电话：400-101-4595
    </div>
</div>


<script type="text/javascript">
	var currentImage;
    var currentIndex = -1;
    var interval;
    function showImage(index){
        if(index < $('#bigPic img').length){
        	var indexImage = $('#bigPic img')[index];
            if(currentImage){   
            	if(currentImage != indexImage ){
                    $(currentImage).css('z-index',2);
                    clearTimeout(myTimer);
                    $(currentImage).fadeOut(250, function() {
					    myTimer = setTimeout("showNext()", 7000);
					    $(this).css({'display':'none','z-index':1});
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
	    myTimer = setTimeout("showNext()", 7000);
		showNext(); //loads first image
        $('#thumbs li').bind('click',function(e){
        	var count = $(this).attr('rel');
        	showImage(parseInt(count)-1);
        });
		
        //test();
	});
</script>
</body>
</html>
