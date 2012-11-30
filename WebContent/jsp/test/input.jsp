<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>我爱说-www.say520.cn</title>

<s:if test="#session.userinfo.userCode != 'aaaa' && #session.userinfo.userCode != 'yuliangjun' && #session.userinfo.userCode != '侯海' && #session.userinfo.userCode != '忠诚卫士' && #session.userinfo.userCode != '樊莉'">
<script>
window.location.href = "<%=path%>/index.jsp";
</script>
</s:if>
<s:else>
<script>
var msg = "${sysMsg}";
if(msg != "")
{
  alert(msg);
}
</script>
</s:else>

    
  </head>
  
  <body>
    模拟短信发送测试页面！<br><br/><br/><br/>
    
    <s:form action="smsTest" method="post" theme="simple">
      模拟手机号码：<s:textfield name="SMSTest.number"></s:textfield><br/><br/>
      模拟短信发送内容：<s:textfield name="SMSTest.content" cssStyle="width:560px"></s:textfield><br/><br/>
      模拟短信发送次数：<s:textfield name="SMSTest.count"></s:textfield><br/><br/>
      
      <s:submit value="发送"></s:submit>
    </s:form>
  </body>
</html>
