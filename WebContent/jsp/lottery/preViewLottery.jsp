<%@ page language="java" import="java.util.List, java.util.ArrayList, com.wmp.selfService.bean.LotteryNumber" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
List list = null == request.getAttribute("setList") ? new ArrayList() : (List)request.getAttribute("setList");
LotteryNumber number = null;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我爱说-www.say520.cn</title>
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<link href="<%=path%>/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/scripts/jquery-1.4.2.min.js"></script>
<script>

var flag = true;
var flag2 = false;

var idsArr = ["13880000000", "13881111111", "13882222222", "13883333333", "13885555555", "13886666666", "13887777777", "13888888888", "13889999999", "13881000000"];


var setArr = new Array();
<%
int len = list.size();
int tmp = 0;
int max = 0;
for(int i = 0; i < len; i++)
{
    number = (LotteryNumber)list.get(i);
    tmp = number.getShowIndex();
    if(tmp > max) max = tmp;
%>
    setArr[<%=i%>] = new Array("<%=number.getShowIndex()%>", "<%=number.getShowNumber()%>");
<%
}
%>

var maxIndex = <%=max%>;
var setLen = setArr.length;

var num = idsArr.length - 1;//抽取数量  
var timer;//定义定时器  
var tmp;

var page = 1;
var result = new Array();

var count = 0;

function change()
{
  tmp = idsArr[getEnd(0,num)];
  document.getElementById("expertId").innerHTML = tmp;//抽取值快速变换  
}  

function startExt()
{
  if(flag)
  {
    if(idsArr.length == 0)
    {
      alert("没有新的手机号码，请刷新页面重试！");
      return;
    }
  
    clearInterval(timer); //取消定时器 
    timer = setInterval('change()',10);//10（毫秒）为变换间隔，越小变换的越快 
    
    document.getElementById("startBtn").style.background = "url(<%=path%>/images/sms/lott_stop.png)";
    
    flag = false;
  }
  else
  {
    clearInterval(timer);//取消定时器
    
    flag= true;
    document.getElementById("startBtn").style.background = "url(<%=path%>/images/sms/lott_begin.png)";
    
    count++;
    flag2 = false;
    var index = 0;
    if(setLen > 0 && count <= maxIndex)
    {
      for(var i = 0; i < setLen; i++)
      {
        index = parseInt(setArr[i][0]);
        if(count == index)
        {
          document.getElementById("expertId").innerHTML = setArr[i][1];
          flag2 = true;
          result.push(setArr[i][1]); 
          refreshResultArea(1);
          break;
        }
      }
    }
        
    if(!flag2)
    {
    
      for(var i = 0; i < idsArr.length; i++)
      {
        if(idsArr[i] == tmp)
        {
          idsArr.splice(i, 1);
          num = idsArr.length - 1;
        
          result.push(tmp); 
        
          refreshResultArea(1);
        
          break;
        }
      }
    
    }
    
  }
}

function getEnd(min,max)
{
  return parseInt(Math.random()*(max-min+1));//随机抽取  
}

function refreshResultArea(pageNum)
{
  var len = result.length;
  if(len == 0) return;
  
  var pageCount = parseInt(len / 8);
  if(len % 8 > 0) pageCount++;
    
  if(pageNum > pageCount) return;
  
  
  var showArea = document.getElementById("showArea").rows;
  if(len <= 8)
  {
    var j = 0;
    for(var i = len; i > 0; i--)
    {
      showArea[j].cells[0].innerHTML = i;
      showArea[j].cells[1].innerHTML = result[i - 1];
      j++;
    }
  }
  else
  {
    var start = (pageNum - 1) * 8;
    var end = start + 8;
    if(end > len) end = len - 1;
    var j = 0;
    
    for(i = start; i <= end; i++, j++)
    {
      showArea[j].cells[0].innerHTML = len - i;
      showArea[j].cells[1].innerHTML = result[len - 1 - i];
    }

    for(; j < 8; j++)
    {
      showArea[j].cells[0].innerHTML = "";
      showArea[j].cells[1].innerHTML = "";
    }
    
  }
}

function previousPage()
{

  var len = result.length;
  if(len == 0) 
  {
    return;
  }
    
  if(page == 1) return;

  var pageCount = parseInt(len / 8);
  if(len % 8 > 0) pageCount++;
  if(page > 1)
  {
    page--;
    refreshResultArea(page);
  }
}

function nextPage()
{
  var len = result.length;
  if(len == 0) return;

  var pageCount = parseInt(len / 8);
  if(len % 8 > 0) pageCount++;
  if(page == pageCount) return;
  
  if(pageCount > page)
  {
    page++;
    refreshResultArea(page);
  }
}

$(document).keydown(function(event) {
  if (event.keyCode == 32) {
    startExt();
  }
});

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
<div class="page" style="background: url(<%=path%>/images/bg/bg1.jpg);">
<div class="smstop">
  <div class="probtn">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
        <a href="<%=path%>/preViewSMSPage.action?activityInfo.id=${activityId}&stencilId=${stencilId}"><img src="<%=path%>/images/sms/smsbtn_gray.png" alt="短信许愿墙" title="短信许愿墙" onmouseover="activatePic(1, this)" onmouseout="bgPic(1, this)"/></a>
      </td>
      <td>
        <a href="#"><img src="<%=path%>/images/sms/cjbtn.png" alt="幸运大转盘" title="幸运大转盘" /></a>
      </td>
      <td>
        <a href="<%=path%>/viewQuestions.action?activityId=${activityId}&stencilId=${stencilId}"><img src="<%=path%>/images/sms/dtbtn_gray.png" alt="短信抢答" title="短信抢答" onmouseover="activatePic(3, this)" onmouseout="bgPic(3, this)"/></a>
      </td>
    </tr>
  </table>
          </div>
           <div class="smstitle2">

           </div>
           <div class="Preview"></div>
       </div>   

    <div class="smsleft"></div>


  <div class="smsright">
    <div class="lott_begin">
      <div class="num" id="expertId">88888888888</div>
      <div class="btn_begin">
        <input type="button" style="width:219px; height:48px; background:url(<%=path%>/images/sms/lott_begin.png); border:none;" onclick="startExt()" id="startBtn"/>
      </div>
    </div>
    <div class="lott_list">
    <table border="0" cellspacing="1" cellpadding="0">
      <tr>
        <th>序号</th>
        <th>中奖号码</th>
      </tr>
      <tbody id="showArea">
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      </tbody>
    </table>
    
      <div class="btn_list">
        <input type="button" style="width:168px; height:37px; background:url(<%=path%>/images/sms/lott_up.png); border:none;" onclick="previousPage()" />
        <input type="button" style="width:168px; height:37px; background:url(<%=path%>/images/sms/lott_down.png); border:none;" onclick="nextPage()" />
      </div>
    </div>
  </div>


  <div class="smsbottom">
    婚礼呈现：${userinfo.companyName} 联系电话：${userinfo.tel}
  </div>

</div>
</body>
</html>
