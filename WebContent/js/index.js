/**
 * 
 */
var $loginBox = $("#login-box");
var $registerBox = $("#register-box");
function showLoginBox() {
	var BrowerWidth = $(document).width();
	$loginBox.css("left",(BrowerWidth/2-200)>0?(BrowerWidth/2-200):0);
	$loginBox.show("fast");
}

function showRegisterBox(){
	var BrowerWidth = $(document).width();
	$registerBox.css("left",(BrowerWidth/2-180)>0?(BrowerWidth/2-200):0);
	$registerBox.show("fast");
}

function closeNode(mythis){
	$(mythis).parent().parent().parent().hide("fast");
}
/* 用户名唯一*/
function  deleteUsernameSpan(){
	 $("#usernameSpan").text("");
	 var name=$("#userName").val();
     $.post(
        "json/check.action",    //url
        {    
        	userName : name 
        }
    , function(data) {  //请求成功回调该函数
    	  //如果得不到json 数据可能前台出现错误
    	$("#usernameSpan").text(data.msg);
    });
}

/* 搜索  */
/* 查询 */
$("#search-form").submit(function(event) {
	var content = $("#search").val();
	if (!content) {  //为空不查询
		return false;
	} 
});


