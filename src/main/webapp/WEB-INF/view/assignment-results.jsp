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
        <div>
            <h3>..assignment results:</h3>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/assignment-manager">back to managed assignments</a>
        </div>
        <div class="container">
                <div>
                    <div>
                        assignment name: ${submittedAssignment.name}
                    </div>
                    <div>
                        assignment details: ${submittedAssignment.details}
                    </div>
                    <div>
                        assigner: ${submittedAssignment.assigner.getFirstName()} ${submittedAssignment.assigner.getLastName()}
                    </div>
                    <div>
                        <c:forEach items="${submittedAssignment.submittedQuestionSet}" var="submittetdQuestion">
                            <div>
                                ${submittetdQuestion.masterQuestion.body}
                            </div>
                            <div>
                                <c:forEach items="${submittetdQuestion.submittedAnswerSet}" var="submittedAnswer">
                                    <c:choose>
                                        <c:when test="${submittedAnswer.masterAnswer.isFalse() && submittedAnswer.isGivenAnswer()}">
                                            <div style="background-color:#ff8080" class="row">
                                        </c:when>
                                        <c:otherwise>
                                            <div style="background-color:#80ff9f" class="row">
                                        </c:otherwise>
                                    </c:choose>
                                        <div>
                                            <c:choose>
                                                <c:when test="${submittedAnswer.masterAnswer.isFalse()}">
                                                    should NOT be chosen:
                                                </c:when>
                                                <c:when test="${!submittedAnswer.masterAnswer.isFalse()}">
                                                    should be chosen:
                                                </c:when>
                                            </c:choose>
                                        </div>
                                        <div>
                                            ${submittedAnswer.masterAnswer.body}
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:forEach>
                    </div>
                </div>

        </div>
    </div>

</body>
</html>