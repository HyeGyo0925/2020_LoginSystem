<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ include file="../layouts/header.jspf" %>

<div class="card">
	<div class="card-header">로그인</div>
	<div class="card-body text-center">
		<form class="form-login" action="${pageContext.request.contextPath}/member/login" method="post">
			<input type="email" name="email" class="form-control form-control-email" placeholder="이메일" value="${REQ_EMAIL != null ? REQ_EMAIL : cookie.SAVEEMAIL.value}">
			<input type="password" name="password" class="form-control form-control-password" placeholder="비밀번호">
			<div class="custom-control custom-checkbox text-left mt-2">
				<input type="checkbox" name="saveEmail" class="custom-control-input" id="saveEmail">
				<label class="custom-control-label" for="saveEmail">이메일 저장</label>
			</div>
			<div class="btn-group mt-2" role="group">
				<button type="submit" class="btn btn-primary">로그인</button>
				<a class="btn btn-secondary" href="${pageContext.request.contextPath}" role="button">취소</a>
			</div>
		</form>
	</div>
</div>

<%@ include file="../layouts/footer.jspf" %>