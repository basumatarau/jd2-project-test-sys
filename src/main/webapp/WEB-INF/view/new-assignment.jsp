<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
        <h1>Make a new assignment</h1>
        <form:form action="new-assignment/processNewAssignment" modelAttribute="newAssignmentDetails" method="post">
            <div class="container">
                Enter assignment name:
                <form:input placeholder="name" path="name" />
                <br />
                Provide assignment details:
                <form:input placeholder="details" path="details" />
                <br />
                Provide assignment due date:
                <form:input placeholder="dueDate" path="dueDate" />
                <br />
                <div>
                    Pick assignees:
                    <c:choose>
                        <c:when test="${!followers.isEmpty()}" >
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Assign</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${followers}" var="user">
                                        <tr>
                                            <td>${user.id}</td>
                                            <td>${user.firstName} ${user.lastName}</td>
                                            <td>${user.email}</td>
                                            <td>
                                                <form:checkbox path="assigneeIds" value="${user.getId()}"/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:when test="${followers.isEmpty()}" >
                            <p>no followers yet...</p>
                        </c:when>
                    </c:choose>
                </div>
                <div>
                    Select the test for the assignment:
                    <c:choose>
                        <c:when test="${!tests.isEmpty()}" >
                            <div>
                                <div>
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th scope="col">Test Name</th>
                                                <th scope="col">Description</th>
                                                <th scope="col">Select</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${tests}" var="test">
                                                <tr>
                                                    <td>${test.id}</td>
                                                    <td>${test.name}</td>
                                                    <td>${test.description}</td>
                                                    <td>
                                                        <form:radiobutton path="assignedTestId" value="${test.getId()}"/>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="pagination">
                                    <ul>
                                    <c:forEach begin="${startpage}" end="${endpage}" var="p">
                                        <li class="page-item">
                                            <a href="${pageContext.request.contextPath}/assignment-manager/new-assignment?page=${p}">${p}</a>
                                        </li>
                                    </c:forEach>
                                        </ul>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${tests.isEmpty()}" >
                            <div>
                                <p>no tests to assign...</p>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <input type="submit" value="Create New Assignment!" />
        </form:form>
    </div>
</body>
</html>