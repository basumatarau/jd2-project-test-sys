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
        <h1>followed users:</h1>
        <c:choose>
            <c:when test="${!followedUsers.isEmpty()}" >
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Unsubscribe</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${followedUsers}" var="user">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.firstName} ${user.lastName}</td>
                                    <td>${user.email}</td>
                                    <td>
                                        <form name="user" id="user" class="form-horizontal" action="${pageContext.request.contextPath}/subscription?id=${user.id}&action=unsubscribe" method="post">
                                            <button id="unsubscribe" value="unsubscribe" name="unsubscribe" class="btn btn-danger">
                                                unsubscribe
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:when test="${followedUsers.isEmpty()}" >
                <p>nobody is followed...</p>
            </c:when>
        </c:choose>
    </div>

    <div class="container">
        <h1>registered users:</h1>
        <c:choose>
            <c:when test="${!userList.isEmpty()}" >
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Subscribe</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${userList}" var="user">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.firstName} ${user.lastName}</td>
                                    <td>${user.email}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${!followedUsers.contains(user)}">
                                                <form name="user" id="user" class="form-horizontal" action="${pageContext.request.contextPath}/subscription?id=${user.id}&action=subscribe" method="post">
                                                    <button id="subscribe" value="subscribe" name="subscribe" class="btn btn-success">
                                                        subscribe
                                                    </button>
                                                </form>
                                            </c:when>
                                            <c:when test="${followedUsers.contains(user)}">
                                                <form name="user" id="user" class="form-horizontal" action="${pageContext.request.contextPath}/subscription?id=${user.id}&action=unsubscribe" method="post">
                                                    <button id="unsubscribe" value="unsubscribe" name="unsubscribe" class="btn btn-danger">
                                                        unsubscribe
                                                    </button>
                                                </form>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="pagination">
                    <ul>
                        <c:forEach begin="${startpage}" end="${endpage}" var="p">
                            <li class="page-item">
                                <a href="${pageContext.request.contextPath}/subscription?page=${p}">${p}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:when>
            <c:when test="${userList.isEmpty()}" >
                <p>nobody else has been registered fo far...</p>
            </c:when>
        </c:choose>
    </div>
</body>
</html>