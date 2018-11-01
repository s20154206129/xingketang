<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
    <%@ include file="/WEB-INF/jsp/public/head.jspf"%>
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
							<div class="carousel-caption">
								
							</div>

						</div>
						<div class="item">
							<a href="#"><img src="img/2.jpg" alt=""></a>
							<div class="carousel-caption">
								
							</div>
						</div>
						<div class="item">
							<a href="#"><img src="img/3.jpg" alt=""></a>
							<div class="carousel-caption">
								
							</div>
						</div>
						<div class="item">
							<a href="#"><img src="img/4.jpg" alt=""></a>
							<div class="carousel-caption">
								
							</div>
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
			style="margin-bottom: 25px;margin-left: 90px;">
			<!--课程开始-->
			<div class="fl second_title_left">
				<div>课程</div>
				<div>Courses</div>
			</div>
			<div class="second_tuijian_name ml100 f20 active" id="recommend_courses"
				onmouseover="show_recommend_courses()">推荐课程</div>
			<div class="second_tuijian_name f20 " id="hot_courses"
				onmouseover="show_hot_courses()">最热课程</div>
			<a href="" class="fr show_more"
				id="more_course" target="_top">更多</a>
       </div>
	 
	
	
	
	
	<div class="container content-box">
		<div class="row">
		
		   <c:forEach   items="${courseList}"   var="list">
			<!--描述：分类-->
			<div class="col-md-3" >
			    <!-- 将每个课程的id   映射给后端     -->
				<a target="_top" class="zuire_courses one mr"   href="course/content.action?courseId=${list.id}">
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
		<!--  隐藏的 div-->
		<div class="second_courses_all" style="display: none;" id="recommend_courses_show">
                <a target="_top" class="zuire_courses one mr" href="">
                    <div class="second_div_img">
                        <img class="all_img" src="img/c1.jpg">
                    </div>
                    <div class="second_bottom_div">
                        <div class="second_bottom_div_name">Java Web技术及应用</div>
                        <p class="clearfix">
                            <span class="second_bottom_more">冯娟娟</span>
                            <span class="second_bottom_more fr">6051人听课</span>
                            </p><p><span class="second_bottom_school">新学堂</span></p>
                        <p></p>
                    </div>
                </a>
            <div class="cb"></div>
        </div><!--课程结束-->
		
	</div>
			
	
<!-- 登录注册 模块 -->
<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
 <script type='text/javascript'>
   $("#register-form").submit(function(event){
     var  username= $("#userName").val(); 
     var pwd = $("#password").val();
     var repwd=$("#rePassword").val();
        if(!username) {
          $("#usernameSpan").css("display","inline-block");
          $("#usernameSpan").text("请输入用户名");
           return false;  
        }else{  //不为空
        	 $("#usernameSpan").text("");
        }
        if(!pwd){
        	 $("#passwordSpan").css("display","inline-block");
             $("#passwordSpan").text("请输入密码");
             return  false;
        }else{
        	$("#passwordSpan").text("");
        	if(!regCheck(pwd)){   //格式没通过
           	 $("#passwordSpan").text("格式不正确");
        	return false;
            }
        }
        //重置
        $("#repasswordSpan").text("");
        if(!repwd){
       	  $("#repasswordSpan").css("display","inline-block");
          $("#repasswordSpan").text("请确认密码");
            return  false;
       }else{   //非空 则 判断两次密码是否相等
    	    if(pwd!=repwd){
    	     $("#repasswordSpan").text("两次密码不相同");
    	     return false;
    	    }
       }
 });  
 </script>
	   
</body>
</html>