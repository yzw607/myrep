<%@ page language="java" import="com.wmp.common.Common, com.wmp.userManage.bean.User" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
User user = (User)session.getAttribute(Common.USERINFO);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>我爱说 - 用户管理平台</title>
<link rel="stylesheet" href="<%=path%>/css/style.css" type="text/css" />
<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/ie9.css"/>
<![endif]-->

<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/ie8.css"/>
<![endif]-->

<!--[if IE 7]>
    <link rel="stylesheet" media="screen" href="css/ie7.css"/>
<![endif]-->
<script type="text/javascript" src="<%=path%>/scripts/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="<%=path%>/scripts/plugins/jquery.flot.min.js"></script>
<script type="text/javascript" src="<%=path%>/scripts/plugins/jquery.flot.resize.min.js"></script>
<script type="text/javascript" src="<%=path%>/scripts/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/scripts/custom/general.js"></script>
<script type="text/javascript" src="<%=path%>/scripts/custom/dashboard.js"></script>
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body class="loggedin">

	<!-- START OF HEADER -->
	<div class="header radius3">
    	<div class="headerinner">
        	
            <a href="#"><img src="<%=path%>/images/starlight_admin_template_logo_small.png" alt="" /></a>
            
              
            <div class="headright">
            	<div class="headercolumn">&nbsp;</div>
                <div id="userPanel" class="headercolumn">
                    <a href="#" class="userinfo radius2">
                        <img src="<%=path%>/images/avatar.png" alt="" class="radius2" />
                        <span><strong>张三</strong></span>
                    </a>
                    <div class="userdrop">
                        <ul>
                            <li><a href="#">个人信息</a></li>
                            <li><a href="#">密码修改</a></li>
                            <li><a href="index.html">登出</a></li>
                        </ul>
                    </div><!--userdrop-->
                </div><!--headercolumn-->
            </div><!--headright-->
        
        </div><!--headerinner-->
	</div><!--header-->
    <!-- END OF HEADER -->
        
    <!-- START OF MAIN CONTENT -->
    <div class="mainwrapper">
     	<div class="mainwrapperinner">
         	
        <div class="mainleft">
          	<div class="mainleftinner">
            
              	<div class="leftmenu">
            		<ul>
                    	<li class="current"><a href="<%=path%>/login.action" class="dashboard"><span>首页</span></a></li>
                        <li><a href="" class="tables"><span>用户信息</span></a></li>
                        <li><a href="特殊号码设置.html" class="editor"><span>特殊号码设置</span></a></li>
                        <li><a href="进入亲友短信.html" class="elements"><span>进入亲友短信</span></a></li>
                        <li><a href="进入短信抽奖.html" class="charts"><span>进入短信抽奖</span></a></li>
                        <li><a href="使用记录查询.html" class="calendar"><span>使用记录查询</span></a></li>
                    </ul>
                        
                </div><!--leftmenu-->
            	<div id="togglemenuleft"><a></a></div>
            </div><!--mainleftinner-->
        </div><!--mainleft-->
        
        <div class="maincontent noright">
        	<div class="maincontentinner">
            	
                <ul class="maintabmenu">
                	<li class="current"><a href="dashboard.html">首页</a></li>
                </ul><!--maintabmenu-->
                
                <div class="content">
                    
                    <div class="contenttitle">
                    	<h2 class="widgets"><span>消息提示</span></h2>
                    </div><!--contenttitle-->
                    
                    <br />
                    
                    <div class="notification msginfo">
                        <a class="close"></a>
                        <p>This is an information message.</p>
                    </div><!-- notification msginfo -->
                    
                    <div class="notification msgsuccess">
                        <a class="close"></a>
                        <p>This is a success message.</p>
                    </div><!-- notification msgsuccess -->
                    
                    <div class="notification msgalert">
                        <a class="close"></a>
                        <p>This is an alert message.</p>
                    </div><!-- notification msgalert -->
                    
                    <div class="notification msgerror">
                        <a class="close"></a>
                        <p>This is an error message.</p>
                    </div><!-- notification msgerror -->
                    
                    <br />
                    
                                    <div class="contenttitle radiusbottom0">
                	<h2 class="table"><span>用户信息</span></h2>
                </div><!--contenttitle-->	
                <table cellpadding="0" cellspacing="0" border="0" class="stdtable dashtable">
                	<colgroup>
                        <col class="con1" />
                        <col class="con0" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <td>联系人</td>
                            <td>Internet Explorer 5.0</td>
                        </tr>
                        <tr>
                            <td>联系电话</td>
                            <td>Internet Explorer 5.0</td>
                        </tr>
                        <tr>
                            <td>电子邮箱</td>
                            <td>Internet Explorer 5.0</td>
                        </tr>
                        <tr>
                            <td>用户类型</td>
                            <td>企业用户</td>
                        </tr>
                        <tr>
                            <td>公司名称</td>
                            <td>Internet Explorer 5.0</td>
                        </tr>
                        <tr>
                            <td>剩余时长</td>
                            <td>Internet Explorer 5.0</td>
                        </tr>
                    </tbody>
                </table>
                
                <br /><br />

                    
                </div><!--content-->
                
            </div><!--maincontentinner-->
            
            <div class="footer">
            	<p>我爱说 &copy; 2012. All Rights Reserved. </p>
            </div><!--footer-->
            
        </div><!--maincontent-->
     	</div><!--mainwrapperinner-->
    </div><!--mainwrapper-->
	<!-- END OF MAIN CONTENT -->
    

</body>

</html>
