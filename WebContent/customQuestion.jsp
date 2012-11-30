<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>我爱说 - 用户管理平台</title>
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<link href="<%=path%>/css/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=path%>/jsp/admin/css/style.css" type="text/css" />


<script>
var flag =false;
function nextQuestion()
{
  document.getElementById("nextBtn").style.background = "url(<%=path%>/images/sms/qd_nextqus.png)";
  document.getElementById("questionArea").innerHTML = "请问新郎昨天晚上睡了几个小时？";
  flag = true;
}

function nextAnswer()
{
  if(flag)
  {
    document.getElementById("senderArea").innerHTML = "13888888888：";
    document.getElementById("answerArea").innerHTML = "没有睡觉";
  }
}

function showStandardAnswer()
{
  if(flag)
  {
    document.getElementById("standardAnswerArea").innerHTML = "没有睡觉";
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
  
<body style="overflow:hidden">
<div class="page" style="background:url(images/bg/bg1.jpg); ">
    <div class="smstop">
          <div class="probtn">
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>
                <a href="<%=path%>/customSms.jsp"><img src="images/sms/smsbtn_gray.png" alt="短信许愿墙" title="短信许愿墙" onmouseover="activatePic(1, this)" onmouseout="bgPic(1, this)"/ /></a>
              </td>
              <td>
                <a href="<%=path%>/customLottery.jsp"><img src="images/sms/cjbtn_gray.png" alt="幸运大转盘" title="幸运大转盘" onmouseover="activatePic(2, this)" onmouseout="bgPic(2, this)"/></a>
              </td>
              <td>
                <a href="#"><img src="images/sms/dtbtn.png" alt="短信抢答" title="短信抢答" /></a>
              </td>
            </tr>
          </table>
          </div>
           <div class="smstitle1">
<p><img src="images/sms/bjdx.png" /><img src="images/5.png" height="40" /><img src="images/2.png" height="40" /><img src="images/0.png" height="40" /><img src="images/dxqd/qiangda.png" height="40" /><img src="images/sms/jia.png" /><img src="images/dxqd/daan.png" /></p><br /><p><img src="images/sms/fsd.png" /><img src="images/1.png" height="40" /><img src="images/5.png" height="40" /><img src="images/0.png" height="40" /><img src="images/7.png" height="40" /><img src="images/1.png" height="40" /><img src="images/3.png" height="40" /><img src="images/1.png" height="40" /><img src="images/4.png" height="40" /><img src="images/5.png" height="40" /><img src="images/2.png" height="40" /><img src="images/0.png" height="40" />
       </p>
           </div>
           <div class="Preview"></div>
       </div>

    <div class="smsleft"></div>

    <div class="smsright">

<div class="qd_qus">
<p id="questionArea"></p>
<input type="button" style="width:168px; height:37px; background:url(images/sms/qd_begin.png); border:none;" id="nextBtn" onclick="nextQuestion()" />
</div>
<div class="qd_qdq">
<h1 id="senderArea"></h1>
<p id="answerArea"></p>
<input type="button" style="width:168px; height:37px; background:url(images/sms/qd_nextlist.png); border:none;" onclick="nextAnswer()" />
</div>
<div class="qd_daan">
<p id="standardAnswerArea"></p>
<input type="button" style="width:168px; height:37px; background:url(images/sms/qd_showdaan.png); border:none;" onclick="showStandardAnswer()" />
</div>
</div>
    <div class="smsbottom">
    </div>

</div>
</body>
</html>
