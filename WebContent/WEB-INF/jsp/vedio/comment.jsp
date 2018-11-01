<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath}/">
<link rel="stylesheet" type="text/css" href="resources/css/docResult.css" />
<link href="resources/umeditor1_2_2-utf8-jsp/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8"
	src="resources/umeditor1_2_2-utf8-jsp/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/umeditor1_2_2-utf8-jsp/umeditor.min.js"></script>
<script type="text/javascript"
	src="resources/umeditor1_2_2-utf8-jsp/lang/zh-cn/zh-cn.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档评论</title>
</head>
<body>
    <%@ include file="/WEB-INF/jsp/public/head.jspf"%>
    <!-- 评论区div -->
				<div class="panel panel-default comment-box"   style="margin-bottom: 30px;">

					<ul class="list-group">
						<li class="list-group-item"><b>评论</b></li>

						<li class="list-group-item" id="getMore"><button
								id="get-more-button" onclick="getMoreInfo()">查看更多</button></li>
						<li class="list-group-item comment-text">
							<!--style给定宽度可以影响编辑器的最终宽度--> 
							<script type="text/plain"
								id="myEditor" style="width: 100%; height: 200px;">
						    </script>
						</li>
					</ul>
					<p>
						<button class="btn btn-default comment-submit"
							onclick="replyCommentp(this)">发表</button>
					</p>
				</div>
    <%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>
</html>