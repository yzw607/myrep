<%@ page contentType="application/vnd.ms-excel;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<style type="text/css">
table {
  font-size: 12px;
}
</style>

<title></title>
</head>

<%	
String filename = "短信记录导出.xls";
response.setHeader("Content-disposition", "attachment; filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1"));	
%>

<body>
<table border="1"  cellpadding="1" cellspacing="1">
  <tr>
    <td bgcolor="#99CCFF" width="200" align="center">手机号码</td>
    <td bgcolor="#99CCFF" width="205" align="center">发送人</td>
    <td bgcolor="#99CCFF" width="555" align="center">发送内容</td>
  </tr>

  <c:forEach var="data" items="${exportList}">                    
    <tr>
      <td align="center">${data[0]}</td>
      <td>${data[2]}</td>
      <td>${data[1]}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
