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
        <h1>My assignments</h1>
    </div>

    <div class="container">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Assigner</th>
                    <th scope="col">Due date</th>
                    <th scope="col">Details</th>
                    <th scope="col">Submitted</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${assignmentList}" var="assignment">
                    <form name="assignment" id="assignment" class="form-horizontal" action="${pageContext.request.contextPath}/my-assignments/start?id=${assignment.id}" method="post">
                        <tr>
                            <td>${assignment.id}</td>
                            <td>${assignment.name}</td>
                            <td>${assignment.assigner.firstName} ${assignment.assigner.lastName}</td>
                            <td>${assignment.deadline} </td>
                            <td>${assignment.details}</td>
                            <td>
                                 <c:choose>
                                   <c:when test="${assignment.isSubmitted()}">
                                     <span class="badge badge-success">submitted</span>
                                   </c:when>
                                   <c:when test="${!assignment.isSubmitted()}">
                                     <button class="btn btn-warning">
                                        pending(start)
                                     </button>
                                   </c:when>
                                 </c:choose>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>

        <div class="pagination">
            <ul>
                <c:forEach begin="${startpage}" end="${endpage}" var="p">
                    <li class="page-item">
                        <a href="${pageContext.request.contextPath}/my-assignments?page=${p}">${p}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>

    </div>

</body>
</html>