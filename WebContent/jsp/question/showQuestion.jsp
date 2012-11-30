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
<script type="text/javascript" src="<%=path%>/scripts/ajax.js"></script>

<script>
var flag =false;
var number = "${number}";
var actionType = "";
var questionId = "";
var standardAnswer = "";
var timer;

function nextQuestion()
{
  document.getElementById("questionArea").innerHTML = "<img src='<%=path%>/images/load.gif' />题目加载中";
  document.getElementById("senderArea").innerHTML = "";
  document.getElementById("answerArea").innerHTML = "";
  document.getElementById("standardAnswerArea").innerHTML = "";
  standardAnswer = "";
  actionType = "next";
  flag = false;
  
  var  url = "<%=path%>/getNewestQuestion.action";
  var result = "number=${number}&activityId=${activity.id}&questionId=" + questionId;
  send_request_post(url, result);
}

function nextAnswer()
{
  if(flag)
  {
    document.getElementById("senderArea").innerHTML = "";
    document.getElementById("answerArea").innerHTML = "<img src='<%=path%>/images/load.gif' />等待短信抢答中...";
    timer = setInterval("getAnswer()", 1000);
    flag = false;
  }
}

function showStandardAnswer()
{
  document.getElementById("standardAnswerArea").innerHTML = standardAnswer;
}

function processRequest()
{
  if (http_request.readyState == 4)
  {
    if (http_request.status == 200)
    {
      var returnStr = http_request.responseText;

      if(returnStr != "")
      {
        var json = eval("(" + returnStr + ")");
        if(actionType == "next")
        {
          document.getElementById("questionArea").innerHTML = json.question;
          document.getElementById("senderArea").innerHTML = "";
          document.getElementById("answerArea").innerHTML = "<img src='<%=path%>/images/load.gif' />等待短信抢答中...";
          standardAnswer = json.answer;
          document.getElementById("standardAnswerArea").innerHTML = "";
          
          questionId = json.questionId;
          
          document.getElementById("nextBtn").style.background = "url(<%=path%>/images/sms/qd_nextqus.png)";
          flag = false;
          
          actionType = "answer";
          timer = setInterval("getAnswer()", 1000);
        }
        else if(actionType == "answer")
        {
          document.getElementById("senderArea").innerHTML = json.sender + "：";
          document.getElementById("answerArea").innerHTML = json.msgTitle.substring(5, json.msgTitle.length);
        
          clearInterval(timer);
          flag = true;
        }
      }
      else
      {
        if(actionType == "next")
        {
          document.getElementById("questionArea").innerHTML = "<img src='<%=path%>/images/alert.png' style='vertical-align:bottom;'/>已经是最后一题了";
          document.getElementById("senderArea").innerHTML = "";
          document.getElementById("answerArea").innerHTML = "";
          document.getElementById("standardAnswerArea").innerHTML = "";
          flag = false;
        }
      }
    }
    else
    {
      return false;
    }
  }
}

function getAnswer()
{
  var url = "<%=path%>/getTheLatestAnswer.action";
  var result = "number=${number}";
  send_request_post(url, result);
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
<div class="page" style="background:url(<%=path%>/images/bg/bg1.jpg); ">
    <div class="smstop">
          <div class="probtn">
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>
                <a href="<%=path%>/showSMSPage.action?activityInfo.id=${activityId}&stencilId=${stencilId}"><img src="<%=path%>/images/sms/smsbtn_gray.png" alt="短信许愿墙" title="短信许愿墙" onmouseover="activatePic(1, this)" onmouseout="bgPic(1, this)" /></a>
              </td>
              <td>
                <a href="<%=path%>/goLottery.action?activityId=${activityId}&stencilId=${stencilId}"><img src="<%=path%>/images/sms/cjbtn_gray.png" alt="幸运大转盘" title="幸运大转盘" onmouseover="activatePic(2, this)" onmouseout="bgPic(2, this)" /></a>
              </td>
              <td>
                <a href="#"><img src="<%=path%>/images/sms/dtbtn.png" alt="短信抢答" title="短信抢答" /></a>
              </td>
            </tr>
          </table>
          </div>
           <div class="smstitle3">
<p>
         <img src="images/sms/bjdx.png" />${activity.numberHTML }<img src="images/dxqd/qiangda.png" height="40" /><img src="images/sms/jia.png" /><img src="images/dxqd/daan.png" /></p><br /><p><img src="images/sms/fsd.png" />${activity.sysTelHTML}
       </p>
           </div>
             <div class="smsout"><img src="<%=path%>/jsp/admin/images/off2.png" onmouseover="javascript:this.src='<%=path%>/jsp/admin/images/off3.png'" onmouseout="javascript:this.src='<%=path%>/jsp/admin/images/off2.png'" title="关闭退出" onclick="javascript:if(window.confirm('确认退出')){window.close();}"/></div>
       </div>

    <div class="smsleft"></div>

    <div class="smsright">

<div class="qd_qus">
<p id="questionArea"></p>
<input type="button" style="width:168px; height:37px; background:url(<%=path%>/images/sms/qd_begin.png); border:none;" id="nextBtn" onclick="nextQuestion()" />
</div>
<div class="qd_qdq">
<h1 id="senderArea"></h1>
<p id="answerArea"></p>
<input type="button" style="width:168px; height:37px; background:url(<%=path%>/images/sms/qd_nextlist.png); border:none;" onclick="nextAnswer()" />
</div>
<div class="qd_daan">
<p id="standardAnswerArea"></p>
<input type="button" style="width:168px; height:37px; background:url(<%=path%>/images/sms/qd_showdaan.png); border:none;" onclick="showStandardAnswer()" />
</div>
</div>
    <div class="smsbottom">
        婚礼呈现：${userinfo.companyName} 联系电话：${userinfo.tel}
    </div>

</div>
</body>
</html>
