<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<title>Vod</title>
<link rel="stylesheet"
	href="//g.alicdn.com/de/prismplayer/2.6.0/skins/default/aliplayer-min.css" />
<script type="text/javascript"
	src="//g.alicdn.com/de/prismplayer/2.6.0/aliplayer-min.js"></script>
</head>
<body>
	<div class="prism-player" id="J_prismPlayer"></div>
	<script> 
	var player = new Aliplayer({ 
		id: "J_prismPlayer", 
		autoplay: true, width:"1920px", height:"1280px",
		vid:"${videoid}", 
		playauth:'${playAuth}', 
		cover:'${coverurl}'}); 
	</script>
</body>
</html>
