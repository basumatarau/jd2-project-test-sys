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
        <ul>
            <h3>
                <li><a href="${pageContext.request.contextPath}/assignment-manager/test-constructor"/>test constructor</a></li>
            </h3>
            <h3>
                <li><a href="${pageContext.request.contextPath}/assignment-manager/test-bank"/>test bank</a></li>
            </h3>
            <h3>
                <li><a href="${pageContext.request.contextPath}/assignment-manager/my-tests"/>my tests</a></li>
            </h3>
        </ul>

        <h1>Assignment manager</h1>

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
                    <form name="assignment" id="assignment" class="form-horizontal" action="delAssignId${assignment.id}" method="post">
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
                                <button id="delete" value="delete" name="delete" class="btn btn-danger">
                                    Delete
                                </button>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>