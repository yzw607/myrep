<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<s:if test="#session.userinfo==null">
    <div class="indexnew_login">
        <div class="loginbg">
        </div>
        <div class="loginform">
        <form id="login" action="<%=path%>/login.action" method="post">
        <table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>用户名：</td>
            <td><input type="text" name="user.userCode" id="userCode" value="" onkeydown="enterIn(event);"//></td>
          </tr>
          <tr>
            <td>密&nbsp;&nbsp;码：</td>
            <td><input type="password" name="user.passWord" id="password" value="" onkeydown="enterIn(event);"//></td>
          </tr>
        </table>
        </form>
        <br />
        <a href="<%=path%>/forgetPass.jsp">忘记密码请点这里</a><br /><br />
            <button class="stdbtn btn_blue" onclick="javascript:window.location.href='<%=path%>/jsp/admin/userRegister.jsp'">免费注册</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button class="stdbtn btn_blue" onclick="doLogin()">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
        </div>
    </div>
</s:if>
<s:else>
    <div class="indexnew_login">
        <div class="loginbg">
        </div>
        <div class="loginform" >
        <table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/la.png" />&nbsp;尊敬${userinfo.userName}，欢迎登陆“我爱说”</td>
          </tr>
          <tr>
            <td><img src="images/la.png" />&nbsp;您的剩余次数为：${userinfo.remainingTime}次</td>
          </tr>
          <tr>
            <th>
            </th>
          </tr>
        </table>
            <button class="stdbtn btn_blue" onclick="javascript:window.location.href='<%=path%>/planList.action'">婚礼管理</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button class="stdbtn btn_blue" onclick="doLoginOut()">退&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出</button>
        </div>
    </div>
</s:else>