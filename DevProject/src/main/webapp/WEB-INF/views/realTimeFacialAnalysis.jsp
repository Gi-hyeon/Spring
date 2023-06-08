<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

#myImg {
	width: 450px;
	height: 450px;
}

.container {
	margin-top: 5%;
	position: relative;
}

canvas {
	position: absolute;
	left: 0;
	top: 0;
}
</style>
<script src="${pageContext.request.contextPath }/resources/face-api.js-master/dist/face-api.js"></script>
<script src="${pageContext.request.contextPath }/resources/face-api.js-master/dist/face-api.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script type="text/javascript">
var bestExpression = "";

Promise.all([
	faceapi.nets.faceRecognitionNet.loadFromUri('/resources/face-api.js-master/models'),
	faceapi.nets.faceLandmark68Net.loadFromUri('/resources/face-api.js-master/models'),
	faceapi.nets.ssdMobilenetv1.loadFromUri('/resources/face-api.js-master/models'),
	faceapi.nets.faceExpressionNet.loadFromUri('/resources/face-api.js-master/models'),  // 얼굴 인식 파일 로드
	faceapi.nets.tinyFaceDetector.loadFromUri('/resources/face-api.js-master/models')
])
.then(uploadImage);

	// 얼굴 감지 및 이미지 업로드 기능
	function uploadImage(){
		const con = document.querySelector('.container');
		const input = document.querySelector('#myImg');
		const imgFile = document.querySelector('#myFile');
		
		var can;
		var img;
		
		imgFile.addEventListener('change', async()=>{
			bestExpression = "";
			if(can){ can.remove(); }
			if(img){ img.remove(); }
		
			// html 요소 생성
			img = await faceapi.bufferToImage(myFile.files[0]);
			input.src = img.src;
			const detections = await faceapi.detectAllFaces(input).withFaceLandmarks().withFaceExpressions();
			console.log(detections);
		
			// canvas 생성
			can = faceapi.createCanvasFromMedia(input);
			con.append(can);
			
			// box size...
			faceapi.matchDimensions(can, {width:input.width, height:input.height});
			const detectionsForSize = faceapi.resizeResults(detections, {width:input.width, height:input.height});
			
			faceapi.draw.drawDetections(can, detectionsForSize);
			faceapi.draw.drawFaceLandmarks(can, detectionsForSize);
		   
			detectionsForSize.forEach(result => {
				const { expressions, detection } = result;
				const box = detection.box;
				const expression = getTopExpression(expressions); // 가장 높은 확률의 표정 얻기
				const text = new faceapi.draw.DrawTextField([
					`\${expression.expression} : \${expression.probability.toFixed(2)}` // 표정과 확률 텍스트 생성
					],
					box.bottomLeft // 텍스트 위치 설정 (얼굴 아래쪽 왼쪽)
				);
				text.draw(can);
				
				bestExpression = `\${expression.expression}`;
			});
			console.log("가장 높은 표정 : " + bestExpression);
		});
	}

	// 가장 높은 확률의 표정을 가져오는 함수
	function getTopExpression(expressions) {
		let topExpression = null;
		let topProbability = 0;
		
		Object.entries(expressions).forEach(([expression, probability]) => {
			if (probability > topProbability) {
				topExpression = expression;
				topProbability = probability;
			}
		});
		
		return {
			expression: topExpression,
			probability: topProbability
		};
	}
</script>
</head>
<body>
	<h1>Face-Api.js Test</h1>

	<div class="container">
		<img alt="" src="" id="myImg">
	</div>
	<input type="file" id="myFile" onchange="uploadImage()" accept=".jpg, .jpeg, .png">
	
	<p style="font-size: 50px; color: blue;"></p>
	<img alt="" src="https://i.namu.wiki/i/EbHl4I2dCr3aoC7AFjMYv7zBAFQTE0Cr0-r2XiIKLakxARH3BY9eonE3AZ2_ctET_2vpLI-piN4F224wAUdyyQ.webp" style="height: 1000px; height: 1000px">
</body>
</html>



















