<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<title>视频点播</title>
<link rel="stylesheet"
	href="//g.alicdn.com/de/prismplayer/2.6.0/skins/default/aliplayer-min.css" />
<script type="text/javascript"
	src="//g.alicdn.com/de/prismplayer/2.6.0/aliplayer-min.js"></script>

<link rel="stylesheet"
	href="//g.alicdn.com/de/prismplayer/2.6.0/skins/default/aliplayer-min.css" />
<script type="text/javascript"
	src="//g.alicdn.com/de/prismplayer/2.6.0/aliplayer-min.js"></script>
<script> 
 var palyerDom = document.createElement('div'); 
 palyerDom.id = 'J_prismPlayer'; palyerDom.style.width = '1920px';
 palyerDom.style.height = '1280px'; palyerDom.className = 'prism-player';
 var body = document.getElementsByTagName('body'); 
 if(body.length) { 
	body[0].appendChild(palyerDom); 
	var player = new Aliplayer({ 
		id: "J_prismPlayer", 
		autoplay: true, 
		width:"1920px", 
		height:"1280px", 
		vid:"88beea65eb8b4423968b7264cfad3281",
		playauth:'', 
		cover:'' }); 
	}
</script>
</head>
<body>
	<div class="prism-player" id="J_prismPlayer"></div>
	<script> var player = new Aliplayer({ id: "J_prismPlayer", autoplay: true, width:"1920px", height:"1280px", vid:"88beea65eb8b4423968b7264cfad3281", playauth:'', cover:'' }; </script>
</body>
</html>
