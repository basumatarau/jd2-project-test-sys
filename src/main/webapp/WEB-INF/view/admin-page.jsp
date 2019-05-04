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

	    <div>
			<c:if test="${bindingErrors != null}">
			    <c:forEach items="${bindingErrors}" var="error">
				    <p>${error.getField()} + ${error.getDefaultMessage()}</p>
				</c:forEach>
			</c:if>
	    </div>

		<div class="container">
			<div class="row">
				<div class=col-md-1>ID</div>
				<div class=col-md-2>First name</div>
				<div class=col-md-2>Last name</div>
				<div class=col-md-3>Email</div>
				<div class=col-md-2>Role</div>
			</div>
		</div>

		<div class="container">
			<c:forEach items="${userList}" var="user">
				<form class="update-user-${user.id}"
					method="post">

					<div class="row">
						<div class="col-md-1">
							<input name="id" class="form-control input-md" value="${user.id}" />
						</div>
						<div class="col-md-2">
							<input id="firstName" class="form-control input-md" name="firstName"
								value="${user.firstName}" />
						</div>
						<div class="col-md-2">
							<input id="lastName" class="form-control input-md" name="lastName"
								value="${user.lastName}" />
						</div>
						<div class="col-md-3">
							<input id="email" class="form-control input-md" name="email"
								value="${user.email}" />
						</div>

						<div class="col-md-2">
							<div class="col">
                                <c:forEach items="${roles}" var="role">
                                    <div class="row">
                                        <input type="checkbox" name="roles[${role.id}].roleName"
                                             value="${role.name}" ${user.getRoles().contains(role)?"checked":""}>
                                        <p>${role.name}</p>
                                    </div>
                                </c:forEach>
							</div>
						</div>

						<div class="col-md-1">
							<button type="submit" id="updateUser" value="updateUser" name="updateUser"
								formaction="${pageContext.request.contextPath}/admin/processAdminFormUpdate" class="btn btn-success">Update</button>
						</div>
						<div class="col-md-1">
							<button type="submit" id="deleteUser" value="deleteUser" name="deleteUser"
								formaction="${pageContext.request.contextPath}/admin/processAdminFormDelete" class="btn btn-danger">Delete</button>
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