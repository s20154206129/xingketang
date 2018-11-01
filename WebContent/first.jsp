<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath}/">
<title>创新课堂</title>
<link rel="stylesheet" type="text/css" href="css/index2.css" />
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<!-- top -->
			<div class="col-md-12">
				<div class="body-top">
					<p>
						<a href="javascript:onclick=showLoginBox()">登录</a> <a
							href="javascript:onclick=showRegisterBox()">注册</a>
					</p>
				</div>
			</div>
		</div>
		<div class="row">
			<!-- search -->
			<div class="col-md-3"></div>
			<div class="col-md-9">
				<div class="search-form-box">
					<form id="search-form" action="course/search.action" method="post">
						<div class="logo-box"
							style="background-image: url('img/logo.png');"></div>
						<div class="input-search-box">
							<input type="search" id="search" class="input-search-input"
								name="searchInfo" placeholder="请输入搜索的内容">
							<button type="submit" class="input-search-btn">
								<i class="glyphicon glyphicon-search"></i> 搜索
							</button>
						</div>
					</form>
				</div>
			</div>

		</div>
		<div class="row">
			<div class="search-nav">
				<a href="main/index"> 首页 </a> <a href="main/recent">课程 </a> <a
					href="main/hot"> 院校 </a> <a href="main/all-type"> 企业</a> <a
					href="model.jsp"> 教材 </a> <a href="">名师</a>
			</div>
		</div>
	</div>


	<div class="col-md-12 right-nav">
		<!--描述：焦点图-->
		<div id="carousel-example-generic" class="carousel slide"
			data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#carousel-example-generic" data-slide-to="0"
					class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="1"></li>
				<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				<li data-target="#carousel-example-generic" data-slide-to="3"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<a href="#"><img src="img/1.jpg" alt=""></a>
					<div class="carousel-caption"></div>

				</div>
				<div class="item">
					<a href="#"><img src="img/2.jpg" alt=""></a>
					<div class="carousel-caption"></div>
				</div>
				<div class="item">
					<a href="#"><img src="img/3.jpg" alt=""></a>
					<div class="carousel-caption"></div>
				</div>
				<div class="item">
					<a href="#"><img src="img/4.jpg" alt=""></a>
					<div class="carousel-caption"></div>
				</div>
			</div>
			<!-- Controls -->
			<a class="left carousel-control" href="#carousel-example-generic"
				role="button" data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#carousel-example-generic"
				role="button" data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>



	<div class="col-md-12   second_title second_course_name"
		style="margin-bottom: 25px; margin-left: 90px;">
		<!--课程开始-->
		<div class="fl second_title_left">
			<div>课程</div>
			<div>Courses</div>
		</div>
		<div class="second_tuijian_name ml100 f20 active"
			id="recommend_courses" onmouseover="show_recommend_courses()">推荐课程</div>
		<div class="second_tuijian_name f20 " id="hot_courses"
			onmouseover="show_hot_courses()">最热课程</div>
		<a href="" class="fr show_more" id="more_course" target="_top">更多</a>
	</div>

	<div class="container content-box">
		<div class="row">

			<c:forEach items="${courseList}" var="list">
				<!--描述：分类-->
				<div class="col-md-3">
					<!-- 将每个课程的id   临时目录下-->
					<a target="_top" class="zuire_courses one mr"
						href="course/content.action?courseId=${list.id}">
						<div class="second_div_img">
							<img class="all_img" src="${list.courseimg}">
						</div>
						<div class="second_bottom_div">
							<div class="second_bottom_div_name">${list.coursename}</div>
							<p class="clearfix">
								<span class="second_bottom_more">${list.teacher.tname}</span> <span
									class="second_bottom_more fr">${list.studynum}人听课</span>
							</p>
							<p>
								<span class="second_bottom_school">新学堂</span>
							</p>
							<p></p>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
		<!--  隐藏的div-->
		<div class="second_courses_all" style="display: none;"
			id="recommend_courses_show">

			<div class="cb"></div>
		</div>
		<!--课程结束-->

	</div>

	<!-- 登录注册 模块 -->
	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
	<script type='text/javascript'>
		$("#register-form").submit(function(event) {
			var username = $("#userName").val();
			var pwd = $("#password").val();
			var repwd = $("#rePassword").val();
			if (!username) {
				$("#usernameSpan").css("display", "inline-block");
				$("#usernameSpan").text("请输入用户名");
				return false;
			} else { //不为空
				$("#usernameSpan").text("");
			}
			if (!pwd) {
				$("#passwordSpan").css("display", "inline-block");
				$("#passwordSpan").text("请输入密码");
				return false;
			} else {
				$("#passwordSpan").text("");
			}
			//重置
			$("#repasswordSpan").text("");
			if (!repwd) {
				$("#repasswordSpan").css("display", "inline-block");
				$("#repasswordSpan").text("请确认密码");
				return false;
			} else { //非空 则 判断两次密码是否相等
				if (pwd != repwd) {
					$("#repasswordSpan").text("两次密码不相同");
					return false;
				}
			}
		});

		$("#login-box").submit(function(event) {
			var username = $("#exampleInputEmail1").val();
			var pwd = $("#exampleInputPassword1").val();
			if (!username) {
				$("#ts").css("display", "inline-block");
				$("#ts").text("请输入用户名");
				return false;
			} else { //不为空
				$("#ts").text("");
			}
			if (!pwd) {
				$("#ts").css("display", "inline-block");
				$("#ts").text("请输入密码");
				return false;
			} else {
				$("#ts").text("");
			}
		});
	</script>
</body>
</html>