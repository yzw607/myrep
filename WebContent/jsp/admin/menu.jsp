<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<div class="leftmenu">
		<ul>
			<s:if test="menuType == 'home'">
				<li class="current">
					<a href="<%=path%>/goHome.action" class="dashboard"><span>首页</span>
					</a>
				</li>
			</s:if>
			<s:else>
				<li>
					<a href="<%=path%>/goHome.action" class="dashboard"><span>首页</span>
					</a>
				</li>
			</s:else>


			<s:if test="menuType == 'weddingManage'">
				<li class="current">
					<a href="<%=path%>/planList.action" class="elements"><span>婚礼管理</span>
					</a>
				</li>
			</s:if>
			<s:else>
				<li>
					<a href="<%=path%>/planList.action" class="elements"><span>婚礼管理</span>
					</a>
				</li>
			</s:else>

			<s:if test="menuType == 'sendAll'">
				<li class="current">
					<a href="<%=path%>/queryAll.action" class="calendar"><span>群发管理</span>
					</a>
				</li>
			</s:if>
			<s:else>
				<li>
					<a href="<%=path%>/queryAll.action" class="calendar"><span>群发管理</span>
					</a>
				</li>
			</s:else>
			
			<s:if test="menuType == 'msgTemplate'">
				<li class="current">
					<a href="<%=path%>/msgTemplate.action" class="users"><span>模板管理</span>
					</a>
				</li>
			</s:if>
			<s:else>
			    <li>
					<a href="<%=path%>/msgTemplate.action" class="users"><span>模板管理</span>
					</a>
				</li>
			</s:else>

			<s:if test="menuType == 'myAccount'">
				<li class="current">
					<a href="<%=path%>/queryInfo.action" class="tables"><span>用户信息</span>
					</a>
				</li>
			</s:if>
			<s:else>
				<li>
					<a href="<%=path%>/queryInfo.action" class="tables"><span>用户信息</span>
					</a>
				</li>
			</s:else>
			
			<s:if test="menuType == 'sendMsg'">
				<li class="current">
					<a href="<%=path%>/sendMsg.action" class="users"><span>短信发送</span>
					</a>
				</li>
			</s:if>
			<s:else>
			    <li>
					<a href="<%=path%>/sendMsg.action" class="users"><span>短信发送</span>
					</a>
				</li>
			</s:else>
			
		</ul>
	</div>
	<!--leftmenu-->

</html>
