<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script>
function loginOut()
{
  if(window.confirm("您要确认要退出本系统吗？"))
  {
    window.location.href="<%=path%>/loginOut.action";
  }
}

function setting()
{
  window.location.href="<%=path%>/goUpdateInfo.action";
}

function updatePsw()
{
  window.location.href="<%=path%>/goUpdatePsw.action";
}
</script>
</head>

	<!-- START OF HEADER -->
	<div class="header radius3">
		<div class="headerinner">
        <div class="headlogo"><a href="<%=path%>/index.jsp"><img src="<%=path%>/jsp/admin/images/mainlogo.png"></a></div>
			<ul>
           		<li class="welcome">
                <p>尊敬的${userinfo.userName}，欢迎您！您的账户还有${userinfo.remainingTime}次使用次数。</p>
				</li>
				<li>
					<a href="javascript:setting()" title="信息维护"><img
							src="<%=path%>/jsp/admin/images/admin.png">
					</a>
				</li>
				<li>
					<a href="javascript:updatePsw()" title="密码修改"><img
							src="<%=path%>/jsp/admin/images/setting.png">
					</a>
				</li>
				<li>
					<a href="javascript:loginOut()" title="退出"><img
							src="<%=path%>/jsp/admin/images/off.png">
					</a>
				</li>
			</ul>
		</div>
	</div>

	<!--header-->
</html>
