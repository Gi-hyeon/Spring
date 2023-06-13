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
Promise.all([
	faceapi.nets.faceRecognitionNet.loadFromUri('/resources/face-api.js-master/models'),	// 얼굴 인식 모델 로드
	faceapi.nets.faceLandmark68Net.loadFromUri('/resources/face-api.js-master/models'),		// 얼굴 랜드마크 모델 로드
	faceapi.nets.ssdMobilenetv1.loadFromUri('/resources/face-api.js-master/models'),		// 얼굴 감지 모델 로드
	faceapi.nets.faceExpressionNet.loadFromUri('/resources/face-api.js-master/models'),		// 표정 인식 파일 로드
	faceapi.nets.tinyFaceDetector.loadFromUri('/resources/face-api.js-master/models'),		// 작은 얼굴 감지 모델 로드
	faceapi.nets.ageGenderNet.loadFromUri('/resources/face-api.js-master/models') 			// 성별 및 나이 인식 모델 로드
])
.then(uploadImage);

// 얼굴 감지 및 이미지 업로드 기능
function uploadImage(){
	// 각 요소들 가지고옴
	const con = document.querySelector('.container');
	const input = document.querySelector('#myImg');
	const imgFile = document.querySelector('#myFile');
	
	var can;
	var img;
	
	imgFile.addEventListener('change', async()=>{
		// 가장 높은 확률의 표정을 저장하기 위한 변수
		var bestExpression = "";
		var genderString = "";
		if(can){ 
			can.remove(); 
		}
		if(img){ 
			img.remove(); 
		}
		
		// html 요소 생성
		img = await faceapi.bufferToImage(myFile.files[0]);
		input.src = img.src;
		
								// 얼굴 감지, 표정, 성별 인식
		const detections = await faceapi.detectAllFaces(input).withFaceLandmarks().withFaceExpressions().withAgeAndGender();
		console.log(detections);	// 얼굴의 정보는 객체로 표현 -> 다양한 정보들이 담겨있음
	
		// canvas 생성
		can = faceapi.createCanvasFromMedia(input);		// 이미지 기반 캔버스 생성
		con.append(can);	// 얼굴 감지, 랜드마크를 그린 후 생성된 canvas를 HTML에 표시
		
		// 캔버스 크기 조정
		faceapi.matchDimensions(can, {width:input.width, height:input.height});		// 캔버스 크기를 이미지 크기에 맞게 조정
		const detectionsForSize = faceapi.resizeResults(detections, {width:input.width, height:input.height});	// 감지 결과의 크기도 조정
		
		// 얼굴 감지 및 랜드마크 그리기
		faceapi.draw.drawDetections(can, detectionsForSize);	// 감지된 얼굴에 사각형 그리기
		faceapi.draw.drawFaceLandmarks(can, detectionsForSize);	// 감지된 얼굴에 랜드마크 그리기
	   
		console.log("인원 수 : " +detections.length);
		
		detectionsForSize.forEach(result => {
			const { expressions, detection, gender } = result;
			const box = detection.box;
			const expression = getTopExpression(expressions); // 가장 높은 확률의 표정 얻기
			const text = new faceapi.draw.DrawTextField([
				`\${expression.expression} : \${expression.probability.toFixed(2)}` // 표정과 확률 텍스트 생성
				], 
				box.bottomLeft // 텍스트 위치 설정 (얼굴 아래 왼쪽)
			);
			text.draw(can);
			
			
			// 성별 텍스트 이미지 오른쪽 하단에 생성
			const genderText = new faceapi.draw.DrawTextField([
				`Gender: \${gender}` // 성별 텍스트 생성
			],
				box.bottomRight // 텍스트 위치 설정 (얼굴 아래 오른쪽)
			);
			genderText.draw(can);
			
			bestExpression = `\${expression.expression}`;
			
			console.log("표정 : " + bestExpression);
			console.log("성별 : " + gender);
		});
	});
}

// 가장 높은 확률의 표정을 가져오는 함수
function getTopExpression(expressions) {
	let topExpression = null;
	let topProbability = 0;
	
	// expressions 객체 속성을 반복하면서 가장 높은 확률의 표정을 찾음
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
	<h1>Face-api Image Upload</h1>

	<div class="container">
		<img alt="" src="" id="myImg">
	</div>
	<input type="file" id="myFile" onchange="uploadImage()" accept=".jpg, .jpeg, .png">
	
</body>
</html>



















