<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>test system</title>
<jsp:include page="include/default-js-css-res.jsp" />
</head>
<body>
	<jsp:include page="include/header.jsp" />
	<div class="container">
		<h1>My followers</h1>
		<c:choose>
			<c:when test="${!followers.isEmpty()}">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Name</th>
							<th scope="col">Email</th>
							<th scope="col">Group</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${followers}" var="user">
							<tr>
								<td>${user.id}</td>
								<td>${user.firstName}${user.lastName}</td>
								<td>${user.email}</td>
								<td>todo: group</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:when test="${followers.isEmpty()}">
				<p>no followers yet...</p>
			</c:when>
		</c:choose>
	</div>

</body>
</html>