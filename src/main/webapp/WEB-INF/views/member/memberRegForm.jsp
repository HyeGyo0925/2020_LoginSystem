<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ include file="../layouts/header.jspf" %>

<div class="card">
	<div class="card-header">회원가입</div>
	<div class="card-body text-center">
		<form class="form-signup" action="${pageContext.request.contextPath}/member/register" method="post" enctype="multipart/form-data">
			<input type="email" name="email" class="form-control form-control-email" placeholder="이메일" value="${REQ_EMAIL}">
			<input type="password" name="password" class="form-control form-control-password" placeholder="비밀번호">
			<input type="password" name="confirmPassword" class="form-control form-control-confirmPassword" placeholder="비밀번호 확인">
			<input type="text" name="name" class="form-control form-control-name" placeholder="이름" value="${REQ_NAME}">
			<div class="form-group mt-1">
				<div class="input-group">
					<div class="custom-file">
						<input type="file" name="photofile" class="custom-file-input">
						<label class="custom-file-label text-left" for="file">회원 이미지 업로드</label>
					</div>
				</div>
			</div>
			<div class="btn-group mt-3" role="group">
				<button type="submit" class="btn btn-primary">회원가입</button>
				<a class="btn btn-secondary" href="${pageContext.request.contextPath}" role="button">취소</a>
			</div>
		</form>
	</div>
</div>

<%@ include file="../layouts/footer.jspf" %>