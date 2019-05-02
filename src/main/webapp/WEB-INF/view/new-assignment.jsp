<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
</head>
<body>
	<jsp:include page="include/header.jsp" />

	<div class="container">
		<h1>Make a new assignment</h1>
		<div class="container">
			<form
				action="${pageContext.request.contextPath}/assignment-manager/new-assignment/processNewAssignment"
				method="POST">
				<div class="form-group row">
					<label for="input-assignment-name" class="col-sm-4 col-form-label">Enter
						assignment name:</label>
					<div class="col-sm-8">
					    <div>
                            <input class="form-control" id="name" name="name"
                                placeholder="name">
					    </div>
						<div>
                            <c:if test="${name != null}">
                                <p>${name}</p>
                            </c:if>
						</div>
					</div>
				</div>
				<div class="form-group row">
					<label for="input-assignment-details"
						class="col-sm-4 col-form-label">Enter assignment details:</label>
					<div class="col-sm-8">
						<input class="form-control" id="details" name="details"
							placeholder="details">
					</div>
				</div>
				<div class="form-group row">
					<label for="dueDate" class="col-sm-4 col-form-label">Set
						the assignment due date:</label>
					<div class="col-sm-8">
						<input type="text" readonly class="form-control-plaintext"
							data-date-format="yyyy-mm-dd" id="datepicker" name="dueDate"
							placeholder="[picker]" value="">
					</div>
				</div>

				<div>
					Select assignees:
					<c:choose>
						<c:when test="${!followers.isEmpty()}">
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
											<td>${user.firstName}${user.lastName}</td>
											<td>${user.email}</td>
											<td><input type="checkbox" name="assigneeIds"
												id="assigneeIds" placeholder="assigneeIds"
												value="${user.getId()}"></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:when test="${followers.isEmpty()}">
							<p>no followers yet...</p>
						</c:when>
					</c:choose>
				</div>
				<div>
					Pick the test for the assignment:
					<c:choose>
						<c:when test="${!tests.isEmpty()}">
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
													<td><input type="radio" name="assignedTestId"
														id="assignedTestId" value="${test.getId()}" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="pagination">
									<ul>
										<c:forEach begin="${startpage}" end="${endpage}" var="p">
											<li class="page-item"><a
												href="${pageContext.request.contextPath}/assignment-manager/new-assignment?page=${p}">${p}</a>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</c:when>
						<c:when test="${tests.isEmpty()}">
							<div>
								<p>no tests to assign...</p>
							</div>
						</c:when>
					</c:choose>
				</div>


                <c:if test="${newAssignmentErrors != null}">
					<c:forEach items="${newAssignmentErrors}" var="error">
					    <p>${error.getField()} + ${error.getDefaultMessage()}</p>
					</c:forEach>
				</c:if>

				<div id="submit-test-div">
					<button type="submit" class="btn btn-success btn-lg btn-block">Create
						New Assignment!</button>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
        $(document).ready(function() {
            $(function() {
                $("#datepicker").datepicker({

                });
              });
        });
    </script>
</body>
</html>