<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
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
					<div class="panel-heading">课程管理</div>
					<div class="panel-body">
						<table class="table doc-table table-condensed">
							<tr>
								<th>课程编号</th>
								<th>课程名称</th>
								<th>上传时间</th>
								<th>教师</th>
								<th>类别</th>
								<th>浏览权限</th>
								<th>操作</th>
							</tr>
							<c:forEach var="d" items="${pageBean.list }">
								<tr>
									<td>${d.id }</td>
									<td>${d.coursename}</td>
									<td>${d.starttime }</td>
									<td>${d.teacher.tname }</td>
									<td>${d.courseGrade2.courseGrade.name }${d.courseGrade2.name }</td>
									<c:choose>
										<c:when test="${d.coureAuthority eq  1}">
											<td><select class="form-control" id="docAuthority"
												onchange="changeDocAuthority(${d.id },this)">
													<option value="1" selected="selected">公开</option>
													<option value="2">会员</option>
													<option value="3">仅自己</option>
											</select></td>
										</c:when>
										<c:when test="${d.coureAuthority eq  2}">
											<td><select class="form-control" id="docAuthority"
												onchange="changeDocAuthority(${d.id },this)">
													<option value="1">公开</option>
													<option value="2" selected="selected">会员</option>
													<option value="3">仅自己</option>
											</select></td>
										</c:when>
										<c:otherwise>
											<td><select class="form-control" id="docAuthority"
												onchange="changeDocAuthority(${d.id },this)">
													<option value="1">公开</option>
													<option value="2">会员</option>
													<option value="3" selected="selected">仅自己</option>
											</select></td>
										</c:otherwise>
									</c:choose>
									<td><a href="course/delete.action?courseId=${d.id }"
										class="btn btn-danger btn-sm">删除</a> <a
										onclick="updateAuthority(${d.id })"
										class="btn btn-warning btn-sm">修改</a> <a
										href="vedio/look.action?courseId=${d.id }"
										class="btn btn-info btn-sm">查看</a></td>
								</tr>
							</c:forEach>
						</table>
						
						<input type="hidden" value="doc/mydoc-list" id="actionValue">
						<%@ include file="/WEB-INF/jsp/public/pageView.jspf"%>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
	<script type="text/javascript" src="js/common.js"></script>
</body>

<script type="text/javascript">
   function   updateAuthority(id){
	   var  authority=$('#docAuthority').val();
	   window.location.href="course/update.action?courseId="+id+"&&authority="+authority;
   }
</script>
</html>


