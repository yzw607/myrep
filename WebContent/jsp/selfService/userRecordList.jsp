<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'userRecordList.jsp' starting page</title>
  </head>
  
  <body>

{$userinfo.userCode}<br/>

     <table border="0" width="97%">
       <tr>
         <td>消费类型</td>
         <td>初始数量</td>
         <td>消费数量</td>
         <td>消费时间</td>
         <td>剩余数量</td>
         <td>特殊号码</td>
         <td>开始时间</td>
         <td>结束时间</td>
       </tr>
       <s:iterator value="#request.recordList" id="record">
           <tr>
              <td><s:property value="#record.orderTypeStr" /></td>
              <td><s:property value="#record.originalNum" /></td>
              <td><s:property value="#record.orderNum" /></td>
              <td><s:property value="#record.orderDate" /></td>
              <td><s:property value="#record.lastNum" /></td>
              <td><s:property value="#record.costNumber" /></td>
              <td><s:property value="#record.costStart" /></td>
              <td><s:property value="#record.costEnd" /></td>
           </tr>
       </s:iterator>
     </table>



  </body>
</html>
