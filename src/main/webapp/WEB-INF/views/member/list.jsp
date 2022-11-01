<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../layouts/header.jspf" %>

<style>
td {
    vertical-align: inherit;
  }
</style>
<table class="table">
<thead class="thead-light">  
	<tr>
		<th scope="col">회원사진</th> <%-- 해당 셀이 열(column)을 위한 헤더 셀임을 명시함. --%>
		<th scope="col">회원번호</th>
		<th scope="col">이메일</th>
		<th scope="col">이름</th>
		<th scope="col">가입일</th>
		<th scope="col">관리</th>
	</tr>
</thead>
<tbody>
<c:choose>
<%-- 등록된 메시지가 없는 경우 --%>
<c:when test="${totalCount == 0}">
	<tr>
		<td colspan="6" class="text-center">글이 없습니다.</td>
	</tr>
</c:when>
<c:otherwise>
	<c:forEach var="member" items="${memberList}">
	<tr style="height: 150px;vertical-align: inherit;text-align: center;"> <%--inherit : 수직 정렬 속성의 값이 부모 요소에서 상속하도록 지정 --%>
		<td style="height: 150px;vertical-align: inherit;" ><img src="${pageContext.request.contextPath}/uploadfile/memberphoto/${member.photo}" width="100" height="150px" ></td>
		<td style="height: 150px;vertical-align: inherit;" >${member.id}</td>
		<td style="height: 150px;vertical-align: inherit;" >${member.email}</td>
		<td style="height: 150px;vertical-align: inherit;" >${member.name}</td>
		<td style="height: 150px;vertical-align: inherit;" >${member.registerDate}<%-- <fmt:formatDate value="${member.registerDate}" type="date" pattern="YYYY/MM/dd HH:mm:SS" /> --%></td>
		<td style="height: 150px;vertical-align: inherit;" >
			<div class="btn-group-vertical" role="group" aria-label="Basic example">
				<a class="btn btn-secondary" href="memberInfo/${member.id}" role="button">보기</a>
				<a class="btn btn-secondary" href="update/${member.id}" role="button">수정</a>
				<a class="btn btn-secondary" href="delete/${member.id}" role="button">삭제</a>
			</div>
		</td>
	</tr>
	</c:forEach>
</c:otherwise>
</c:choose>
</tbody>
</table>

<%@ include file="../layouts/footer.jspf" %>