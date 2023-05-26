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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	var searchForm = $('#searchForm');
	var pagingArea = $('#pagingArea');
	
	pagingArea.on('click', 'a', function(event) {
		event.preventDefault();
		var pageNo = $(this).data('page');
		console.log(pageNo);
		searchForm.find('#page').val(pageNo);
		searchForm.submit();
	});
	
});
</script>
</head>

<body class="g-sidenav-show  bg-gray-200">
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
					<a class="nav-link text-white active bg-gradient-primary" href="../assets/tables.html">
						<div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
							<i class="material-icons opacity-10">table_view</i>
						</div> 
						<span class="nav-link-text ms-1">공지사항</span>
					</a>
				</li>
			</ul>
		</div>
	</aside>
	<main
		class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
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
										<span class="d-sm-inline d-none" id="login">로그인</span>
									</c:otherwise>
								</c:choose>
							</a>
						</li>
						<li class="nav-item d-flex align-items-center">　</li>
						<li class="nav-item d-flex align-items-center">
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
										<span class="d-sm-inline d-none" id="logout">로그아웃</span>
									</c:when>
									<c:otherwise>
										<i class="fa fa-user me-sm-1" style="display: none;"></i> 
										<span class="d-sm-inline d-none" style="visibility: hidden; ">로그아웃</span>
									</c:otherwise>
								</c:choose>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- End Navbar -->
		<div class="container-fluid py-4">
			<div class="row">
				<div class="col-12">
					<div class="card my-4">
						<div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
							<div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3">
								<h6 class="text-white text-capitalize ps-3">공지사항 게시판</h6>
							</div>
						</div>
						<div class="card-body px-0 pb-2">
							<div class="row">
								<form class="input-group input-group-sm" method="post" id="searchForm">
									<div class="col-md-6"></div>
									<div class="col-md-1">
										<div class="input-group input-group-static mb-4">
												<input type="hidden" name="page" id="page" />
												<select class="form-control" name="searchType" id="exampleFormControlSelect1">
													<option value="title" <c:if test="${searchType == 'title' }"><c:out value="selected"/></c:if>>제목</option>
													<option value="writer" <c:if test="${searchType == 'writer' }"><c:out value="selected"/></c:if>>작성자</option>
												</select>
										</div>
									</div>
									<div class="col-md-3">
										<div class="ms-md-auto">
											<div class="input-group input-group-outline">
												<label class="form-label"></label> 
												<input type="text" name="searchWord" class="form-control" value="${searchWord }" placeholder="Search">
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<button type="submit" class="btn btn-outline-secondary">검색</button>
									</div>
								</form>
							</div>
						</div>
						<div class="card-body px-0 pb-2">
							<div class="table-responsive p-0">
								<table class="table align-items-center mb-0">
									<thead>
										<tr class="text-center">
											<th width="4%" class="text-dark font-weight-bolder">번호</th>
											<th width="px" class="text-dark font-weight-bolder">제목</th>
											<th width="14%" class="text-dark font-weight-bolder">작성자</th>
											<th width="14%" class="text-dark font-weight-bolder">작성일</th>
											<th width="6%" class="text-dark font-weight-bolder">조회수</th>
										</tr>
									</thead>
									<tbody>
										<c:set value="${pagingVO.dataList }" var="boardList"/>
										
										<c:choose>
											<c:when test="${empty boardList }">
												<tr class="text-center">
													<td colspan="5" class="text-dark font-weight-bolder">조회하신 게시글이 존재하지 않습니다.</td>
												</tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${boardList }" var="board">
													<tr onclick="location.href='/dditDetail?boNo=${board.boNo}'" class="text-center">
														<td width="4%" class="text-dark">${board.boNo }</td>
														<td width="px" class="text-dark">${board.boTitle }</td>
														<td width="14%" class="text-dark">${board.boDate }</td>
														<td width="14%" class="text-dark">${board.boWriter }</td>
														<td width="6%" class="text-dark">${board.boHit }</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
										
									</tbody>
								</table>
							</div>
						</div>
						<nav aria-label="Page navigation example" id="pagingArea">
							${pagingVO.pagingHTML }
						</nav>
					</div>
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