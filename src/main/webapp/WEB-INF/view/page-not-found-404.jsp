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
    <jsp:include page="include/header.jsp"/>
    <div class="container">
        <h1>404 page not found</h1>

        <br>
        <p>${exception.message}</p>
        <br>
        <a href="${pageContext.request.contextPath}/">Back to Home Page</a>
	</div>
</body>

</html>