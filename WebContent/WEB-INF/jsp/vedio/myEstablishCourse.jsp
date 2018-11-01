<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath}/">
<link rel="stylesheet" type="text/css" href="css/index2.css" />
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<title>我的开设课程</title>
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
						<c:forEach items="${courseList}" var="list">
							<a target="_top" class="zuire_courses one mr"
								href="course/content.action?courseId=${list.id}">
								<div class="second_div_img">
									<img class="all_img" src="${list.courseimg}">
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
								</div>
							</a>
						</c:forEach>




					</div>
				</div>
			</div>
		</div>
	</div>
     <script>
     $("#left-list-nav").find("a").eq(2).addClass("active");
     </script>
</body>
</html>