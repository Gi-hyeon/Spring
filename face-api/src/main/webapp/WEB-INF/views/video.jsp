<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
	margin: 0;
	padding: 0;
	width: 100vw;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
}

canvas{
	position: absolute;
}
</style>
<script src="${pageContext.request.contextPath }/resources/face-api.js-master/dist/face-api.min.js"></script>
<script type="text/javascript">
	document.addEventListener('DOMContentLoaded', function() {
		Promise.all([
			faceapi.nets.faceRecognitionNet.loadFromUri('/resources/face-api.js-master/models'),	// 얼굴 인식 모델 로드
			faceapi.nets.faceLandmark68Net.loadFromUri('/resources/face-api.js-master/models'),		// 얼굴 랜드마크 모델 로드
			faceapi.nets.faceExpressionNet.loadFromUri('/resources/face-api.js-master/models'),		// 표정 인식 파일 로드
			faceapi.nets.ssdMobilenetv1.loadFromUri('/resources/face-api.js-master/models'),		// 얼굴 감지 모델 로드
			faceapi.nets.tinyFaceDetector.loadFromUri('/resources/face-api.js-master/models'),		// 작은 얼굴 감지 모델 로드
			faceapi.nets.ageGenderNet.loadFromUri('/resources/face-api.js-master/models') 			// 성별 및 나이 인식 모델 로드			
		]).then(startVideo);
		
		const video = document.getElementById('video');
		
		// 비디오를 시작하는 함수
		function startVideo() {
			navigator.getUserMedia(
				{ video : {} },
				stream => video.srcObject = stream,
				err => console.error(err)
			)
		}
		
		//
		video.addEventListener('play', () => {
			const canvas = faceapi.createCanvasFromMedia(video);
			document.body.append(canvas);
			const displaySize = { width: video.width, height: video.height }
			faceapi.matchDimensions(canvas, displaySize);
			
			// setInterval을 사용하여 0.2초 단위로 실행
			setInterval(async () => {
				
				// 얼굴 감지, 얼굴 특징, 표정, 나이 및 성별을 모두 포함하여 얼굴 정보를 가져옵니다.
				const detections = await faceapi.detectAllFaces(video, new faceapi.TinyFaceDetectorOptions())
				.withFaceLandmarks()
				.withFaceExpressions()
				.withAgeAndGender();
				console.log(detections); 
				
				// resizeResults 함수를 사용하여 감지 결과인 detections을 주어진 displaySize에 맞게 조정함
				const resizedDetections = faceapi.resizeResults(detections, displaySize);
				// 새로운 프레임을 그리기 위해 초기화하는 역할을 한다.
				canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height);
				faceapi.draw.drawDetections(canvas, resizedDetections);			// 감지된 얼굴에 사각형 그리기
				faceapi.draw.drawFaceLandmarks(canvas, resizedDetections);		// 감지된 얼굴에 랜드마크 그리기
				faceapi.draw.drawFaceExpressions(canvas, resizedDetections);	// 표정 인식
				
				console.log("인원 수 : " + detections.length);
				
				if (detections && detections.length > 0) {
					detections.forEach((faceDetection) => {
						const expressions = faceDetection.expressions;
						
						// 가장 높은 확률의 표정 찾음 -> 배열의 요소들을 순회하면서 더 큰 확률을 가진 표정을 가지고온다
						const maxExpression = Object.keys(expressions).reduce((a, b) => expressions[a] > expressions[b] ? a : b);
						console.log("표정 : " + maxExpression);
						
						const gender = faceDetection.gender; // 얼굴의 성별 정보를 가져옵니다.
						console.log("성별 : " + gender);
						
						const box = faceDetection.detection.box;
						
						// 성별 텍스트 이미지 오른쪽 하단에 생성
						const genderText = new faceapi.draw.DrawTextField([
							`Gender: \${gender}`
						], box.bottomRight); // 텍스트 위치 설정 (얼굴 아래쪽 오른쪽)
						genderText.draw(canvas);
					});
				} else {
					console.log("얼굴 감지x");
				}
				
				
			}, 200)
		})
	})
</script>
</head>
<body>
	<video src="" id="video" width="720px" height="500px" autoplay="autoplay"></video>
</body>
</html>