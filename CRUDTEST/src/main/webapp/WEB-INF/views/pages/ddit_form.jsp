<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
<link rel="icon" type="image/png" href="../assets/img/favicon.png">
<title>
  대덕인재개발원 CRUD 연습
</title>
<!--     Fonts and icons     -->
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
<!-- Nucleo Icons -->
<link href="../assets/css/nucleo-icons.css" rel="stylesheet" />
<link href="../assets/css/nucleo-svg.css" rel="stylesheet" />
<!-- Font Awesome Icons -->
<script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
<!-- Material Icons -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
<!-- CSS Files -->
<link id="pagestyle" href="../assets/css/material-dashboard.css?v=3.0.4" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	$('#register').on('click', function() {
		var memId = "${member.mem_id}";
		var title = $('#boTitle').val();
		var content = CKEDITOR.instances.boContent.getData();
		var ext = content.replace(/(&nbsp;|<p>|<\/p>|\s+)/g, "");
		
		if (memId == "") {
			alert("로그인 후 다시 시도해주세요!");
			return;
		}
		
		if(title == ""){
			alert("정확한 제목을 입력해주세요!");
			$('#boTitle').focus();
			return false;
		}
		
		if(ext=='' || ext == 0){
			alert("정확한 내용을 입력해주세요!");
			CKEDITOR.instances.boContent.focus();
			return false;
		}
		
		//alert($(this).text());
		
		if ($(this).text() == "수정") {
			$('#rForm').attr('action', '/boardUpdate.do');
		}
		
		$('#rForm').submit();
		
	});
	
	$('#cancel').on('click', function() {
		location.href = "/dditList";
	});
	
	$('#dditList').on('click', function() {
		location.href = "/dditList";
	});
	
	CKEDITOR.replace("boContent");
	CKEDITOR.config.allowedContent = true;
	
})
</script>
</head>
<body class="g-sidenav-show  bg-gray-200">
	<c:set value="등록" var="rBtn"/>
	<c:if test="${status eq 'u' }">
		<c:set value="수정" var="rBtn"></c:set>
	</c:if>
	<aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3   bg-gradient-dark" id="sidenav-main">
		<div class="sidenav-header">
			<i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i> 
			<a class="navbar-brand m-0" href="" target="_blank"> 
				<img src="../assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo"> 
				<span class="ms-1 font-weight-bold text-white">대덕인재개발원</span>
			</a>
		</div>
		<hr class="horizontal light mt-0 mb-2">
		<div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link text-white active bg-gradient-info" href="../assets/tables.html">
						<div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
							<i class="material-icons opacity-10">table_view</i>
						</div> 
						<span class="nav-link-text ms-1">공지사항</span>
					</a>
				</li>
			</ul>
		</div>
	</aside>
	<main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
		<!-- Navbar -->
		<nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur" data-scroll="true">
			<div class="container-fluid py-1 px-3">
				<div class="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4" id="navbar">
					<div class="ms-md-auto pe-md-3 d-flex align-items-center"></div>
					<ul class="navbar-nav  justify-content-end">
						<li class="nav-item d-flex align-items-center">
							<a href="/dditSignin" class="nav-link text-body font-weight-bold px-0"> 
								<c:choose>
									<c:when test="${not empty member }">
										<i class="fa fa-user me-sm-1" style="display: none;"></i> 
										<span class="d-sm-inline d-none" style="visibility: hidden;">로그인</span>
									</c:when>
									<c:otherwise>
										<i class="fa fa-user me-sm-1"></i> 
										<span class="d-sm-inline d-none">로그인</span>
									</c:otherwise>
								</c:choose>
							</a>
						</li>
						<li class="nav-item d-flex align-items-center">　</li>
						<li class="nav-item">
							<div class="d-flex align-items-center justify-content-between">
								<div class="avatar-group mt-2 avatar avatar-xs rounded-circle">
									<img alt="Image placeholder" src="../assets/img/team-1.jpg" style="width: 40px;">
								</div>
							</div>
						</li>
						<li class="nav-item d-flex align-items-center">　</li>
						<li class="nav-item d-flex align-items-center">
							<div class="d-flex flex-column justify-content-center">
								<c:choose>
									<c:when test="${not empty member }">
										<h6 class="mb-0 text-sm"><c:out value="${member.mem_name }"/></h6>
										<p class="text-xs text-secondary mb-0">${member.mem_id }</p>
									</c:when>
									<c:otherwise>
										<h5 class="mb-0 text-sm">로그인이 필요합니다.</h5>
									</c:otherwise>
								</c:choose>
							</div>
						</li>
						<li class="nav-item d-flex align-items-center">　</li>
						<li class="nav-item d-flex align-items-center">
						<a href="/logout.do" class="nav-link text-body font-weight-bold px-0">
							<c:choose>
								<c:when test="${not empty member }">
									<i class="fa fa-user me-sm-1"></i> 
									<span class="d-sm-inline d-none">로그아웃</span>
								</c:when>
								<c:otherwise>
									<i class="fa fa-user me-sm-1" style="display: none;"></i> 
									<span class="d-sm-inline d-none" style="visibility: hidden;">로그아웃</span>
								</c:otherwise>
							</c:choose>
						</a>
						</li>
						<li class="nav-item d-flex align-items-center">　</li>
						<li class="nav-item d-flex align-items-center">
							<a href="" class="nav-link text-body font-weight-bold px-0"> 
								<i class="fa fa-user me-sm-1"></i> 
								<span class="d-sm-inline d-none">마이페이지</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- End Navbar -->
		<div class="container-fluid px-2 px-md-4">
			<div class="page-header min-height-300 border-radius-xl mt-4"
				style="background-image: url('https://images.unsplash.com/photo-1531512073830-ba890ca4eba2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1920&q=80');">
				<span class="mask  bg-gradient-secondary opacity-6"></span>
			</div>
			<div class="card card-body mx-3 mx-md-4 mt-n6">
				<div class="row gx-4 mb-2">
					<form action="/boardInsert.do" method="post" id="rForm">
						<c:if test="${status eq 'u' }">
							<input type="hidden" value="${board.boNo }" name="boNo" id="boNo">
						</c:if>
						<input type="hidden" value="${member.mem_id}" name="mem_id" id="mem_id">
						<div class="col-md-12">
							<div class="input-group input-group-outline mb-4">
								<!-- <label class="form-label">제목을 입력해주세요.</label>  -->
								<input type="text" class="form-control" name="boTitle" id="boTitle" value="${board.boTitle }">
							</div>
						</div>
						<div class="col-md-12">
							<div class="input-group input-group-outline mb-4">
								<textarea class="form-control" rows="20" name="boContent" id="boContent">${board.boContent }</textarea>
							</div>
						</div>
						<div class="col-md-12"></div>
						<div class="col-md-12">
							<button type="button" class="btn btn-primary" id="register">${rBtn }</button>
							<button type="button" class="btn btn-danger" id="cancel">취소</button>
							<button type="button" class="btn btn-info" id="dditList">목록</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
	<div class="fixed-plugin">
		<a class="fixed-plugin-button text-dark position-fixed px-3 py-2">
			<i class="material-icons py-2">settings</i>
		</a>
		<div class="card shadow-lg">
			<div class="card-header pb-0 pt-3">
				<div class="float-start">
					<h5 class="mt-3 mb-0">Material UI Configurator</h5>
					<p>See our dashboard options.</p>
				</div>
				<div class="float-end mt-4">
					<button class="btn btn-link text-dark p-0 fixed-plugin-close-button">
						<i class="material-icons">clear</i>
					</button>
				</div>
				<!-- End Toggle Button -->
			</div>
			<hr class="horizontal dark my-1">
			<div class="card-body pt-sm-3 pt-0">
				<!-- Sidebar Backgrounds -->
				<div>
					<h6 class="mb-0">Sidebar Colors</h6>
				</div>
				<a href="javascript:void(0)" class="switch-trigger background-color">
					<div class="badge-colors my-2 text-start">
						<span class="badge filter bg-gradient-primary active" data-color="primary" onclick="sidebarColor(this)"></span> 
						<span class="badge filter bg-gradient-dark" data-color="dark" onclick="sidebarColor(this)"></span> 
						<span class="badge filter bg-gradient-info" data-color="info" onclick="sidebarColor(this)"></span> 
						<span class="badge filter bg-gradient-success" data-color="success" onclick="sidebarColor(this)"></span> 
						<span class="badge filter bg-gradient-warning" data-color="warning" onclick="sidebarColor(this)"></span> 
						<span class="badge filter bg-gradient-danger" data-color="danger" onclick="sidebarColor(this)"></span>
					</div>
				</a>
				<hr class="horizontal dark my-3">
				<div class="mt-2 d-flex">
					<h6 class="mb-0">Light / Dark</h6>
					<div class="form-check form-switch ps-0 ms-auto my-auto">
						<input class="form-check-input mt-1 ms-auto" type="checkbox" id="dark-version" onclick="darkMode(this)">
					</div>
				</div>
				<hr class="horizontal dark my-sm-4">
			</div>
		</div>
	</div>
	<!--   Core JS Files   -->
	<script src="../assets/js/core/popper.min.js"></script>
	<script src="../assets/js/core/bootstrap.min.js"></script>
	<script src="../assets/js/plugins/perfect-scrollbar.min.js"></script>
	<script src="../assets/js/plugins/smooth-scrollbar.min.js"></script>
	<script>
    var win = navigator.platform.indexOf('Win') > -1;
    if (win && document.querySelector('#sidenav-scrollbar')) {
      var options = {
        damping: '0.5'
      }
      Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
    }
  </script>
	<!-- Github buttons -->
	<script async defer src="https://buttons.github.io/buttons.js"></script>
	<!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
	<script src="../assets/js/material-dashboard.min.js?v=3.0.4"></script>
</body>

</html>