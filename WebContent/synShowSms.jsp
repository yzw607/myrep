<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我爱说-www.say520.cn</title>
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<link href="<%=path%>/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/scripts/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="<%=path%>/scripts/ajax.js"></script>
<script language="javascript">
var lastSmsId = "";
setInterval(loadSMS, 2000);
setInterval(showSMS, 6000);
var smsArr = new Array();

function processRequest()
{
  if (http_request.readyState == 4)
  {
    if (http_request.status == 200)
    {
      var returnStr = http_request.responseText;

      if(returnStr != "")
      {
        if (http_request.status == 200)
        {
          var returnStr = http_request.responseText;

          if(returnStr != "")
          {
            var json = eval("(" + returnStr + ")");
            lastSmsId = json.id;
	        var tmp = json.msgTitle.substring(3, json.msgTitle.length);
	        var tmpLen = tmp.length;

            if(tmpLen > 72)
            {
              var len = parseInt(tmpLen / 72);
              if(tmpLen % 72 > 0)
              {
                len++; 
              }
              var start = 0;
              var end = 0;
              var str = "";
              for(var i = 0; i < len; i++)
              {
                start = i * 72;
                end = (i + 1) * 72;
                str = "[" + (i + 1) + "/" + len + "]"; 
          
                smsArr.push(new Array(json.sender, tmp.substring(start, end) + str));
              }
            }
            else
            {
              smsArr.push(new Array(json.sender, tmp));
            }
          }
        }
        else
        {
          return false;
        }
      }
    }
    else
    {
      return false;
    }
  }
}

function showSMS()
{
  var sender = "";
  var sms = "";
  if(smsArr.length > 0)
  {
    var tmpArr = smsArr.shift(); 
    sender = tmpArr[0];
    sms = tmpArr[1];
  }
  else
  {
    return;
  }

  var uu = document.getElementById("smsLi");
  var t = uu.childNodes.length;
  if(t >= 6)
  {
    uu.removeChild(uu.childNodes[5]);
  }
  
  var li= document.createElement("li");
  li.style.display = "none";

  var len = strLen(sms);
  if(len <= 18)
  {
    li.className = "oneline nopic";
  }
  else if(len <= 50)
  {
    li.className = "twoline nopic";
  }
  else
  {
    li.className = "threeline nopic";
  }
            
  li.innerHTML = "<h3>" + sender + "：</h3><p>" + sms + "</p>";
  uu.appendChild(li);
        
  var $ul = $("#con ul");
  var liHeight = $ul.find("li:last").height();

  $ul.animate({marginTop : liHeight+8 +"px"},1000,function(){
    $ul.find("li:last").prependTo($ul)
    $ul.find("li:first").hide();
    $ul.css({marginTop:0});
    $ul.find("li:first").fadeIn(1000);
  });
}

function strLen(s) 
{
 var l = 0;
 var a = s.split("");
 for (var i=0;i<a.length;i++) {
  if (a[i].charCodeAt(0)<299) {
   l++;
  } else {
   l+=2;
  }
 }
 return l;
}

function loadSMS()
{
  if(smsArr.length <= 1)
  {
    url = "<%=path%>/loadSynSms.action";
    var result = "number=${activityInfo.number}&recordTime=${recordTime}&lastSmsId=" + lastSmsId;
    send_request_post(url, result);
  }
}
</script>
</head>
<body style="overflow: hidden">
<div class="page" style="background:url(${activityInfo.bgPicPath}); ">
    <div class="smsleft">
        <div class="smslogo"></div>
          <div class="probtn">
          </div>
          <div class="smstips">
      <img src="<%=path%>/images/sms/smstips2.png" />
          </div>
    </div>

    <div class="smsright">
       <div class="smstitle">
       <p><img src="<%=path%>/images/sms/bjdx.png" />${activityInfo.numberHTML }<img src="<%=path%>/images/sms/jia.png" /><img src="<%=path%>/images/sms/zfnr.png" /></p><br /><p><img src="<%=path%>/images/sms/fsd.png" />${activityInfo.sysTelHTML}
       </p>
       <div class="f11"></div>
       <div class="smsout"><img src="<%=path%>/jsp/admin/images/off2.png" onmouseover="javascript:this.src='<%=path%>/jsp/admin/images/off3.png'" onmouseout="javascript:this.src='<%=path%>/jsp/admin/images/off2.png'" title="关闭退出" onclick="javascript:if(window.confirm('确认退出')){window.close();}"/></div>
       </div>
      <div class="smsmain" id="con">
        <ul id="smsLi">
<s:if test="activityInfo.welcomeMsg == ''">
          <li class="twoline nopic">
            <h3>${userinfo.companyName}：</h3>
            <p>祝新郎${activityInfo.bridegroom}，新娘${activityInfo.bride}，新婚快乐，永结同心，美满幸福！</p>
          </li>
</s:if>
<s:elseif test="activityInfo.welcomeMsg.length <= 18">
          <li class="oneline nopic">
            <h3>${userinfo.companyName}：</h3>
            <p>${activityInfo.welcomeMsg}</p>
          </li>
</s:elseif>
<s:elseif test="activityInfo.welcomeMsg.length <= 50">
          <li class="oneline nopic">
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
          <li class="twoline nopic">
            <h3>我爱说-www.say520.cn：</h3>
            <p>发送每条祝福短信仅正常扣取话费0.1元，无任何其他费用。</p>
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
