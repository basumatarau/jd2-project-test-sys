<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

    <div class="container">
        <h1>Admin page</h1>

    </div>

    <div class="container">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Authority</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.firstName} ${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>

                        </td>
                        <td>

                        </td>
                        <td>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="pagination">
            <ul>
                <c:forEach begin="${startpage}" end="${endpage}" var="p">
                    <li class="page-item">
                        <a href="${pageContext.request.contextPath}/admin?page=${p}">${p}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</body>
</html>