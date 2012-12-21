<%@ page language="java" import="com.wmp.common.Common" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我爱说 - 用户管理平台</title>
<link rel="shortcut icon" href="<%=path%>/ico.ico" />
<link rel="stylesheet" href="<%=path%>/jsp/admin/css/style.css" />

<script>
function chooseNumber(numberId, number)
{
  window.parent.document.getElementById("userNumberId").value = numberId;
  window.parent.document.getElementById("number").value = number;
  window.parent.numDialog.dialog('close');
}
</script>

</head>

<body class="loggedin" >
 
    <div class="mainwrapper">
     	<div class="mainwrapperinner">
         	
        
        <div>
        	<div class="maincontentinner">
            	
                
                <div class="content">
                   
                <div class="contenttitle radiusbottom0">
                	<h2 class="table"><span>已有祝福短号</span></h2>
                </div>


<s:iterator value="#request.hasChooseList" id="userNumber">
                <div class="closenew">
                <p>
                ${userNumber.number}
                </p>
                </div>
</s:iterator>

                <br/>
                
                <div class="contenttitle radiusbottom0">
                	<h2 class="table"><span>可选择祝福短号</span></h2>
                </div>

                    <ul class="imagelist">
                
<s:iterator value="#request.notSelectList" id="idleNumber">
                        <li>
                          <strong>${idleNumber.number}</strong>
                          <span><a href="javascript:chooseNumber('${idleNumber.numberId}', '${idleNumber.number}')" class="stdbtn btn_red">选择</a></span>
                        </li>
</s:iterator>
                    </ul>
                    

 
                    <br clear="all" /><br />
                    

                </div><!--content-->
                
            </div><!--maincontentinner-->
            
            
        </div><!--maincontent-->
        
     	</div><!--mainwrapperinner-->
    </div><!--mainwrapper-->
 


<br/>

</body>
</html>
