<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程目录</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/public/head.jspf"%>
	<div class="container content-box">
		<div class="row">
			<div class="col-md-2 mycenter-left-nav">
				<%@ include file="/WEB-INF/jsp/public/left-list.jspf"%>
			</div>
			<div class="col-md-2">
				<ul class="list-group" id="left">
					<c:forEach items="${courseList}" var="list">
						<li style="list-style-type: none;"><a
							onclick="showDirectory(${list.id})" class="list-group-item">
								${list.coursename}</a></li>
					</c:forEach>
				</ul>
			</div>

			<div class="col-md-8">
				<div  id="content"  class="panel panel-default"  style="display: none">
					<div class="panel-heading" style="height: 50px">
						<label class="col-md-2 control-label">设计课程目录</label>
						<input type="hidden"  name="id" id="id"><br>
					</div>
					<div class="panel-body col-md-8"
						style="height: 500px;">
						<!--  传入添加的是  课程id-->
						<form method="post" id="f" class="col-md-12"  action="" >
							<table   style="width:650px;line-height: 45px;" id="table">
								<tr>
									<td id="tx"><span style="">设置课程章数：</span>
										<button id="additem" type="button"
											class="btn btn-info btn-sm bt" onclick="addclick()">添加章</button>
										<button type="submit" class="btn btn-info">保存</button>	
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
	   //左边的显示css 
       $("#left-list-nav").find("a").eq(3).addClass("active");
	
		var i = 1;
		var j=1;
		var  k;
		function showDirectory(id) {
			$('#id').val(id);
			//$('#f').attr("action","course/createSection.action?courseId="+id);
			$('#content').css("display","block");
			$("#left").find("li").eq(0).addClass("active");
		}
        /*   这个 增加章节有点bug  */
		function addclick() {
			addrow();
		}
		function addrow() {
			var tables = $('#table');
			var addtr = $("<tr><td><span>"
					+ i
					+ "</span><input name='zitem'   style='width:220px;height: 35px;'  type='text'  id='item'  size='20'/></td>"
					+ "<td><button id='addsitem' type='button' class='btn btn-info btn-sm bt' onclick='addsectionclick(\""+"section"+ "\")'>添加节</button>"
					+"<button style='color:red' class='btn btn-info btn-sm bt' onclick='deleteTrRow(this);'>&nbsp;删除</button></td>"
					+"<td><table  style='line-height: 25px;' id='section"+i+"' ></table></td>"
					+ "</tr>"
			);
		/* 	alert("------"+i); */
			addtr.appendTo(tables);
			i++;
		}
		/*  这里有个bug 只能顺序增加*/
		function  addsectionclick(bt){
			var k=i-1;
		/* 	alert(k); */
			var tables = $("#"+bt+k);
			var addtr = $("<tr><td>"
					+ "<span>"+k+"</span><input name='sitem"+k+"'     style='width:220px;height: 35px;'  type='text'  id=''/>"
					+"<button style='color:red' class='btn btn-info btn-sm bt' onclick='deleteTrChridRow(this);'>删除</button>"
					+"</td></tr>"
					);
			addtr.appendTo(tables);
			j++
		}
		function deleteTrRow(tr) {
			//多一个parent就代表向前一个标签,
			//本删除范围为<td><tr>两个标签,即向前两个parent
			//如果多一个parent就会删除整个table
			$(tr).parent().parent().remove();
			i--;
		}
		function deleteTrChridRow(tr) {
			$(tr).parent().parent().remove();
			j--;
		}
		/* form 设置了 action属性  submit 方法是不能阻止的*/
		//onsubmit方法
		$("#f").submit(function(event) {
		  $("input[name='zitem']").each(function(){
			    if(!$(this).val()){
			    	  alert('input 不能为空');
			    	  e.preventDefault();//阻止提交
			    	  return false;
			    }
			   var id=$('#id').val();
			    $('#f').attr("action","course/createSection.action?courseId="+id); 
		  })
		});
	</script>
</body>
</html>