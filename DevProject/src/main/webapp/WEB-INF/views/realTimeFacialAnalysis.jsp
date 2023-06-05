<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}

#myImg{
	width: 450px;
	height: 450px;
}

.container{
	margin-top: 5%;
	position: relative;
}

canvas{
	position: absolute;
	left: 0;
	top: 0;
}
</style>
<script src="${pageContext.request.contextPath }/resources/face-api.js-master/dist/face-api.js"></script>
<script src="${pageContext.request.contextPath }/resources/face-api.js-master/dist/face-api.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/face-api.js-master/script.js"></script>
<script type="text/javascript">

</script>	
</head>
<body>
	<h1>Face-Api.js Test</h1>
	
	<div class="container">
		<img alt="" src="" id="myImg">
	</div>
	<input type="file" id="myFile" onchange="uploadImage()" accept=".jpg, .jpeg, .png">
</body>
</html>



















