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
		<h1>Admin page</h1>

	</div>

	<div class="container">
		<div class="container">
			<div class="row">
				<div class=col-md-1>ID</div>
				<div class=col-md-3>Name</div>
				<div class=col-md-3>Email</div>
				<div class=col-md-3>Authority</div>
			</div>
		</div>

		<div class="container">
			<c:forEach items="${userList}" var="user">
				<form class="update-user-${user.id}" action="do?command=Admin"
					method="post">

					<div class="row">
						<div class="col-md-1">
							<input name="id" class="form-control input-md" value="${user.id}" />
						</div>
						<div class="col-md-3">
							<input id="login" class="form-control input-md" name="login"
								value="${user.firstName} ${user.lastName}" />
						</div>
						<div class="col-md-3">
							<input id="email" class="form-control input-md" name="email"
								value="${user.email}" />
						</div>

						<div class="col-md-3">
							<select class="form-control input-md" id="roles_id"
								name="roles_id">
								<c:forEach items="${authoritySet}" var="authority">
									<option class="form-control input-md" value="${authority.id}"
										${role.id==user.roles_Id?"selected":""}>${role.role}
									</option>
								</c:forEach>
							</select>
						</div>

						<div class="col-md-1">
							<button id="updateUser" value="updateUser" name="updateUser"
								class="btn btn-success">Update</button>
						</div>
						<div class="col-md-1">
							<button id="deleteUser" value="deleteUser" name="deleteUser"
								class="btn btn-danger">Delete</button>
						</div>
					</div>
				</form>
			</c:forEach>
		</div>
		<div class="pagination">
			<ul>
				<c:forEach begin="${startpage}" end="${endpage}" var="p">
					<li class="page-item"><a
						href="${pageContext.request.contextPath}/admin?page=${p}">${p}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>