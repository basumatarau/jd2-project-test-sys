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
    <script type="text/javascript">
        $(document).ready(function() {
            $.ajax({
                url: "http://localhost:8080${pageContext.request.contextPath}/api/assignment-get-test?id=${id}"
            }).then(function(testDto) {
               var testElement = document.createElement('div');

               var testHeader = document.createElement('h2');
               testHeader.innerHTML = testDto.testName;
               testElement.appendChild(testHeader);
               testElement.appendChild(document.createElement('br'));

               var testDescription = document.createElement('p');
               testDescription.innerHTML = testDto.testDescription;
               testElement.appendChild(testDescription);
               testElement.appendChild(document.createElement('br'));
               testElement.setAttribute("id", testDto.testId);
               testElement.setAttribute("class", "test");
               for (var i = 0; i < testDto.questionDtos.length; i++) {
                    var question = testDto.questionDtos[i];
                    console.log(question);
                    var questionElement = document.createElement('div');
                    questionElement.setAttribute("id", question.id);
                    questionElement.setAttribute("class", "question");
                    var questionBodyDiv = document.createElement('div');
                    var questionBody = document.createElement('p');

                    questionBody.innerHTML = question.body;
                    questionBodyDiv.appendChild(questionBody);
                    questionElement.appendChild(questionBodyDiv);

                    for (var j = 0; j < question.answerDtos.length; j++) {
                        var answer = question.answerDtos[j];
                        var answerElement = document.createElement('div');
                        answerElement.setAttribute("class","row answer");
                        answerElement.setAttribute("id", answer.id);

                        var answerBodyDiv = document.createElement('div');
                        var answerBody = document.createElement('p');
                        answerBody.innerHTML = answer.body;
                        answerBodyDiv.appendChild(answerBody);
                        answerElement.appendChild(answerBodyDiv);

                        var answerOptDiv = document.createElement('div');
                        answerOptDiv.setAttribute("class", "form-check");
                        var answerOpt = document.createElement('input');
                        answerOpt.setAttribute("type","checkbox");
                        answerOpt.setAttribute("class","form-check-input");

                        answerOptDiv.appendChild(answerOpt);
                        answerElement.appendChild(answerOptDiv);
                        questionElement.appendChild(answerElement);
                    }
                    testElement.appendChild(questionElement);
               }
               var submitButtonElement = document.createElement('div');

               testElement.appendChild(submitButtonElement);
               document.getElementById("test-place-holder").appendChild(testElement);
            });
        });
    </script>
</head>
<body>
    <jsp:include page="include/header.jsp" />

    <div class="container">
        <h1>Answer the following questions</h1>
        <div id="test-place-holder" class="test">
        </div>
        <div>
            <button type="button" class="btn btn-success" id="submit-test">submit test</button>
        </div>
    </div>

    <script type="text/javascript">
        $( "button" ).click(function() {
            var submission = {};
            var submittedTest = document.getElementsByClassName('test')[0];
            submission.id = submittedTest.id;
            var submittedQuestions = [];
            var questions = submittedTest.getElementsByClassName('question');
            for(var i = 0; i < questions.length; i++){
                var question = {};
                question.id = questions[i].id;
                submittedAnswers = [];
                var answers = questions[i].getElementsByClassName('answer');
                for(var j = 0; j < answers.length; j++){
                    var answer = {};
                    answer.id = answers[j].id;
                    if(answers[j].getElementsByClassName('form-check-input')[0].checked){
                        answer.answer = true;
                    }else{
                        answer.answer = false;
                    }
                    submittedAnswers.push(answer);
                }
                question.submittedAnswerDtos = submittedAnswers;
                submittedQuestions.push(question);
            }
            submission.submittedQuestionDtos = submittedQuestions;
            console.log(submission);

             $.ajax({
                    method: "POST",
                    url: "http://localhost:8080${pageContext.request.contextPath}/api/assignment-get-test"
                    data: submission
                }).then(function(testDto) {
             });
        });
    </script>
</body>
</html>