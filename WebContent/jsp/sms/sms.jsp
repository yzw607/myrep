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
<script type="text/javascript" src="<%=path%>/scripts/open.js"></script>
<script language="javascript">
<s:if test="restTime > 0">
setTimeout("timeOut()", ${restTime});
function timeOut()
{
  alert("系统维护阶段，如您已订购下个时段，请重新进入本页面！");
  window.close();
}
</s:if>
setInterval(loadSMS, 1500);
var timer = setInterval(showSMS, 1500);

function loadSMS()
{
  if(smsArr.length <= 1)
  {
    url = "<%=path%>/loadSMS.action";
    var result = "number=${activityInfo.number}&activityId=${activityInfo.id}";
    send_request_post(url, result);
  }
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
<body style="overflow: hidden">

<div class="page" 
<s:if test="activityInfo.bgPicPath != null && activityInfo.bgPicPath != ''">
style="background:url(${activityInfo.bgPicPath}); "
</s:if>
<s:else>
style="background:url(<%=path%>/images/bg/bg1.jpg); "
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
                <a href="<%=path%>/goLottery.action?activityId=${activityInfo.id}&stencilId=${stencilId}"><img src="<%=path%>/images/sms/cjbtn_gray.png" alt="幸运大转盘" title="幸运大转盘"  onmouseover="activatePic(2, this)" onmouseout="bgPic(2, this)" /></a>
              </td>
              <td>
                <a href="<%=path%>/viewQuestions.action?activityId=${activityInfo.id}&stencilId=${stencilId}"><img src="<%=path%>/images/sms/dtbtn_gray.png" alt="短信抢答" title="短信抢答"  onmouseover="activatePic(3, this)" onmouseout="bgPic(3, this)" /></a>
              </td>
            </tr>
          </table>
          </div>
           <div class="smstitle1">
       <p><img src="<%=path%>/images/sms/bjdx.png" />${activityInfo.numberHTML }<img src="<%=path%>/images/sms/jia.png" /><img src="<%=path%>/images/sms/zfnr.png" /></p><br /><p><img src="<%=path%>/images/sms/fsd.png" />${activityInfo.sysTelHTML}
       </p>
           </div>
       </div>

    <div class="smsleft"></div>
    <div class="smsout"><img src="<%=path%>/jsp/admin/images/off2.png"  onmouseover="javascript:this.src='<%=path%>/jsp/admin/images/off3.png'" onmouseout="javascript:this.src='<%=path%>/jsp/admin/images/off2.png'" title="关闭退出" onclick="javascript:if(window.confirm('确认退出')){window.close();}"/></div>

      <div class="smsmain" id="con">
        <ul id="smsLi">
<s:if test="activityInfo.welcomeMsg == null || activityInfo.welcomeMsg == ''">
          <li class="threeline nopic">
            <h3>${userinfo.companyName}：</h3>
            <p>祝新郎${activityInfo.bridegroom}，新娘${activityInfo.bride}，新婚快乐，永结同心，美满幸福！</p>
          </li>
</s:if>
<s:elseif test="activityInfo.welcomeMsg.length() > 50">
          <li class="threeline nopic">
            <h3>${userinfo.companyName}：</h3>
            <p>${activityInfo.welcomeMsg}</p>
          </li>
</s:elseif>
<s:elseif test="activityInfo.welcomeMsg.length() > 18">
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
          <li class="threeline nopic">
            <h3>我爱说-www.say520.cn：</h3>
            <p>发送每条祝福短信仅正常扣取话费0.1元，无任何其他费用。</p>
          </li>
        </ul>
      </div>

    <div class="smsbottom">
        婚礼呈现：${userinfo.companyName} 联系电话：${userinfo.tel}
    </div>

</div>
</body>
</html>
