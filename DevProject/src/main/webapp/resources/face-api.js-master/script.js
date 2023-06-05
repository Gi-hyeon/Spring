Promise.all([
faceapi.nets.faceRecognitionNet.loadFromUri('/resources/face-api.js-master/models'),
faceapi.nets.faceLandmark68Net.loadFromUri('/resources/face-api.js-master/models'),
faceapi.nets.ssdMobilenetv1.loadFromUri('/resources/face-api.js-master/models'),
faceapi.nets.faceExpressionNet.loadFromUri('/resources/face-api.js-master/models'),	// 얼굴 인식 파일 로드
faceapi.nets.tinyFaceDetector.loadFromUri('/resources/face-api.js-master/models')
])
.then(uploadImage)

// 얼굴 감지 및 이미지 업로드 기능
function uploadImage(){
	const con = document.querySelector('.container');
	const input = document.querySelector('#myImg');
	const imgFile = document.querySelector('#myFile');
	
	var can;
	var img;
	
	imgFile.addEventListener('change', async()=>{
		if(can){ can.remove(); }
		if(img){ img.remove(); }
	
		// html 요소 생성
		img = await faceapi.bufferToImage(myFile.files[0]);
		input.src = img.src;
		const results = await faceapi.detectAllFaces(input).withFaceLandmarks().withFaceDescriptors()
		console.log(results);
		const faceMatcher = new faceapi.FaceMatcher(results); 
		results.forEach(fd => {
		  const bestMatch = faceMatcher.findBestMatch(fd.descriptor)
		  console.log(bestMatch);
		})
		
		// canvas 생성
		can = faceapi.createCanvasFromMedia(input);
		con.append(can);
		
		// box size...
		faceapi.matchDimensions(can, {width:input.width, height:input.height});
		const detectionsForSize = faceapi.resizeResults(results, {width:input.width, height:input.height});
		const box = results[0].detection.box;
		const facebox = new faceapi.draw.DrawBox(box);
		
		faceapi.draw.drawDetections(can, detectionsForSize);
		faceapi.draw.drawFaceLandmarks(can, detectionsForSize);
		faceapi.draw.drawFaceExpressions(can, detectionsForSize);
	});
	
}

