<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>HTML5 File Drag and Drop Upload with jQuery and PHP | Tutorialzine Demo</title>
        <link rel="stylesheet" href="<%=path%>/jsp/test/assets/css/styles.css" />
        <!--[if lt IE 9]>
          <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    
    <body>
		
		<header>
			<h1>HTML5 File Upload with jQuery and PHP</h1>
		</header>
		
		<div id="dropbox">
			<span class="message">Drop images here to upload. <br /><i>(they will only be visible to you)</i></span>
		</div>
		
        <footer>
	        <h2>HTML5 File Upload with jQuery and PHP</h2>
            <a class="tzine" href="http://tutorialzine.com/2011/09/html5-file-upload-jquery-php/">Read &amp; Download on</a>
        </footer>
        
        <!-- Including The jQuery Library -->
		<script src="http://code.jquery.com/jquery-1.6.3.min.js"></script>
		<script src="<%=path%>/jsp/test/assets/js/jquery.filedrop.js"></script>
        <script src="<%=path%>/jsp/test/assets/js/script.js"></script>
    
    </body>
</html>

