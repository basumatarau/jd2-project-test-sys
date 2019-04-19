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

        <h1>Assignment manager</h1>

        <ul>
            <h3>
                <li><a href="${pageContext.request.contextPath}/assignment-manager/new-assignment"/>new assignment</a></li>
            </h3>
        </ul>
    </div>

    <div class="container">
        <h2>Managed assignments</h2>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Assignee</th>
                    <th scope="col">Due date</th>
                    <th scope="col">Details</th>
                    <th scope="col" colspan="2">Submitted</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${assignmentList}" var="assignment">
                        <tr>
                            <td>${assignment.id}</td>
                            <td>${assignment.name}</td>
                            <td>${assignment.assignee.firstName} ${assignment.assignee.lastName}</td>
                            <td>${assignment.deadline}</td>
                            <td>${assignment.details}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${assignment.isSubmitted()}">
                                        <span class="badge badge-success">yes</span>
                                    </c:when>
                                    <c:when test="${!assignment.isSubmitted()}">
                                        <span class="badge badge-warning">no</span>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${assignment.isSubmitted()}">
                                        <form class="form-horizontal" action="${pageContext.request.contextPath}/assignment-manager/showResult" method="get">
                                            <button id="show-result" value="${assignment.id}" name="id" class="btn btn-primary">
                                                Show results
                                            </button>
                                        </form>
                                    </c:when>
                                    <c:when test="${!assignment.isSubmitted()}">
                                        <form class="form-horizontal" action="${pageContext.request.contextPath}/assignment-manager/delete?id=${assignment.id}" method="post">
                                            <button id="delete" value="delete" name="delete" class="btn btn-danger">
                                                Delete assignment
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
                        <a href="${pageContext.request.contextPath}/assignment-manager?page=${p}">${p}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>

    </div>

</body>
</html>