<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script> -->

<section class="content">
	<div class="container-fluid">
		<div class="row" style="justify-content: center;">
			<div class="col-md-6">
				<div class="card card-primary">
					<div class="card-header">
						<h3 class="card-title">공지사항 등록</h3>
					</div>
					
					<!-- 
						파일 업로드
						1) method는 꼭 post
						2) enctype="multipart/form-data"
						3) <input type="file" name="boFile">
					 -->
					<!-- 
						요청 URI : /notice/generalFormPost
						요청 방식 : post
						요청파라미터 : {boTitle=제목입니다, boContent=내용입니다, boWriter=개똥이, boFile=파일객체}
					 -->
					<form id="frm" name="frm" action="/notice/generalFormPost" method="post" enctype="multipart/form-data">
						<div class="card-body">
							<div class="form-group">
								<label for="boTitle">제목</label> 
								<input type="text" class="form-control" id="boTitle" name="boTitle" placeholder="Enter title" required="required">
							</div>
							<div class="form-group">
								<label for="boContent">내용</label> 
								<textarea id="boContent" name="boContent" class="form-control" rows="3" cols="5"></textarea>
							</div>
							<div class="form-group">
								<label for="boWriter">작성자</label> 
								<input type="text" class="form-control" id="boWriter" name="boWriter" placeholder="Enter writer" required="required">
							</div>
							<div class="form-group">
								<label for="exampleInputFile">File input</label>
								<div class="input-group">
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="exampleInputFile" name="boFile" multiple="multiple"> 
										<label class="custom-file-label" for="exampleInputFile">Choose file</label>
									</div>
								</div>
							</div>
						</div>

						<div class="card-footer">
							<button type="submit" class="btn btn-primary">등록</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
	// name="boContent"를 넣어줘야한다.
	CKEDITOR.replace("boContent");
</script>




























