<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<link rel="stylesheet" type="text/css" href="css/course.css"/>
<link rel="stylesheet" type="text/css" href="css/index2.css"/>
<script src="js/course.js" type="text/javascript" charset="utf-8"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
<title>课程介绍</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/public/head.jspf"%>
    <div  id="content" style="margin-top: 30px;position: relative;">
    <div  id="top"  class="col-md-9" style="margin-left: 200px;  "> 
        <div class="col-md-6">
            <img class="all_img"   src="${courses.courseimg}"/>
        </div>
        <div   class="col-md-6">
             <div style="width:500px;word-break:keep-all;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;font-size: 24px;">
                ${courses.coursename}
             </div>
             <div  id="bt"  style="margin-top: 20px;"> 
             <!--  判断是否登录   没有登录要先进行登录-->
             <c:choose>
                    <c:when test="${empty user }">
                           <button  class="btn  btn-primary" id="joinCourse"  onclick="showLoginBox()">开始学习</button>
                     </c:when>
                     <c:otherwise>
                            <!-- 登录成功后发送请求  跳转视频页面 -->
                            <a  class="btn  btn-primary"   id="joinCourse"  href="vedio/look.action?courseid=${courses.id}">开始学习</a>
                     </c:otherwise>
             </c:choose>
             </div>
        </div>
    </div>
    
    
     <div  id="center"   style="margin-left: 200px;">
            <div  id="left" class="col-md-8">
                      <div  id="jj">
                           <h3>课程简介</h3>
                           <h4 style="font-weight: bold;">关于本课</h4>
                           <div>
                             ${courses.coursedesc}
                           </div>
                      </div>
                      <div   id="t"   style="margin-top: 30px">
                             <div  class="col-md-2"  style="margin-left: 0px">
                                 <img  src="  ${courses.teacher.teacherimg}"  style="width:45 px  ;height: 140px" />
                             </div>
                             <div  class="col-md-9"  style="float:left;margin-left: 20px;">
                             <h4  style="font-weight: bold;">主讲老师</h4>
                             <div style="margin-top: 10px">
                                 <h4 style="font-weight: bold;"> ${courses.teacher.tname}</h4>
                                 <div>${courses.teacher.teacherDesc}</div>
                             </div>
                             </div>
                      </div>
            </div>
            <div   id="right" class="col-md-3  panel panel-default"  style="margin-left: 50px;margin-top: 10px">
              <ul class="list-group">
						<li class="list-group-item">课程开始 : <span class="start-date">${courses.starttime}</span></li>
						<li class="list-group-item">学时:<span class="effort-weeks">${courses.couretime}</span></li>
						<li class="list-group-item">程度:<span class="level">${courses.level}</span></li>
						<li class="list-group-item">学习人数:<span class="student-num">${courses.studynum}</span></li>
						<li class="list-group-item">
							<button href="#" class="btn btn-default btn-sm">收藏</button>
							<button href="vedio/comment.action" class="btn btn-default btn-sm">评论</button></li>
		       </ul>
              <div  id="share">
                <header>
                <div class="social-sharing">
                    <h5>分享给好友</h5>
                    <!-- <div class="sharing-message">与亲朋好友分享！</div> -->
                        <a id="bt"  class="share">
                            <img style="cursor:pointer;" src="http://www.moocollege.cn/static/images/social/weixin_sharing.929757a6289c.png" alt="weixin that you've registered for this course" >
                        </a>
                        
                        <a href="http://service.weibo.com/share/share.php?&title=我正在关注锐聘学院的课程《Java Web技术及应用》,快来一起加入吧" class="share" target="_blank" id="weibo_share">
                            <img src="http://www.moocollege.cn/static/images/social/weibo_sharing.56323a5eb5de.png" alt="Post a weibo message to say you've registered for this course" >
                          
                        </a>
                        <a href="mailto:?subject=在%20锐聘学院%20上在线学习课程&body=我刚刚通过%20%20锐聘学院%20注册了编号为%20S003%20的课程%20Java%20Web技术及应用，链接是：http://121.42.46.245/courses/MOOCOLLEGE/S003/Always/about" class="share">
                            <img src="http://www.moocollege.cn/static/images/social/mail_sharing.d7f89e549aee.png" alt="Email someone to say you've registered for this course">
                        </a>
                </div>
                </header>
              </div>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>
</html>