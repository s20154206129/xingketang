<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 清除缓存 -->
<meta http-equiv="Pragma" CONTENT="no-cache">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Expires" CONTENT="0">
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath}/">
<link rel="stylesheet" type="text/css"
	href="webuploader/webuploader.css">
<script type="text/javascript" src="js/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="webuploader/webuploader.js"></script>
<style>  
    #picker div:nth-child(2){width:100%!important;height:100%!important;}  
</style> 

</head>

<body>
	<%@ include file="/WEB-INF/jsp/public/head.jspf"%>
	<div class="container content-box">
		<div class="row">
			<div class="col-md-2 mycenter-left-nav">
				<%@ include file="/WEB-INF/jsp/public/left-list.jspf"%>
			</div>

			<div class="col-md-2" id="div">
				<ul class="list-group" id="left">
					<c:forEach items="${courseList}" var="list">
						<li id="courseId" style="list-style-type: none;"><a
							onclick="showDirectory(${list.id})" class="list-group-item">
								${list.coursename}</a>
							<div id="zs"></div></li>
					</c:forEach>
				</ul>
			</div>
			<div>
				<input type="hidden" value="" name="cid" id="cid"><br>
				<input type="hidden" value="" name="zid" id="zid"><br>
				<input type="hidden" value="" name="sid" id="sid"><br>

			</div>
			<div id="documentViewer" class="col-md-6"
				style="width: 200px; display: none; height: 100%; padding-bottom: 30px; box-sizing: border-box;">

			</div>


			<div class="col-md-4" style="height: 500px">
				<div class="panel panel-default">
					<div class="panel-heading"></div>
					<div  id="pannel"  class="panel-body" style="display: none;" >
						<span id="info"></span>
						<div id="uploader" class="wu-example">
							<!--用来存放文件信息-->
							<div id="thelist" class="uploader-list"></div>
							<div class="btns">
								<div id="picker">选择视频</div>
								<button id="ctlBtn" class="btn btn-default">开始上传</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>
<script language='javascript' type='text/javascript'>
   $("#left-list-nav").find("a").eq(4).addClass("active");
	_extensions = '3gp,mp4,rmvb,mov,avi,m4v';
	_mimeTypes = 'video/*,audio/*,application/*';
	var $btn = $('#ctlBtn');
	//视频详情的js 这里就是那个视频重新写的js 后台没变只是多了一个判断
	$(function() {
		
		var $list = $('#thelist');
		var state = 'pending';
		var  cid,zid,sid;
		cid=$('#cid').val();
		zid=$('#zid').val();
		sid=$('#sid').val();
		var thumbnailWidth = 100;
		var thumbnailHeight = 100;
		var chunkSize = 500 * 1024;
		var uniqueFileName = null;
		var md5Mark = null;
		var uploader = WebUploader.create({
			// 选完文件后，是否自动上传。
			/* auto: true, */
			// swf文件路径
			swf : '/webuploader/Uploader.swf',
			// 文件接收服务端。
			server : 'vedio/ViedoText.action?cid='+cid+"&&zid="+zid+"&&sid="+sid,
			// 选择文件的按钮。可选。
			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
			//pick: '.uploadBut',
			pick : '#picker',
			//限制文件格式 
			accept : {
				title : '视频上传',
				extensions : _extensions,
				mimeTypes : _mimeTypes
			},
			fileNumLimit : 1,//文件上传数量限制  
			threads : 1
		//上传并发数。允许同时最大上传进程数,为了保证文件上传顺序  
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		//resize: false
		});
		// 当有文件被添加进队列的时候
		uploader.on('fileQueued', function(file) {
			$list.append('<div id="' + file.id + '" class="item">'
					+ '<h4 id="FilevideoName" class="info">' + file.name
					+ '</h4>' + '<p class="state">等待上传...</p>' + '</div>');
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
		uploader.on('uploadSuccess', function(file, response) {
			//$( '#'+file.id ).find('p.state').text('已上传');
			var data = response;
			$("#fileList").show();
			if (data == "0")
				$('#' + file.id).find('p.state').text('没有获取到文件');
			else if (data == "-1")
				$('#' + file.id).find('p.state').text('视频不能为空');
			else if (data == "-2")
				$('#' + file.id).find('p.state').text('视频类型错误');
			else if (data == "-3")
				$('#' + file.id).find('p.state').text('视频太大');
			else if (data == "-4")
				$('#' + file.id).find('p.state').text('视频文件名已存在');
			else {
				$('#' + file.id).find('p.state').text('视频上传成功');
				//刷新页面
				
				//vedio/uploadVedio.action
			}
		});
		uploader.on('uploadError', function(file) {
			$('#' + file.id).find('p.state').text('上传出错');
			window.location.href="vedio/uploadVedio.action";
		});
		uploader.on('uploadComplete', function(file) {
			$('#' + file.id).find('.progress').fadeOut();
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
	
	$btn.on('click', function() {
		if ( state === 'uploading' ) {
			   uploader.stop();
			  } else {
			  
			      uploader.upload();
			   
	    }
	});
	});


	//建立一個可存取到該file的url
	function getObjectURL(file) {
		var url = null;
		if (window.createObjectURL != undefined) { // basic
			url = window.createObjectURL(file);
		} else if (window.URL != undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file);
		} else if (window.webkitURL != undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file);
		}
		return url;
	}
	//截取 file名
	function getFileName(o) {
		var pos = o.lastIndexOf("\\");
		return o.substring(pos + 1);
	}
	
	function showDirectory(id) {
		 $.post(
        "json/courseChater.action",    //url
        {    
        	courseId : id 
        }
    , function(data) {  //请求成功回调该函数
   //  {"chapterList":[{"cid":22,"sectionList":[{"cid":22,"sectionContent":"第二节 java 编程","sid":2,"videoaddress":null,"zid":1},{"cid":22,"sectionContent":"第一节 java 入门","sid":1,"videoaddress":null,"zid":1}],"zContent":"第一章 java基础应用","zid":1}]}
           var i,j;
		var str="<ul class='nav navbar-nav navbar-left'>";
		for(i=0;i<data.chapterList.length;i++){
		   str+="<li><a id='label'>"+data.chapterList[i].zContent+"</a>";
		   for(j=0;j<data.chapterList[i].sectionList.length;j++){
			   str+="<ul class='nav'>";
			   str+="<li><a id='label' onclick='showButton("+data.chapterList[i].cid+","+data.chapterList[i].sectionList[j].zid+","+data.chapterList[i].sectionList[j].sid+")'     ><i class='glyphicon glyphicon-play'></i>"+data.chapterList[i].sectionList[j].sectionContent+"</a></li>";
			   str+="</ul>";
		   }
		   str+="</li>";
		}
		 $('#documentViewer').html(str);
		 $('#pannel').css("display","none");
         $('#documentViewer').css("display","block");
    });
	
	}
	
    function  showButton(cid,zid,sid){
    	//字符串的字符串  '“”'
    	//alert(cid+","+zid+","+sid);
    	$('#cid').val(cid);
    	$('#zid').val(zid);
    	$('#sid').val(sid);
    	$('#info').val(content);
    	 $('#pannel').css("display","block");
    }
</script>
</html>
