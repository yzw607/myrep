<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script>
function showUser(userId)
{
  window.open("<%=path%>/showUser.action?user.userId=" + userId);
}
</script>
</head>
  
  <body>
    
     <table border="0" width="97%">
       <tr>
         <td>用户名</td>
         <td>联系人</td>
         <td>联系电话</td>
         <td>电子邮箱</td>
         <td>用户类型</td>
         <td>公司名称</td>
         <td>剩余时间</td>
       </tr>
       <s:iterator value="#request.userList" id="user">
           <tr>
              <td><s:property value="#user.userCode" /></td>
              <td><s:property value="#user.userName" /></td>
              <td><s:property value="#user.tel" /></td>
              <td><s:property value="#user.email" /></td>
              <td><s:property value="#user.userTypeStr" /></td>
              <td><s:property value="#user.companyName" /></td>
              <td><s:property value="#user.remainingTime" /></td>
              <td><input type="button" value="查看详情" onclick="showUser('${user.userId}')"/></td>
           </tr>
       </s:iterator>
     </table>
     
     <s:form action="querySuggest" theme="simple">
     <s:hidden name="page.currentPage" id="page.currentPage"></s:hidden>
     </s:form>
  </body>
</html>
