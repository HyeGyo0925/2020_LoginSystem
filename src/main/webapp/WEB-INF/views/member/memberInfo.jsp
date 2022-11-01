<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ include file="../layouts/header.jspf" %>

<div class="card" style="width:730px">
	<img class="card-img-top" src="${pageContext.request.contextPath}/uploadfile/memberphoto/${loginInfo.photo}">
	<div class="card-body">
		<h5 class="card-title">이름 : ${loginInfo.name}</h5>
		<p class="card-text">
			이메일 : ${loginInfo.email}<br>
			가입일 : ${loginInfo.registerDate}<%-- <fmt:formatDate value="${loginInfo.registerDate}" type="date" pattern="YYYY/MM/dd HH:mm:SS" /> --%>
		</p>
	</div>
</div>

<%@ include file="../layouts/footer.jspf" %>