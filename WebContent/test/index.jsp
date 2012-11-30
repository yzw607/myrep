<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<html>
<head>
<style type="text/css">
		.swfuploadbtn {
			display: block;
			width: 50px;
			font-size: 16px; 
			color: #666666
		}
		.browsebtn { background: url(/images/add.png) no-repeat 0 4px; }
		.uploadbtn { 
			display: none;
			background: url(/images/accept.png) no-repeat 0 4px; 
		}
		.cancelbtn { 
			display: block;
			width: 16px;
			height: 16px;
			float: right;
			background: url(/images/cancel.png) no-repeat; 
		}
		#cancelqueuebtn {
			display: block;
			display: none;
			background: url(/images/cancel.png) no-repeat 0 4px;
			margin: 10px 0;
		}
		#SWFUploadFileListingFiles ul {
			margin: 0;
			padding: 0;
			list-style: none;
		}
		.SWFUploadFileItem {
			display: block;
			width: 230px;
			height: 70px;
			float: left;
			background: #eaefea;
			margin: 0 10px 10px 0;
			padding: 5px;

		}
		.fileUploading { background: #fee727; }
		.uploadCompleted { background: #d2fa7c; }
		.uploadCancelled { background: #f77c7c; }
		.uploadCompleted .cancelbtn, .uploadCancelled .cancelbtn {
			display: none;
		}
		span.progressBar {
			width: 200px;
			display: block;
			font-size: 10px;
			height: 4px;
			margin-top: 2px;
			margin-bottom: 10px;
			background-color: #CCC;
		}
	</style>
	<script type="text/javascript" src="js/SWFUpload.js"></script>
	<script type="text/javascript" src="js/example_callbacks.js"></script>
	<script type="text/javascript">
		var swfu;
	window.onload = function() {
	swfu = new SWFUpload({
		upload_script : "upload.jsp",
		target : "SWFUploadTarget",
		flash_path : "js/SWFUpload.swf",
		allowed_filesize : 30720,	// 30 MB
		allowed_filetypes : "*.*",
		allowed_filetypes_description : "All files...",
		browse_link_innerhtml : "选择",
		upload_link_innerhtml : "开始上传",
		browse_link_class : "swfuploadbtn browsebtn",
		upload_link_class : "swfuploadbtn uploadbtn",
		flash_loaded_callback : 'swfu.flashLoaded',
		upload_file_queued_callback : "fileQueued",
		upload_file_start_callback : 'uploadFileStart',
		upload_progress_callback : 'uploadProgress',
		upload_file_complete_callback : 'uploadFileComplete',
		upload_file_cancel_callback : 'uploadFileCancelled',
		upload_queue_complete_callback : 'uploadQueueComplete',
		upload_error_callback : 'uploadError',
		upload_cancel_callback : 'uploadCancel',
		auto_upload : false
	});
};
</script>
</head>
		<p>请选择文件</p>
		<div id="SWFUploadTarget">
			<body>
			<form action="upload.jsp" method="post" enctype="multipart/form-data">
			</form>
			</body>
		</div>
		<h4 id="queueinfo">未选择文件</h4>
		<div id="SWFUploadFileListingFiles"></div>
		<br class="clr" />
		<a class="swfuploadbtn" id="cancelqueuebtn" href="javascript:cancelQueue();">删除所有已选择文件</a>
<html>