<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<link rel="stylesheet" type="text/css" href="css/docResult.css" />
<link href="css/video-js.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="//g.alicdn.com/de/prismplayer/2.6.0/skins/default/aliplayer-min.css" />

<script src="js/video.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/bootstrap-treeview.js"></script>
<script type="text/javascript"
	src="//g.alicdn.com/de/prismplayer/2.6.0/aliplayer-min.js"></script>
<!-- 设置flash 路径 -->
<script>
	videojs.options.flash.swf = "video-js.swf";
</script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/public/head.jspf"%>
	<div class="container content-box">
		<div class="col-md-12">
			<ul class="nav navbar-nav">
				<li class="active"><a>&nbsp;&nbsp;课程页面</a></li>
				<li><a>&nbsp;&nbsp;课程信息</a></li>
				<li><a href="vedio/comment.action">&nbsp;&nbsp;讨论</a></li>
			</ul>
		</div>
		<%--  <li ><a id="label"  onclick="showVedio(${chapter.sectionList.cid},${chapter.sectionList.zid},${chapter.sectionList.sid})"> <i class="glyphicon glyphicon-play"></i> ${section.sectionContent}</a></li>	  --%>
		<div class="row">
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-body doc-context">
						<div id="documentViewer"
							style="width: 200px; height: 100%; padding-bottom: 30px; box-sizing: border-box;">
							<div>
								<ul class="nav navbar-nav navbar-left">
									<c:forEach items="${chapterList}" var="chapter">
										<li class=""><a id="label"> ${chapter.zContent}</a> <c:forEach
												items="${chapter.sectionList}" var="section">
												<ul class="nav">
													<li><a id="label"
														onclick="showVedio(${section.cid},${section.zid},${section.sid})"><i
															class="glyphicon glyphicon-play"></i>
															${section.sectionContent}</a></li>
												</ul>
											</c:forEach></li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div class="panel panel-default">
					<!--tracktrack> 标签为媒体元素（比如 <audio> and <video>）规定外部文本轨道。
                                                   这个元素用于规定字幕文件或其他包含文本的文件，当媒体播放时，这些文件是可见的。
                    poster        设置播放初始图   
                                                 直接放在是不能加载本地视频的  -->
					<!-- <video id="example_video_1" class="video-js vjs-default-skin"
						controls preload="none" width="640" height="264" poster=""
						data-setup="{}">
						<source src="" type='video/mp4' />
						<source src="" type='video/webm' />
						<source src="http://" type='video/ogg' />
						<track kind="captions" src="" srclang="en" label="English"></track>
							kind="subtitles"    en是文本语言     src 规定轨道文件的 URL。 
						<track kind="subtitles" src="" srclang="en" label="English"></track>
					</video> -->
					<div class="prism-player" id="J_prismPlayer"></div>
					<script> 
	                  var player = new Aliplayer({ 
		              id: "J_prismPlayer", 
		              autoplay: true, width:"500px", height:"400px",
		              vid:"", 
		              playauth:'', 
		              cover:''}); 
	               </script> 
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
	<!-- 配置自动播放 
   videojs 方法中的第一个参数是你的 video 标签的 ID，用你自己的代替。
      第二个参数是一个选项对象。它允许你像设置 data-setup 属性一样设置额外的选项
      第三个参数是一个 'ready' 回调。一旦 Video.js 初始化完成后，就会触发这个回调。
      无法加载本地视屏
   -->
	<script type="text/javascript">
       //var myPlayer = videojs('example_video_1');
		/* 一加载就进行  */
		/* videojs("example_video_1").ready(function() {
			var myPlayer = this;
			myPlayer.src('first.mp4');
			myPlayer.play();
		});  */

		function shows(address) {
			alert(address);
			/* myPlayer.src(address);
			myPlayer.play();  */
		}
		
		function  showVedio(cid,zid,sid){
			//alert(cid+","+zid+","+sid);
			//异步获取 阿里云的 id  地址 
			 $.post(
				        "json/vedioId.action",    //url
				        {    
				        	cid :cid,
				        	zid: zid,
				        	sid :sid
				        }
				    , function(data) {  //请求成功回调该函数
		                  var player = new Aliplayer({ 
			              id: "J_prismPlayer", 
			              autoplay: true, 
			              width:"500px", 
			              height:"400px",
			              vid:data.vedioId,
			              playauth:data.playAuth,
			              cover: data.playURL
				    });
				  });
				    
		}
	</script>
</body>

</html>
