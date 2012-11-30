<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'lottery.jsp' starting page</title>

<script type="text/javascript" src="<%=path%>/scripts/co.js"></script>
<script type="text/JavaScript">
//====  参数配置区域 ↓ ================================================================

    var m      = [1,20];//按号码范围抽 m_name必须为[];
	var m_name = 
	 ["13995548333",
	  "13871111616",
	  "13871194447",
	  "15071004006",
	  "02782435011",
	  "15879549845",
	  "13364879231",
	  "87956412347",
	  "13587965978",
	  "13554094567",
	  "18795468754",
	  "96548971113"];//人员名单 本数组有值“号码范围”自动失效
	
    var r      = [4,2,1];//奖品分组数量
    var r_name = ["二等奖","一等奖","特等奖"];//奖品分组名称
	
	window.rep = 0; //是否允许号码重复出现
	window.obo = 1; //一次抽一（组0/个1）
	window.arr = []; //排除号码数组 例子 [0,1,2,3,4]

</script>
  </head>
  
<body scroll="no" style="background-color: #2C0001" onload="group()">
<div class="oneOut">
  <div></div>
</div>

<div class="OFF" title="关闭本程序" onclick="closeie()">关闭</div>

<div class="Top">
  <div id="Go">
    <fieldset class="MainBG">
      <div id="Main">
        <h2 class="title">加载奖项...</h2>
        <div class="input">列表加载...</div>
      </div>
      <div id="input">
        <input id="start" name="start" type="button" value="开始(空格)"/>
        <input id="end" name="end" type="button" value="停止(空格)" />
        <input id="login" name="login" type="button" value="下一组(回车)" />
      </div>
    </fieldset>
  </div>
  
  <div id="out">
    <fieldset>
      <legend> 中奖结果 </legend>
      <ul id="tableOUT">
        <li style="display:none"></li>
      </ul>
    </fieldset>
  </div>
</div>

<div class="End" align="center">
  <ul></ul>
</div>

</body>
</html>

