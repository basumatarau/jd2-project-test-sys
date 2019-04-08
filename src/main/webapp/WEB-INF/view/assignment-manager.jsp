<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>test system</title>
    <jsp:include page="include/default-js-css-res.jsp"/>
</head>
<body>
    <jsp:include page="include/header.jsp" />
    <h1>Assignment manager</h1>

       <ul>
            <li><a href="${pageContext.request.contextPath}/assignment-manager/test-constructor"/>test constructor</a></li>
       		<li><a href="${pageContext.request.contextPath}/assignment-manager/test-bank"/>test bank</a></li>
       		<li><a href="${pageContext.request.contextPath}/assignment-manager/my-tests"/>my tests</a></li>
       </ul>

</body>
</html>