<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 清除缓存 -->
<meta    http-equiv="Pragma" CONTENT="no-cache">
<meta    http-equiv="Cache-Control" CONTENT="no-cache">
<meta    http-equiv="Expires" CONTENT="0">
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath}/">
<link rel="stylesheet" type="text/css"
	href="webuploader/webuploader.css">
<link rel="stylesheet" type="text/css" href="css/upload.css" />
<script type="text/javascript" src="js/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="webuploader/webuploader.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/public/head.jspf"%>
	<div class="container content-box">
		<div class="row">
			<div class="col-md-2 mycenter-left-nav">
				<%@ include file="/WEB-INF/jsp/public/left-list.jspf"%>
			</div>
			<div class="col-md-10">
				<div class="panel panel-default">
					<div class="panel-heading"></div>
					<div class="panel-body">
						<div class="alert alert-info" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<i class="glyphicon glyphicon-info-sign"></i> 上传课程 (提示：图片必须小于10M)
						</div>
						<!--  设置视频wu-example  css  -->
						<div id="uploader" class="wu-example form-horizontal">
							<!-- 展示信息 -->
							<div id="thelist" class="uploader-list"></div>
							<div class="btns" style="">
								<span id="picker"            style="">选择图片</span>
							<!-- <button id="ctlBtn" class="btn btn-default"></button> --> 
							</div>
						</div>
						<div class="alert alert-info" role="alert"
							style="margin-top: 50px;">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<i class="glyphicon glyphicon-info-sign"></i> 填写课程基本信息
						</div>

						<form class="form-horizontal" id="doc-upload-form" action=""
							method="post">
							<!-- 判断是否选中文件  -->
							<input type="hidden" value="" name="id" id="id"><br>
							<input type="hidden" value="" name="coures.courseimg" id="AlldocName"><br>
							<!-- 选中文件的大小 -->
							<input type="hidden" value="" name="docSize" id="docSize"><br>
							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">名称</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputDocName"
										name="coures.coursename" placeholder="请输入课程名称" value="">
								</div>
								<div class="col-sm-2 input-warning">请填写课程名称</div>
							</div>
							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">资源类型</label>
								<div class="col-sm-4">
									<select class="form-control" id="parent-type"  name="coures.courseGrade2.courseGrade.id">
									   <option value="0" disabled selected hidden>请选择</option>
									   	<option value="1">前端开发</option>
										<option value="2">后端开发</option>
										<option value="3">移动开发</option>
									    <option value="4">大数据开发</option>
									    <option value="5">游戏开发</option>
									     <option value="6">其他</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">技术分类</label>
								<div class="col-sm-4">
									<select class="form-control" id="parent-type"
										name="coures.courseGrade2.id">
										<option value="0" disabled selected hidden>请选择</option>
										<option value="1">Node.js</option>
										<option value="2">html/css</option>
										<option value="3">jquery</option>
										<option value="4">js</option>
										<option value="5">c++/c</option>
										<option value="6">c#</option>
										<option value="7">java</option>
										<option value="8">IOS</option>
										<option value="9">Android</option>
										<option value="10">Hadoop</option>
										<option value="11">spark</option>
										<option value="12">python</option>
										<option value="13">Unity</option>
										<option value="14">其他</option>
									</select>
								</div>
							</div>
							<!-- 上传时间 -->
						 	<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">上传时间</label>
								<div class="col-sm-8">
									<input type="datetime" name="coures.starttime"  id="time" />
								</div>
								<div class="col-sm-2">选择上传时间</div>
							</div>
							<!-- 课程课时安排 -->
							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">课程课时安排</label>
								<div class="col-sm-8">
									<input type="text" placeholder="格式 ：N小时/周，M周" id="timeArray"
									class="form-control"	name="coures.couretime" />
								</div>
							</div>
							<!-- 级别 -->
							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">级别</label>
								<div class="col-sm-8">
									<select class="form-control" name="coures.level"
										id="level">
										<option value="基础">基础</option>
										<option value="高级">高级</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">权限</label>
								<div class="col-sm-8">
									<select class="form-control" name="coures.coureAuthority"
										id="docAuthority">
										<option value="1">公开</option>
										<option value="2">会员</option>
										<option value="3">仅自己</option>
									</select>
								</div>
								<div class="col-sm-2">请选择课程查看权限</div>
							</div>

							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">课程说明</label>
								<div class="col-sm-8">
									<textarea class="form-control" id="doc-desc" rows="3"
										name="coures.coursedesc" placeholder="请对课程进行简要的说明"></textarea>
								</div>
								<div class="col-sm-2">请输入课程描述</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-default">提交</button>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>
<script language='javascript' type='text/javascript'>
	$("#left-list-nav").find("a").eq(1).addClass("active");
	var isUpload = false;
	var $ = jQuery, $list = $('#thelist'), $btn = $('#ctlBtn'), state = 'pending', uploader;
	var nowFile = null;

	uploader = WebUploader.create({
		// 不压缩image
		resize : false,
		// swf文件路径
		swf : '/webuploader/Uploader.swf',
		// 文件接收服务端。    doc/file-upload
		server : 'upload/uploadPhoto.action',
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick :{
			 id:  '#picker',
             multiple:false
		} ,
		formData : {},
		fileSizeLimit : 10 * 1024 * 1024, //10M
		accept : {
			extensions : 'gif,jpg,jpeg,bmp,png',
			mimeTypes : 'image/gif,image/jpg,image/jpeg,image/bmp,image/png'
		}
	});
	// 当有文件添加进来的时候
	uploader.on('fileQueued', function(file) {
		if (nowFile != null) {
			uploader.removeFile(nowFile);
		}
		nowFile = file;
		/* $("#inputDocName").val(
				file.name.substring(0, file.name.lastIndexOf("."))); */
		/*  picker*/
		/* $("#picker").text("已选择");  */
		/*  完整名称 包括 后缀  */
		$("#AlldocName").val(file.name);
		/*前端文件的大小 doc   size是  字节B  */
		$("#docSize").val(file.size);
		/* alert(file.size); */
		/*  表单提交是表明 文件选中 */
		$("#id").val(file.name.substring(0, file.name.lastIndexOf(".")));

		$list.html('<div id="' + file.id + '" class="item">'
				+ '<h4 class="info">' + file.name + '</h4>'
				+ '<p class="state">等待上传...</p>' + '</div>');
	});

	//startUpload
	uploader.on('error', function(type) {
		if (type == 'Q_EXCEED_SIZE_LIMIT') {
			alert('文件大小太大');
		} else if (type == 'Q_TYPE_DENIED') {
			alert('文件类型不支持');
		}

	});

	// 文件上传过程中创建进度条实时显示。
	uploader
			.on(
					'uploadProgress',
					function(file, percentage) {
						var $li = $('#' + file.id), $percent = $li
								.find('.progress .progress-bar');
						// 避免重复创建
						if (!$percent.length) {
							$percent = $(
									'<div class="progress progress-striped active">'
											+ '<div class="progress-bar" role="progressbar" style="width: 0%">'
											+ '</div>' + '</div>')
									.appendTo($li).find('.progress-bar');
						}
						$li.find('p.state').text('上传中');
						$percent.css('width', percentage * 100 + '%');
					});
	uploader.on('uploadSuccess', function(file) {
		$('#' + file.id).find('p.state').text('已上传');
		$("#picker").remove();
		$("#ctlBtn").remove();

	});
	uploader.on('uploadError', function(file, reason) {
		$('#' + file.id).find('p.state').text('上传出错');
	});
	uploader.on('uploadComplete', function(file) {
		$('#' + file.id).find('.progress').fadeOut();
		isUpload = true;
	});
	uploader.on('all', function(type) {
		if (type === 'startUpload') {
			state = 'uploading';
		} else if (type === 'stopUpload') {
			state = 'paused';
		} else if (type === 'uploadFinished') {
			state = 'done';
		}
		if (state === 'uploading') {
			$btn.text('暂停上传');
		} else {
			$btn.text('开始上传');
		}
	});
	//当按钮被点击时触发，根据状态开始上传或是暂停
	  $btn.on( 'click', function() {
	  if ( state === 'uploading' ) {
	   uploader.stop();
	  } else {
		  
	   uploader.upload();
	  }
	 }); 

	$("#doc-upload-form").submit(function() {

		if ($("#inputDocName").val() == "") {
			$("#inputDocName").parent().parent().addClass("has-error");
		} else {
			$("#inputDocName").parent().parent().removeClass("has-error")
		}
		if ($("#timeArray").val() == "") {
			$("#timeArray").parent().parent().addClass("has-error");
		} else {
			$("#timeArray").parent().parent().removeClass("has-error")
		}
		if ($("#doc-desc").val() == "") {
			$("#doc-desc").parent().parent().addClass("has-error");
		} else {
			$("#doc-desc").parent().parent().removeClass("has-error")
		}
		/* timeArray */
		if ($("#id").val() == null || $("#id").val().trim() == '') { //判断是否上传文件
			alert("请上传文件!");
			return false;
		}
		if ($(this).find(".has-error").length == 0) { //判断表单是否  填好
			if ($("#id").val() == null || $("#id").val().trim() == '') { //判断是否上传文件
				alert("请先上传文件!");
				return false;
			}
			//weuploader 插件上传文件  uploader.options.server = '服务器路径'
			uploader.upload();
			//上传表单信息
			$("#doc-upload-form").attr("action", "upload/add-coure.action");
		} else {
			alert("表单不可为空");
			return false;
		}
	});
	 
 
 window.onload = function(){
	 function getDate(){
	   var today = new Date();
	   var date;
	   /* + " " + today.getHours()+":"+today.getMinutes()+":"+today.getSeconds() */
	   date = (today.getFullYear()) +"-" + (today.getMonth() + 1 ) + "-" + today.getDate();
	   return date;
	 }
	 window.setInterval(function(){
	     document.getElementById("time").value=getDate();
	 }, 1000);
}

</script>
</html>
