<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>test system</title>
    <jsp:include page="include/default-js-css-res.jsp"/>
</head>
<body>
	<jsp:include page="include/header.jsp" />
	<div class="container">
		<div class="row">
			<h1>Please login</h1>
		</div>
		<form id="login-form" action="${pageContext.request.contextPath}/loginAction" method="POST">
			<div class="form-group">
				<label for="make">Username</label>
				<input name="custom_username" class="form-control" value="testemail@mail.com"/>
			</div>
			<div class="form-group">
				<label for="model">Password</label>
				<input type="password" name="custom_password" class="form-control" value="testpassword" />
			</div>
			<sec:csrfInput />
			<c:if test="${param.error != null}" >
				<p>Invalid Username or Password.</p>
			</c:if>
			<button type="submit" id="btn-save" class="btn btn-primary">Login</button>
		</form>
	</div>
</body>
</html>
