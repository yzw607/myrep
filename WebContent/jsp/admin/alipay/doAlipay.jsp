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
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery.flot.min.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery.flot.resize.min.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/custom/general.js"></script>
<script type="text/javascript" src="<%=path%>/jsp/admin/js/custom/dashboard.js"></script>
<script>
function goBack()
{
  window.location.href = "<%=path%>/goRecharge.action";
}

function doPay()
{
  //window.location.href = "<%=path%>/goAlipayInfo.action";
}
</script>

<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body class="loggedin">

	<!-- START OF HEADER -->
	<jsp:include page="../header.jsp" />
	<!--header-->
    <!-- END OF HEADER -->
        
    <!-- START OF MAIN CONTENT -->
    <div class="mainwrapper">
     	<div class="mainwrapperinner">
         	
        <div class="mainleft">
          	<div class="mainleftinner">
                <jsp:include page="../menu.jsp" /> 
            	<div id="togglemenuleft"><a></a></div>
            </div><!--mainleftinner-->
        </div><!--mainleft-->
        
        <div class="maincontent noright">
        	<div class="maincontentinner">
            	
                <ul class="maintabmenu">
                	<li class="current"><a href="../dashboard.html">用户信息</a></li>
                </ul><!--maintabmenu-->
                
                <div class="content">
                    
                <div class="contenttitle radiusbottom0">
                	<h2 class="table"><span>订单确认</span></h2>
                </div><!--contenttitle-->	

<s:form action="doAlipay" method="post" target="_blank">
<s:hidden name="payNum"/>  
<s:hidden name="payMoney"/>              
                <table cellpadding="0" cellspacing="0" border="0" class="stdtable dashtable">
                	<colgroup>
                        <col class="con1" />
                        <col class="con0" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <td>
                            充值主题：&nbsp;&nbsp;&nbsp;&nbsp;<span>“我爱说”婚礼现场互动服务</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            充值次数：&nbsp;&nbsp;&nbsp;&nbsp;<span>${payNum}次</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            充值金额：&nbsp;&nbsp;&nbsp;&nbsp;<span>${payMoney}元</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            付款方式：&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=path%>/images/alipay/alipay_s.png" />
                            </td>
                        </tr>

                        <tr>
                            <td>
                            <input type="button" value="返        回"  onclick="goBack()" class="stdbtn btn_yellow" />
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="submit" value="确认付款"  onclick="" class="stdbtn btn_yellow" />
                            </td>
                        </tr>
                    </tbody>
                </table>
</s:form>
              
                <br />
                <br />
                
                 充值说明：<br/>
                 1.在新建婚礼时，每一个婚礼时段消耗一次使用次数，如果单场婚礼选多个时段则会消耗多次使用次数。<br/>
                 2.您为避免祝福短号资源浪费，我们给您的账号设置了20个祝福短号的限制，您每充值一次，我们就会为您增加一个祝福短号的上限，例如您共计充值5次，那么您的祝福短号上限将为5，这意味着您可以选择5个属于您自己的祝福短号。<br/>
                 3.一次性充值10次我们会赠送您一个吉祥祝福号，例如：666,888,520等。
                <br />
                
                </div><!--content-->
                
            </div><!--maincontentinner-->
            
            <jsp:include page="../footer.jsp" />
            
            <!--footer-->
            
        </div><!--maincontent-->
     	</div><!--mainwrapperinner-->
    </div><!--mainwrapper-->
	<!-- END OF MAIN CONTENT -->
    

</body>

</html>

