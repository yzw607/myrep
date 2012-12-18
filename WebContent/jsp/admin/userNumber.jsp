<%@ page language="java" import="com.wmp.userManage.bean.User, com.wmp.common.Common, java.util.List, com.wmp.selfService.bean.UserNumber" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
User user = (User)session.getAttribute(Common.USERINFO);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<title>我爱说 - 用户管理平台</title>
<link rel="stylesheet" href="<%=path%>/jsp/admin/css/style.css" type="text/css" />
<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/ie9.css"/>
<![endif]-->

<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/ie8.css"/>
<![endif]-->

<!--[if IE 7]>
    <link rel="stylesheet" media="screen" href="css/ie7.css"/>
<![endif]-->
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/custom/general.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/custom/tables.js"></script>
<script type="text/javascript" src="<%=path%>/scripts/ajax.js"></script>
<script>
function chooseNumber(numberId, number)
{
  if(window.confirm("您选择了" + number + "，祝福短号为终身制，选择后将不能修改，是否继续？"))
  {
    url = "<%=path%>/setNumber.action";
    var result = "numberId=" + numberId;
    send_request_post(url, result);
  }
}

function delNumber(numberId, number)
{
  if(window.confirm("您选择删除祝福号码" + number + "，是否继续？"))
  {
    url = "<%=path%>/delNumber.action";
    var result = "numberId=" + numberId;
    send_request_post(url, result);
  }
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
        alert(returnStr);
        return;
      }
      else
      {
        alert("设置成功！");
        window.location.href = "<%=path%>/queryNumber.action";
      }
    }
    else
    {
      alert(http_request.status);
      return false;
    }
  }
}

function refreshPage()
{
  window.location.reload();
}
</script>

<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body class="loggedin">

	<!-- START OF HEADER -->
	<jsp:include page="header.jsp" />
	<!--header-->
    <!-- END OF HEADER -->
        
    <!-- START OF MAIN CONTENT -->
    <div class="mainwrapper">
     	<div class="mainwrapperinner">
         	
        <div class="mainleft">
          	<div class="mainleftinner">
            
                <jsp:include page="menu.jsp" /> 
            	<div id="togglemenuleft"><a></a></div>
            </div><!--mainleftinner-->
        </div><!--mainleft-->
        
        <div class="maincontent noright">
        	<div class="maincontentinner">
            	
                <ul class="maintabmenu">
                	<li class="current"><a href="#">祝福短号</a></li>
                </ul><!--maintabmenu-->
                
                <div class="content">
                    <p style="margin:10px;">${numberInfo}</p>
                <div class="contenttitle radiusbottom0">
                	<h2 class="table"><span>已有祝福短号</span></h2>
                </div>


<s:if test="numberList.size == 20 && num2 > 0">

<s:iterator value="#request.numberList" id="userNumber">
    <s:if test="#userNumber.numberType == 1">
                <div class="closenew">
                <a class="close" title="删除" onclick="delNumber('${userNumber.numberId}', '${userNumber.number}')"></a>
                <p>
                ${userNumber.number}
                </p>
                </div>
    </s:if>
    
    <s:if test="#userNumber.numberType == 2">
                <div class="closelucky">
                <p>
                ${userNumber.number}
                </p>
                </div>
    </s:if>
</s:iterator>

</s:if>

<s:else>

<s:iterator value="#request.numberList" id="userNumber">
    <s:if test="#userNumber.numberType == 1">
                <div class="closeall">
                <p>
                ${userNumber.number}
                </p>
                </div>
    </s:if>
    
    <s:if test="#userNumber.numberType == 2">
                <div class="closelucky">
                <p>
                ${userNumber.number}
                </p>
                </div>
    </s:if>
</s:iterator>

</s:else>
                 
                <br/>
                
<s:if test="num1 > 0">
                <div class="contenttitle radiusbottom0">
                	<h2 class="table"><span>选择祝福短号</span></h2>
                </div>

                    <ul class="imagelist">
                
<s:iterator value="#request.idleList" id="idleNumber">
                        <li>
                          <strong>${idleNumber.number}</strong>
                          <span><a href="javascript:chooseNumber('${idleNumber.numberId}', '${idleNumber.number}')" class="stdbtn btn_red">选择</a></span>
                        </li>
</s:iterator>
                    </ul>
</s:if>
                    

<s:if test="num2 > 0">
  	
                <div class="contenttitle radiusbottom0">
                	<h2 class="table"><span>选择吉祥祝福短号</span></h2>
                </div>
                
                <ul class="imagelist">
<s:iterator value="#request.goodList" id="goodNumber">
                     <li>
                         <strong>${goodNumber.number}</strong>
                         <span><a href="javascript:chooseNumber('${goodNumber.numberId}', '${goodNumber.number}')" class="stdbtn btn_red">选择</a></span>
                     </li>
</s:iterator>
                </ul>
</s:if>
                    <br clear="all" /><br />
                    
                    
                    
     <font color="red"><a name="A0" id="A0">什么是祝福短号：</a></font><br/>
     “祝福短号”是用户在婚礼现场发送短信时，需在短信内容前加入的3位数字代码。例如在使用我们的“短信许愿墙”应用时，婚礼现场的大屏幕上会显示 <br/>
     发送“001”加上您的祝福语到13871022182<br/>
     这里的“001”就是祝福短号，您只有指定了祝福短号，才能正常的使用我们的短信许愿墙服务。<br/>
<br/>
     <font color="red"><a name="A1" id="A1">什么是吉祥祝福短号：</a></font><br/>
     吉祥祝福号是类似“111”，“222”，“520”，这样，有特殊意义，或者方便记忆的祝福短号，您一次性充值5次或以上，我们将赠送您一个吉祥祝福号，快去挑选专属于您的吉祥祝福短号吧。<br/>
<br/>
     <font color="red"><a name="A2" id="A2">怎样增加祝福短号上限：</a></font><br/>
     在给您给账号充值后，您就可以选择给你自己选择祝福短号，选定后，这个祝福短号将成为您的专属号码。 <br/>
     为避免祝福短号资源浪费，我们给您的账号设置了20个祝福短号的限制，您每充值一次，我们就会为您增加一个祝福短号的上限，例如您共计充值5次，那么您的祝福短号上限将为5，这意味着您可以选择5个属于您自己的祝福短号。<br/> 
     如果您需要调整该限制，可以随时联系我们的爱说热线400-101-4595，我们会为您增加上限。<br/>

                    
                    

                </div><!--content-->
                
            </div><!--maincontentinner-->
            
            <jsp:include page="footer.jsp" />
            <!--footer-->
            
        </div><!--maincontent-->
        
     	</div><!--mainwrapperinner-->
    </div><!--mainwrapper-->
	<!-- END OF MAIN CONTENT -->
    

</body>

</html>

