<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图片滚动</title>
<link rel="stylesheet" href="<%=path%>/jsp/admin/js/picslider/style.css" type="text/css" />
<script type="text/javascript" src="<%=path%>/jsp/admin/js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" language="javascript" src="<%=path%>/jsp/admin/js/letterslider/jquery.easing.min.1.3.js"></script>
<script type="text/javascript" language="javascript" src="<%=path%>/jsp/admin/js/letterslider/jquery.jcontent.0.8.min.js"></script>
<link href="<%=path%>/jsp/admin/js/letterslider/css/demo.css" rel="stylesheet" type="text/css"/>  
<link href="<%=path%>/jsp/admin/js/letterslider/css/jcontent.css" rel="stylesheet" type="text/css"/> 
	<script type="text/javascript" language="javascript">
            $("document").ready(function(){ 
            	<s:if test="#request.msgList.size>0" > 
	                $("div#msg").jContent({orientation: 'horizontal', 
	                     easing: "easeOutCirc", 
	                     duration: 1500,
	                     height: 50,
	                     auto: true,
						 pause_on_hover: true,
	                     direction: 'next',
	                     pause: 3000});
				</s:if>
            });
        </script>	
</head>
<body>
	<div id="wowslider-container1">
	<div class="ws_images"><ul>
	<s:iterator value="#request.picList" id="picname" status="st">
		<li><img src="${picname}" height="380" width="200" id="wows1_${st.index}"/></li>
	</s:iterator>


</ul></div>
 
	<div class="ws_shadow"></div>
	</div>	
	<script type="text/javascript" src="<%=path%>/jsp/admin/js/picslider/wowslider.js"></script>
	<script type="text/javascript" src="<%=path%>/jsp/admin/js/picslider/script.js"></script>

	<div id="msg">		
            <div class="slides">
               <s:iterator value="#request.msgList" id="msgbox">
                <div>
                    <p><span  style="font-weight: bold;font-size: 16px;">${msgbox.sender}</span>
                    	&nbsp;&nbsp;&nbsp;&nbsp;${msgbox.msgTitle}
                    </p>
                </div>
                </s:iterator>
            </div>		
        </div>

 
</body>
</html>