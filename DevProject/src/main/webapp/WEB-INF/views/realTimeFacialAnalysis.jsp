<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs"></script>
<script src="https://cdn.jsdelivr.net/npm/@vladmandic/face-api@1/dist/face-api.js"></script>
<script>
	window.onload = function() {
	    startFaceDetection();
	};
	
    async function startFaceDetection() {
        const video = document.getElementById('video');
        const canvas = document.getElementById('canvas');

        // Check if video element exists
        if (!video) {
            console.error('Video element not found.');
            return;
        }

        const displaySize = { width: video.width, height: video.height };

        await faceapi.loadSsdMobilenetv1Model('/models');
        await faceapi.loadFaceExpressionModel('/models');

        try {
            const stream = await navigator.mediaDevices.getUserMedia({ video: true });
            video.srcObject = stream;

            video.addEventListener('loadedmetadata', () => {
                const context = canvas.getContext('2d');

                setInterval(async () => {
                    const detections = await faceapi.detectAllFaces(video).withFaceExpressions();

                    context.clearRect(0, 0, canvas.width, canvas.height);

                    faceapi.draw.drawDetections(canvas, faceapi.resizeResults(detections, displaySize));
                    faceapi.draw.drawFaceExpressions(canvas, faceapi.resizeResults(detections, displaySize));
                }, 100);
            });
        } catch (error) {
            console.error('Error accessing webcam:', error);
        }
    }

    startFaceDetection();
</script>
</head>
<body>
	<h1>Face-Api.js Test</h1>
	
	<video src="" id="video" width="600" height="500" autoplay="autoplay"></video>
	<canvas id="canvas" ></canvas>
</body>
</html>