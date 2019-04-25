<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>test system</title>
<jsp:include page="include/default-js-css-res.jsp" />
</head>
<body>
	<jsp:include page="include/header.jsp" />


	<div class="container">
		<div>
			<h2>My resources</h2>
			<div>
				<ul>
					<h3>
						<li><a href="${pageContext.request.contextPath}">back to
								home page</a></li>
					</h3>
				</ul>
				<ul>
					<h3>
						<li><a
							href="${pageContext.request.contextPath}/my-resources/newTest">create
								a new test</a></li>
					</h3>
				</ul>
			</div>
		</div>
		<div>
			Tests authored by me:
			<c:choose>
				<c:when test="${!myTests.isEmpty()}">
					<div>
						<div class="user-tests">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th scope="col">ID</th>
										<th scope="col">Test Name</th>
										<th scope="col">Description</th>
										<th scope="col">Published</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${myTests}" var="test">
										<tr>
											<td>${test.id}</td>
											<td>${test.name}</td>
											<td>${test.description}</td>
											<td><c:choose>
													<c:when test="${tests.isPublic()}">
														<span class="badge badge-success">published</span>
													</c:when>
													<c:otherwise>
														<span class="badge badge-pill badge-info">private</span>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</c:when>
				<c:when test="${myTests.isEmpty()}">
					<div>
						<p>no tests authored so far...</p>
					</div>
				</c:when>
			</c:choose>
		</div>
	</div>
</body>
</html>