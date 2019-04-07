<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test system login page</title>
	
	<style>
		.failed {
			color: red;
		}
	</style>
</head>
<body>
	<h3>Test system login page</h3>

	<form:form action="${pageContext.request.contextPath}/loginAction"
			   method="post">

		<!-- Check for login error -->
	
		<c:if test="${param.error != null}">
		
			<i class="failed">Invalid credentials. Sorry!</i>
			
		</c:if>
			
		<p>
			User name: <input type="text" name="username" />
		</p>

		<p>
			Password: <input type="password" name="password" />
		</p>
		
		<input type="submit" value="Login" />
		
	</form:form>
</body>
</html>