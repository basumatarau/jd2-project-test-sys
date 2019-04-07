<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-inverse navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<a href="<spring:url value="/"/>" class="navbar-brand">Test system home</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="<spring:url value="/authored-tests/"/>">My tests</a></li>
			<li><a href="<spring:url value="/assigned-tests/" />">Assigned tests</a></li>
			<li><a href="<spring:url value="/subscribers/" />">Subscribers</a></li>
			<li><a href="<spring:url value="/subscription/" />">Subscription</a></li>

			<sec:authorize access="authenticated" var="authenticated" />

			<c:choose>
				<c:when test="${authenticated}">
					<li>
						<p class="navbar-text">
							welcome
							<sec:authentication property="name" />
							<a id="logout" href="#">Logout</a> 
						</p>
						<form id="logout-form" action="<c:url value="/logout"/>" method="POST">
							<sec:csrfInput/>
						</form>
					</li>
				</c:when>
				<c:otherwise>
					<li><a href="<spring:url value="/login/"/>">Sign In</a></li>
				</c:otherwise>
			</c:choose>


		</ul>
	</div>
</nav>
<script src="<spring:url value="/resources/js/global.js"/>"></script>