<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/index2.css"/>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/public/head.jspf"%>
	<div class="container content-box">
		<div class="panel">
			<div class="panel-heading">
				<ol class="breadcrumb">
					<li><a href="course/goMain.action">首页</a></li>
					<li class="active">搜索结果</li>
				</ol>
			</div>
			<div class="panel-body">
				<ul class="list-group doc-list" id="doc-list-ul">
					<c:forEach var="list" items="${courseList}">
						<li class="list-group-item"><a target="_top"
							class="zuire_courses one mr"
							href="course/seacher-content.action?courseimg=${list.courseimg}">
								<div class="second_div_img">
									<img   class="all_img"   src="${list.courseimg}">
									<div  style="float: right;font-size: 20px"> 所属类别 ：${list.courseGrade2.courseGrade.name} ${list.courseGrade2.name }</div>
								</div>
								<div class="second_bottom_div">
									<div class="second_bottom_div_name">${list.coursename}</div>
									<p class="clearfix">
										<span class="second_bottom_more">${list.teacher.tname}</span>
										<span class="second_bottom_more fr">${list.studynum}人听课</span>
									</p>
									<p>
										<span class="second_bottom_school">新学堂</span>
									</p>
									<p></p>
								</div>
						</a></li>
					</c:forEach>
				</ul>
				<p id="get-more-btn-p">
					<button onclick="getMoreResult()" id="get-more-btn">查看更多</button>
				</p>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>


</html>