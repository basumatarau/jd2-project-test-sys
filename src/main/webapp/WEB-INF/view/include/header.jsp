<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <a class="navbar-brand" href="${pageContext.request.contextPath}">Home</a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

      <div class="collapse navbar-collapse" id="navbarNav">
            <div>
                <ul class="navbar-nav">
                  <li class="nav-item active">
                    <a class="nav-link" href="<spring:url value="/assignment-manager"/>">Assignment Manager</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="<spring:url value="/my-assignments"/>">My Assignments</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="<spring:url value="/subscribers" />">Subscribers</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="<spring:url value="/subscription" />">Subscription</a>
                  </li>
                </ul>
            </div>

        <div class="d-flex justify-content-between">
            <div></div>
            <div>
                <span class="navbar-text" class="pull-right">
                <sec:authorize access="authenticated" var="authenticated" />
                <c:choose>
                    <c:when test="${authenticated}">
                        welcome
                        <sec:authentication property="name" />
                        <a id="logout" href="#"> (logout)</a>
                        <form id="logout-form" action="<c:url value="/logout"/>" method="POST">
                            <sec:csrfInput/>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <a href="<spring:url value="/login/"/>">Sign In</a>
                    </c:otherwise>
                </c:choose>
                </span>
            </div>
        </div>
      </div>
</nav>
<script src="<spring:url value="/resources/js/global.js"/>"></script>