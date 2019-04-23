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
    <style>

        div.right-answer{
            background-color: #80ff9f;
        }

        div.wrong-answer{
            background-color: #ff8080;
        }

        div.answer-form{
            margin: 5px;
            padding: 5px;
        }

        div.question-form {
            margin: 10px;
            padding: 10px;
            border-style: solid;
            border-width: 2px;
            border-color: #666699;
            border-radius: 10px;
        }

        p.answer-body{
            margin: 5px;
            padding: 5px;
        }

        div.test-question{
            margin: 10px;
            padding: 15px;
        }

        #assignment-details{
            margin: 20px;
            padding: 20px;
        }
    </style>
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
            <div id="assignment-details">
                <dl class="row">
                    <dt class="col-sm-4">Assignment name:</dt> <dd class="col-sm-8">${submittedAssignment.name}</dd>
                    <dt class="col-sm-4">Assignment details:</dt> <dd class="col-sm-8">${submittedAssignment.details}</dd>
                    <dt class="col-sm-4">Assignee:</dt> <dd class="col-sm-8">${submittedAssignment.assignee.getFirstName()} ${submittedAssignment.assignee.getLastName()}</dd>
                </dl>
            </div>
                    <div class="container">
                        <c:forEach items="${submittedAssignment.submittedQuestionSet}" var="submittetdQuestion">
                            <div class="question-form">
                                <div class="test-question">
                                    <p class="test-question-body">${submittetdQuestion.masterQuestion.body}</p>
                                </div>
                                <div class="test-answers">
                                    <c:forEach items="${submittetdQuestion.submittedAnswerSet}" var="submittedAnswer">
                                        <c:choose>
                                            <c:when test="${submittedAnswer.masterAnswer.isFalse() == submittedAnswer.isGivenAnswer()}">
                                                <div class="answer-form wrong-answer row">
                                                    <div>
                                                        <c:choose>
                                                            <c:when test="${submittedAnswer.masterAnswer.isFalse()}">
                                                                <span class="badge badge-pill badge-info">should NOT be chosen</span>
                                                            </c:when>
                                                            <c:when test="${!submittedAnswer.masterAnswer.isFalse()}">
                                                                <span class="badge badge-pill badge-info">should be chosen</span>
                                                            </c:when>
                                                        </c:choose>
                                                    </div>
                                                    <div>
                                                        <p class="answer-body">${submittedAnswer.masterAnswer.body}</p>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test="${submittedAnswer.masterAnswer.isFalse() != submittedAnswer.isGivenAnswer() && !submittedAnswer.masterAnswer.isFalse()}">
                                                <div class="answer-form right-answer row">
                                                    <div>
                                                        <span class="badge badge-pill badge-info">ok</span>
                                                    </div>
                                                    <div>
                                                        <p class="answer-body">${submittedAnswer.masterAnswer.body}</p>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="answer-form row">
                                                    <div>
                                                        <p class="answer-body">${submittedAnswer.masterAnswer.body}</p>
                                                    </div>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
        </div>
    </div>
</body>
</html>